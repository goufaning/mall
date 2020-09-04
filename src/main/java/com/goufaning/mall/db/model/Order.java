package com.goufaning.mall.db.model;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.baomidou.mybatisplus.extension.activerecord.Model;
import java.math.BigDecimal;
import java.util.Date;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
* $description
* @author goufn
* @date 2020/8/31 2:24 下午
* @version V1.0
*/

/**
 * 订单表
 */
@Data
@EqualsAndHashCode(callSuper = true)
@TableName(value = "litemall_order")
public class Order extends Model {
    @TableId(value = "id", type = IdType.AUTO)
    private Integer id;

    /**
     * 用户表的用户ID
     */
    @TableField(value = "user_id")
    private Integer userId;

    /**
     * 订单编号
     */
    @TableField(value = "order_sn")
    private String orderSn;

    /**
     * 订单状态
     */
    @TableField(value = "order_status")
    private Short orderStatus;

    /**
     * 售后状态，0是可申请，1是用户已申请，2是管理员审核通过，3是管理员退款成功，4是管理员审核拒绝，5是用户已取消
     */
    @TableField(value = "aftersale_status")
    private Short aftersaleStatus;

    /**
     * 收货人名称
     */
    @TableField(value = "consignee")
    private String consignee;

    /**
     * 收货人手机号
     */
    @TableField(value = "mobile")
    private String mobile;

    /**
     * 收货具体地址
     */
    @TableField(value = "address")
    private String address;

    /**
     * 用户订单留言
     */
    @TableField(value = "message")
    private String message;

    /**
     * 商品总费用
     */
    @TableField(value = "goods_price")
    private BigDecimal goodsPrice;

    /**
     * 配送费用
     */
    @TableField(value = "freight_price")
    private BigDecimal freightPrice;

    /**
     * 优惠券减免
     */
    @TableField(value = "coupon_price")
    private BigDecimal couponPrice;

    /**
     * 用户积分减免
     */
    @TableField(value = "integral_price")
    private BigDecimal integralPrice;

    /**
     * 团购优惠价减免
     */
    @TableField(value = "groupon_price")
    private BigDecimal grouponPrice;

    /**
     * 订单费用， = goods_price + freight_price - coupon_price
     */
    @TableField(value = "order_price")
    private BigDecimal orderPrice;

    /**
     * 实付费用， = order_price - integral_price
     */
    @TableField(value = "actual_price")
    private BigDecimal actualPrice;

    /**
     * 微信付款编号
     */
    @TableField(value = "pay_id")
    private String payId;

    /**
     * 微信付款时间
     */
    @TableField(value = "pay_time")
    private Date payTime;

    /**
     * 发货编号
     */
    @TableField(value = "ship_sn")
    private String shipSn;

    /**
     * 发货快递公司
     */
    @TableField(value = "ship_channel")
    private String shipChannel;

    /**
     * 发货开始时间
     */
    @TableField(value = "ship_time")
    private Date shipTime;

    /**
     * 实际退款金额，（有可能退款金额小于实际支付金额）
     */
    @TableField(value = "refund_amount")
    private BigDecimal refundAmount;

    /**
     * 退款方式
     */
    @TableField(value = "refund_type")
    private String refundType;

    /**
     * 退款备注
     */
    @TableField(value = "refund_content")
    private String refundContent;

    /**
     * 退款时间
     */
    @TableField(value = "refund_time")
    private Date refundTime;

    /**
     * 用户确认收货时间
     */
    @TableField(value = "confirm_time")
    private Date confirmTime;

    /**
     * 待评价订单商品数量
     */
    @TableField(value = "comments")
    private Short comments;

    /**
     * 订单关闭时间
     */
    @TableField(value = "end_time")
    private Date endTime;

    /**
     * 创建时间
     */
    @TableField(value = "add_time")
    private Date addTime;

    /**
     * 更新时间
     */
    @TableField(value = "update_time")
    private Date updateTime;

    /**
     * 逻辑删除
     */
    @TableField(value = "deleted")
    private Boolean deleted;

    public static final String COL_ID = "id";

    public static final String COL_USER_ID = "user_id";

    public static final String COL_ORDER_SN = "order_sn";

    public static final String COL_ORDER_STATUS = "order_status";

    public static final String COL_AFTERSALE_STATUS = "aftersale_status";

    public static final String COL_CONSIGNEE = "consignee";

    public static final String COL_MOBILE = "mobile";

    public static final String COL_ADDRESS = "address";

    public static final String COL_MESSAGE = "message";

    public static final String COL_GOODS_PRICE = "goods_price";

    public static final String COL_FREIGHT_PRICE = "freight_price";

    public static final String COL_COUPON_PRICE = "coupon_price";

    public static final String COL_INTEGRAL_PRICE = "integral_price";

    public static final String COL_GROUPON_PRICE = "groupon_price";

    public static final String COL_ORDER_PRICE = "order_price";

    public static final String COL_ACTUAL_PRICE = "actual_price";

    public static final String COL_PAY_ID = "pay_id";

    public static final String COL_PAY_TIME = "pay_time";

    public static final String COL_SHIP_SN = "ship_sn";

    public static final String COL_SHIP_CHANNEL = "ship_channel";

    public static final String COL_SHIP_TIME = "ship_time";

    public static final String COL_REFUND_AMOUNT = "refund_amount";

    public static final String COL_REFUND_TYPE = "refund_type";

    public static final String COL_REFUND_CONTENT = "refund_content";

    public static final String COL_REFUND_TIME = "refund_time";

    public static final String COL_CONFIRM_TIME = "confirm_time";

    public static final String COL_COMMENTS = "comments";

    public static final String COL_END_TIME = "end_time";

    public static final String COL_ADD_TIME = "add_time";

    public static final String COL_UPDATE_TIME = "update_time";

    public static final String COL_DELETED = "deleted";
}