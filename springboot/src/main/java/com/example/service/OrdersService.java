package com.example.service;

import cn.hutool.core.date.DateUtil;
import cn.hutool.core.util.ObjectUtil;
import cn.hutool.core.util.RandomUtil;
import cn.hutool.db.sql.Order;
import com.example.entity.*;
import com.example.exception.CustomException;
import com.example.mapper.*;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import jakarta.annotation.Resource;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * Orders service
 **/
@Service
public class OrdersService {

    @Resource
    private OrdersMapper ordersMapper;
    @Resource
    GoodsMapper goodsMapper;
    @Resource
    UserMapper userMapper;
    @Resource
    OrderDetailMapper orderDetailMapper;
    @Resource
    CartMapper cartMapper;

    /**
     * Add
     */
    @Transactional
    public void add(Orders orders) {
        orders.setStatus("Pending");
        orders.setTime(DateUtil.now());
        // Generate random order number 20261012
        String orderNo = DateUtil.format(new Date(), "yyyyMMdd") + System.currentTimeMillis() + RandomUtil.randomNumbers(4);
        orders.setOrderNo(orderNo);
        ordersMapper.insert(orders);
        Integer orderId = orders.getId();

        List<Cart> cartList = orders.getCartList();
        User user = userMapper.selectById(orders.getUserId());
        BigDecimal totalPrice = BigDecimal.ZERO;
        for (Cart cart : cartList){
            Integer goodsId = cart.getGoodsId();
            Goods goods = goodsMapper.selectById(goodsId);
            if (goods.getStore() < cart.getNum()){  // Check if the goods is enough
                throw new CustomException(goods.getName() + "The goods is not enough");
            }
            goods.setStore(goods.getStore() - cart.getNum());
            goodsMapper.updateById(goods);  // Update goods store

            // Add order detail
            OrderDetail orderDetail = new OrderDetail();
            orderDetail.setNum(cart.getNum());
            orderDetail.setGoodsId(goodsId);
            orderDetail.setGoodsImg(goods.getImg());
            orderDetail.setGoodsName(goods.getName());
            orderDetail.setGoodsPrice(goods.getPrice());
            orderDetail.setOrderId(orderId);
            orderDetailMapper.insert(orderDetail);

            // Delete goods from cart
            cartMapper.deleteById(cart.getId());

            totalPrice = totalPrice.add(goods.getPrice().multiply(BigDecimal.valueOf(cart.getNum())));
        }
        if (user.getAccount().compareTo(totalPrice) < 0){   // Check if the balance is enough
            throw new CustomException("The balance is not enough, please deposit");
        }
        user.setAccount(user.getAccount().subtract(totalPrice));
        userMapper.updateById(user);    // Update user balance
        orders.setTotal(totalPrice);
        ordersMapper.updateById(orders);    // Update order total price
    }

    /**
     * Delete
     */
    public void deleteById(Integer id) {
        ordersMapper.deleteById(id);
    }

    /**
     * Update
     */
    public void updateById(Orders orders) {
        ordersMapper.updateById(orders);
    }

    /**
     * Select by id
     */
    public Orders selectById(Integer id) {
        return ordersMapper.selectById(id);
    }

    /**
     * Select all
     */
    public List<Orders> selectAll(Orders orders) {
        return ordersMapper.selectAll(orders);
    }

    /**
     * Select page
     */
    public PageInfo<Orders> selectPage(Orders orders, Integer pageNum, Integer pageSize) {
        PageHelper.startPage(pageNum, pageSize);
        List<Orders> list = ordersMapper.selectAll(orders);
        for (Orders o : list){
            OrderDetail orderDetail = new OrderDetail();
            orderDetail.setOrderId(o.getId());
            List<OrderDetail> orderDetailList = orderDetailMapper.selectAll(orderDetail);
            o.setOrderDetailList(orderDetailList);
        }
        return PageInfo.of(list);
    }
}