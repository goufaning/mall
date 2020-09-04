package com.goufaning.mall.db.model;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.baomidou.mybatisplus.extension.activerecord.Model;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.util.Date;

/**
* $description
* @author goufn
* @date 2020/8/31 2:24 下午
* @version V1.0
*/

/**
 * 用户表
 */
@Data
@EqualsAndHashCode(callSuper = true)
@TableName(value = "litemall_user")
public class User extends Model {
    @TableId(value = "id", type = IdType.AUTO)
    private Integer id;

    /**
     * 用户名称
     */
    @TableField(value = "username")
    private String username;

    /**
     * 用户密码
     */
    @TableField(value = "`password`")
    private String password;

    /**
     * 性别：0 未知， 1男， 1 女
     */
    @TableField(value = "gender")
    private Byte gender;

    /**
     * 生日
     */
    @TableField(value = "birthday")
    private Date birthday;

    /**
     * 最近一次登录时间
     */
    @TableField(value = "last_login_time")
    private Date lastLoginTime;

    /**
     * 最近一次登录IP地址
     */
    @TableField(value = "last_login_ip")
    private String lastLoginIp;

    /**
     * 0 普通用户，1 VIP用户，2 高级VIP用户
     */
    @TableField(value = "user_level")
    private Byte userLevel;

    /**
     * 用户昵称或网络名称
     */
    @TableField(value = "nickname")
    private String nickname;

    /**
     * 用户手机号码
     */
    @TableField(value = "mobile")
    private String mobile;

    /**
     * 用户头像图片
     */
    @TableField(value = "avatar")
    private String avatar;

    /**
     * 微信登录openid
     */
    @TableField(value = "weixin_openid")
    private String weixinOpenid;

    /**
     * 微信登录会话KEY
     */
    @TableField(value = "session_key")
    private String sessionKey;

    /**
     * 0 可用, 1 禁用, 2 注销
     */
    @TableField(value = "`status`")
    private Byte status;

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

    public static final String COL_USERNAME = "username";

    public static final String COL_PASSWORD = "password";

    public static final String COL_GENDER = "gender";

    public static final String COL_BIRTHDAY = "birthday";

    public static final String COL_LAST_LOGIN_TIME = "last_login_time";

    public static final String COL_LAST_LOGIN_IP = "last_login_ip";

    public static final String COL_USER_LEVEL = "user_level";

    public static final String COL_NICKNAME = "nickname";

    public static final String COL_MOBILE = "mobile";

    public static final String COL_AVATAR = "avatar";

    public static final String COL_WEIXIN_OPENID = "weixin_openid";

    public static final String COL_SESSION_KEY = "session_key";

    public static final String COL_STATUS = "status";

    public static final String COL_ADD_TIME = "add_time";

    public static final String COL_UPDATE_TIME = "update_time";

    public static final String COL_DELETED = "deleted";
}