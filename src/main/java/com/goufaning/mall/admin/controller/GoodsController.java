package com.goufaning.mall.admin.controller;

import com.goufaning.mall.admin.service.GoodsCateService;
import com.goufaning.mall.bean.vo.CategoriesVo;
import com.goufaning.mall.common.page.PageRequest;
import com.goufaning.mall.common.result.CommonResult;
import com.goufaning.mall.common.result.PageResult;
import com.goufaning.mall.common.utils.StringUtils;
import com.goufaning.mall.db.model.GoodsCate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Date;
import java.util.List;

/**
 * @author goufn
 * @version V1.0
 * @date 2020-07-15 16:49
 */
@RestController
@RequestMapping("/goods")
public class GoodsController {

    @Autowired
    private GoodsCateService goodsCateService;

    @PostMapping("/categories/page")
    public CommonResult getCategoriesPage(@RequestBody PageRequest pageRequest) {
        int type = 0;
        if (!StringUtils.isBlank(pageRequest.getParamValue("type"))) {
            type = Integer.parseInt(pageRequest.getParamValue("type"));
        }
        if (type == 0) {
            List<CategoriesVo> allCategories = goodsCateService.getAllCategories();
            return CommonResult.success(allCategories);
        }
        int pageNum = pageRequest.getPageNum();
        int pageSize = pageRequest.getPageSize();
        if (pageNum == 0 || pageSize == 0) {
            pageNum = 1;
            pageSize = 100;
        }
        PageResult page = goodsCateService.getPageByType(type, pageNum, pageSize);
        return CommonResult.success(page);
    }

    @GetMapping("/categories/list")
    public CommonResult getCategoriesList(@RequestParam int type) {
        List<CategoriesVo> categories = goodsCateService.getCategoriesByType(type);
        return CommonResult.success(categories);
    }


    @PostMapping("/categories/add")
    public CommonResult addCategories(@RequestBody CategoriesVo categoriesVo) {
        GoodsCate goodsCate = new GoodsCate();
        goodsCate.setCatName(categoriesVo.getName());
        goodsCate.setParentId(categoriesVo.getParentId());
        goodsCate.setCatSort(1);
        goodsCate.setDataFlag(1);
        goodsCate.setIsShow(1);
        goodsCate.setCreateTime(new Date());
        boolean success = goodsCate.insert();
        if (!success) {
            return CommonResult.error("插入失败！请稍后再试试");
        }
        return CommonResult.success("插入成功");
    }
}
