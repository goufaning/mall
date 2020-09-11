package com.goufaning.mall.db.service;

import com.goufaning.mall.common.utils.WebUtils;
import com.goufaning.mall.db.model.Cart;
import com.goufaning.mall.db.model.Coupon;
import com.goufaning.mall.db.model.CouponUser;
import com.goufaning.mall.db.util.CouponConstant;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.*;

/**
 * 优惠卷检测
 *
 * @author goufn
 * @version V1.0
 * @date 2020/9/8 2:04 下午
 */
@Service
public class CouponVerifyService {

    @Autowired
    private CouponUserService couponUserService;
    @Autowired
    private CouponService couponService;
    @Autowired
    private GoodsService goodsService;

    /**
     * 检测优惠券是否适合
     *
     * @param userId
     * @param couponId
     * @param checkedGoodsPrice
     * @return
     */
    public Coupon checkCoupon(Integer userId, Integer couponId, Integer userCouponId, BigDecimal checkedGoodsPrice, List<Cart> cartList) {
        Coupon coupon = couponService.getById(couponId);
        if (coupon == null || coupon.getDeleted()) {
            return null;
        }
        CouponUser couponUser = couponUserService.getById(userCouponId);
        if (couponUser == null) {
            couponUser = couponUserService.queryOne(userId, couponId);
        } else if (!couponId.equals(couponUser.getCouponId())) {
            return null;
        }

        if (couponUser == null) {
            return null;
        }

        // 检查是否超期
        Short timeType = coupon.getTimeType();
        Short days = coupon.getDays();
        LocalDateTime now = LocalDateTime.now();
        if (timeType.equals(CouponConstant.TIME_TYPE_TIME)) {
            if (now.isBefore(coupon.getStartTime()) || now.isAfter(coupon.getEndTime())) {
                return null;
            }
        }
        else if(timeType.equals(CouponConstant.TIME_TYPE_DAYS)) {
            LocalDateTime expired = couponUser.getAddTime().plusDays(days);
            if (now.isAfter(expired)) {
                return null;
            }
        }
        else {
            return null;
        }

        // 检测商品是否符合
        Map<Integer, List<Cart>> cartMap = new HashMap<>();
        //可使用优惠券的商品或分类
        List<Integer> goodsValueList = WebUtils.stringToList(coupon.getGoodsValue());
        Short goodType = coupon.getGoodsType();

        if (goodType.equals(CouponConstant.GOODS_TYPE_CATEGORY) ||
                goodType.equals((CouponConstant.GOODS_TYPE_ARRAY))) {
            for (Cart cart : cartList) {
                Integer key = goodType.equals(CouponConstant.GOODS_TYPE_ARRAY) ? cart.getGoodsId() :
                        goodsService.getById(cart.getGoodsId()).getCategoryId();
                List<Cart> carts = cartMap.get(key);
                if (carts == null) {
                    carts = new LinkedList<>();
                }
                carts.add(cart);
                cartMap.put(key, carts);
            }
            //购物车中可以使用优惠券的商品或分类
            goodsValueList.retainAll(cartMap.keySet());
            //可使用优惠券的商品的总价格
            BigDecimal total = new BigDecimal(0);

            for (Integer goodsId : goodsValueList) {
                List<Cart> carts = cartMap.get(goodsId);
                for (Cart cart : carts) {
                    total = total.add(cart.getPrice().multiply(new BigDecimal(cart.getNumber())));
                }
            }
            //是否达到优惠券满减金额
            if (total.compareTo(coupon.getMin()) == -1) {
                return null;
            }
        }

        // 检测订单状态
        Short status = coupon.getStatus();
        if (!status.equals(CouponConstant.STATUS_NORMAL)) {
            return null;
        }
        // 检测是否满足最低消费
        if (checkedGoodsPrice.compareTo(coupon.getMin()) == -1) {
            return null;
        }

        return coupon;
    }

}
