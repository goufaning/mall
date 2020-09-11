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
 * 商品基本信息表
 */
@Data
@EqualsAndHashCode(callSuper = true)
@TableName(value = "litemall_goods")
public class Goods extends Model {
    @TableId(value = "id", type = IdType.AUTO)
    private Integer id;

    /**
     * 商品编号
     */
    @TableField(value = "goods_sn")
    private String goodsSn;

    /**
     * 商品名称
     */
    @TableField(value = "`name`")
    private String name;

    /**
     * 商品所属类目ID
     */
    @TableField(value = "category_id")
    private Integer categoryId;

    @TableField(value = "brand_id")
    private Integer brandId;

    /**
     * 商品宣传图片列表，采用JSON数组格式
     */
    @TableField(value = "gallery")
    private String gallery;

    /**
     * 商品关键字，采用逗号间隔
     */
    @TableField(value = "keywords")
    private String keywords;

    /**
     * 商品简介
     */
    @TableField(value = "brief")
    private String brief;

    /**
     * 是否上架
     */
    @TableField(value = "is_on_sale")
    private Boolean isOnSale;

    @TableField(value = "sort_order")
    private Short sortOrder;

    /**
     * 商品页面商品图片
     */
    @TableField(value = "pic_url")
    private String picUrl;

    /**
     * 商品分享海报
     */
    @TableField(value = "share_url")
    private String shareUrl;

    /**
     * 是否新品首发，如果设置则可以在新品首发页面展示
     */
    @TableField(value = "is_new")
    private Boolean isNew;

    /**
     * 是否人气推荐，如果设置则可以在人气推荐页面展示
     */
    @TableField(value = "is_hot")
    private Boolean isHot;

    /**
     * 商品单位，例如件、盒
     */
    @TableField(value = "unit")
    private String unit;

    /**
     * 专柜价格
     */
    @TableField(value = "counter_price")
    private BigDecimal counterPrice;

    /**
     * 零售价格
     */
    @TableField(value = "retail_price")
    private BigDecimal retailPrice;

    /**
     * 商品详细介绍，是富文本格式
     */
    @TableField(value = "detail")
    private String detail;

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

    public static final String COL_GOODS_SN = "goods_sn";

    public static final String COL_NAME = "name";

    public static final String COL_CATEGORY_ID = "category_id";

    public static final String COL_BRAND_ID = "brand_id";

    public static final String COL_GALLERY = "gallery";

    public static final String COL_KEYWORDS = "keywords";

    public static final String COL_BRIEF = "brief";

    public static final String COL_IS_ON_SALE = "is_on_sale";

    public static final String COL_SORT_ORDER = "sort_order";

    public static final String COL_PIC_URL = "pic_url";

    public static final String COL_SHARE_URL = "share_url";

    public static final String COL_IS_NEW = "is_new";

    public static final String COL_IS_HOT = "is_hot";

    public static final String COL_UNIT = "unit";

    public static final String COL_COUNTER_PRICE = "counter_price";

    public static final String COL_RETAIL_PRICE = "retail_price";

    public static final String COL_DETAIL = "detail";

    public static final String COL_ADD_TIME = "add_time";

    public static final String COL_UPDATE_TIME = "update_time";

    public static final String COL_DELETED = "deleted";
}