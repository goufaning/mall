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
* @date 2020/8/31 1:35 下午
* @version V1.0
*/
/**
    * 权限表
    */
@Data
@EqualsAndHashCode(callSuper=true)
@TableName(value = "admin_permission")
public class AdminPermission extends Model {
    @TableId(value = "id", type = IdType.AUTO)
    private Integer id;

    /**
     * 权限名称
     */
    @TableField(value = "`name`")
    private String name;

    /**
     * 父id
     */
    @TableField(value = "parent_id")
    private Short parentId;

    /**
     * 控制器
     */
    @TableField(value = "controller")
    private String controller;

    /**
     * 操作方法
     */
    @TableField(value = "`operation`")
    private String operation;

    /**
     * 权限等级
     */
    @TableField(value = "`level`")
    private Integer level;

    /**
     * 排序优先级
     */
    @TableField(value = "order_num")
    private Short orderNum;
}