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
 * 订单商品表
 */
@Data
@EqualsAndHashCode(callSuper = true)
@TableName(value = "litemall_order_goods")
public class OrderGoods extends Model {
    @TableId(value = "id", type = IdType.AUTO)
    private Integer id;

    /**
     * 订单表的订单ID
     */
    @TableField(value = "order_id")
    private Integer orderId;

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
     * 商品编号
     */
    @TableField(value = "goods_sn")
    private String goodsSn;

    /**
     * 商品货品表的货品ID
     */
    @TableField(value = "product_id")
    private Integer productId;

    /**
     * 商品货品的购买数量
     */
    @TableField(value = "`number`")
    private Short number;

    /**
     * 商品货品的售价
     */
    @TableField(value = "price")
    private BigDecimal price;

    /**
     * 商品货品的规格列表
     */
    @TableField(value = "specifications")
    private String specifications;

    /**
     * 商品货品图片或者商品图片
     */
    @TableField(value = "pic_url")
    private String picUrl;

    /**
     * 订单商品评论，如果是-1，则超期不能评价；如果是0，则可以评价；如果其他值，则是comment表里面的评论ID。
     */
    @TableField(value = "`comment`")
    private Integer comment;

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

    public static final String COL_GOODS_ID = "goods_id";

    public static final String COL_GOODS_NAME = "goods_name";

    public static final String COL_GOODS_SN = "goods_sn";

    public static final String COL_PRODUCT_ID = "product_id";

    public static final String COL_NUMBER = "number";

    public static final String COL_PRICE = "price";

    public static final String COL_SPECIFICATIONS = "specifications";

    public static final String COL_PIC_URL = "pic_url";

    public static final String COL_COMMENT = "comment";

    public static final String COL_ADD_TIME = "add_time";

    public static final String COL_UPDATE_TIME = "update_time";

    public static final String COL_DELETED = "deleted";
}