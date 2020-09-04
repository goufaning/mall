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
 * 类目表
 */
@Data
@EqualsAndHashCode(callSuper = true)
@TableName(value = "litemall_category")
public class Category extends Model {
    private static final long serialVersionUID = 1L;
    @TableId(value = "id", type = IdType.AUTO)
    private Integer id;

    /**
     * 类目名称
     */
    @TableField(value = "`name`")
    private String name;

    /**
     * 类目关键字，以JSON数组格式
     */
    @TableField(value = "keywords")
    private String keywords;

    /**
     * 类目广告语介绍
     */
    @TableField(value = "`desc`")
    private String desc;

    /**
     * 父类目ID
     */
    @TableField(value = "pid")
    private Integer pid;

    /**
     * 类目图标
     */
    @TableField(value = "icon_url")
    private String iconUrl;

    /**
     * 类目图片
     */
    @TableField(value = "pic_url")
    private String picUrl;

    @TableField(value = "`level`")
    private String level;

    /**
     * 排序
     */
    @TableField(value = "sort_order")
    private Byte sortOrder;

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

    public static final String COL_KEYWORDS = "keywords";

    public static final String COL_DESC = "desc";

    public static final String COL_PID = "pid";

    public static final String COL_ICON_URL = "icon_url";

    public static final String COL_PIC_URL = "pic_url";

    public static final String COL_LEVEL = "level";

    public static final String COL_SORT_ORDER = "sort_order";

    public static final String COL_ADD_TIME = "add_time";

    public static final String COL_UPDATE_TIME = "update_time";

    public static final String COL_DELETED = "deleted";
}