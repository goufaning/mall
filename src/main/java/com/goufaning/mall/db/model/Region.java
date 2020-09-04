package com.goufaning.mall.db.model;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.baomidou.mybatisplus.extension.activerecord.Model;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
* $description
* @author goufn
* @date 2020/8/31 2:24 下午
* @version V1.0
*/

/**
 * 行政区域表
 */
@Data
@EqualsAndHashCode(callSuper = true)
@TableName(value = "litemall_region")
public class Region extends Model {
    @TableId(value = "id", type = IdType.AUTO)
    private Integer id;

    /**
     * 行政区域父ID，例如区县的pid指向市，市的pid指向省，省的pid则是0
     */
    @TableField(value = "pid")
    private Integer pid;

    /**
     * 行政区域名称
     */
    @TableField(value = "`name`")
    private String name;

    /**
     * 行政区域类型，如如1则是省， 如果是2则是市，如果是3则是区县
     */
    @TableField(value = "`type`")
    private Byte type;

    /**
     * 行政区域编码
     */
    @TableField(value = "code")
    private Integer code;

    public static final String COL_ID = "id";

    public static final String COL_PID = "pid";

    public static final String COL_NAME = "name";

    public static final String COL_TYPE = "type";

    public static final String COL_CODE = "code";
}