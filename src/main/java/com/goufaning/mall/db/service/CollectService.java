package com.goufaning.mall.db.service;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.goufaning.mall.db.mapper.CollectMapper;
import com.goufaning.mall.db.model.Collect;
import org.springframework.stereotype.Service;
/**
* $description
* @author goufn
* @date 2020/8/31 1:26 下午
* @version V1.0
*/
@Service
public class CollectService extends ServiceImpl<CollectMapper, Collect> {

    public int count(int userId, int goodsId) {
        QueryWrapper<Collect> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq(Collect.COL_USER_ID, userId);
        queryWrapper.eq(Collect.COL_VALUE_ID, goodsId);
        queryWrapper.eq(Collect.COL_DELETED, false);
        return count(queryWrapper);
    }

}
