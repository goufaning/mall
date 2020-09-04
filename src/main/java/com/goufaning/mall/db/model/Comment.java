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
 * 评论表
 */
@Data
@EqualsAndHashCode(callSuper = true)
@TableName(value = "litemall_comment")
public class Comment extends Model {
    @TableId(value = "id", type = IdType.AUTO)
    private Integer id;

    /**
     * 如果type=0，则是商品评论；如果是type=1，则是专题评论。
     */
    @TableField(value = "value_id")
    private Integer valueId;

    /**
     * 评论类型，如果type=0，则是商品评论；如果是type=1，则是专题评论；
     */
    @TableField(value = "`type`")
    private Byte type;

    /**
     * 评论内容
     */
    @TableField(value = "content")
    private String content;

    /**
     * 管理员回复内容
     */
    @TableField(value = "admin_content")
    private String adminContent;

    /**
     * 用户表的用户ID
     */
    @TableField(value = "user_id")
    private Integer userId;

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
     * 评分， 1-5
     */
    @TableField(value = "star")
    private Short star;

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

    public static final String COL_VALUE_ID = "value_id";

    public static final String COL_TYPE = "type";

    public static final String COL_CONTENT = "content";

    public static final String COL_ADMIN_CONTENT = "admin_content";

    public static final String COL_USER_ID = "user_id";

    public static final String COL_HAS_PICTURE = "has_picture";

    public static final String COL_PIC_URLS = "pic_urls";

    public static final String COL_STAR = "star";

    public static final String COL_ADD_TIME = "add_time";

    public static final String COL_UPDATE_TIME = "update_time";

    public static final String COL_DELETED = "deleted";
}