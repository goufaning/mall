package com.goufaning.mall.admin.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.goufaning.mall.admin.service.GoodsCateService;
import com.goufaning.mall.bean.vo.CategoriesVo;
import com.goufaning.mall.common.result.PageResult;
import com.goufaning.mall.db.mapper.GoodsCateMapper;
import com.goufaning.mall.db.model.GoodsCate;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

/**
 * @author goufn
 * @version V1.0
 * @date 2020-07-15 15:18
 */
@Service
public class GoodsCateServiceImpl extends ServiceImpl<GoodsCateMapper, GoodsCate> implements GoodsCateService {


    @Override
    public List<CategoriesVo> getAllCategories() {
        return getCategoriesByType(3);
    }

    @Override
    public List<CategoriesVo> getCategoriesByType(int type) {
        List<GoodsCate> goodsCates = list();
        List<CategoriesVo> result = new ArrayList<>();
        for (GoodsCate goodsCate : goodsCates) {
            if (goodsCate.getParentId() == 0) {
                CategoriesVo categoriesVo = new CategoriesVo(goodsCate);
                result.add(categoriesVo);
            }
        }
        setChildren(result, goodsCates, 1, type);
        return result;
    }

    @Override
    public PageResult getPageByType(int type, int pageNum, int pageSize) {
        List<CategoriesVo> result = new ArrayList<>();
        IPage<GoodsCate> page = new Page<>(pageNum, pageSize);
        LambdaQueryWrapper<GoodsCate> queryWrapper = new LambdaQueryWrapper();
        queryWrapper.eq(GoodsCate::getParentId, 0);
        IPage<GoodsCate> goodsCatePage = baseMapper.selectPage(page, queryWrapper);
        List<GoodsCate> goodsCates = goodsCatePage.getRecords();
        for (GoodsCate goodsCate : goodsCates) {
            CategoriesVo categoriesVo = new CategoriesVo(goodsCate);
            result.add(categoriesVo);
        }
        setChildren(result, list(), 1, type);
        IPage<CategoriesVo> pageResult = new Page<>(goodsCatePage.getCurrent(), goodsCatePage.getSize(), goodsCatePage.getTotal());
        pageResult.setRecords(result);
        return new PageResult(pageResult);
    }

    /**
     *
     * @param parents
     * @param cates
     * @param level parent level
     */
    private void setChildren(List<CategoriesVo> parents, List<GoodsCate> cates, int level, int maxLevel) {
        // 孩子等级超过level,直接跳过
        if (level + 1 > maxLevel) {
            return;
        }
        for (CategoriesVo parent : parents) {
            parent.setLevel(level);
            List<CategoriesVo> children = new ArrayList<>();
            for (GoodsCate cate : cates) {
                if (cate.getParentId() == parent.getId()) {
                    CategoriesVo vo = new CategoriesVo(cate);
                    children.add(vo);
                }
            }
            parent.setChildren(children);
            setChildren(children, cates, level + 1, maxLevel);
        }
    }
}
