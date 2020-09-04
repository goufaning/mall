package com.goufaning.mall.db.service;

import org.springframework.stereotype.Service;
import javax.annotation.Resource;
import java.util.List;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.goufaning.mall.db.mapper.CartMapper;
import com.goufaning.mall.db.model.Cart;
/**
* $description
* @author goufn
* @date 2020/8/31 1:26 下午
* @version V1.0
*/
@Service
public class CartService extends ServiceImpl<CartMapper, Cart> {

}
