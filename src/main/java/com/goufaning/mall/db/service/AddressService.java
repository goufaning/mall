package com.goufaning.mall.db.service;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.goufaning.mall.db.mapper.AddressMapper;
import com.goufaning.mall.db.model.Address;
import com.goufaning.mall.db.util.EnumSortType;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.time.LocalDateTime;
import java.util.List;

/**
 * 用户地址
 *
 * @author goufn
 * @version V1.0
 * @date 2020/9/9 1:54 下午
 */
@Service
public class AddressService extends ServiceImpl<AddressMapper, Address> {

    public List<Address> queryByUid(Integer uid) {
        LambdaQueryWrapper<Address> queryWrapper = new LambdaQueryWrapper<>();
        queryWrapper.eq(Address::getUserId, uid);
        queryWrapper.eq(Address::getDeleted, false);
        return list(queryWrapper);
    }

    public Address query(Integer userId, Integer id) {
        LambdaQueryWrapper<Address> queryWrapper = new LambdaQueryWrapper<>();
        queryWrapper.eq(Address::getId, id);
        queryWrapper.eq(Address::getUserId, userId);
        queryWrapper.eq(Address::getDeleted, false);
        return getOne(queryWrapper);
    }

    public int add(Address address) {
        address.setAddTime(LocalDateTime.now());
        address.setUpdateTime(LocalDateTime.now());
        return baseMapper.insert(address);
    }

    public int update(Address address) {
        address.setUpdateTime(LocalDateTime.now());
        return baseMapper.updateById(address);
    }


    public Address findDefault(Integer userId) {
        LambdaQueryWrapper<Address> queryWrapper = new LambdaQueryWrapper<>();
        queryWrapper.eq(Address::getUserId, userId);
        queryWrapper.eq(Address::getIsDefault, true);
        queryWrapper.eq(Address::getDeleted, false);
        return getOne(queryWrapper);
    }

    public void resetDefault(Integer userId) {
        Address address = new Address();
        address.setIsDefault(false);
        address.setUpdateTime(LocalDateTime.now());
        LambdaQueryWrapper<Address> queryWrapper = new LambdaQueryWrapper<>();
        queryWrapper.eq(Address::getUserId, userId);
        queryWrapper.eq(Address::getDeleted, false);
        update(address, queryWrapper);
    }

    public IPage<Address> querySelective(Integer userId, String name, Integer current, Integer size, String sort, String order) {
        QueryWrapper<Address> queryWrapper = new QueryWrapper<>();
        if (userId != null) {
            queryWrapper.eq("", userId);
        }
        if (!StringUtils.isEmpty(name)) {
            queryWrapper.like("name", name);
        }
        queryWrapper.eq("deleted", false);

        if (!StringUtils.isEmpty(sort) && !StringUtils.isEmpty(order)) {
            queryWrapper.orderBy(true, order.equalsIgnoreCase(EnumSortType.ASC.type()), sort);
        }
        IPage<Address> page = new Page<>(current, size);
        return page(page, queryWrapper);
    }
}
