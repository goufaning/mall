package com.goufaning.mall.admin.service;

import com.goufaning.mall.bean.vo.CategoriesVo;
import com.goufaning.mall.common.result.PageResult;

import java.util.List;

/**
 * @author goufn
 * @version V1.0
 * @date 2020-07-15 15:18
 */
public interface GoodsCateService {

    List<CategoriesVo> getAllCategories();

    List<CategoriesVo> getCategoriesByType(int type);

    PageResult getPageByType(int type, int pageNum, int pageSize);
}
