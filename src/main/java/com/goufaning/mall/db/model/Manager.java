package com.goufaning.mall.db.model;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.baomidou.mybatisplus.extension.activerecord.Model;
import com.goufaning.mall.bean.vo.ManagerVo;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import java.util.Date;

/**
* $description
* @author goufn
* @date 2020-07-14 11:20
* @version V1.0
*/

/**
 * 管理员表
 */
@Data
@NoArgsConstructor
@EqualsAndHashCode(callSuper = false)
@TableName(value = "mall_manager")
public class Manager extends Model {
    /**
     * 主键id
     */
    @TableId(value = "id", type = IdType.AUTO)
    private Integer id;

    /**
     * 名称
     */
    @TableField(value = "name")
    private String name;

    /**
     * 密码
     */
    @TableField(value = "password")
    private String password;

    /**
     * 注册时间
     */
    @TableField(value = "create_time")
    private Date createTime;

    /**
     * 角色id
     */
    @TableField(value = "role_id")
    private Integer roleId;

    @TableField(value = "mobile")
    private String mobile;

    @TableField(value = "email")
    private String email;

    /**
     * 1：表示启用 0:表示禁用
     */
    @TableField(value = "state")
    private Integer state;


    /**
     * 角色名称
     */
    @TableField(exist = false)
    private String roleName;


    public Manager(ManagerVo managerVo) {
        this.name = managerVo.getName();
        this.password = managerVo.getPassword();
        this.email = managerVo.getEmail();
        this.mobile = managerVo.getMobile();
    }
}