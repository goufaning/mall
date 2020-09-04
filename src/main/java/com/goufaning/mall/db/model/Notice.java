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
 * 通知表
 */
@Data
@EqualsAndHashCode(callSuper = true)
@TableName(value = "litemall_notice")
public class Notice extends Model {
    @TableId(value = "id", type = IdType.AUTO)
    private Integer id;

    /**
     * 通知标题
     */
    @TableField(value = "title")
    private String title;

    /**
     * 通知内容
     */
    @TableField(value = "content")
    private String content;

    /**
     * 创建通知的管理员ID，如果是系统内置通知则是0.
     */
    @TableField(value = "admin_id")
    private Integer adminId;

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

    public static final String COL_TITLE = "title";

    public static final String COL_CONTENT = "content";

    public static final String COL_ADMIN_ID = "admin_id";

    public static final String COL_ADD_TIME = "add_time";

    public static final String COL_UPDATE_TIME = "update_time";

    public static final String COL_DELETED = "deleted";
}