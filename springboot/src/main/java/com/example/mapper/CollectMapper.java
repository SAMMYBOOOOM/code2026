package com.example.mapper;

import com.example.entity.Collect;
import org.apache.ibatis.annotations.Select;

import java.util.List;

/**
 * CollectMapper interface
*/
public interface CollectMapper {

    /**
      * insert
    */
    int insert(Collect collect);

    /**
      * delete by id
    */
    int deleteById(Integer id);

    /**
      * update by id
    */
    int updateById(Collect collect);

    /**
      * select by id
    */
    Collect selectById(Integer id);

    /**
      * select all
    */
    List<Collect> selectAll(Collect collect);
}