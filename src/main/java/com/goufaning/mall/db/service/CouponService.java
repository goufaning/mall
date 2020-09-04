package com.goufaning.mall.db.service;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.goufaning.mall.common.utils.WebUtils;
import com.goufaning.mall.db.mapper.CouponMapper;
import com.goufaning.mall.db.model.Coupon;
import com.goufaning.mall.db.model.CouponUser;
import com.goufaning.mall.db.util.CouponConstant;
import com.goufaning.mall.db.util.EnumSortType;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

/**
 * @author goufn
 * @version V1.0
 * @date 2020/8/31 11:33 上午
 */
@Service
public class CouponService extends ServiceImpl<CouponMapper, Coupon> {
    @Autowired
    private CouponUserService couponUserService;

    public List<Coupon> queryList(int offset, int limit) {
        return queryList(offset, limit, "add_time", "desc");
    }

    public List<Coupon> queryList(int offset, int limit, String sort, String order) {
        return queryList(new QueryWrapper<Coupon>(), offset, limit, sort, order);
    }

    public List<Coupon> queryList(QueryWrapper<Coupon> queryWrapper, int offset, int limit, String sort, String order) {
        IPage<Coupon> page = new Page(offset, limit);
        queryWrapper.eq(Coupon.COL_TYPE, CouponConstant.TYPE_COMMON);
        queryWrapper.eq(Coupon.COL_STATUS, CouponConstant.STATUS_NORMAL);
        queryWrapper.eq(Coupon.COL_DELETED, false);
        if (order.equalsIgnoreCase(EnumSortType.DESC.type())) {
            queryWrapper.orderByDesc(sort);
        } else {
            queryWrapper.orderByAsc(sort);
        }
        return baseMapper.selectPage(page, queryWrapper).getRecords();
    }

    public List<Coupon> queryAvailableList(Integer userId, int offset, int limit) {
        assert userId != null;
        // 过滤掉登录账号已经领取过的coupon
        QueryWrapper<Coupon> couponQueryWrapper = new QueryWrapper<>();
        QueryWrapper<CouponUser> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq(CouponUser.COL_USER_ID, userId);
        List<CouponUser> couponUserList = couponUserService.list(queryWrapper);
        if (!WebUtils.isEmptyList(couponUserList)) {
            couponQueryWrapper.notIn(Coupon.COL_ID, couponUserList.stream().map(CouponUser::getId).collect(Collectors.toList()));
        }
        return queryList(couponQueryWrapper, offset, limit, "add_time", "desc");
    }


}
