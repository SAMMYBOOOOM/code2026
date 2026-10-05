package com.example.mapper;

import com.example.entity.Recharge;
import org.apache.ibatis.annotations.Select;

import java.util.List;

/**
 * RechargeMapper interface
*/
public interface RechargeMapper {

    /**
      * insert
    */
    int insert(Recharge recharge);

    /**
      * delete by id
    */
    int deleteById(Integer id);

    /**
      * update by id
    */
    int updateById(Recharge recharge);

    /**
      * select by id
    */
    Recharge selectById(Integer id);

    /**
      * select all
    */
    List<Recharge> selectAll(Recharge recharge);
}