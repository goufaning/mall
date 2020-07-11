package com.goufaning.mall.admin.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.goufaning.mall.admin.service.UserService;
import com.goufaning.mall.db.mapper.ManagerMapper;
import com.goufaning.mall.db.model.Manager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * 用户信息
 *
 * @author goufn
 * @version V1.0
 * @date 2020-07-10 14:01
 */
@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private ManagerMapper managerMapper;


    @Override
    public void getUser() {
        List<Manager> users = managerMapper.selectList(new LambdaQueryWrapper<Manager>().eq(Manager::getName, "admin"));
        System.err.println(users.toString());
    }
}
