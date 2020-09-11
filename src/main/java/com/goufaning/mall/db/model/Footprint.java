package com.goufaning.mall.db.model;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.baomidou.mybatisplus.extension.activerecord.Model;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.time.LocalDateTime;

/**
* 用户浏览足迹表
* @author goufn
* @date 2020/8/31 2:24 下午
* @version V1.0
*/
@Data
@EqualsAndHashCode(callSuper = true)
@TableName(value = "litemall_footprint")
public class Footprint extends Model {
    @TableId(value = "id", type = IdType.AUTO)
    private Integer id;

    /**
     * 用户表的用户ID
     */
    @TableField(value = "user_id")
    private Integer userId;

    /**
     * 浏览商品ID
     */
    @TableField(value = "goods_id")
    private Integer goodsId;

    /**
     * 创建时间
     */
    @TableField(value = "add_time")
    private LocalDateTime addTime;

    /**
     * 更新时间
     */
    @TableField(value = "update_time")
    private LocalDateTime updateTime;

    /**
     * 逻辑删除
     */
    @TableField(value = "deleted")
    private Boolean deleted;

    public static final String COL_ID = "id";

    public static final String COL_USER_ID = "user_id";

    public static final String COL_GOODS_ID = "goods_id";

    public static final String COL_ADD_TIME = "add_time";

    public static final String COL_UPDATE_TIME = "update_time";

    public static final String COL_DELETED = "deleted";
}