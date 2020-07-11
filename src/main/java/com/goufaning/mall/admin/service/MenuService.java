package com.goufaning.mall.admin.service;

import com.goufaning.mall.bean.vo.MenuVo;

import java.util.List;

/**
 * @author goufn
 * @version V1.0
 * @date 2020-07-11 15:19
 */
public interface MenuService {

    List<MenuVo> getMenuTree(String username);

}
