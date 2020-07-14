package com.goufaning.mall.bean.vo;

import com.goufaning.mall.db.model.Permission;
import lombok.Data;

import java.util.List;

/**
 * 菜单 VO
 *
 * @author goufn
 * @version V1.0
 * @date 2020-07-11 15:22
 */
@Data
public class MenuVo {

    private int id;

    private String authName;

    private int level;

    private int parentId;

    private String path;

    private int order;

    List<MenuVo> children;

    public MenuVo(Permission permission) {
        this.id = permission.getId();
        this.authName = permission.getName();
        this.parentId = permission.getParentId();
        this.level = permission.getLevel();
        this.path = permission.getOperation();
        this.order = permission.getOrderNum();
    }
}
