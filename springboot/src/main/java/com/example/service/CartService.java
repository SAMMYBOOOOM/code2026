package com.example.service;

import cn.hutool.core.util.ObjectUtil;
import com.example.entity.Account;
import com.example.entity.Cart;
import com.example.exception.CustomException;
import com.example.mapper.CartMapper;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import jakarta.annotation.Resource;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Cart service
 **/
@Service
public class CartService {

    @Resource
    private CartMapper cartMapper;

    /**
     * Add
     */
    public void add(Cart cart) {
        Cart dbCart = cartMapper.selectByGoodsIdAndUserId(cart.getGoodsId(), cart.getUserId());
        if (dbCart != null) {    // If the cart already exists, update the quantity
            dbCart.setNum(dbCart.getNum() + cart.getNum());
            cartMapper.updateById(dbCart);  // Update the cart
        } else {
            cartMapper.insert(cart);
        }
    }

    /**
     * Delete
     */
    public void deleteById(Integer id) {
        cartMapper.deleteById(id);
    }

    /**
     * Update
     */
    public void updateById(Cart cart) {
        cartMapper.updateById(cart);
    }

    /**
     * Select by id
     */
    public Cart selectById(Integer id) {
        return cartMapper.selectById(id);
    }

    /**
     * Select all
     */
    public List<Cart> selectAll(Cart cart) {
        return cartMapper.selectAll(cart);
    }

    /**
     * Select page
     */
    public PageInfo<Cart> selectPage(Cart cart, Integer pageNum, Integer pageSize) {
        PageHelper.startPage(pageNum, pageSize);
        List<Cart> list = cartMapper.selectAll(cart);
        return PageInfo.of(list);
    }
}