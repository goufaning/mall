package com.goufaning.mall.common.page;

import lombok.Data;

import java.util.HashMap;
import java.util.Map;

/**
 * 分页请求参数
 */
@Data
public class PageRequest {
    /**
     * 当前页码
     */
    private int pageNum;
    /**
     * 每页数量
     */
    private int pageSize;

    /**
     * 匹配参数
     */
    private Map<String, String> paramMap = new HashMap<String, String>();

    public String getParamValue(String filterName) {
        String value = this.getParamMap().get(filterName);
        return value;
    }
}
