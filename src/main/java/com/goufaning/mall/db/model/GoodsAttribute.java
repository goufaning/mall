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
 * 商品参数表
 */
@Data
@EqualsAndHashCode(callSuper = true)
@TableName(value = "litemall_goods_attribute")
public class GoodsAttribute extends Model {
    @TableId(value = "id", type = IdType.AUTO)
    private Integer id;

    /**
     * 商品表的商品ID
     */
    @TableField(value = "goods_id")
    private Integer goodsId;

    /**
     * 商品参数名称
     */
    @TableField(value = "`attribute`")
    private String attribute;

    /**
     * 商品参数值
     */
    @TableField(value = "`value`")
    private String value;

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

    public static final String COL_ATTRIBUTE = "attribute";

    public static final String COL_VALUE = "value";

    public static final String COL_ADD_TIME = "add_time";

    public static final String COL_UPDATE_TIME = "update_time";

    public static final String COL_DELETED = "deleted";
}