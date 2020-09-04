package com.goufaning.mall.db.service;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.goufaning.mall.common.utils.StringUtils;
import com.goufaning.mall.common.utils.WebUtils;
import com.goufaning.mall.db.mapper.AdminPermissionMapper;
import com.goufaning.mall.db.mapper.AdminRoleMapper;
import com.goufaning.mall.db.model.AdminPermission;
import com.goufaning.mall.db.model.AdminRole;
import com.goufaning.mall.db.model.Manager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
/**
* $description
* @author goufn
* @date 2020/8/31 1:35 下午
* @version V1.0
*/
@Service
public class AdminPermissionService extends ServiceImpl<AdminPermissionMapper, AdminPermission> {

    @Autowired
    private ManagerService managerService;
    @Autowired
    private AdminRoleMapper adminRoleMapper;


    /**
     * 通过管理员名字查找其权限
     *
     * @param managerName
     * @return
     */
    public List<AdminPermission> findListByManagerName(String managerName) {
        if (StringUtils.isEmpty(managerName)) {
            return Collections.emptyList();
        }
        Manager manager = managerService.findByName(managerName);
        if (manager == null) {
            return Collections.emptyList();
        }
        int roleId = manager.getRoleId();
        List<AdminPermission> permissionList = findListByRoleId(roleId);
        return permissionList;
    }

    /**
     * 通过角色id查找
     *
     * @param roleId
     * @return
     */
    public List<AdminPermission> findListByRoleId(int roleId) {
        AdminRole role = adminRoleMapper.selectById(roleId);
        if (role == null) {
            return Collections.emptyList();
        }
        String permissionIds = role.getPermissionIds();
        List<Integer> pIdList = WebUtils.stringToList(permissionIds);
        if (pIdList.isEmpty()) {
            return Collections.emptyList();
        }
        List<AdminPermission> permissionList = getBaseMapper().selectBatchIds(pIdList);
        permissionList.sort(Comparator.comparingInt(AdminPermission::getOrderNum));
        return null;
    }

    public List<AdminPermission> findChildren(int permissionId) {
        AdminPermission permission = baseMapper.selectById(permissionId);
        if (permission == null) {
            return Collections.emptyList();
        }
        List<AdminPermission> result = new ArrayList<>();
        LambdaQueryWrapper<AdminPermission> queryWrapper = new LambdaQueryWrapper<>();
        queryWrapper.eq(AdminPermission::getParentId, permissionId);
        List<AdminPermission> selectList = baseMapper.selectList(queryWrapper);
        result.addAll(selectList);
        for (AdminPermission per : selectList) {
            result.addAll(findChildren(per.getId()));
        }
        return result;
    }

}
