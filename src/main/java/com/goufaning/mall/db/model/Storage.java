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
 * 文件存储表
 */
@Data
@EqualsAndHashCode(callSuper = true)
@TableName(value = "litemall_storage")
public class Storage extends Model {
    @TableId(value = "id", type = IdType.AUTO)
    private Integer id;

    /**
     * 文件的唯一索引
     */
    @TableField(value = "`key`")
    private String key;

    /**
     * 文件名
     */
    @TableField(value = "`name`")
    private String name;

    /**
     * 文件类型
     */
    @TableField(value = "`type`")
    private String type;

    /**
     * 文件大小
     */
    @TableField(value = "`size`")
    private Integer size;

    /**
     * 文件访问链接
     */
    @TableField(value = "url")
    private String url;

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

    public static final String COL_KEY = "key";

    public static final String COL_NAME = "name";

    public static final String COL_TYPE = "type";

    public static final String COL_SIZE = "size";

    public static final String COL_URL = "url";

    public static final String COL_ADD_TIME = "add_time";

    public static final String COL_UPDATE_TIME = "update_time";

    public static final String COL_DELETED = "deleted";
}