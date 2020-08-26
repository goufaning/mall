package com.goufaning.mall.db.service;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.goufaning.mall.db.mapper.CategoryMapper;
import com.goufaning.mall.db.model.Category;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * 分类
 *
 * @author goufn
 * @version V1.0
 * @date 2020-08-26 11:17
 */
@Service
public class CategoryService extends ServiceImpl<CategoryMapper, Category> {

    /**
     * 查询一级分类
     * @return
     */
    public List<Category> queryChannel() {
        LambdaQueryWrapper<Category> queryWrapper = new LambdaQueryWrapper<>();
        queryWrapper.eq(Category::getLevel, "L1").eq(Category::getDeleted, false);
        return baseMapper.selectList(queryWrapper);
    }
}
