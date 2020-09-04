package com.goufaning.mall.admin.service;

import com.goufaning.mall.common.result.PageResult;

/**
 * 用户信息service
 *
 * @author goufn
 * @version V1.0
 * @date 2020-07-10 14:02
 */
public interface AdminUserService {

    PageResult getUsersPageByName(String name, int pageNum, int pageSize);

    void getUser();
}
