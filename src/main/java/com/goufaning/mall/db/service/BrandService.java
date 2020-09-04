package com.goufaning.mall.db.service;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.goufaning.mall.db.mapper.BrandMapper;
import com.goufaning.mall.db.model.Brand;
import com.goufaning.mall.db.util.EnumSortType;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.util.List;

/**
* 商标
* @author goufn
* @date 2020/8/31 1:26 下午
* @version V0
*/
@Service
public class BrandService extends ServiceImpl<BrandMapper, Brand> {

    public List<Brand> query(Integer current, Integer size, String sort, String order) {
        QueryWrapper<Brand> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq(Brand.COL_DELETED, false);
        if (!StringUtils.isEmpty(sort) && !StringUtils.isEmpty(order)) {
            if (order.equalsIgnoreCase(EnumSortType.DESC.type())) {
                queryWrapper.orderByDesc(sort);
            }
        }
        IPage<Brand> brandIPage = new Page<>(current, size);
        return baseMapper.selectPage(brandIPage, queryWrapper).getRecords();
    }

    public List<Brand> query(Integer current, Integer size) {
        return query(current, size, null, null);
    }

}
