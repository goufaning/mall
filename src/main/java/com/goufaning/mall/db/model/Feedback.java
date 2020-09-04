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
 * 意见反馈表
 */
@Data
@EqualsAndHashCode(callSuper = true)
@TableName(value = "litemall_feedback")
public class Feedback extends Model {
    @TableId(value = "id", type = IdType.AUTO)
    private Integer id;

    /**
     * 用户表的用户ID
     */
    @TableField(value = "user_id")
    private Integer userId;

    /**
     * 用户名称
     */
    @TableField(value = "username")
    private String username;

    /**
     * 手机号
     */
    @TableField(value = "mobile")
    private String mobile;

    /**
     * 反馈类型
     */
    @TableField(value = "feed_type")
    private String feedType;

    /**
     * 反馈内容
     */
    @TableField(value = "content")
    private String content;

    /**
     * 状态
     */
    @TableField(value = "`status`")
    private Integer status;

    /**
     * 是否含有图片
     */
    @TableField(value = "has_picture")
    private Boolean hasPicture;

    /**
     * 图片地址列表，采用JSON数组格式
     */
    @TableField(value = "pic_urls")
    private String picUrls;

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

    public static final String COL_USER_ID = "user_id";

    public static final String COL_USERNAME = "username";

    public static final String COL_MOBILE = "mobile";

    public static final String COL_FEED_TYPE = "feed_type";

    public static final String COL_CONTENT = "content";

    public static final String COL_STATUS = "status";

    public static final String COL_HAS_PICTURE = "has_picture";

    public static final String COL_PIC_URLS = "pic_urls";

    public static final String COL_ADD_TIME = "add_time";

    public static final String COL_UPDATE_TIME = "update_time";

    public static final String COL_DELETED = "deleted";
}