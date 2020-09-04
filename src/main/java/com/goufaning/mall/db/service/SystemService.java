package com.goufaning.mall.db.service;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.UpdateWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.goufaning.mall.db.mapper.SystemMapper;
import com.goufaning.mall.db.model.System;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
/**
* $description
* @author goufn
* @date 2020/8/31 1:26 下午
* @version V1.0
*/
@Service
public class SystemService extends ServiceImpl<SystemMapper, System> {

    public Map<String, String> queryAll() {
        QueryWrapper<System> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq(System.COL_DELETED, false);
        return getKeyValueMap(queryWrapper);
    }

    public Map<String, String> listMail() {
        QueryWrapper<System> queryWrapper = new QueryWrapper<>();
        queryWrapper.like(System.COL_KEY_NAME, "litemall_mall_%").eq(System.COL_DELETED, false);
        return getKeyValueMap(queryWrapper);
    }

    private Map<String, String> getKeyValueMap(QueryWrapper<System> queryWrapper) {
        List<System> systemList = baseMapper.selectList(queryWrapper);
        Map<String, String> data = new HashMap<>();
        for(System system : systemList){
            data.put(system.getKeyName(), system.getKeyValue());
        }
        return data;
    }

    public Map<String, String> listWx() {
        QueryWrapper<System> queryWrapper = new QueryWrapper<>();
        queryWrapper.like(System.COL_KEY_NAME, "itemall_wx_%").eq(System.COL_DELETED, false);
        return getKeyValueMap(queryWrapper);
    }

    public Map<String, String> listOrder() {
        QueryWrapper<System> queryWrapper = new QueryWrapper<>();
        queryWrapper.like(System.COL_KEY_NAME, "litemall_order_%").eq(System.COL_DELETED, false);
        return getKeyValueMap(queryWrapper);
    }

    public Map<String, String> listExpress() {
        QueryWrapper<System> queryWrapper = new QueryWrapper<>();
        queryWrapper.like(System.COL_KEY_NAME, "litemall_express_%").eq(System.COL_DELETED, false);
        return getKeyValueMap(queryWrapper);
    }

    public void updateConfig(Map<String, String> data) {
        for (Map.Entry<String, String> entry : data.entrySet()) {
            UpdateWrapper<System> queryWrapper = new UpdateWrapper<>();
            queryWrapper.eq(System.COL_KEY_NAME, entry.getKey()).eq(System.COL_DELETED, false);
            System system = new System();
            system.setKeyName(entry.getKey());
            system.setKeyValue(entry.getValue());
            system.setUpdateTime(LocalDateTime.now());
            baseMapper.update(system, queryWrapper);
        }

    }

    public void addConfig(String key, String value) {
        System system = new System();
        system.setKeyName(key);
        system.setKeyValue(value);
        system.setAddTime(LocalDateTime.now());
        system.setUpdateTime(LocalDateTime.now());
        system.insert();
    }

}
