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
 * 售后表
 */
@Data
@EqualsAndHashCode(callSuper = true)
@TableName(value = "litemall_aftersale")
public class Aftersale extends Model {
    @TableId(value = "id", type = IdType.AUTO)
    private Integer id;

    /**
     * 售后编号
     */
    @TableField(value = "aftersale_sn")
    private String aftersaleSn;

    /**
     * 订单ID
     */
    @TableField(value = "order_id")
    private Integer orderId;

    /**
     * 用户ID
     */
    @TableField(value = "user_id")
    private Integer userId;

    /**
     * 售后类型，0是未收货退款，1是已收货（无需退货）退款，2用户退货退款
     */
    @TableField(value = "`type`")
    private Short type;

    /**
     * 退款原因
     */
    @TableField(value = "reason")
    private String reason;

    /**
     * 退款金额
     */
    @TableField(value = "amount")
    private BigDecimal amount;

    /**
     * 退款凭证图片链接数组
     */
    @TableField(value = "pictures")
    private String pictures;

    /**
     * 退款说明
     */
    @TableField(value = "`comment`")
    private String comment;

    /**
     * 售后状态，0是可申请，1是用户已申请，2是管理员审核通过，3是管理员退款成功，4是管理员审核拒绝，5是用户已取消
     */
    @TableField(value = "`status`")
    private Short status;

    /**
     * 管理员操作时间
     */
    @TableField(value = "handle_time")
    private Date handleTime;

    /**
     * 添加时间
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

    public static final String COL_AFTERSALE_SN = "aftersale_sn";

    public static final String COL_ORDER_ID = "order_id";

    public static final String COL_USER_ID = "user_id";

    public static final String COL_TYPE = "type";

    public static final String COL_REASON = "reason";

    public static final String COL_AMOUNT = "amount";

    public static final String COL_PICTURES = "pictures";

    public static final String COL_COMMENT = "comment";

    public static final String COL_STATUS = "status";

    public static final String COL_HANDLE_TIME = "handle_time";

    public static final String COL_ADD_TIME = "add_time";

    public static final String COL_UPDATE_TIME = "update_time";

    public static final String COL_DELETED = "deleted";
}