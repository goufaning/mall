package com.goufaning.mall.bean.vo;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.goufaning.mall.db.model.GoodsCate;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

/**
 * 分类Vo
 *
 * @author goufn
 * @version V1.0
 * @date 2020-07-15 15:27
 */
@Data
@NoArgsConstructor
public class CategoriesVo {

    private int id;

    private String name;

    private int parentId;

    private int level;

    private boolean deleted;

    @JsonInclude(JsonInclude.Include.NON_NULL)
    private List<CategoriesVo> children;

    public CategoriesVo(GoodsCate goodsCate) {
        this.id = goodsCate.getId();
        this.name = goodsCate.getCatName();
        this.parentId = goodsCate.getParentId();
        this.deleted = goodsCate.getIsShow() != 1;
    }
}
