package com.goufaning.mall.wx.vo;

import lombok.Data;

import java.math.BigDecimal;
import java.time.LocalDateTime;

/**
 * 团购信息
 *
 * @author goufn
 * @version V1.0
 * @date 2020/9/2 10:59 上午
 */
@Data
public class GrouponRuleVo {
    private Integer id;
    private String name;
    private String brief;
    private String picUrl;
    private BigDecimal counterPrice;
    private BigDecimal retailPrice;
    /** 团购价格 */
    private BigDecimal grouponPrice;
    /** 团购折扣 */
    private BigDecimal grouponDiscount;
    /** 团购人数 */
    private Integer grouponMember;
    /** 过期时间 */
    private LocalDateTime expireTime;
}
