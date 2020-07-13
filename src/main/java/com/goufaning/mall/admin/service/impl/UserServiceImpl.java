package com.goufaning.mall.admin.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.goufaning.mall.admin.service.UserService;
import com.goufaning.mall.common.result.PageResult;
import com.goufaning.mall.common.utils.StringUtils;
import com.goufaning.mall.db.mapper.ManagerMapper;
import com.goufaning.mall.db.mapper.UserMapper;
import com.goufaning.mall.db.model.Manager;
import com.goufaning.mall.db.model.User;
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
public class UserServiceImpl extends ServiceImpl<UserMapper, User> implements UserService {

    @Autowired
    private ManagerMapper managerMapper;

    @Override
    public PageResult getUsersPageByName(String name, int pageNum, int pageSize) {
        Page<User> userPage = new Page<>(pageNum, pageSize);
        LambdaQueryWrapper<User> queryWrapper = new LambdaQueryWrapper<>();
        if(!StringUtils.isBlank(name)) {
            queryWrapper.eq(User::getUsername, name);
        }
        IPage<User> result = baseMapper.selectPage(userPage, queryWrapper);
        PageResult pageResult = new PageResult(result);
        return pageResult;
    }

    /**
     * 加载用户角色
     * @param page
     */
    private void findUserRole(IPage page) {
        List<?> content = page.getRecords();
        for(Object object:content) {
            User user = (User) object;
        }
    }

    @Override
    public void getUser() {
        List<Manager> users = managerMapper.selectList(new LambdaQueryWrapper<Manager>().eq(Manager::getName, "admin"));
    }
}
