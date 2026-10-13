package com.example.service;

import cn.hutool.core.date.DateUtil;
import cn.hutool.core.util.ObjectUtil;
import com.example.entity.Account;
import com.example.entity.Comment;
import com.example.exception.CustomException;
import com.example.mapper.CommentMapper;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import jakarta.annotation.Resource;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Comment service
 **/
@Service
public class CommentService {

    @Resource
    private CommentMapper commentMapper;

    /**
     * Add
     */
    public void add(Comment comment) {
        comment.setTime(DateUtil.now());
        commentMapper.insert(comment);
    }

    /**
     * Delete
     */
    public void deleteById(Integer id) {
        commentMapper.deleteById(id);
    }

    /**
     * Update
     */
    public void updateById(Comment comment) {
        commentMapper.updateById(comment);
    }

    /**
     * Select by id
     */
    public Comment selectById(Integer id) {
        return commentMapper.selectById(id);
    }

    /**
     * Select all
     */
    public List<Comment> selectAll(Comment comment) {
        return commentMapper.selectAll(comment);
    }

    /**
     * Select page
     */
    public PageInfo<Comment> selectPage(Comment comment, Integer pageNum, Integer pageSize) {
        PageHelper.startPage(pageNum, pageSize);
        List<Comment> list = commentMapper.selectAll(comment);
        return PageInfo.of(list);
    }
}