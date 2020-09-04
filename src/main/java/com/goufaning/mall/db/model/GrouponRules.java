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
import java.util.Date;

/**
* $description
* @author goufn
* @date 2020/8/31 2:24 下午
* @version V1.0
*/

/**
 * 团购规则表
 */
@Data
@EqualsAndHashCode(callSuper = true)
@TableName(value = "litemall_groupon_rules")
public class GrouponRules extends Model {
    @TableId(value = "id", type = IdType.AUTO)
    private Integer id;

    /**
     * 商品表的商品ID
     */
    @TableField(value = "goods_id")
    private Integer goodsId;

    /**
     * 商品名称
     */
    @TableField(value = "goods_name")
    private String goodsName;

    /**
     * 商品图片或者商品货品图片
     */
    @TableField(value = "pic_url")
    private String picUrl;

    /**
     * 优惠金额
     */
    @TableField(value = "discount")
    private BigDecimal discount;

    /**
     * 达到优惠条件的人数
     */
    @TableField(value = "discount_member")
    private Integer discountMember;

    /**
     * 团购过期时间
     */
    @TableField(value = "expire_time")
    private LocalDateTime expireTime;

    /**
     * 团购规则状态，正常上线则0，到期自动下线则1，管理手动下线则2
     */
    @TableField(value = "`status`")
    private Short status;

    /**
     * 创建时间
     */
    @TableField(value = "add_time")
    private LocalDateTime addTime;

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

    public static final String COL_GOODS_ID = "goods_id";

    public static final String COL_GOODS_NAME = "goods_name";

    public static final String COL_PIC_URL = "pic_url";

    public static final String COL_DISCOUNT = "discount";

    public static final String COL_DISCOUNT_MEMBER = "discount_member";

    public static final String COL_EXPIRE_TIME = "expire_time";

    public static final String COL_STATUS = "status";

    public static final String COL_ADD_TIME = "add_time";

    public static final String COL_UPDATE_TIME = "update_time";

    public static final String COL_DELETED = "deleted";
}