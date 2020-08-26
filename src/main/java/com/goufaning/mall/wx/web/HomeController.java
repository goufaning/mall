package com.goufaning.mall.wx.web;

import com.goufaning.mall.common.result.CommonResult;
import com.goufaning.mall.db.service.AdService;
import com.goufaning.mall.db.service.CategoryService;
import com.goufaning.mall.wx.cache.HomeCacheManager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

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
public class HomeController {
    @Autowired
    private AdService adService;

    @Autowired
    private CategoryService categoryService;

    @GetMapping("/index")
    public CommonResult index(Integer userId) {
        //优先从缓存中读取
        if (HomeCacheManager.hasData(HomeCacheManager.INDEX)) {
            return CommonResult.success(HomeCacheManager.getCacheData(HomeCacheManager.INDEX));
        }
        ExecutorService executorService = Executors.newFixedThreadPool(10);
        Callable<List> bannerListCallable = () -> adService.queryIndex();
        Callable<List> channelListCallable = () -> categoryService.queryChannel();



        FutureTask<List> bannerTask = new FutureTask<>(bannerListCallable);
        FutureTask<List> channelTask = new FutureTask<>(channelListCallable);


        executorService.submit(bannerTask);
        executorService.submit(channelTask);

        Map<String, Object> entity = new HashMap<>(16);
        try {
            entity.put("banner", bannerTask.get());
            entity.put("channel", channelTask.get());
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


}
