package com.goufaning.mall.db.service;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.goufaning.mall.db.mapper.ManagerMapper;
import com.goufaning.mall.db.model.Manager;
import org.springframework.stereotype.Service;
/**
* $description
* @author goufn
* @date 2020-07-11 11:41
* @version V1.0
*/
@Service
public class ManagerService extends ServiceImpl<ManagerMapper, Manager> {


    public Manager findByName(String name) {
        LambdaQueryWrapper<Manager> wrapper = new LambdaQueryWrapper<>();
        wrapper.eq(Manager::getName, name);
        return this.getOne(wrapper);
    }

}
