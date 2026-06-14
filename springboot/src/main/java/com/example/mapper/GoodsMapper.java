package com.example.mapper;

import com.example.entity.Goods;
import org.apache.ibatis.annotations.Select;

import java.util.List;

/**
 * GoodsMapper interface
*/
public interface GoodsMapper {

    /**
      * insert
    */
    int insert(Goods goods);

    /**
      * delete by id
    */
    int deleteById(Integer id);

    /**
      * update by id
    */
    int updateById(Goods goods);

    /**
      * select by id
    */
    Goods selectById(Integer id);

    /**
      * select all
    */
    List<Goods> selectAll(Goods goods);
}