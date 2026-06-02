package com.example.mapper;

import com.example.entity.Admin;
import org.apache.ibatis.annotations.Select;

import java.util.List;

/**
 * 操作admin相关数据接口
*/
public interface AdminMapper {

    /**
      * insert
    */
    int insert(Admin admin);

    /**
      * delete by id
    */
    int deleteById(Integer id);

    /**
      * update by id
    */
    int updateById(Admin admin);

    /**
      * select by id
    */
    Admin selectById(Integer id);

    /**
      * select all
    */
    List<Admin> selectAll(Admin admin);

    @Select("select * from `admin` where username = #{username}")
    Admin selectByUsername(String username);

}