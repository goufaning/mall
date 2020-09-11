package com.goufaning.mall.core.util;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.goufaning.mall.common.result.CommonResult;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author goufn
 * @version V1.0
 * @date 2020/9/9 11:00 上午
 */
public class ResponseUtil {

    public static Object okList(IPage page) {
        Map<String, Object> data = new HashMap<>();
        data.put("list", page.getRecords());
        data.put("total", page.getTotal());
        data.put("page", page.getCurrent());
        data.put("limit", page.getSize());
        data.put("pages", page.getPages());
        return CommonResult.success(data);
    }

    public static Object okList(List list) {
        Map<String, Object> data = new HashMap<>();
        data.put("list", list);
        data.put("total", list.size());
        data.put("page", 1);
        data.put("limit", list.size());
        data.put("pages", 1);
        return CommonResult.success(data);
    }
}
