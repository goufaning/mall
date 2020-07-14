package com.goufaning.mall.db.service;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.goufaning.mall.common.utils.StringUtils;
import com.goufaning.mall.common.utils.WebUtils;
import com.goufaning.mall.db.mapper.PermissionMapper;
import com.goufaning.mall.db.mapper.RoleMapper;
import com.goufaning.mall.db.model.Manager;
import com.goufaning.mall.db.model.Permission;
import com.goufaning.mall.db.model.Role;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

/**
 * @author goufn
 * @version V1.0
 * @date 2020-07-11 15:30
 */
@Service
public class PermissionService extends ServiceImpl<PermissionMapper, Permission> {

    @Autowired
    private ManagerService managerService;
    @Autowired
    private RoleMapper roleMapper;


    /**
     * 通过管理员名字查找其权限
     * @param managerName
     * @return
     */
    public List<Permission> findListByManagerName(String managerName) {
        if (StringUtils.isEmpty(managerName)) {
            return Collections.emptyList();
        }
        Manager manager = managerService.findByName(managerName);
        if (manager == null) {
            return Collections.emptyList();
        }
        int roleId = manager.getRoleId();
        List<Permission> permissionList = findListByRoleId(roleId);
        return permissionList;
    }

    /**
     * 通过角色id查找
     * @param roleId
     * @return
     */
    public List<Permission> findListByRoleId(int roleId) {
        Role role = roleMapper.selectById(roleId);
        if (role == null) {
            return Collections.emptyList();
        }
        String permissionIds = role.getPermissionIds();
        List<Integer> pIdList = WebUtils.stringToList(permissionIds);
        if (pIdList.isEmpty()) {
            return Collections.emptyList();
        }
        List<Permission> permissionList = getBaseMapper().selectBatchIds(pIdList);
        permissionList.sort(Comparator.comparingInt(Permission::getOrderNum));
        return permissionList;
    }

    public List<Permission> findChildren(int permissionId) {
        Permission permission = baseMapper.selectById(permissionId);
        if (permission == null) {
            return Collections.emptyList();
        }
        List<Permission> result = new ArrayList<>();
        LambdaQueryWrapper<Permission> queryWrapper = new LambdaQueryWrapper<>();
        queryWrapper.eq(Permission::getParentId, permissionId);
        List<Permission> selectList = baseMapper.selectList(queryWrapper);
        result.addAll(selectList);
        for (Permission per : selectList) {
            result.addAll(findChildren(per.getId()));
        }
        return result;
    }
}
