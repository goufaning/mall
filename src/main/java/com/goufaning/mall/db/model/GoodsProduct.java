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
 * 商品货品表
 */
@Data
@EqualsAndHashCode(callSuper = true)
@TableName(value = "litemall_goods_product")
public class GoodsProduct extends Model {
    @TableId(value = "id", type = IdType.AUTO)
    private Integer id;

    /**
     * 商品表的商品ID
     */
    @TableField(value = "goods_id")
    private Integer goodsId;

    /**
     * 商品规格值列表，采用JSON数组格式
     */
    @TableField(value = "specifications")
    private String specifications;

    /**
     * 商品货品价格
     */
    @TableField(value = "price")
    private BigDecimal price;

    /**
     * 商品货品数量
     */
    @TableField(value = "`number`")
    private Integer number;

    /**
     * 商品货品图片
     */
    @TableField(value = "url")
    private String url;

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

    public static final String COL_GOODS_ID = "goods_id";

    public static final String COL_SPECIFICATIONS = "specifications";

    public static final String COL_PRICE = "price";

    public static final String COL_NUMBER = "number";

    public static final String COL_URL = "url";

    public static final String COL_ADD_TIME = "add_time";

    public static final String COL_UPDATE_TIME = "update_time";

    public static final String COL_DELETED = "deleted";
}