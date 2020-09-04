package com.goufaning.mall.db.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.goufaning.mall.db.model.Order;
import org.apache.ibatis.annotations.Mapper;

/**
 * $description
 *
 * @author goufn
 * @version V1.0
 * @date 2020/8/31 2:24 下午
 */
@Mapper
public interface OrderMapper extends BaseMapper<Order> {
}