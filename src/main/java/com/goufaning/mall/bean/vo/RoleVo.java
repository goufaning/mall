package com.goufaning.mall.bean.vo;

import com.goufaning.mall.db.model.Role;
import lombok.Data;

import java.util.List;

/**
 * @author goufn
 * @version V1.0
 * @date 2020-07-14 15:01
 */
@Data
public class RoleVo {

    private int id;

    private String roleName;

    private String roleDesc;

    private List<MenuVo> children;

    public RoleVo(Role role) {
        this.id = role.getId();
        this.roleName = role.getName();
        this.roleDesc = role.getDescription();
    }
}
