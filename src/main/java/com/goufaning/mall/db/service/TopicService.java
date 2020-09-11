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
        return queryList(current, size, "add_time", "desc").getRecords();
    }

    public IPage<Topic> queryList(int current, int size, String sort, String order) {
        QueryWrapper<Topic> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq(Topic.COL_DELETED, false);
        if (order.equalsIgnoreCase(EnumSortType.DESC.type())) {
            queryWrapper.orderByDesc(sort);
        } else {
            queryWrapper.orderByAsc(sort);
        }
        IPage<Topic> page = new Page<>(current, size);
        return baseMapper.selectPage(page, queryWrapper);
    }

    public IPage<Topic> queryRelatedList(Integer id, int current, int size) {
        QueryWrapper<Topic> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq(Topic.COL_ID, id);
        queryWrapper.eq(Topic.COL_DELETED, false);
        List<Topic> topics = list(queryWrapper);
        if (topics.size() == 0) {
            return queryList(current, size, "add_time", "desc");
        }
        Topic topic = topics.get(0);
        queryWrapper = new QueryWrapper<>();
        queryWrapper.ne(Topic.COL_ID, topic.getId());
        queryWrapper.eq(Topic.COL_DELETED, false);
        IPage<Topic> page = new Page<>(current, size);
        IPage<Topic> relateds = page(page, queryWrapper);
        if (relateds.getSize() != 0) {
            return relateds;
        }

        return queryList(current, size, "add_time", "desc");
    }

    public Topic findById(Integer id) {
        QueryWrapper<Topic> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq(Topic.COL_ID, id);
        queryWrapper.eq(Topic.COL_DELETED, false);
        return getOne(queryWrapper);
    }

}
