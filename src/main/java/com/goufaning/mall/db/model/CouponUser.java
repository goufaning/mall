package com.goufaning.mall.db.model;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.baomidou.mybatisplus.extension.activerecord.Model;
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
 * 优惠券用户使用表
 */
@Data
@EqualsAndHashCode(callSuper = true)
@TableName(value = "litemall_coupon_user")
public class CouponUser extends Model {
    @TableId(value = "id", type = IdType.AUTO)
    private Integer id;

    /**
     * 用户ID
     */
    @TableField(value = "user_id")
    private Integer userId;

    /**
     * 优惠券ID
     */
    @TableField(value = "coupon_id")
    private Integer couponId;

    /**
     * 使用状态, 如果是0则未使用；如果是1则已使用；如果是2则已过期；如果是3则已经下架；
     */
    @TableField(value = "`status`")
    private Short status;

    /**
     * 使用时间
     */
    @TableField(value = "used_time")
    private Date usedTime;

    /**
     * 有效期开始时间
     */
    @TableField(value = "start_time")
    private Date startTime;

    /**
     * 有效期截至时间
     */
    @TableField(value = "end_time")
    private Date endTime;

    /**
     * 订单ID
     */
    @TableField(value = "order_id")
    private Integer orderId;

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

    public static final String COL_COUPON_ID = "coupon_id";

    public static final String COL_STATUS = "status";

    public static final String COL_USED_TIME = "used_time";

    public static final String COL_START_TIME = "start_time";

    public static final String COL_END_TIME = "end_time";

    public static final String COL_ORDER_ID = "order_id";

    public static final String COL_ADD_TIME = "add_time";

    public static final String COL_UPDATE_TIME = "update_time";

    public static final String COL_DELETED = "deleted";
}