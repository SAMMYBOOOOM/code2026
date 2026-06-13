package com.example.mapper;

import com.example.entity.Category;
import org.apache.ibatis.annotations.Select;

import java.util.List;

/**
 * CategoryMapper interface
*/
public interface CategoryMapper {

    /**
      * insert
    */
    int insert(Category category);

    /**
      * delete by id
    */
    int deleteById(Integer id);

    /**
      * update by id
    */
    int updateById(Category category);

    /**
      * select by id
    */
    Category selectById(Integer id);

    /**
      * select all
    */
    List<Category> selectAll(Category category);
}