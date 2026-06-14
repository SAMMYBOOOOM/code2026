package com.example.service;

import cn.hutool.core.util.ObjectUtil;
import com.example.entity.Account;
import com.example.entity.Carousel;
import com.example.exception.CustomException;
import com.example.mapper.CarouselMapper;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import jakarta.annotation.Resource;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Carousel service
 **/
@Service
public class CarouselService {

    @Resource
    private CarouselMapper carouselMapper;

    /**
     * Add
     */
    public void add(Carousel carousel) {
        carouselMapper.insert(carousel);
    }

    /**
     * Delete
     */
    public void deleteById(Integer id) {
        carouselMapper.deleteById(id);
    }

    /**
     * Update
     */
    public void updateById(Carousel carousel) {
        carouselMapper.updateById(carousel);
    }

    /**
     * Select by id
     */
    public Carousel selectById(Integer id) {
        return carouselMapper.selectById(id);
    }

    /**
     * Select all
     */
    public List<Carousel> selectAll(Carousel carousel) {
        return carouselMapper.selectAll(carousel);
    }

    /**
     * Select page
     */
    public PageInfo<Carousel> selectPage(Carousel carousel, Integer pageNum, Integer pageSize) {
        PageHelper.startPage(pageNum, pageSize);
        List<Carousel> list = carouselMapper.selectAll(carousel);
        return PageInfo.of(list);
    }
}