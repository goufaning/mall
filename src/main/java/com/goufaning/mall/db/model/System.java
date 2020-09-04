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
* $description
* @author goufn
* @date 2020/8/31 2:24 下午
* @version V1.0
*/

/**
 * 系统配置表
 */
@Data
@EqualsAndHashCode(callSuper = true)
@TableName(value = "litemall_system")
public class System extends Model {
    @TableId(value = "id", type = IdType.AUTO)
    private Integer id;

    /**
     * 系统配置名
     */
    @TableField(value = "key_name")
    private String keyName;

    /**
     * 系统配置值
     */
    @TableField(value = "key_value")
    private String keyValue;

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

    public static final String COL_KEY_NAME = "key_name";

    public static final String COL_KEY_VALUE = "key_value";

    public static final String COL_ADD_TIME = "add_time";

    public static final String COL_UPDATE_TIME = "update_time";

    public static final String COL_DELETED = "deleted";
}