package com.goufaning.mall.db.service;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.goufaning.mall.db.mapper.GoodsMapper;
import com.goufaning.mall.db.model.Goods;
import com.goufaning.mall.db.util.EnumSortType;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

/**
* $description
* @author goufn
* @date 2020/8/31 1:26 下午
* @version V1.0
*/
@Service
public class GoodsService extends ServiceImpl<GoodsMapper, Goods> {

    /**
     * 获取新品上市
     *
     * @param current
     * @param size
     * @return
     */
    public List<Goods> queryByNew(int current, int size) {
        QueryWrapper<Goods> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq(Goods.COL_IS_NEW, true);
        buildOnSaleWrapper(queryWrapper);
        buildSortWrapper(queryWrapper);
        IPage<Goods> page = new Page<>(current, size);
        return page(page, queryWrapper).getRecords();
    }
    /**
     * 获取热卖商品
     *
     * @param current
     * @param size
     * @return
     */
    public List<Goods> queryByHot(int current, int size) {
        QueryWrapper<Goods> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq(Goods.COL_IS_HOT, true);
        buildOnSaleWrapper(queryWrapper);
        buildSortWrapper(queryWrapper);
        IPage<Goods> page = new Page<>(current, size);
        return page(page, queryWrapper).getRecords();
    }

    /**
     * 获取分类下的商品
     * @param catList
     * @param current
     * @param size
     * @return
     */
    public List<Goods> queryByCategory(List<Integer> catList, int current, int size) {
        QueryWrapper<Goods> queryWrapper = new QueryWrapper<>();
        queryWrapper.in(Goods.COL_CATEGORY_ID, catList);
        buildOnSaleWrapper(queryWrapper);
        IPage<Goods> page = new Page<>(current, size);
        return page(page, queryWrapper).getRecords();
    }

    /**
     * 获取分类下的商品
     *
     * @param catId
     * @param current
     * @param size
     * @return
     */
    public List<Goods> queryByCategory(Integer catId, int current, int size) {
        QueryWrapper<Goods> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq(Goods.COL_CATEGORY_ID, catId);
        buildOnSaleWrapper(queryWrapper);
        buildSortWrapper(queryWrapper);
        IPage<Goods> page = new Page<>(current, size);
        return page(page, queryWrapper).getRecords();
    }

    /**
     * 获取所有在售物品总数
     *
     * @return
     */
    public Integer queryOnSale() {
        QueryWrapper<Goods> queryWrapper = buildOnSaleWrapper(new QueryWrapper<>());
        return count(queryWrapper);
    }

    private QueryWrapper<Goods> buildOnSaleWrapper(QueryWrapper<Goods> queryWrapper) {
        queryWrapper.eq(Goods.COL_IS_ON_SALE, true);
        queryWrapper.eq(Goods.COL_DELETED, false);
        return queryWrapper;
    }

    private QueryWrapper<Goods> buildSortWrapper(QueryWrapper<Goods> queryWrapper) {
        queryWrapper.orderByDesc(Goods.COL_ADD_TIME);
        return queryWrapper;
    }


    public IPage<Goods> querySelective(int catId, Integer brandId, String keywords, Boolean isHot, Boolean isNew, int current, int size, String sort, String order) {
        QueryWrapper<Goods> queryWrapper = new QueryWrapper<>();
        if (!StringUtils.isEmpty(catId) && catId != 0) {
            queryWrapper.eq(Goods.COL_CATEGORY_ID, catId);
        }
        buildSelectiveWrapper(brandId, keywords, isHot, isNew, queryWrapper);
        if (!StringUtils.isEmpty(sort) && !StringUtils.isEmpty(order)) {
            queryWrapper.orderBy(true, order.equalsIgnoreCase(EnumSortType.ASC.type()), sort);
        }
        IPage<Goods> page = new Page<>(current, size);
        return page(page, queryWrapper);
    }

    public List<Integer> getCatIds(Integer brandId, String keywords, Boolean isHot, Boolean isNew) {
        QueryWrapper<Goods> queryWrapper = new QueryWrapper<>();
        buildSelectiveWrapper(brandId, keywords, isHot, isNew, queryWrapper);
        List<Goods> goodsList = list(queryWrapper);
        List<Integer> cats = goodsList.stream().map(Goods::getCategoryId).collect(Collectors.toList());
        return cats;
    }

    /**
     * 获取某个商品信息，仅展示相关内容
     *
     * @param id
     * @return
     */
    public Goods findByIdVO(Integer id) {
        QueryWrapper<Goods> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq(Goods.COL_ID, id);
        buildOnSaleWrapper(queryWrapper);
        return getOne(queryWrapper);
    }


    public int update(Goods goods) {
        goods.setUpdateTime(LocalDateTime.now());
        return baseMapper.updateById(goods);
    }


    public void add(Goods goods) {
        goods.setAddTime(LocalDateTime.now());
        goods.setUpdateTime(LocalDateTime.now());
        goods.insert();
    }

    /**
     * 获取所有物品总数，包括在售的和下架的，但是不包括已删除的商品
     *
     * @return
     */
    public int countNotDeleted() {
        QueryWrapper<Goods> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq(Goods.COL_DELETED, false);
        return count(queryWrapper);
    }

    public boolean checkExistByName(String name) {
        QueryWrapper<Goods> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq(Goods.COL_NAME, name);
        buildOnSaleWrapper(queryWrapper);
        return count(queryWrapper) != 0;
    }

    public List<Goods> queryByIds(Integer[] ids) {
        QueryWrapper<Goods> queryWrapper = new QueryWrapper<>();
        queryWrapper.in(Goods.COL_ID, ids);
        buildOnSaleWrapper(queryWrapper);
        return list(queryWrapper);
    }

    private void buildSelectiveWrapper(Integer brandId, String keywords, Boolean isHot, Boolean isNew, QueryWrapper<Goods> queryWrapper) {
        if (!StringUtils.isEmpty(brandId)) {
            queryWrapper.eq(Goods.COL_BRAND_ID, brandId);
        }
        if (!StringUtils.isEmpty(isNew)) {
            queryWrapper.eq(Goods.COL_IS_NEW, isNew);
        }
        if (!StringUtils.isEmpty(isHot)) {
            queryWrapper.eq(Goods.COL_IS_HOT, isHot);
        }
        if (!StringUtils.isEmpty(keywords)) {
            queryWrapper.and(Wrapper -> Wrapper.eq(Goods.COL_KEYWORDS, keywords).or().eq(Goods.COL_NAME, keywords));
        }
        buildOnSaleWrapper(queryWrapper);
    }

}
