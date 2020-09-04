package com.goufaning.mall.db.service;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.goufaning.mall.db.mapper.UserMapper;
import com.goufaning.mall.db.model.User;
import org.springframework.stereotype.Service;

/**
 * 用户
 *
 * @author goufn
 * @version V1.0
 * @date 2020/9/3 2:26 下午
 */
@Service
public class UserService extends ServiceImpl<UserMapper, User> {
}
