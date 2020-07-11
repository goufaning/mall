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
 *
 * @author goufn
 * @version V1.0
 * @date 2020-07-11 15:47
 */
@Data
@EqualsAndHashCode(callSuper = true)
@TableName(value = "mall_role")
public class Role extends Model {

    @TableId(value = "id", type = IdType.AUTO)
    private Short id;

    /**
     * 角色名称
     */
    @TableField(value = "name")
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