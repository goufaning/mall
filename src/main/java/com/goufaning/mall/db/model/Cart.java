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
* @date 2020/8/31 2:24 下午
* @version V1.0
*/

/**
 * 购物车商品表
 */
@Data
@EqualsAndHashCode(callSuper = true)
@TableName(value = "litemall_cart")
public class Cart extends Model {
    @TableId(value = "id", type = IdType.AUTO)
    private Integer id;

    /**
     * 用户表的用户ID
     */
    @TableField(value = "user_id")
    private Integer userId;

    /**
     * 商品表的商品ID
     */
    @TableField(value = "goods_id")
    private Integer goodsId;

    /**
     * 商品编号
     */
    @TableField(value = "goods_sn")
    private String goodsSn;

    /**
     * 商品名称
     */
    @TableField(value = "goods_name")
    private String goodsName;

    /**
     * 商品货品表的货品ID
     */
    @TableField(value = "product_id")
    private Integer productId;

    /**
     * 商品货品的价格
     */
    @TableField(value = "price")
    private BigDecimal price;

    /**
     * 商品货品的数量
     */
    @TableField(value = "`number`")
    private Short number;

    /**
     * 商品规格值列表，采用JSON数组格式
     */
    @TableField(value = "specifications")
    private String specifications;

    /**
     * 购物车中商品是否选择状态
     */
    @TableField(value = "`checked`")
    private Boolean checked;

    /**
     * 商品图片或者商品货品图片
     */
    @TableField(value = "pic_url")
    private String picUrl;

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

    public static final String COL_USER_ID = "user_id";

    public static final String COL_GOODS_ID = "goods_id";

    public static final String COL_GOODS_SN = "goods_sn";

    public static final String COL_GOODS_NAME = "goods_name";

    public static final String COL_PRODUCT_ID = "product_id";

    public static final String COL_PRICE = "price";

    public static final String COL_NUMBER = "number";

    public static final String COL_SPECIFICATIONS = "specifications";

    public static final String COL_CHECKED = "checked";

    public static final String COL_PIC_URL = "pic_url";

    public static final String COL_ADD_TIME = "add_time";

    public static final String COL_UPDATE_TIME = "update_time";

    public static final String COL_DELETED = "deleted";
}