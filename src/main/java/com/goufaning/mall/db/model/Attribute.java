package com.goufaning.mall.db.model;

import com.baomidou.mybatisplus.annotation.TableName;
import com.baomidou.mybatisplus.extension.activerecord.Model;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.util.Date;

/**
 * mall_attribute
 * @author 
 */
@Data
@TableName("mall_attribute")
@EqualsAndHashCode(callSuper = false)
public class Attribute extends Model {
    /**
     * 主键id
     */
    private Short id;

    /**
     * 属性名称
     */
    private String name;

    /**
     * 外键，类型id
     */
    private Short catId;

    /**
     * 1:only:输入框(唯一)  2:many:后台下拉列表/前台单选框
     */
    private Short sel;

    /**
     * 1:manual:手工录入  2:list:从列表选择
     */
    private String type;

    /**
     * 可选值列表信息,例如颜色：白色,红色,绿色,多个可选值通过逗号分隔
     */
    private String value;

    /**
     * 删除时间标志
     */
    private Date deleteTime;

    private static final long serialVersionUID = 1L;
}