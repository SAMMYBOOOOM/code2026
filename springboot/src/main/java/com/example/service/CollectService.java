package com.example.service;

import cn.hutool.core.date.DateUtil;
import cn.hutool.core.util.ObjectUtil;
import com.example.entity.Account;
import com.example.entity.Collect;
import com.example.exception.CustomException;
import com.example.mapper.CollectMapper;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import jakarta.annotation.Resource;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Collect service
 **/
@Service
public class CollectService {

    @Resource
    private CollectMapper collectMapper;

    /**
     * Add
     */
    public void add(Collect collect) {
        collect.setTime(DateUtil.now());
        collectMapper.insert(collect);
    }

    /**
     * Delete
     */
    public void deleteById(Integer id) {
        collectMapper.deleteById(id);
    }

    /**
     * Update
     */
    public void updateById(Collect collect) {
        collectMapper.updateById(collect);
    }

    /**
     * Select by id
     */
    public Collect selectById(Integer id) {
        return collectMapper.selectById(id);
    }

    /**
     * Select all
     */
    public List<Collect> selectAll(Collect collect) {
        return collectMapper.selectAll(collect);
    }

    /**
     * Select page
     */
    public PageInfo<Collect> selectPage(Collect collect, Integer pageNum, Integer pageSize) {
        PageHelper.startPage(pageNum, pageSize);
        List<Collect> list = collectMapper.selectAll(collect);
        return PageInfo.of(list);
    }
}