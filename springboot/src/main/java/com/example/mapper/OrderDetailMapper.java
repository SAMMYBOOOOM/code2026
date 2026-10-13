package com.example.mapper;

import com.example.entity.OrderDetail;
import org.apache.ibatis.annotations.Select;

import java.util.List;

/**
 * OrderDetailMapper interface
*/
public interface OrderDetailMapper {

    /**
      * insert
    */
    int insert(OrderDetail orderDetail);

    /**
      * delete by id
    */
    int deleteById(Integer id);

    /**
      * update by id
    */
    int updateById(OrderDetail orderDetail);

    /**
      * select by id
    */
    OrderDetail selectById(Integer id);

    /**
      * select all
    */
    List<OrderDetail> selectAll(OrderDetail orderDetail);
}