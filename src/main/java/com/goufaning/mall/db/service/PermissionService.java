package com.goufaning.mall.db.service;

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

import java.util.Collections;
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
        Role role = roleMapper.selectById(roleId);
        if (role == null) {
            return Collections.emptyList();
        }
        String permissionIds = role.getPermissionIds();
        List<Integer> pIdList = WebUtils.stringToList(permissionIds);
        List<Permission> permissionList = getBaseMapper().selectBatchIds(pIdList);
        return permissionList;
    }
}
