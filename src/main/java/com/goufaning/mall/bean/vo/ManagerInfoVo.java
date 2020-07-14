package com.goufaning.mall.bean.vo;

import com.goufaning.mall.db.model.Manager;
import lombok.Data;

import java.util.Date;

/**
 * 传给view的manager信息
 *
 * @author goufn
 * @version V1.0
 * @date 2020-07-14 10:29
 */
@Data
public class ManagerInfoVo {

    private int id;

    private String name;

    private String mobile;

    private int type;

    private String openId;

    private String email;

    private Date createTime;

    private Date modifyTime;

    private boolean isDelete;

    private boolean isActive;

    public ManagerInfoVo(Manager manager) {
        this.id = manager.getId();
        this.name = manager.getName();
        this.mobile = manager.getMobile();
        this.type = 1;
        this.openId = "";
        this.email = manager.getEmail();
        this.createTime = manager.getCreateTime();
        this.modifyTime = null;
        this.isDelete = false;
        this.isActive = manager.getState() == 1;
    }


}
