package com.goufaning.mall.db.service;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.goufaning.mall.db.mapper.CouponUserMapper;
import com.goufaning.mall.db.model.CouponUser;
import com.goufaning.mall.db.util.CouponUserConstant;
import com.goufaning.mall.db.util.EnumSortType;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.time.LocalDateTime;
import java.util.List;

/**
* $description
* @author goufn
* @date 2020/8/31 1:26 下午
* @version V1.0
*/
@Service
public class CouponUserService extends ServiceImpl<CouponUserMapper, CouponUser> {

    public int countCoupon(Integer couponId) {
        QueryWrapper<CouponUser> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq(CouponUser.COL_COUPON_ID, couponId);
        queryWrapper.eq(CouponUser.COL_DELETED, false);
        return count(queryWrapper);
    }

    public int countUserAndCoupon(Integer userId, Integer couponId) {
        QueryWrapper<CouponUser> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq(CouponUser.COL_USER_ID, userId);
        queryWrapper.eq(CouponUser.COL_COUPON_ID, couponId);
        queryWrapper.eq(CouponUser.COL_DELETED, false);
        return count(queryWrapper);
    }

    public void add(CouponUser couponUser) {
        couponUser.setAddTime(LocalDateTime.now());
        couponUser.setUpdateTime(LocalDateTime.now());
        couponUser.insert();
    }

    public List<CouponUser> queryList(Integer userId, Integer couponId, Short status, Integer current, Integer size, String sort, String order) {
        QueryWrapper<CouponUser> queryWrapper = new QueryWrapper<>();
        if (userId != null) {
            queryWrapper.eq(CouponUser.COL_USER_ID, userId);
        }
        if(couponId != null){
            queryWrapper.eq(CouponUser.COL_COUPON_ID, couponId);
        }
        if (status != null) {
            queryWrapper.eq(CouponUser.COL_STATUS, status);
        }
        queryWrapper.eq(CouponUser.COL_DELETED, false);

        if (!StringUtils.isEmpty(sort) && !StringUtils.isEmpty(order)) {
            queryWrapper.orderBy(true, order.equalsIgnoreCase(EnumSortType.ASC.type()), sort);
        }

        if (!StringUtils.isEmpty(current) && !StringUtils.isEmpty(size)) {
            IPage<CouponUser> page = new Page<>(current, size);
            return page(page, queryWrapper).getRecords();
        }
        return list(queryWrapper);
    }

    public List<CouponUser> queryAll(Integer userId, Integer couponId) {
        return queryList(userId, couponId, CouponUserConstant.STATUS_USABLE, null, null, "add_time", "desc");
    }

    public List<CouponUser> queryAll(Integer userId) {
        return queryList(userId, null, CouponUserConstant.STATUS_USABLE, null, null, "add_time", "desc");
    }

    public CouponUser queryOne(Integer userId, Integer couponId) {
        List<CouponUser> couponUserList = queryList(userId, couponId, CouponUserConstant.STATUS_USABLE, 1, 1, "add_time", "desc");
        if(couponUserList.size() == 0){
            return null;
        }
        return couponUserList.get(0);
    }


    public int update(CouponUser couponUser) {
        couponUser.setUpdateTime(LocalDateTime.now());
        return baseMapper.updateById(couponUser);
    }

    public List<CouponUser> queryExpired() {
        QueryWrapper<CouponUser> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq(CouponUser.COL_STATUS, CouponUserConstant.STATUS_USABLE);
        // 小于等于  过期
        queryWrapper.le(CouponUser.COL_END_TIME, LocalDateTime.now());
        queryWrapper.eq(CouponUser.COL_DELETED, false);
        return list(queryWrapper);
    }

    public List<CouponUser> findByOid(Integer orderId) {
        QueryWrapper<CouponUser> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq(CouponUser.COL_ORDER_ID, orderId);
        queryWrapper.eq(CouponUser.COL_DELETED, false);
        return list(queryWrapper);
    }

}
