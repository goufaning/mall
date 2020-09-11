package com.goufaning.mall.db.service;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.goufaning.mall.common.utils.StringUtils;
import com.goufaning.mall.common.utils.WebUtils;
import com.goufaning.mall.db.mapper.CouponMapper;
import com.goufaning.mall.db.model.Coupon;
import com.goufaning.mall.db.model.CouponUser;
import com.goufaning.mall.db.util.CouponConstant;
import com.goufaning.mall.db.util.EnumSortType;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Random;
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

    public Coupon findByCode(String code) {
        QueryWrapper<Coupon> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq(Coupon.COL_CODE, code);
        queryWrapper.eq(Coupon.COL_TYPE, CouponConstant.TYPE_CODE);
        queryWrapper.eq(Coupon.COL_STATUS, CouponConstant.STATUS_NORMAL);
        queryWrapper.eq(Coupon.COL_DELETED, false);
        List<Coupon> couponList = list(queryWrapper);
        if(couponList.size() > 1){
            throw new RuntimeException("");
        } else if(couponList.size() == 0) {
            return null;
        } else {
            return couponList.get(0);
        }
    }

    /**
     * 查询新用户注册优惠券
     *
     * @return
     */
    public List<Coupon> queryRegister() {
        QueryWrapper<Coupon> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq(Coupon.COL_TYPE, CouponConstant.TYPE_REGISTER);
        queryWrapper.eq(Coupon.COL_STATUS, CouponConstant.STATUS_NORMAL);
        queryWrapper.eq(Coupon.COL_DELETED, false);
        return list(queryWrapper);
    }

    public IPage<Coupon> querySelective(String name, Short type, Short status, Integer current, Integer size, String sort, String order) {
        QueryWrapper<Coupon> queryWrapper = new QueryWrapper<>();
        if (!StringUtils.isEmpty(name)) {
            queryWrapper.like(Coupon.COL_NAME, name);
        }
        if (type != null) {
            queryWrapper.eq(Coupon.COL_TYPE, type);
        }
        if (status != null) {
            queryWrapper.eq(Coupon.COL_STATUS, status);
        }
        queryWrapper.eq(Coupon.COL_DELETED, false);
        if (!StringUtils.isEmpty(sort) && !StringUtils.isEmpty(order)) {
            queryWrapper.orderBy(true, order.equalsIgnoreCase(EnumSortType.ASC.type()), sort);
        }
        IPage<Coupon> page = new Page<>(current, size);
        return page(page, queryWrapper);
    }

    public void add(Coupon coupon) {
        coupon.setAddTime(LocalDateTime.now());
        coupon.setUpdateTime(LocalDateTime.now());
        baseMapper.insert(coupon);
    }

    public int update(Coupon coupon) {
        coupon.setUpdateTime(LocalDateTime.now());
        return baseMapper.updateById(coupon);
    }


    private String getRandomNum(Integer num) {
        String base = "ABCDEFGHIJKLMNOPQRSTUVWXYZ";
        base += "0123456789";

        Random random = new Random();
        StringBuffer sb = new StringBuffer();
        for (int i = 0; i < num; i++) {
            int number = random.nextInt(base.length());
            sb.append(base.charAt(number));
        }
        return sb.toString();
    }

    /**
     * 生成优惠码
     *
     * @return 可使用优惠码
     */
    public String generateCode() {
        String code = getRandomNum(8);
        while(findByCode(code) != null){
            code = getRandomNum(8);
        }
        return code;
    }

    /**
     * 查询过期的优惠券:
     * 注意：如果timeType=0, 即基于领取时间有效期的优惠券，则优惠券不会过期
     *
     * @return
     */
    public List<Coupon> queryExpired() {
        QueryWrapper<Coupon> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq(Coupon.COL_STATUS, CouponConstant.STATUS_NORMAL);
        queryWrapper.eq(Coupon.COL_TIME_TYPE, CouponConstant.TIME_TYPE_TIME);
        queryWrapper.le(Coupon.COL_END_TIME, LocalDateTime.now());
        queryWrapper.eq(Coupon.COL_DELETED, false);
        return list(queryWrapper);
    }


}
