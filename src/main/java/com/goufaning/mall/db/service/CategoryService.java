package com.goufaning.mall.db.service;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
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
     *
     * @return
     */
    public List<Category> queryChannel() {
        QueryWrapper<Category> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq(Category.COL_LEVEL, "L1");
        return listNotDeleted(queryWrapper);
    }

    public List<Category> queryL1WithoutRecommend(int current, int size) {
        QueryWrapper<Category> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq(Category.COL_LEVEL, "L1");
        queryWrapper.ne(Category.COL_NAME, "推荐");
        return pageNotDeleted(current, size, queryWrapper).getRecords();
    }

    public List<Category> queryByPid(Integer pid) {
        QueryWrapper<Category> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq(Category.COL_PID, pid);
        return listNotDeleted(queryWrapper);
    }

    public List<Category> queryL1() {
        QueryWrapper<Category> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq(Category.COL_LEVEL, "L1");
        return listNotDeleted(queryWrapper);
    }

    public List<Category> queryL2ByIds(List<Integer> ids) {
        QueryWrapper<Category> queryWrapper = new QueryWrapper<>();
        queryWrapper.in(Category.COL_ID, ids);
        queryWrapper.eq(Category.COL_LEVEL, "L2");
        return listNotDeleted(queryWrapper);
    }

    private List<Category> listNotDeleted(QueryWrapper<Category> queryWrapper) {
        queryWrapper.eq(Category.COL_DELETED, false);
        return list(queryWrapper);
    }

    private IPage<Category> pageNotDeleted(int current, int size, QueryWrapper<Category> queryWrapper) {
        IPage<Category> page = new Page<>(current, size);
        queryWrapper.eq(Category.COL_DELETED, false);
        return page(page, queryWrapper);
    }
}

