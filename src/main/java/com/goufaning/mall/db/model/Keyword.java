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
 * 关键字表
 */
@Data
@EqualsAndHashCode(callSuper = true)
@TableName(value = "litemall_keyword")
public class Keyword extends Model {
    @TableId(value = "id", type = IdType.AUTO)
    private Integer id;

    /**
     * 关键字
     */
    @TableField(value = "keyword")
    private String keyword;

    /**
     * 关键字的跳转链接
     */
    @TableField(value = "url")
    private String url;

    /**
     * 是否是热门关键字
     */
    @TableField(value = "is_hot")
    private Boolean isHot;

    /**
     * 是否是默认关键字
     */
    @TableField(value = "is_default")
    private Boolean isDefault;

    /**
     * 排序
     */
    @TableField(value = "sort_order")
    private Integer sortOrder;

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

    public static final String COL_KEYWORD = "keyword";

    public static final String COL_URL = "url";

    public static final String COL_IS_HOT = "is_hot";

    public static final String COL_IS_DEFAULT = "is_default";

    public static final String COL_SORT_ORDER = "sort_order";

    public static final String COL_ADD_TIME = "add_time";

    public static final String COL_UPDATE_TIME = "update_time";

    public static final String COL_DELETED = "deleted";
}