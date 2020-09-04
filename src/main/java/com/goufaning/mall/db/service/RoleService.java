package com.goufaning.mall.db.service;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.goufaning.mall.db.mapper.RoleMapper;
import com.goufaning.mall.db.model.Role;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * $description
 *
 * @author goufn
 * @version V1.0
 * @date 2020-07-11 15:45
 */
@Service
public class RoleService extends ServiceImpl<RoleMapper, Role> {
    @Autowired
    private PermissionService permissionService;

    public boolean removePermission(int roleId, int permissionId) {
        Role role = baseMapper.selectById(roleId);
        if (role == null) {
            return false;
        }

//        List<Permission> permissions = permissionService.findChildren(permissionId);
//        for (Permission permission : permissions) {
//            removePermission(roleId, permission.getId());
//        }
//        String permissionIds = role.getPermissionIds();
//        List<Integer> permissionList = WebUtils.stringToList(permissionIds);
//        if (!permissionList.contains(permissionId)) {
//            return true;
//        }
//        permissionList.remove(permissionList.indexOf(permissionId));
//        permissionIds = WebUtils.listToString(permissionList);
//        role.setPermissionIds(permissionIds);
        return role.updateById();
    }


}


