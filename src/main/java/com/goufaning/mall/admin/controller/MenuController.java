package com.goufaning.mall.admin.controller;

import com.goufaning.mall.admin.service.MenuService;
import com.goufaning.mall.bean.vo.MenuVo;
import com.goufaning.mall.common.result.CommonResult;
import com.goufaning.mall.db.service.PermissionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * 菜单
 *
 * @author goufn
 * @version V1.0
 * @date 2020-07-11 15:16
 */
@RestController
@RequestMapping("/rights")
public class MenuController {
    @Autowired
    private MenuService menuService;

    @Autowired
    private PermissionService permissionService;

    @GetMapping("/menus")
    public CommonResult getLeftMenus() {
        return CommonResult.success(menuService.getMenuTree("admin"));
    }

    @GetMapping("/list")
    public CommonResult getPermissionList() {
        List<MenuVo> list = menuService.getPermissionList("");
        return CommonResult.success(list);
    }

    @GetMapping("/tree")
    public CommonResult getPermissionTree() {
        return CommonResult.success(menuService.getTree());
    }




}
