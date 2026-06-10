/**
 * Functionality:
 * Author: Sam Hsu
 * Date: 6/9/2026 10:05 PM
 */

package com.example.service;

import cn.hutool.core.util.StrUtil;
import com.example.entity.User;
import com.example.exception.CustomException;
import com.example.mapper.UserMapper;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import jakarta.annotation.Resource;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.List;

@Service
public class UserService {

    @Resource
    private UserMapper userMapper;

    public PageInfo<User> selectPage(Integer pageNum, Integer pageSize, String name) {
        PageHelper.startPage(pageNum, pageSize);
        List<User> list = userMapper.selectAll(name);
        return PageInfo.of(list);
    }

    public void deleteById(Integer id) {
        userMapper.deleteById(id);
    }

    public void add(User user) {
        String username = user.getUsername();
        // Check if the username exists
        User dbUser = userMapper.selectByUsername(username);
        if (dbUser != null) {
            throw new CustomException("Username already exists");
        }

        if (StrUtil.isBlank(user.getPassword())) {
            // Default password
            user.setPassword("123");
        }
        if (StrUtil.isBlank(user.getName())) {
            // Default name
            user.setName(user.getUsername());
        }
        user.setRole("Normal User");
        user.setAccount(BigDecimal.ZERO);   // Default balance
        userMapper.insert(user);
    }

    public void update(User user) {
        userMapper.updateById(user);
    }
}
