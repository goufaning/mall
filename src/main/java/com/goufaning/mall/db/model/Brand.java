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
 * 品牌商表
 */
@Data
@EqualsAndHashCode(callSuper = true)
@TableName(value = "litemall_brand")
public class Brand extends Model {
    @TableId(value = "id", type = IdType.AUTO)
    private Integer id;

    /**
     * 品牌商名称
     */
    @TableField(value = "`name`")
    private String name;

    /**
     * 品牌商简介
     */
    @TableField(value = "`desc`")
    private String desc;

    /**
     * 品牌商页的品牌商图片
     */
    @TableField(value = "pic_url")
    private String picUrl;

    @TableField(value = "sort_order")
    private Byte sortOrder;

    /**
     * 品牌商的商品低价，仅用于页面展示
     */
    @TableField(value = "floor_price")
    private BigDecimal floorPrice;

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

    public static final String COL_NAME = "name";

    public static final String COL_DESC = "desc";

    public static final String COL_PIC_URL = "pic_url";

    public static final String COL_SORT_ORDER = "sort_order";

    public static final String COL_FLOOR_PRICE = "floor_price";

    public static final String COL_ADD_TIME = "add_time";

    public static final String COL_UPDATE_TIME = "update_time";

    public static final String COL_DELETED = "deleted";
}