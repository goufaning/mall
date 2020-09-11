package com.goufaning.mall.wx.web;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.goufaning.mall.common.result.CommonResult;
import com.goufaning.mall.common.utils.WebUtils;
import com.goufaning.mall.core.util.ResponseUtil;
import com.goufaning.mall.db.model.Goods;
import com.goufaning.mall.db.model.Topic;
import com.goufaning.mall.db.service.GoodsService;
import com.goufaning.mall.db.service.TopicService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.constraints.NotNull;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 专题
 * @author goufn
 * @version V1.0
 * @date 2020/9/9 1:20 下午
 */
@RestController
@RequestMapping("/wx/topic")
@Validated
public class WxTopicController {

    @Autowired
    private TopicService topicService;
    @Autowired
    private GoodsService goodsService;

    /**
     * 专题列表
     *
     * @param page 分页页数
     * @param limit 分页大小
     * @return 专题列表
     */
    @GetMapping("list")
    public Object list(@RequestParam(defaultValue = "1") Integer page,
                       @RequestParam(defaultValue = "10") Integer limit,
                       @RequestParam(defaultValue = "add_time") String sort,
                       @RequestParam(defaultValue = "desc") String order) {
        IPage<Topic> topicList = topicService.queryList(page, limit, sort, order);
        return ResponseUtil.okList(topicList);
    }

    /**
     * 专题详情
     *
     * @param id 专题ID
     * @return 专题详情
     */
    @GetMapping("detail")
    public Object detail(@NotNull Integer id) {
        Topic topic = topicService.findById(id);
        List<Goods> goods = new ArrayList<>();
        List<Integer> goodids = WebUtils.stringToList(topic.getGoods());
        for (Integer i : goodids) {
            Goods good = goodsService.findByIdVO(i);
            if (null != good) {
                goods.add(good);
            }
        }
        Map<String, Object> entity = new HashMap<String, Object>();
        entity.put("topic", topic);
        entity.put("goods", goods);
        return CommonResult.success(entity);
    }

    /**
     * 相关专题
     *
     * @param id 专题ID
     * @return 相关专题
     */
    @GetMapping("related")
    public Object related(@NotNull Integer id) {
        IPage<Topic> topicRelatedList = topicService.queryRelatedList(id, 0, 4);
        return ResponseUtil.okList(topicRelatedList);
    }
}
