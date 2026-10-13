package com.example.mapper;

import com.example.entity.Orders;
import org.apache.ibatis.annotations.Select;

import java.util.List;

/**
 * OrdersMapper interface
*/
public interface OrdersMapper {

    /**
      * insert
    */
    int insert(Orders orders);

    /**
      * delete by id
    */
    int deleteById(Integer id);

    /**
      * update by id
    */
    int updateById(Orders orders);

    /**
      * select by id
    */
    Orders selectById(Integer id);

    /**
      * select all
    */
    List<Orders> selectAll(Orders orders);
}