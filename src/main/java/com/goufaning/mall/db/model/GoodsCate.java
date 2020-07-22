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
* @date 2020-07-15 15:16
* @version V1.0
*/
@Data
@EqualsAndHashCode(callSuper=true)
@TableName(value = "mall_goods_cate")
public class GoodsCate extends Model {
    /**
     * 分类id
     */
    @TableId(value = "id", type = IdType.AUTO)
    private Integer id;

    /**
     * 父级id
     */
    @TableField(value = "parent_id")
    private Integer parentId;

    /**
     * 分类名称
     */
    @TableField(value = "cat_name")
    private String catName;

    /**
     * 是否显示
     */
    @TableField(value = "is_show")
    private Integer isShow;

    /**
     * 分类排序
     */
    @TableField(value = "cat_sort")
    private Integer catSort;

    /**
     * 数据标记
     */
    @TableField(value = "data_flag")
    private Integer dataFlag;

    /**
     * 创建时间
     */
    @TableField(value = "create_time")
    private Date createTime;
}