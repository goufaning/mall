package com.goufaning.mall.db.service;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.goufaning.mall.db.mapper.CommentMapper;
import com.goufaning.mall.db.model.Comment;
import org.springframework.stereotype.Service;

import java.util.List;

/**
* $description
* @author goufn
* @date 2020/8/31 1:26 下午
* @version V1.0
*/
@Service
public class CommentService extends ServiceImpl<CommentMapper, Comment> {

    public List<Comment> queryGoodsByGid(int goodsId, int current, int size) {
        IPage<Comment> page = new Page<>();
        QueryWrapper<Comment> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq(Comment.COL_VALUE_ID, goodsId);
        queryWrapper.eq(Comment.COL_TYPE, 0);
        queryWrapper.eq(Comment.COL_DELETED, false);
        queryWrapper.orderByDesc(Comment.COL_ADD_TIME);
        return page(page, queryWrapper).getRecords();
    }

}
