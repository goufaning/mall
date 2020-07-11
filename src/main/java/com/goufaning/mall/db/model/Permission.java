package com.goufaning.mall.db.model;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.baomidou.mybatisplus.extension.activerecord.Model;
import lombok.Data;

/**
* 权限表
* @author goufn
* @date 2020-07-11 11:28
* @version V1.0
*/
@Data
@TableName(value = "mall_permission")
public class Permission extends Model<Permission> {
    @TableId(value = "id", type = IdType.AUTO)
    private Integer id;

    /**
     * 权限名称
     */
    @TableField(value = "name")
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
    @TableField(value = "operation")
    private String operation;

    /**
     * 权限等级
     */
    @TableField(value = "level")
    private Integer level;
}