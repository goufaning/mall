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
* @date 2020-07-11 11:40
* @version V1.0
*/

/**
 * 会员表
 */
@Data
@EqualsAndHashCode(callSuper = true)
@TableName(value = "mall_user")
public class User extends Model<User> {
    /**
     * 自增id
     */
    @TableId(value = "user_id", type = IdType.AUTO)
    private Integer userId;

    /**
     * 登录名
     */
    @TableField(value = "username")
    private String username;

    /**
     * qq官方唯一编号信息
     */
    @TableField(value = "qq_open_id")
    private String qqOpenId;

    /**
     * 登录密码
     */
    @TableField(value = "password")
    private String password;

    /**
     * 邮箱
     */
    @TableField(value = "user_email")
    private String userEmail;

    /**
     * 新用户注册邮件激活唯一校验码
     */
    @TableField(value = "user_email_code")
    private String userEmailCode;

    /**
     * 新用户是否已经通过邮箱激活帐号
     */
    @TableField(value = "is_active")
    private Integer isActive;

    /**
     * 性别
     */
    @TableField(value = "user_sex")
    private String userSex;

    /**
     * qq
     */
    @TableField(value = "user_qq")
    private String userQq;

    /**
     * 手机
     */
    @TableField(value = "user_tel")
    private String userTel;

    /**
     * 学历
     */
    @TableField(value = "user_xueli")
    private String userXueli;

    /**
     * 爱好
     */
    @TableField(value = "user_hobby")
    private String userHobby;

    /**
     * 简介
     */
    @TableField(value = "user_introduce")
    private String userIntroduce;

    /**
     * 创建时间
     */
    @TableField(value = "create_time")
    private Integer createTime;

    /**
     * 修改时间
     */
    @TableField(value = "update_time")
    private Integer updateTime;


}