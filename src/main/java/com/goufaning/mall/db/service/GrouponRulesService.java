package com.goufaning.mall.db.service;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.goufaning.mall.db.mapper.GrouponRulesMapper;
import com.goufaning.mall.db.model.GrouponRules;
import com.goufaning.mall.db.util.EnumSortType;
import com.goufaning.mall.db.util.GrouponConstant;
import org.springframework.stereotype.Service;

import java.util.List;
/**
* $description
* @author goufn
* @date 2020/8/31 1:26 下午
* @version V1.0
*/
@Service
public class GrouponRulesService extends ServiceImpl<GrouponRulesMapper, GrouponRules> {

    /**
     * 获取首页团购规则列表
     * @param current
     * @param size
     * @return
     */
    public List<GrouponRules> queryList(Integer current, Integer size) {
        return queryList(current, size, "add_time", "desc");
    }

    public List<GrouponRules> queryList(Integer current, Integer size, String sort, String order) {
        return queryPage(current, size, sort, order).getRecords();
    }

    public IPage<GrouponRules> queryPage(int current, int size, String sort, String order) {
        IPage<GrouponRules> page = new Page<>(current, size);
        QueryWrapper<GrouponRules> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq(GrouponRules.COL_STATUS, GrouponConstant.STATUS_ON);
        queryWrapper.eq(GrouponRules.COL_DELETED, false);
        if (order.equalsIgnoreCase(EnumSortType.DESC.type())) {
            queryWrapper.orderByDesc(sort);
        } else {
            queryWrapper.orderByAsc(sort);
        }
        return baseMapper.selectPage(page, queryWrapper);
    }

    /**
     * 查询某个商品关联的团购规则
     *
     * @param goodsId
     * @return
     */
    public List<GrouponRules> queryByGoodsId(int goodsId) {
        QueryWrapper<GrouponRules> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq(GrouponRules.COL_GOODS_ID, goodsId);
        queryWrapper.eq(GrouponRules.COL_STATUS, GrouponConstant.RULE_STATUS_ON);
        queryWrapper.eq(GrouponRules.COL_DELETED, false);
        return list(queryWrapper);
    }

}
