package com.goufaning.mall.wx.vo;

import lombok.Data;

import java.math.BigDecimal;
import java.time.LocalDateTime;
@Data
public class CouponVo {
    private Integer id;
    private Integer couponId;
    /** 优惠卷名称 */
    private String name;
    /** 描述 */
    private String description;
    /**优惠卷标签 */
    private String tag;
    /** 最低消费多少才能使用优惠券 */
    private BigDecimal min;
    /** 优惠金额 */
    private BigDecimal discount;
    private LocalDateTime startTime;
    private LocalDateTime endTime;
    private boolean available;
}
