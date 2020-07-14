package com.goufaning.mall.db.service;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.goufaning.mall.common.result.PageResult;
import com.goufaning.mall.common.utils.StringUtils;
import com.goufaning.mall.db.mapper.ManagerMapper;
import com.goufaning.mall.db.model.Manager;
import com.goufaning.mall.db.model.Role;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
* $description
* @author goufn
* @date 2020-07-11 11:41
* @version V1.0
*/
@Service
public class ManagerService extends ServiceImpl<ManagerMapper, Manager> {

    @Autowired
    private RoleService roleService;


    /**
     * 通过 name 查找
     * @param name
     * @return
     */
    public Manager findByName(String name) {
        LambdaQueryWrapper<Manager> wrapper = new LambdaQueryWrapper<>();
        wrapper.eq(Manager::getName, name);
        return this.getOne(wrapper);
    }

    /**
     * 分页查询
     * @param name  可以为空
     * @param pageNum
     * @param pageSize
     * @return
     */
    public PageResult getPageByName(String name, int pageNum, int pageSize) {
        Page<Manager> userPage = new Page<>(pageNum, pageSize);
        LambdaQueryWrapper<Manager> queryWrapper = new LambdaQueryWrapper<>();
        if(!StringUtils.isBlank(name)) {
            queryWrapper.like(Manager::getName, name);
        }
        IPage<Manager> result = baseMapper.selectPage(userPage, queryWrapper);
        setRoleName(result);
        PageResult pageResult = new PageResult(result);
        return pageResult;
    }

    /**
     * 加载用户角色
     * @param page
     */
    private void setRoleName(IPage page) {
        List<?> content = page.getRecords();
        for(Object object:content) {
            Manager manager = (Manager) object;
            int roleId = manager.getRoleId();
            Role role = roleService.getById(roleId);
            if (role != null) {
                manager.setRoleName(role.getName());
            }
        }
    }

}
