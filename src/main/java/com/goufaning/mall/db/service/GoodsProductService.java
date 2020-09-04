package com.goufaning.mall.db.service;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.goufaning.mall.db.mapper.GoodsProductMapper;
import com.goufaning.mall.db.model.GoodsProduct;
import org.springframework.stereotype.Service;

import java.util.List;

/**
* $description
* @author goufn
* @date 2020/8/31 1:26 下午
* @version V1.0
*/
@Service
public class GoodsProductService extends ServiceImpl<GoodsProductMapper, GoodsProduct> {

    public List<GoodsProduct> queryByGid(int goodsId) {
        QueryWrapper<GoodsProduct> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq(GoodsProduct.COL_GOODS_ID, goodsId);
        queryWrapper.eq(GoodsProduct.COL_DELETED, false);
        return list(queryWrapper);
    }

}
