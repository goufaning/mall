package com.goufaning.mall.db.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.goufaning.mall.db.model.User;
import org.apache.ibatis.annotations.Mapper;

/**
 * $description
 *
 * @author goufn
 * @version V1.0
 * @date 2020-07-11 11:40
 */
@Mapper
public interface UserMapper extends BaseMapper<User> {
}