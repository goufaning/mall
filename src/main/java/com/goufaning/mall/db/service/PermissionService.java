package com.goufaning.mall.db.service;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.goufaning.mall.common.utils.StringUtils;
import com.goufaning.mall.db.mapper.PermissionMapper;
import com.goufaning.mall.db.mapper.RoleMapper;
import com.goufaning.mall.db.model.Manager;
import com.goufaning.mall.db.model.Permission;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * @author goufn
 * @version V1.0
 * @date 2020-07-11 15:30
 */
@Service
public class PermissionService extends ServiceImpl<PermissionMapper, Permission> {


}

