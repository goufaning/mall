package com.goufaning.mall.admin.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.goufaning.mall.admin.service.MenuService;
import com.goufaning.mall.bean.vo.MenuVo;
import com.goufaning.mall.common.utils.StringUtils;
import com.goufaning.mall.db.model.AdminPermission;
import com.goufaning.mall.db.service.AdminPermissionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

/**
 * 菜单service
 *
 * @author goufn
 * @version V1.0
 * @date 2020-07-11 15:19
 */
@Service
public class MenuServiceImpl implements MenuService {

    @Autowired
    private AdminPermissionService adminPermissionService;


    @Override
    public List<MenuVo> getMenuTree(String managerName) {
        List<MenuVo> parentList = new ArrayList<>();
        List<AdminPermission> permissions = adminPermissionService.findListByManagerName(managerName);
        for (AdminPermission permission : permissions) {
            if (permission.getLevel() == 1) {
                MenuVo menuVo = new MenuVo(permission);
                parentList.add(menuVo);
            }
        }
        findChildren(parentList, permissions);
        return parentList;
    }

    @Override
    public List<MenuVo> getTree() {
        List<MenuVo> parentList = new ArrayList<>();
        List<AdminPermission> permissions = adminPermissionService.list();
        for (AdminPermission permission : permissions) {
            if (permission.getLevel() == 1) {
                MenuVo menuVo = new MenuVo(permission);
                parentList.add(menuVo);
            }
        }
        findChildren(parentList, permissions);
        return parentList;
    }

    @Override
    public List<MenuVo> getTreeByRoleId(int roleId) {
        List<MenuVo> parentList = new ArrayList<>();
        List<AdminPermission> permissions = adminPermissionService.findListByRoleId(roleId);
        for (AdminPermission permission : permissions) {
            if (permission.getLevel() == 1) {
                MenuVo menuVo = new MenuVo(permission);
                parentList.add(menuVo);
            }
        }
        findChildren(parentList, permissions);
        return parentList;
    }

    @Override
    public List<MenuVo> getPermissionList(String name) {
        List<MenuVo> parentList = new ArrayList<>();
        LambdaQueryWrapper<AdminPermission> wrapper = new LambdaQueryWrapper<>();
        if (!StringUtils.isBlank(name)) {
            wrapper.eq(AdminPermission::getName, name);
        }
        List<AdminPermission> permissions = adminPermissionService.list(wrapper);
        for (AdminPermission permission : permissions) {
            MenuVo menuVo = new MenuVo(permission);
            parentList.add(menuVo);
        }
        return parentList;
    }

    private void findChildren(List<MenuVo> parentList, List<AdminPermission> childList) {
        for (MenuVo parent : parentList) {
            List<MenuVo> children = new ArrayList<>();
            for (AdminPermission permission : childList) {
                if (permission.getParentId() == parent.getId()) {
                    MenuVo menuVo = new MenuVo(permission);
                    children.add(menuVo);
                }
            }
            parent.setChildren(children);
            findChildren(children, childList);
        }
    }
}
