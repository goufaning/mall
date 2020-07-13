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
    private Map<String, ColumnFilter> columnFilters = new HashMap<String, ColumnFilter>();

    public String getColumnFilterValue(String filterName) {
        String value = null;
        ColumnFilter columnFilter = this.getColumnFilters().get(filterName);
        if(columnFilter != null) {
            value = columnFilter.getValue();
        }
        return value;
    }
}
