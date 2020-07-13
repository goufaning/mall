package com.goufaning.mall.admin.controller;

import com.goufaning.mall.common.page.PageRequest;
import com.goufaning.mall.common.result.CommonResult;
import com.goufaning.mall.db.service.ManagerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author goufn
 * @version V1.0
 * @date 2020-07-13 17:50
 */
@RestController
public class ManagerController {

    @Autowired
    private ManagerService managerService;

    @PostMapping("/managers")
    public CommonResult getPageByName(@RequestBody PageRequest pageRequest) {
        String name = pageRequest.getColumnFilterValue("name");
        return CommonResult.success(managerService.getPageByName(name, pageRequest.getPageNum(), pageRequest.getPageSize()));

    }
}
