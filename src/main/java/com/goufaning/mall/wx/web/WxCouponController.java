package com.goufaning.mall.wx.web;

import com.goufaning.mall.common.result.CommonResult;
import com.goufaning.mall.core.util.JacksonUtil;
import com.goufaning.mall.core.util.ResponseUtil;
import com.goufaning.mall.db.model.Cart;
import com.goufaning.mall.db.model.Coupon;
import com.goufaning.mall.db.model.CouponUser;
import com.goufaning.mall.db.model.GrouponRules;
import com.goufaning.mall.db.service.*;
import com.goufaning.mall.db.util.CouponConstant;
import com.goufaning.mall.wx.util.WxResponseCode;
import com.goufaning.mall.wx.vo.CouponVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

/**
 * 优惠卷
 *
 * @author goufn
 * @version V1.0
 * @date 2020/9/8 2:02 下午
 */
@RestController
@RequestMapping("/wx/coupon")
@Validated
public class WxCouponController {

    @Autowired
    private CouponService couponService;
    @Autowired
    private CouponUserService couponUserService;
    @Autowired
    private GrouponRulesService grouponRulesService;
    @Autowired
    private CartService cartService;
    @Autowired
    private CouponVerifyService couponVerifyService;

    /**
     * 优惠券列表
     *
     * @param page
     * @param limit
     * @param sort
     * @param order
     * @return
     */
    @GetMapping("list")
    public Object list(@RequestParam(defaultValue = "1") Integer page,
                       @RequestParam(defaultValue = "10") Integer limit,
                       @RequestParam(defaultValue = "add_time") String sort,
                       @RequestParam(defaultValue = "desc") String order) {

        List<Coupon> couponList = couponService.queryList(page, limit, sort, order);
        return CommonResult.success(couponList);
    }

    /**
     * 个人优惠券列表
     *
     * @param userId
     * @param status
     * @param page
     * @param limit
     * @param sort
     * @param order
     * @return
     */
    @GetMapping("mylist")
    public Object mylist(Integer userId,
                         Short status,
                         @RequestParam(defaultValue = "1") Integer page,
                         @RequestParam(defaultValue = "10") Integer limit,
                         @RequestParam(defaultValue = "add_time") String sort,
                         @RequestParam(defaultValue = "desc") String order) {
        if (userId == null) {
            return CommonResult.unlogin();
        }
        List<CouponUser> couponUserList = couponUserService.queryList(userId, null, status, page, limit, sort, order);
        List<CouponVo> couponVoList = change(couponUserList);
        return ResponseUtil.okList(couponVoList);
    }

    private List<CouponVo> change(List<CouponUser> couponList) {
        List<CouponVo> couponVoList = new ArrayList<>(couponList.size());
        for(CouponUser couponUser : couponList){
            Integer couponId = couponUser.getCouponId();
            Coupon coupon = couponService.getById(couponId);
            CouponVo couponVo = new CouponVo();
            couponVo.setId(couponUser.getId());
            couponVo.setCouponId(coupon.getId());
            couponVo.setName(coupon.getName());
            couponVo.setDescription(coupon.getDescription());
            couponVo.setTag(coupon.getTag());
            couponVo.setMin(coupon.getMin());
            couponVo.setDiscount(coupon.getDiscount());
            couponVo.setStartTime(couponUser.getStartTime());
            couponVo.setEndTime(couponUser.getEndTime());
            couponVoList.add(couponVo);
        }
        return couponVoList;
    }


    /**
     * 当前购物车下单商品订单可用优惠券
     *
     * @param userId
     * @param cartId
     * @param grouponRulesId
     * @return
     */
    @GetMapping("selectlist")
    public Object selectlist(Integer userId, Integer cartId, Integer grouponRulesId) {
        if (userId == null) {
            return CommonResult.unlogin();
        }
        // 团购优惠
        BigDecimal grouponPrice = new BigDecimal(0.00);
        GrouponRules grouponRules = grouponRulesService.getById(grouponRulesId);
        if (grouponRules != null) {
            grouponPrice = grouponRules.getDiscount();
        }
        // 商品价格
        List<Cart> checkedGoodsList = null;
        if (cartId == null || cartId.equals(0)) {
            checkedGoodsList = cartService.queryByUidAndChecked(userId);
        } else {
            Cart cart = cartService.findByUserIdAndId(userId, cartId);
            if (cart == null) {
                return CommonResult.badArgumentValue();
            }
            checkedGoodsList = new ArrayList<>(1);
            checkedGoodsList.add(cart);
        }
        BigDecimal checkedGoodsPrice = new BigDecimal(0.00);
        for (Cart cart : checkedGoodsList) {
            //  只有当团购规格商品ID符合才进行团购优惠
            if (grouponRules != null && grouponRules.getGoodsId().equals(cart.getGoodsId())) {
                checkedGoodsPrice = checkedGoodsPrice.add(cart.getPrice().subtract(grouponPrice).multiply(new BigDecimal(cart.getNumber())));
            } else {
                checkedGoodsPrice = checkedGoodsPrice.add(cart.getPrice().multiply(new BigDecimal(cart.getNumber())));
            }
        }
        // 计算优惠券可用情况
        List<CouponUser> couponUserList = couponUserService.queryAll(userId);
        List<CouponVo> couponVoList = change(couponUserList);
        for (CouponVo cv : couponVoList) {
            Coupon coupon = couponVerifyService.checkCoupon(userId, cv.getCouponId(), cv.getId(), checkedGoodsPrice, checkedGoodsList);
            cv.setAvailable(coupon != null);
        }

        return ResponseUtil.okList(couponVoList);
    }

    /**
     * 优惠券领取
     *
     * @param userId 用户ID
     * @param body 请求内容， { couponId: xxx }
     * @return 操作结果
     */
    @PostMapping("receive")
    public Object receive(Integer userId, @RequestBody String body) {
        if (userId == null) {
            return CommonResult.unlogin();
        }
        Integer couponId = JacksonUtil.parseInteger(body, "couponId");
        if(couponId == null){
            return CommonResult.badArgument();
        }

        Coupon coupon = couponService.getById(couponId);
        if(coupon == null){
            return CommonResult.badArgumentValue();
        }

        // 当前已领取数量和总数量比较
        Integer total = coupon.getTotal();
        Integer totalCoupons = couponUserService.countCoupon(couponId);
        if((total != 0) && (totalCoupons >= total)){
            return CommonResult.error(WxResponseCode.COUPON_EXCEED_LIMIT, "优惠券已领完");
        }

        // 当前用户已领取数量和用户限领数量比较
        Integer limit = coupon.getLimit().intValue();
        Integer userCounpons = couponUserService.countUserAndCoupon(userId, couponId);
        if((limit != 0) && (userCounpons >= limit)){
            return CommonResult.error(WxResponseCode.COUPON_EXCEED_LIMIT, "优惠券已经领取过");
        }

        // 优惠券分发类型
        // 例如注册赠券类型的优惠券不能领取
        Short type = coupon.getType();
        if(type.equals(CouponConstant.TYPE_REGISTER)){
            return CommonResult.error(WxResponseCode.COUPON_RECEIVE_FAIL, "新用户优惠券自动发送");
        }
        else if(type.equals(CouponConstant.TYPE_CODE)){
            return CommonResult.error(WxResponseCode.COUPON_RECEIVE_FAIL, "优惠券只能兑换");
        }
        else if(!type.equals(CouponConstant.TYPE_COMMON)){
            return CommonResult.error(WxResponseCode.COUPON_RECEIVE_FAIL, "优惠券类型不支持");
        }

        // 优惠券状态，已下架或者过期不能领取
        Short status = coupon.getStatus();
        if(status.equals(CouponConstant.STATUS_OUT)){
            return CommonResult.error(WxResponseCode.COUPON_EXCEED_LIMIT, "优惠券已领完");
        }
        else if(status.equals(CouponConstant.STATUS_EXPIRED)){
            return CommonResult.error(WxResponseCode.COUPON_RECEIVE_FAIL, "优惠券已经过期");
        }

        // 用户领券记录
        CouponUser couponUser = new CouponUser();
        couponUser.setCouponId(couponId);
        couponUser.setUserId(userId);
        Short timeType = coupon.getTimeType();
        if (timeType.equals(CouponConstant.TIME_TYPE_TIME)) {
            couponUser.setStartTime(coupon.getStartTime());
            couponUser.setEndTime(coupon.getEndTime());
        }
        else{
            LocalDateTime now = LocalDateTime.now();
            couponUser.setStartTime(now);
            couponUser.setEndTime(now.plusDays(coupon.getDays()));
        }
        couponUserService.add(couponUser);
        return CommonResult.success();
    }

    /**
     * 优惠券兑换
     *
     * @param userId 用户ID
     * @param body 请求内容， { code: xxx }
     * @return 操作结果
     */
    @PostMapping("exchange")
    public Object exchange(Integer userId, @RequestBody String body) {
        if (userId == null) {
            return CommonResult.unlogin();
        }
        String code = JacksonUtil.parseString(body, "code");
        if(code == null){
            return CommonResult.badArgument();
        }

        Coupon coupon = couponService.findByCode(code);
        if(coupon == null){
            return CommonResult.error(WxResponseCode.COUPON_CODE_INVALID, "优惠券不正确");
        }
        Integer couponId = coupon.getId();

        // 当前已领取数量和总数量比较
        Integer total = coupon.getTotal();
        Integer totalCoupons = couponUserService.countCoupon(couponId);
        if((total != 0) && (totalCoupons >= total)){
            return CommonResult.error(WxResponseCode.COUPON_EXCEED_LIMIT, "优惠券已兑换");
        }

        // 当前用户已领取数量和用户限领数量比较
        Integer limit = coupon.getLimit().intValue();
        Integer userCounpons = couponUserService.countUserAndCoupon(userId, couponId);
        if((limit != 0) && (userCounpons >= limit)){
            return CommonResult.error(WxResponseCode.COUPON_EXCEED_LIMIT, "优惠券已兑换");
        }

        // 优惠券分发类型
        // 例如注册赠券类型的优惠券不能领取
        Short type = coupon.getType();
        if(type.equals(CouponConstant.TYPE_REGISTER)){
            return CommonResult.error(WxResponseCode.COUPON_RECEIVE_FAIL, "新用户优惠券自动发送");
        }
        else if(type.equals(CouponConstant.TYPE_COMMON)){
            return CommonResult.error(WxResponseCode.COUPON_RECEIVE_FAIL, "优惠券只能领取，不能兑换");
        }
        else if(!type.equals(CouponConstant.TYPE_CODE)){
            return CommonResult.error(WxResponseCode.COUPON_RECEIVE_FAIL, "优惠券类型不支持");
        }

        // 优惠券状态，已下架或者过期不能领取
        Short status = coupon.getStatus();
        if(status.equals(CouponConstant.STATUS_OUT)){
            return CommonResult.error(WxResponseCode.COUPON_EXCEED_LIMIT, "优惠券已兑换");
        }
        else if(status.equals(CouponConstant.STATUS_EXPIRED)){
            return CommonResult.error(WxResponseCode.COUPON_RECEIVE_FAIL, "优惠券已经过期");
        }

        // 用户领券记录
        CouponUser couponUser = new CouponUser();
        couponUser.setCouponId(couponId);
        couponUser.setUserId(userId);
        Short timeType = coupon.getTimeType();
        if (timeType.equals(CouponConstant.TIME_TYPE_TIME)) {
            couponUser.setStartTime(coupon.getStartTime());
            couponUser.setEndTime(coupon.getEndTime());
        }
        else{
            LocalDateTime now = LocalDateTime.now();
            couponUser.setStartTime(now);
            couponUser.setEndTime(now.plusDays(coupon.getDays()));
        }
        couponUserService.add(couponUser);

        return CommonResult.success();
    }
}
