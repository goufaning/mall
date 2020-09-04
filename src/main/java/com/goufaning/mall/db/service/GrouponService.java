package com.goufaning.mall.db.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.goufaning.mall.db.mapper.GrouponMapper;
import com.goufaning.mall.db.model.Goods;
import com.goufaning.mall.db.model.Groupon;
import com.goufaning.mall.db.model.GrouponRules;
import com.goufaning.mall.wx.vo.GrouponRuleVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
/**
* $description
* @author goufn
* @date 2020/8/31 1:26 下午
* @version V1.0
*/
@Service
public class GrouponService extends ServiceImpl<GrouponMapper, Groupon> {
    @Autowired
    private GrouponRulesService grouponRulesService;
    @Autowired
    private GoodsService goodsService;

    public List<GrouponRuleVo> queryList(int current, int size) {
        return queryList(current, size, "add_time", "desc");
    }


    public List<GrouponRuleVo> queryList(int current, int size, String sort, String order) {
        IPage<GrouponRules> grouponRulesPage = grouponRulesService.queryPage(current, size, sort, order);
        IPage<GrouponRuleVo> grouponPage = new Page<>();
        List<GrouponRuleVo> grouponRuleVoList = new ArrayList<>();
        grouponPage.setPages(grouponRulesPage.getPages());
        grouponPage.setCurrent(grouponRulesPage.getCurrent());
        grouponPage.setSize(grouponRulesPage.getSize());
        grouponPage.setTotal(grouponRulesPage.getTotal());
        for (GrouponRules rule : grouponRulesPage.getRecords()) {
            int goodsId = rule.getGoodsId();
            Goods goods = goodsService.getById(goodsId);
            if (goods == null) {
                continue;
            }
            GrouponRuleVo grouponRuleVo = new GrouponRuleVo();
            grouponRuleVo.setId(goods.getId());
            grouponRuleVo.setName(goods.getName());
            grouponRuleVo.setBrief(goods.getBrief());
            grouponRuleVo.setPicUrl(goods.getPicUrl());
            grouponRuleVo.setCounterPrice(goods.getCounterPrice());
            grouponRuleVo.setRetailPrice(goods.getRetailPrice());
            grouponRuleVo.setGrouponPrice(goods.getRetailPrice().subtract(rule.getDiscount()));
            grouponRuleVo.setGrouponDiscount(rule.getDiscount());
            grouponRuleVo.setGrouponMember(rule.getDiscountMember());
            grouponRuleVo.setExpireTime(rule.getExpireTime());
            grouponRuleVoList.add(grouponRuleVo);
        }
        grouponPage.setRecords(grouponRuleVoList);
        return grouponRuleVoList;
    }

}
