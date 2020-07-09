package com.goufaning.mall.db.model;

import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

/**
 * 管理员
 *
 * @author goufn
 * @version V1.0
 * @date 2020-07-09 16:02
 */
@Data
@TableName("mall_manager")
public class Manager {

    private int id;

    private String name;

    private String password;

    private int roleId;

}
