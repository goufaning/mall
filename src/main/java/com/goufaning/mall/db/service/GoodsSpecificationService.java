package com.goufaning.mall.db.service;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.goufaning.mall.db.mapper.GoodsSpecificationMapper;
import com.goufaning.mall.db.model.GoodsSpecification;
import lombok.Data;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
* $description
* @author goufn
* @date 2020/8/31 1:26 下午
* @version V1.0
*/
@Service
public class GoodsSpecificationService extends ServiceImpl<GoodsSpecificationMapper, GoodsSpecification> {

    public List<GoodsSpecification> queryByGid(int goodsId) {
        QueryWrapper<GoodsSpecification> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq(GoodsSpecification.COL_GOODS_ID, goodsId);
        queryWrapper.eq(GoodsSpecification.COL_DELETED, false);
        return list(queryWrapper);
    }

    public void deleteByGid(int goodsId) {
        QueryWrapper<GoodsSpecification> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq(GoodsSpecification.COL_GOODS_ID, goodsId);
        remove(queryWrapper);
    }

    /**
     * [
     * {
     * name: '',
     * valueList: [ {}, {}]
     * },
     * {
     * name: '',
     * valueList: [ {}, {}]
     * }
     * ]
     *
     * @param id
     * @return
     */
    public Object getSpecificationVoList(Integer id) {
        List<GoodsSpecification> goodsSpecificationList = queryByGid(id);

        Map<String, VO> map = new HashMap<>();
        List<VO> specificationVoList = new ArrayList<>();

        for (GoodsSpecification goodsSpecification : goodsSpecificationList) {
            String specification = goodsSpecification.getSpecification();
            VO goodsSpecificationVo = map.get(specification);
            if (goodsSpecificationVo == null) {
                goodsSpecificationVo = new VO();
                goodsSpecificationVo.setName(specification);
                List<GoodsSpecification> valueList = new ArrayList<>();
                valueList.add(goodsSpecification);
                goodsSpecificationVo.setValueList(valueList);
                map.put(specification, goodsSpecificationVo);
                specificationVoList.add(goodsSpecificationVo);
            } else {
                List<GoodsSpecification> valueList = goodsSpecificationVo.getValueList();
                valueList.add(goodsSpecification);
            }
        }
        return specificationVoList;
    }

    @Data
    private class VO {
        private String name;
        private List<GoodsSpecification> valueList;
    }

}
