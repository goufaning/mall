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
 * 通知管理员表
 */
@Data
@EqualsAndHashCode(callSuper = true)
@TableName(value = "litemall_notice_admin")
public class NoticeAdmin extends Model {
    @TableId(value = "id", type = IdType.AUTO)
    private Integer id;

    /**
     * 通知ID
     */
    @TableField(value = "notice_id")
    private Integer noticeId;

    /**
     * 通知标题
     */
    @TableField(value = "notice_title")
    private String noticeTitle;

    /**
     * 接收通知的管理员ID
     */
    @TableField(value = "admin_id")
    private Integer adminId;

    /**
     * 阅读时间，如果是NULL则是未读状态
     */
    @TableField(value = "read_time")
    private Date readTime;

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

    public static final String COL_NOTICE_ID = "notice_id";

    public static final String COL_NOTICE_TITLE = "notice_title";

    public static final String COL_ADMIN_ID = "admin_id";

    public static final String COL_READ_TIME = "read_time";

    public static final String COL_ADD_TIME = "add_time";

    public static final String COL_UPDATE_TIME = "update_time";

    public static final String COL_DELETED = "deleted";
}