package com.goufaning.mall.admin.controller;

import com.goufaning.mall.admin.service.MenuService;
import com.goufaning.mall.common.result.CommonResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * 菜单
 *
 * @author goufn
 * @version V1.0
 * @date 2020-07-11 15:16
 */
@RestController
public class MenuController {
    @Autowired
    private MenuService menuService;

    @GetMapping("/menus")
    public CommonResult getLeftMenus() {
        return CommonResult.success(menuService.getMenuTree("admin"));

    }


}
