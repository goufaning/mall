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
 * 团购活动表
 */
@Data
@EqualsAndHashCode(callSuper = true)
@TableName(value = "litemall_groupon")
public class Groupon extends Model {
    @TableId(value = "id", type = IdType.AUTO)
    private Integer id;

    /**
     * 关联的订单ID
     */
    @TableField(value = "order_id")
    private Integer orderId;

    /**
     * 如果是开团用户，则groupon_id是0；如果是参团用户，则groupon_id是团购活动ID
     */
    @TableField(value = "groupon_id")
    private Integer grouponId;

    /**
     * 团购规则ID，关联litemall_groupon_rules表ID字段
     */
    @TableField(value = "rules_id")
    private Integer rulesId;

    /**
     * 用户ID
     */
    @TableField(value = "user_id")
    private Integer userId;

    /**
     * 团购分享图片地址
     */
    @TableField(value = "share_url")
    private String shareUrl;

    /**
     * 开团用户ID
     */
    @TableField(value = "creator_user_id")
    private Integer creatorUserId;

    /**
     * 开团时间
     */
    @TableField(value = "creator_user_time")
    private Date creatorUserTime;

    /**
     * 团购活动状态，开团未支付则0，开团中则1，开团失败则2
     */
    @TableField(value = "`status`")
    private Short status;

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

    public static final String COL_ORDER_ID = "order_id";

    public static final String COL_GROUPON_ID = "groupon_id";

    public static final String COL_RULES_ID = "rules_id";

    public static final String COL_USER_ID = "user_id";

    public static final String COL_SHARE_URL = "share_url";

    public static final String COL_CREATOR_USER_ID = "creator_user_id";

    public static final String COL_CREATOR_USER_TIME = "creator_user_time";

    public static final String COL_STATUS = "status";

    public static final String COL_ADD_TIME = "add_time";

    public static final String COL_UPDATE_TIME = "update_time";

    public static final String COL_DELETED = "deleted";
}