package com.goufaning.mall.wx.web;

import com.goufaning.mall.common.result.CommonResult;
import com.goufaning.mall.db.model.Category;
import com.goufaning.mall.db.model.Goods;
import com.goufaning.mall.db.service.*;
import com.goufaning.mall.system.SystemConfig;
import com.goufaning.mall.wx.cache.HomeCacheManager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.FutureTask;

/**
 * 主页
 *
 * @author goufn
 * @version V1.0
 * @date 2020-08-13 15:23
 */
@RestController
@RequestMapping("/wx/home")
public class WxHomeController {
    @Autowired
    private AdService adService;

    @Autowired
    private CategoryService categoryService;
    @Autowired
    private CouponService couponService;
    @Autowired
    private GoodsService goodsService;
    @Autowired
    private BrandService brandService;
    @Autowired
    private TopicService topicService;
    @Autowired
    private GrouponService grouponService;

    @GetMapping("/index")
    public CommonResult<Map<String, Object>> index(Integer userId) {
        //优先从缓存中读取
        if (HomeCacheManager.hasData(HomeCacheManager.INDEX)) {
            return CommonResult.success(HomeCacheManager.getCacheData(HomeCacheManager.INDEX));
        }
        ExecutorService executorService = Executors.newFixedThreadPool(10);
        // 图片轮播
        Callable<List> bannerListCallable = () -> adService.queryIndex();
        // 分类列表
        Callable<List> channelListCallable = () -> categoryService.queryChannel();
        // 优惠卷列表
        Callable<List> couponListCallable;
        if (userId == null) {
            couponListCallable = () -> couponService.queryList(0, 3);
        } else {
            couponListCallable = () -> couponService.queryAvailableList(userId, 0, 3);
        }
        // 新品推荐
        Callable<List> newGoodsListCallable = () -> goodsService.queryByNew(0, SystemConfig.getNewLimit());
        // 热卖物品
        Callable<List> hotGoodsListCallable = () -> goodsService.queryByHot(0, SystemConfig.getHotLimit());
        // 品牌方
        Callable<List> brandListCallable = () -> brandService.query(0, SystemConfig.getBrandLimit());
        // 专题精选
        Callable<List> topicListCallable = () -> topicService.queryList(0, SystemConfig.getTopicLimit());
        // 团购专区
        Callable<List> grouponListCallable = () -> grouponService.queryList(0, 5);
        // 底部物品分类
        Callable<List> floorGoodsListCallable = this::getCategoryList;

        FutureTask<List> bannerTask = new FutureTask<>(bannerListCallable);
        FutureTask<List> channelTask = new FutureTask<>(channelListCallable);
        FutureTask<List> couponTask = new FutureTask<>(couponListCallable);
        FutureTask<List> newGoodsTask = new FutureTask<>(newGoodsListCallable);
        FutureTask<List> hotGoodsTask = new FutureTask<>(hotGoodsListCallable);
        FutureTask<List> brandListTask = new FutureTask<>(brandListCallable);
        FutureTask<List> topicListTask = new FutureTask<>(topicListCallable);
        FutureTask<List> grouponListTask = new FutureTask<>(grouponListCallable);
        FutureTask<List> floorGoodsListTask = new FutureTask<>(floorGoodsListCallable);


        executorService.submit(bannerTask);
        executorService.submit(channelTask);
        executorService.submit(couponTask);
        executorService.submit(newGoodsTask);
        executorService.submit(hotGoodsTask);
        executorService.submit(brandListTask);
        executorService.submit(topicListTask);
        executorService.submit(grouponListTask);
        executorService.submit(floorGoodsListTask);

        Map<String, Object> entity = new HashMap<>(16);
        try {
            entity.put("banner", bannerTask.get());
            entity.put("channel", channelTask.get());
            entity.put("couponList", couponTask.get());
            entity.put("newGoodsList", newGoodsTask.get());
            entity.put("hotGoodsList", hotGoodsTask.get());
            entity.put("brandList", brandListTask.get());
            entity.put("topicList", topicListTask.get());
            entity.put("grouponList", grouponListTask.get());
            entity.put("floorGoodsList", floorGoodsListTask.get());
            //缓存数据
            HomeCacheManager.loadData(HomeCacheManager.INDEX, entity);
        }
        catch (Exception e) {
            e.printStackTrace();
        }finally {
            executorService.shutdown();
        }
        return CommonResult.success(entity);
    }

    /**
     * 获得分类以及分类下商品
     * @return
     */
    private List<Map<String, Object>> getCategoryList() {
        List<Map<String, Object>> categoryList = new ArrayList<>();
        List<Category> catL1List = categoryService.queryL1WithoutRecommend(0, SystemConfig.getCatlogListLimit());
        for (Category catL1 : catL1List) {
            List<Category> catL2List = categoryService.queryByPid(catL1.getId());
            List<Integer> l2List = new ArrayList<>();
            for (Category catL2 : catL2List) {
                l2List.add(catL2.getId());
            }

            List<Goods> categoryGoods;
            if (l2List.size() == 0) {
                categoryGoods = new ArrayList<>();
            } else {
                categoryGoods = goodsService.queryByCategory(l2List, 0, SystemConfig.getCatlogMoreLimit());
            }

            Map<String, Object> catGoods = new HashMap<>();
            catGoods.put("id", catL1.getId());
            catGoods.put("name", catL1.getName());
            catGoods.put("goodsList", categoryGoods);
            categoryList.add(catGoods);
        }
        return categoryList;
    }

    /**
     * 商城介绍信息
     * @return 商城介绍信息
     */
    @GetMapping("/about")
    public CommonResult<Map<String, Object>> about() {
        Map<String, Object> about = new HashMap<>();
        about.put("name", SystemConfig.getMallName());
        about.put("address", SystemConfig.getMallAddress());
        about.put("phone", SystemConfig.getMallPhone());
        about.put("qq", SystemConfig.getMallQQ());
        about.put("longitude", SystemConfig.getMallLongitude());
        about.put("latitude", SystemConfig.getMallLatitude());
        return CommonResult.success(about);
    }


}
