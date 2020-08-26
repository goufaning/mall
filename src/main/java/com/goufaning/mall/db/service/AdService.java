package com.goufaning.mall.db.service;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.goufaning.mall.db.mapper.MallAdMapper;
import com.goufaning.mall.db.model.MallAd;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * 广告
 *
 * @author goufn
 * @version V1.0
 * @date 2020-08-13 16:28
 */
@Service
public class AdService extends ServiceImpl<MallAdMapper, MallAd> {

    public List<MallAd> queryIndex() {
        LambdaQueryWrapper<MallAd> queryWrapper = new LambdaQueryWrapper<>();
        queryWrapper.eq(MallAd::getPosition, 1).eq(MallAd::getDeleted, 0).eq(MallAd::getEnabled, 1);
        return baseMapper.selectList(queryWrapper);
    }
}
