package com.example.service;

import cn.hutool.core.util.ObjectUtil;
import com.example.entity.Account;
import com.example.entity.Goods;
import com.example.exception.CustomException;
import com.example.mapper.CartMapper;
import com.example.mapper.GoodsMapper;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import jakarta.annotation.Resource;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.List;

/**
 * Goods service
 **/
@Service
public class GoodsService {

    @Resource
    private GoodsMapper goodsMapper;
    @Resource
    private CartMapper cartMapper;

    /**
     * Add
     */
    public void add(Goods goods) {
        goods.setViews(0);
        goods.setSaleCount(0);
        Date date = new Date();
        String now = date.toString();
        goods.setTime(now);
        goodsMapper.insert(goods);
    }

    /**
     * Delete
     */
    @Transactional
    public void deleteById(Integer id) {
        goodsMapper.deleteById(id);
        // Delete the cart items associated with the goods
        cartMapper.deleteByGoodsId(id);
    }

    /**
     * Update
     */
    public void updateById(Goods goods) {
        goodsMapper.updateById(goods);
        // Update the cart items associated with the goods
        if (goods.getStatus().equals("Unlisted")){
            cartMapper.deleteByGoodsId(goods.getId());
        }
    }

    /**
     * Select by id
     */
    public Goods selectById(Integer id) {
        return goodsMapper.selectById(id);
    }

    /**
     * Select all
     */
    public List<Goods> selectAll(Goods goods) {
        return goodsMapper.selectAll(goods);
    }

    /**
     * Select page
     */
    public PageInfo<Goods> selectPage(Goods goods, Integer pageNum, Integer pageSize) {
        PageHelper.startPage(pageNum, pageSize);
        List<Goods> list = goodsMapper.selectAll(goods);
        return PageInfo.of(list);
    }
}