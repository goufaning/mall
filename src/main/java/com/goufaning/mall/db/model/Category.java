package com.goufaning.mall.db.model;

/**
 * 分类
 *
 * @author goufn
 * @version V1.0
 * @date 2020-08-13 17:00
 */

import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

import java.io.Serializable;
import java.util.Date;


@Data
@TableName("mall_category")
public class Category implements Serializable {
    private Integer id;

    /**
     * 类目名称
     */
    private String name;

    /**
     * 类目关键字，以JSON数组格式
     */
    private String keywords;

    /**
     * 类目广告语介绍
     */
    private String description;

    /**
     * 父类目ID
     */
    private Integer pid;

    /**
     * 类目图标
     */
    private String iconUrl;

    /**
     * 类目图片
     */
    private String picUrl;

    private String level;

    /**
     * 排序
     */
    private Byte sortOrder;

    /**
     * 创建时间
     */
    private Date addTime;

    /**
     * 更新时间
     */
    private Date updateTime;

    /**
     * 逻辑删除
     */
    private Boolean deleted;

    private static final long serialVersionUID = 1L;
}
