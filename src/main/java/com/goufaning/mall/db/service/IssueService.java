package com.goufaning.mall.db.service;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.goufaning.mall.db.mapper.IssueMapper;
import com.goufaning.mall.db.model.Issue;
import com.goufaning.mall.db.util.EnumSortType;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.util.List;

/**
* $description
* @author goufn
* @date 2020/8/31 1:26 下午
* @version V1.0
*/
@Service
public class IssueService extends ServiceImpl<IssueMapper, Issue> {

    public List<Issue> querySelective(String question, int current, int size, String sort, String order) {
        QueryWrapper<Issue> queryWrapper = new QueryWrapper<>();
        if (!StringUtils.isEmpty(question)) {
            queryWrapper.like(Issue.COL_QUESTION, question);
        }
        queryWrapper.eq(Issue.COL_DELETED, false);
        if (!StringUtils.isEmpty(sort) && !StringUtils.isEmpty(order)) {
            if (order.equalsIgnoreCase(EnumSortType.DESC.type())) {
                queryWrapper.orderByDesc(sort);
            } else {
                queryWrapper.orderByAsc(sort);
            }
        }
        IPage<Issue> page = new Page<>(current, size);
        return page(page, queryWrapper).getRecords();
    }

}
