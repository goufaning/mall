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
* @date 2020/8/31 11:46 上午
* @version V1.0
*/
/**
    * 收货地址表
    */
@Data
@EqualsAndHashCode(callSuper=true)
@TableName(value = "mall_address")
public class Address extends Model {
    @TableId(value = "id", type = IdType.AUTO)
    private Integer id;

    /**
     * 收货人名称
     */
    @TableField(value = "`name`")
    private String name;

    /**
     * 用户表的用户ID
     */
    @TableField(value = "user_id")
    private Integer userId;

    /**
     * 行政区域表的省ID
     */
    @TableField(value = "province")
    private String province;

    /**
     * 行政区域表的市ID
     */
    @TableField(value = "city")
    private String city;

    /**
     * 行政区域表的区县ID
     */
    @TableField(value = "county")
    private String county;

    /**
     * 详细收货地址
     */
    @TableField(value = "address_detail")
    private String addressDetail;

    /**
     * 地区编码
     */
    @TableField(value = "area_code")
    private String areaCode;

    /**
     * 邮政编码
     */
    @TableField(value = "postal_code")
    private String postalCode;

    /**
     * 手机号码
     */
    @TableField(value = "tel")
    private String tel;

    /**
     * 是否默认地址
     */
    @TableField(value = "is_default")
    private Boolean isDefault;

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
}