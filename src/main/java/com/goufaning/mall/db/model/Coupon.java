package com.goufaning.mall.db.model;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.baomidou.mybatisplus.extension.activerecord.Model;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.math.BigDecimal;
import java.time.LocalDateTime;

/**
* $description
* @author goufn
* @date 2020/8/31 2:17 下午
* @version V1.0
*/

/**
 * 优惠券信息及规则表
 */
@Data
@EqualsAndHashCode(callSuper = true)
@TableName(value = "mall_coupon")
public class Coupon extends Model {
    private static final long serialVersionUID = 1L;
    @TableId(value = "id", type = IdType.AUTO)
    private Integer id;

    /**
     * 优惠券名称
     */
    @TableField(value = "`name`")
    private String name;

    /**
     * 优惠券介绍，通常是显示优惠券使用限制文字
     */
    @TableField(value = "description")
    private String description;

    /**
     * 优惠券标签，例如新人专用
     */
    @TableField(value = "tag")
    private String tag;

    /**
     * 优惠券数量，如果是0，则是无限量
     */
    @TableField(value = "total")
    private Integer total;

    /**
     * 优惠金额，
     */
    @TableField(value = "discount")
    private BigDecimal discount;

    /**
     * 最少消费金额才能使用优惠券。
     */
    @TableField(value = "`min`")
    private BigDecimal min;

    /**
     * 用户领券限制数量，如果是0，则是不限制；默认是1，限领一张.
     */
    @TableField(value = "`limit`")
    private Short limit;

    /**
     * 优惠券赠送类型，如果是0则通用券，用户领取；如果是1，则是注册赠券；如果是2，则是优惠券码兑换；
     */
    @TableField(value = "`type`")
    private Short type;

    /**
     * 优惠券状态，如果是0则是正常可用；如果是1则是过期; 如果是2则是下架。
     */
    @TableField(value = "`status`")
    private Short status;

    /**
     * 商品限制类型，如果0则全商品，如果是1则是类目限制，如果是2则是商品限制。
     */
    @TableField(value = "goods_type")
    private Short goodsType;

    /**
     * 商品限制值，goods_type如果是0则空集合，如果是1则是类目集合，如果是2则是商品集合。
     */
    @TableField(value = "goods_value")
    private String goodsValue;

    /**
     * 优惠券兑换码
     */
    @TableField(value = "code")
    private String code;

    /**
     * 有效时间限制，如果是0，则基于领取时间的有效天数days；如果是1，则start_time和end_time是优惠券有效期；
     */
    @TableField(value = "time_type")
    private Short timeType;

    /**
     * 基于领取时间的有效天数days。
     */
    @TableField(value = "`days`")
    private Short days;

    /**
     * 使用券开始时间
     */
    @TableField(value = "start_time")
    private LocalDateTime startTime;

    /**
     * 使用券截至时间
     */
    @TableField(value = "end_time")
    private LocalDateTime endTime;

    /**
     * 创建时间
     */
    @TableField(value = "add_time")
    private LocalDateTime addTime;

    /**
     * 更新时间
     */
    @TableField(value = "update_time")
    private LocalDateTime updateTime;

    /**
     * 逻辑删除
     */
    @TableField(value = "deleted")
    private Boolean deleted;

    public static final String COL_ID = "id";

    public static final String COL_NAME = "name";

    public static final String COL_DESCRIPTION = "description";

    public static final String COL_TAG = "tag";

    public static final String COL_TOTAL = "total";

    public static final String COL_DISCOUNT = "discount";

    public static final String COL_MIN = "min";

    public static final String COL_LIMIT = "limit";

    public static final String COL_TYPE = "type";

    public static final String COL_STATUS = "status";

    public static final String COL_GOODS_TYPE = "goods_type";

    public static final String COL_GOODS_VALUE = "goods_value";

    public static final String COL_CODE = "code";

    public static final String COL_TIME_TYPE = "time_type";

    public static final String COL_DAYS = "days";

    public static final String COL_START_TIME = "start_time";

    public static final String COL_END_TIME = "end_time";

    public static final String COL_ADD_TIME = "add_time";

    public static final String COL_UPDATE_TIME = "update_time";

    public static final String COL_DELETED = "deleted";
}