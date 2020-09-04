package com.goufaning.mall.db.service;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.goufaning.mall.db.mapper.GoodsMapper;
import com.goufaning.mall.db.model.Goods;
import org.springframework.stereotype.Service;

import java.util.List;

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
     * @param offset
     * @param limit
     * @return
     */
    public List<Goods> queryByNew(int offset, int limit) {
        QueryWrapper<Goods> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq(Goods.COL_IS_NEW, true);
        return getOnSaleGoods(offset, limit, queryWrapper);
    }
    /**
     * 获取热卖商品
     *
     * @param offset
     * @param limit
     * @return
     */
    public List<Goods> queryByHot(int offset, int limit) {
        QueryWrapper<Goods> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq(Goods.COL_IS_HOT, true);
        return getOnSaleGoods(offset, limit, queryWrapper);
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
        queryWrapper.orderByDesc(Goods.COL_ADD_TIME);
        return getOnSaleGoods(current, size, queryWrapper);
    }

    /**
     * 获取在买商品
     * @param current
     * @param size
     * @param queryWrapper
     * @return
     */
    private List<Goods> getOnSaleGoods(int current, int size, QueryWrapper<Goods> queryWrapper) {
        queryWrapper.eq(Goods.COL_IS_ON_SALE, true);
        queryWrapper.eq(Goods.COL_DELETED, false);
        queryWrapper.orderByDesc(Goods.COL_ADD_TIME);
        IPage<Goods> page = new Page<>(current, size);
        return page(page, queryWrapper).getRecords();
    }

}
