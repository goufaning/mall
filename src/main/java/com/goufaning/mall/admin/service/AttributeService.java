package com.goufaning.mall.admin.service;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.goufaning.mall.db.mapper.AttributeMapper;
import com.goufaning.mall.db.model.Attribute;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author goufn
 * @version V1.0
 * @date 2020-07-21 16:27
 */
@Service
public class AttributeService extends ServiceImpl<AttributeMapper, Attribute> {

    public List<Attribute> getListByCatIdAndSel(int catId, int sel) {
        LambdaQueryWrapper<Attribute> queryWrapper = new LambdaQueryWrapper<>();
        queryWrapper.eq(Attribute::getCatId, catId).eq(Attribute::getSel, sel);
        List<Attribute> attributes = baseMapper.selectList(queryWrapper);
        return attributes;
    }
}
