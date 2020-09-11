package com.goufaning.mall.wx.vo;

import com.goufaning.mall.db.model.GoodsSpecification;
import lombok.Data;

import java.util.List;

/**
 * @author goufn
 * @version V1.0
 * @date 2020/9/7 10:47 上午
 */
@Data
public class GoodsSpecificationVO {

    private String name;
    private List<GoodsSpecification> valueList;
}
