package com.goufaning.mall.admin.controller;

import com.goufaning.mall.admin.service.MenuService;
import com.goufaning.mall.bean.vo.MenuVo;
import com.goufaning.mall.bean.vo.RoleVo;
import com.goufaning.mall.bean.vo.UserRoleVo;
import com.goufaning.mall.common.result.CommonResult;
import com.goufaning.mall.db.model.Manager;
import com.goufaning.mall.db.model.Role;
import com.goufaning.mall.db.service.ManagerService;
import com.goufaning.mall.db.service.RoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

/**
 * @author goufn
 * @version V1.0
 * @date 2020-07-14 14:57
 */
@RestController
@RequestMapping("/role")
public class RoleController {

    @Autowired
    private ManagerService managerService;

    @Autowired
    private RoleService roleService;
    @Autowired
    private MenuService menuService;

    @GetMapping("/list")
    public CommonResult getAllRole() {
        List<RoleVo> result = new ArrayList<>();
        List<Role> list = roleService.list();
        for (Role role : list) {
            List<MenuVo> tree = menuService.getTreeByRoleId(role.getId());
            RoleVo roleVo = new RoleVo(role);
            roleVo.setChildren(tree);
            result.add(roleVo);
        }
        return CommonResult.success(result);
    }

    @PostMapping("/set")
    public CommonResult setRole(@RequestBody UserRoleVo userRoleVo) {
        Manager manager = managerService.getById(userRoleVo.getUserId());
        if (manager == null) {
            return CommonResult.error("没有该用户");
        }
        Role role = roleService.getById(userRoleVo.getRoleId());
        if (role == null) {
            return CommonResult.error("没有该角色");
        }
        manager.setRoleId(role.getId());
        boolean success = manager.updateById();
        if (!success) {
            return CommonResult.error("设置失败！稍后再试试～");
        }
        return CommonResult.success("设置成功");

    }

    @PostMapping("/removeRight")
    public CommonResult removePermission(@RequestParam int roleId, @RequestParam int permissionId) {
        Role role = roleService.getById(roleId);
        if (role == null) {
            return CommonResult.error("没有该角色");
        }
        boolean success = roleService.removePermission(roleId, permissionId);
        if (!success) {
            return CommonResult.error("删除失败");
        }
        List<MenuVo> tree = menuService.getTreeByRoleId(role.getId());
        return CommonResult.success("删除成功", tree);
    }

    @PostMapping("/setRights")
    public CommonResult setPermissions(@RequestParam int roleId, @RequestParam String rightIds) {
        Role role = roleService.getById(roleId);
        if (role == null) {
            return CommonResult.error("没有该角色");
        }
//        role.setPermissionIds(rightIds);
        boolean success = role.updateById();
        if (!success) {
            return CommonResult.error("设置失败");
        }
        List<MenuVo> tree = menuService.getTreeByRoleId(role.getId());
        return CommonResult.success("设置成功", tree);
    }
}
