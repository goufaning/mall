package com.goufaning.mall.admin.controller;

import com.goufaning.mall.admin.service.GoodsCateService;
import com.goufaning.mall.bean.vo.CategoriesVo;
import com.goufaning.mall.common.page.PageRequest;
import com.goufaning.mall.common.result.CommonResult;
import com.goufaning.mall.common.result.PageResult;
import com.goufaning.mall.common.utils.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

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
            return getCategoriesList(type);
        }
        PageResult page = goodsCateService.getPageByType(type, pageNum, pageSize);
        return CommonResult.success(page);
    }

    @PostMapping("/categories/list")
    public CommonResult getCategoriesList(int type) {
        List<CategoriesVo> categories = goodsCateService.getCategoriesByType(type);
        return CommonResult.success(categories);
    }
}
