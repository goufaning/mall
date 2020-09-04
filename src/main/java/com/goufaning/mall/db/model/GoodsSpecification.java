package com.goufaning.mall.db.model;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.baomidou.mybatisplus.extension.activerecord.Model;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.util.Date;

/**
* $description
* @author goufn
* @date 2020/8/31 2:24 下午
* @version V1.0
*/

/**
 * 商品规格表
 */
@Data
@EqualsAndHashCode(callSuper = true)
@TableName(value = "litemall_goods_specification")
public class GoodsSpecification extends Model {
    @TableId(value = "id", type = IdType.AUTO)
    private Integer id;

    /**
     * 商品表的商品ID
     */
    @TableField(value = "goods_id")
    private Integer goodsId;

    /**
     * 商品规格名称
     */
    @TableField(value = "specification")
    private String specification;

    /**
     * 商品规格值
     */
    @TableField(value = "`value`")
    private String value;

    /**
     * 商品规格图片
     */
    @TableField(value = "pic_url")
    private String picUrl;

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

    public static final String COL_SPECIFICATION = "specification";

    public static final String COL_VALUE = "value";

    public static final String COL_PIC_URL = "pic_url";

    public static final String COL_ADD_TIME = "add_time";

    public static final String COL_UPDATE_TIME = "update_time";

    public static final String COL_DELETED = "deleted";
}