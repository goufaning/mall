package com.goufaning.mall.db.service;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.goufaning.mall.db.mapper.CartMapper;
import com.goufaning.mall.db.model.Cart;
import com.goufaning.mall.db.util.EnumSortType;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;
/**
* $description
* @author goufn
* @date 2020/8/31 1:26 下午
* @version V1.0
*/
@Service
public class CartService extends ServiceImpl<CartMapper, Cart> {

    public Cart queryExist(Integer goodsId, Integer productId, Integer userId) {
        QueryWrapper<Cart> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq(Cart.COL_GOODS_ID, goodsId);
        queryWrapper.eq(Cart.COL_PRODUCT_ID, productId);
        queryWrapper.eq(Cart.COL_USER_ID, userId);
        queryWrapper.eq(Cart.COL_DELETED, false);
        return getOne(queryWrapper);
    }

    public void add(Cart cart) {
        cart.setAddTime(LocalDateTime.now());
        cart.setUpdateTime(LocalDateTime.now());
        cart.insert();
    }

    public int update(Cart cart) {
        cart.setUpdateTime(LocalDateTime.now());
        return baseMapper.updateById(cart);
    }

    public List<Cart> queryByUid(int userId) {
        QueryWrapper<Cart> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq(Cart.COL_USER_ID, userId);
        queryWrapper.eq(Cart.COL_DELETED, false);
        return list(queryWrapper);
    }

    public List<Cart> queryByUidAndChecked(Integer userId) {
        QueryWrapper<Cart> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq(Cart.COL_USER_ID, userId);
        queryWrapper.eq(Cart.COL_CHECKED, true);
        queryWrapper.eq(Cart.COL_DELETED, false);
        return list(queryWrapper);
    }

    public int delete(List<Integer> productIdList, int userId) {
        QueryWrapper<Cart> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq(Cart.COL_USER_ID, userId);
        queryWrapper.in(Cart.COL_PRODUCT_ID, productIdList);
        return baseMapper.delete(queryWrapper);
    }


    public Cart findByUserIdAndId(Integer userId, Integer id) {
        QueryWrapper<Cart> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq(Cart.COL_USER_ID, userId);
        queryWrapper.eq(Cart.COL_ID, id);
        queryWrapper.eq(Cart.COL_DELETED, false);
        return getOne(queryWrapper);
    }

    public int updateCheck(Integer userId, List<Integer> productIdList, Boolean checked) {
        QueryWrapper<Cart> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq(Cart.COL_USER_ID, userId);
        queryWrapper.in(Cart.COL_PRODUCT_ID, productIdList);
        queryWrapper.eq(Cart.COL_DELETED, false);
        Cart cart = new Cart();
        cart.setChecked(checked);
        cart.setUpdateTime(LocalDateTime.now());
        return baseMapper.update(cart, queryWrapper);
    }

    public void clearGoods(Integer userId) {
        QueryWrapper<Cart> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq(Cart.COL_USER_ID, userId);
        queryWrapper.eq(Cart.COL_CHECKED, true);
        Cart cart = new Cart();
        cart.setDeleted(true);
        baseMapper.update(cart, queryWrapper);
    }

    public IPage<Cart> querySelective(Integer userId, Integer goodsId, Integer current, Integer size, String sort, String order) {
        QueryWrapper<Cart> queryWrapper = new QueryWrapper<>();
        if (!StringUtils.isEmpty(userId)) {
            queryWrapper.eq(Cart.COL_USER_ID, userId);
        }
        if (!StringUtils.isEmpty(goodsId)) {
            queryWrapper.eq(Cart.COL_GOODS_ID, goodsId);
        }
        queryWrapper.eq(Cart.COL_DELETED, false);
        if (!StringUtils.isEmpty(sort) && !StringUtils.isEmpty(order)) {
            queryWrapper.orderBy(true, order.equalsIgnoreCase(EnumSortType.ASC.type()), sort);
        }
        IPage<Cart> page = new Page<>(current, size);
        return page(page, queryWrapper);
    }


    public boolean checkExist(Integer goodsId) {
        QueryWrapper<Cart> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq(Cart.COL_GOODS_ID, goodsId);
        queryWrapper.eq(Cart.COL_CHECKED, true);
        queryWrapper.eq(Cart.COL_DELETED, false);
        return count(queryWrapper) != 0;
    }

    public void updateProduct(Integer productId, String goodsSn, String goodsName, BigDecimal price, String url) {
        Cart cart = new Cart();
        cart.setPrice(price);
        cart.setPicUrl(url);
        cart.setGoodsSn(goodsSn);
        cart.setGoodsName(goodsName);
        QueryWrapper<Cart> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq(Cart.COL_PRODUCT_ID, productId);
        baseMapper.update(cart, queryWrapper);
    }

}
