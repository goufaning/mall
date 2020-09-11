package com.goufaning.mall.db.service;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.goufaning.mall.db.mapper.FootprintMapper;
import com.goufaning.mall.db.model.Footprint;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
/**
* $description
* @author goufn
* @date 2020/8/31 1:26 下午
* @version V1.0
*/
@Service
public class FootprintService extends ServiceImpl<FootprintMapper, Footprint> {

    public void add(Footprint footprint) {
        footprint.setAddTime(LocalDateTime.now());
        footprint.setUpdateTime(LocalDateTime.now());
        footprint.insert();
    }

}
