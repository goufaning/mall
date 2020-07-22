package com.goufaning.mall.admin.controller;

import com.goufaning.mall.admin.service.AttributeService;
import com.goufaning.mall.common.result.CommonResult;
import com.goufaning.mall.db.model.Attribute;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * @author goufn
 * @version V1.0
 * @date 2020-07-21 16:28
 */
@RestController
@RequestMapping("/categories")
public class CategoriesController {

    @Autowired
    private AttributeService attributeService;

    /**
     *
     * @param id
     * @param sel 1:静态参数  2:动态属性
     * @return
     */
    @GetMapping("/{id}/attributes")
    public CommonResult getAttributes(@PathVariable int id, @RequestParam int sel) {
        List<Attribute> list = attributeService.getListByCatIdAndSel(id, sel);
        return CommonResult.success(list);
    }

}
