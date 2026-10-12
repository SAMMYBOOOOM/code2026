package com.example.mapper;

import com.example.entity.Cart;
import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import java.util.List;

/**
 * CartMapper interface
*/
public interface CartMapper {

    /**
      * insert
    */
    int insert(Cart cart);

    /**
      * delete by id
    */
    int deleteById(Integer id);

    /**
      * update by id
    */
    int updateById(Cart cart);

    /**
      * select by id
    */
    Cart selectById(Integer id);

    /**
      * select all
    */
    List<Cart> selectAll(Cart cart);

    @Select("SELECT * FROM cart WHERE goods_id = #{goodsId} AND user_id = #{userId}")
    Cart selectByGoodsIdAndUserId(@Param("goodsId") Integer goodsId, @Param("userId") Integer userId);

    @Delete("DELETE FROM cart WHERE goods_id = #{id}")
    void deleteByGoodsId(Integer id);
}