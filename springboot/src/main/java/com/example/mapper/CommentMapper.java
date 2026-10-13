package com.example.mapper;

import com.example.entity.Comment;
import org.apache.ibatis.annotations.Select;

import java.util.List;

/**
 * CommentMapper interface
*/
public interface CommentMapper {

    /**
      * insert
    */
    int insert(Comment comment);

    /**
      * delete by id
    */
    int deleteById(Integer id);

    /**
      * update by id
    */
    int updateById(Comment comment);

    /**
      * select by id
    */
    Comment selectById(Integer id);

    /**
      * select all
    */
    List<Comment> selectAll(Comment comment);
}