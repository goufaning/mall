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
 * 操作日志表
 */
@Data
@EqualsAndHashCode(callSuper = true)
@TableName(value = "litemall_log")
public class Log extends Model {
    @TableId(value = "id", type = IdType.AUTO)
    private Integer id;

    /**
     * 管理员
     */
    @TableField(value = "`admin`")
    private String admin;

    /**
     * 管理员地址
     */
    @TableField(value = "ip")
    private String ip;

    /**
     * 操作分类
     */
    @TableField(value = "`type`")
    private Integer type;

    /**
     * 操作动作
     */
    @TableField(value = "`action`")
    private String action;

    /**
     * 操作状态
     */
    @TableField(value = "`status`")
    private Boolean status;

    /**
     * 操作结果，或者成功消息，或者失败消息
     */
    @TableField(value = "`result`")
    private String result;

    /**
     * 补充信息
     */
    @TableField(value = "`comment`")
    private String comment;

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

    public static final String COL_ADMIN = "admin";

    public static final String COL_IP = "ip";

    public static final String COL_TYPE = "type";

    public static final String COL_ACTION = "action";

    public static final String COL_STATUS = "status";

    public static final String COL_RESULT = "result";

    public static final String COL_COMMENT = "comment";

    public static final String COL_ADD_TIME = "add_time";

    public static final String COL_UPDATE_TIME = "update_time";

    public static final String COL_DELETED = "deleted";
}