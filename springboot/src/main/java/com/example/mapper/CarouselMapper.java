package com.example.mapper;

import com.example.entity.Carousel;
import org.apache.ibatis.annotations.Select;

import java.util.List;

/**
 * CarouselMapper interface
*/
public interface CarouselMapper {

    /**
      * insert
    */
    int insert(Carousel carousel);

    /**
      * delete by id
    */
    int deleteById(Integer id);

    /**
      * update by id
    */
    int updateById(Carousel carousel);

    /**
      * select by id
    */
    Carousel selectById(Integer id);

    /**
      * select all
    */
    List<Carousel> selectAll(Carousel carousel);
}