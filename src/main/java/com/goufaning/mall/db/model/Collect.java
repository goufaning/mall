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
 * 收藏表
 */
@Data
@EqualsAndHashCode(callSuper = true)
@TableName(value = "litemall_collect")
public class Collect extends Model {
    @TableId(value = "id", type = IdType.AUTO)
    private Integer id;

    /**
     * 用户表的用户ID
     */
    @TableField(value = "user_id")
    private Integer userId;

    /**
     * 如果type=0，则是商品ID；如果type=1，则是专题ID
     */
    @TableField(value = "value_id")
    private Integer valueId;

    /**
     * 收藏类型，如果type=0，则是商品ID；如果type=1，则是专题ID
     */
    @TableField(value = "`type`")
    private Byte type;

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

    public static final String COL_VALUE_ID = "value_id";

    public static final String COL_TYPE = "type";

    public static final String COL_ADD_TIME = "add_time";

    public static final String COL_UPDATE_TIME = "update_time";

    public static final String COL_DELETED = "deleted";
}