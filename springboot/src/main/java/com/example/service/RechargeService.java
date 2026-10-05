package com.example.service;

import cn.hutool.core.date.DateUtil;
import cn.hutool.core.util.ObjectUtil;
import com.example.entity.Account;
import com.example.entity.Recharge;
import com.example.entity.User;
import com.example.exception.CustomException;
import com.example.mapper.RechargeMapper;
import com.example.mapper.UserMapper;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import jakarta.annotation.Resource;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Recharge service
 **/
@Service
public class RechargeService {

    @Resource
    private RechargeMapper rechargeMapper;
    @Resource
    UserMapper userMapper;

    /**
     * Add
     */
    public void add(Recharge recharge) {
        // Deposit to user balance
        Integer userId = recharge.getUserId();
        User user = userMapper.selectById(userId);
        user.setAccount(user.getAccount().add(recharge.getMoney()));
        userMapper.updateById(user);
        recharge.setTime(DateUtil.now());
        rechargeMapper.insert(recharge);
    }

    /**
     * Delete
     */
    public void deleteById(Integer id) {
        rechargeMapper.deleteById(id);
    }

    /**
     * Update
     */
    public void updateById(Recharge recharge) {
        rechargeMapper.updateById(recharge);
    }

    /**
     * Select by id
     */
    public Recharge selectById(Integer id) {
        return rechargeMapper.selectById(id);
    }

    /**
     * Select all
     */
    public List<Recharge> selectAll(Recharge recharge) {
        return rechargeMapper.selectAll(recharge);
    }

    /**
     * Select page
     */
    public PageInfo<Recharge> selectPage(Recharge recharge, Integer pageNum, Integer pageSize) {
        PageHelper.startPage(pageNum, pageSize);
        List<Recharge> list = rechargeMapper.selectAll(recharge);
        return PageInfo.of(list);
    }
}