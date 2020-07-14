package com.goufaning.mall.bean.vo;

import lombok.Data;

/**
 * @author goufn
 * @version V1.0
 * @date 2020/7/13 10:37 下午
 */
@Data
public class ManagerVo {

    private int userId;

    private String name;

    private String password;

    private String email;

    private String mobile;

    private int state;
}
