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
@Data
@EqualsAndHashCode(callSuper=true)
@TableName(value = "admin_role")
public class AdminRole extends Model {
    @TableId(value = "id", type = IdType.AUTO)
    private Short id;

    /**
     * 角色名称
     */
    @TableField(value = "`name`")
    private String name;

    /**
     * 权限ids,1,2,5
     */
    @TableField(value = "permission_ids")
    private String permissionIds;

    /**
     * 控制器-操作,控制器-操作,控制器-操作
     */
    @TableField(value = "controller")
    private String controller;

    @TableField(value = "description")
    private String description;
}