package com.goufaning.mall.db.model;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.baomidou.mybatisplus.extension.activerecord.Model;
import java.math.BigDecimal;
import java.util.Date;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
* $description
* @author goufn
* @date 2020/8/31 2:24 下午
* @version V1.0
*/

/**
 * 专题表
 */
@Data
@EqualsAndHashCode(callSuper = true)
@TableName(value = "litemall_topic")
public class Topic extends Model {
    @TableId(value = "id", type = IdType.AUTO)
    private Integer id;

    /**
     * 专题标题
     */
    @TableField(value = "title")
    private String title;

    /**
     * 专题子标题
     */
    @TableField(value = "subtitle")
    private String subtitle;

    /**
     * 专题内容，富文本格式
     */
    @TableField(value = "content")
    private String content;

    /**
     * 专题相关商品最低价
     */
    @TableField(value = "price")
    private BigDecimal price;

    /**
     * 专题阅读量
     */
    @TableField(value = "read_count")
    private String readCount;

    /**
     * 专题图片
     */
    @TableField(value = "pic_url")
    private String picUrl;

    /**
     * 排序
     */
    @TableField(value = "sort_order")
    private Integer sortOrder;

    /**
     * 专题相关商品，采用JSON数组格式
     */
    @TableField(value = "goods")
    private String goods;

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

    public static final String COL_TITLE = "title";

    public static final String COL_SUBTITLE = "subtitle";

    public static final String COL_CONTENT = "content";

    public static final String COL_PRICE = "price";

    public static final String COL_READ_COUNT = "read_count";

    public static final String COL_PIC_URL = "pic_url";

    public static final String COL_SORT_ORDER = "sort_order";

    public static final String COL_GOODS = "goods";

    public static final String COL_ADD_TIME = "add_time";

    public static final String COL_UPDATE_TIME = "update_time";

    public static final String COL_DELETED = "deleted";
}