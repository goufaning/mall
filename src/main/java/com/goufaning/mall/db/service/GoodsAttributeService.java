package com.goufaning.mall.db.service;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.goufaning.mall.db.mapper.GoodsAttributeMapper;
import com.goufaning.mall.db.model.GoodsAttribute;
import org.springframework.stereotype.Service;

import java.util.List;

/**
* $description
* @author goufn
* @date 2020/8/31 1:26 下午
* @version V1.0
*/
@Service
public class GoodsAttributeService extends ServiceImpl<GoodsAttributeMapper, GoodsAttribute> {

    public List<GoodsAttribute> queryByGid(Integer goodsId) {
        QueryWrapper<GoodsAttribute> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq(GoodsAttribute.COL_GOODS_ID, goodsId);
        queryWrapper.eq(GoodsAttribute.COL_DELETED, false);
        return list(queryWrapper);
    }

}
