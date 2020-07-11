package com.goufaning.mall.db.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.goufaning.mall.db.model.Manager;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

/**
 * 管理员数据管理
 *
 * @author goufn
 * @version V1.0
 * @date 2020-07-09 16:02
 */
@Mapper
@Repository
public interface ManagerMapper extends BaseMapper<Manager> {
}
