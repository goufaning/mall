package com.goufaning.mall.db.service;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.goufaning.mall.db.mapper.TopicMapper;
import com.goufaning.mall.db.model.Topic;
import com.goufaning.mall.db.util.EnumSortType;
import org.springframework.stereotype.Service;

import java.util.List;
/**
* $description
* @author goufn
* @date 2020/8/31 1:26 下午
* @version V1.0
*/
@Service
public class TopicService extends ServiceImpl<TopicMapper, Topic> {

    public List<Topic> queryList(int current, int size) {
        return queryList(current, size, "add_time", "desc");
    }

    public List<Topic> queryList(int current, int size, String sort, String order) {
        QueryWrapper<Topic> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq(Topic.COL_DELETED, false);
        if (order.equalsIgnoreCase(EnumSortType.DESC.type())) {
            queryWrapper.orderByDesc(sort);
        } else {
            queryWrapper.orderByAsc(sort);
        }
        IPage<Topic> page = new Page<>(current, size);
        return baseMapper.selectPage(page, queryWrapper).getRecords();
    }

}
