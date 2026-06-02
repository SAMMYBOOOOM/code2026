package com.example.service;

import cn.hutool.core.util.ObjectUtil;
import com.example.entity.Account;
import com.example.entity.Admin;
import com.example.exception.CustomException;
import com.example.mapper.AdminMapper;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import jakarta.annotation.Resource;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Admin service
 **/
@Service
public class AdminService {

    @Resource
    private AdminMapper adminMapper;

    /**
     * Add
     */
    public void add(Admin admin) {
        Admin dbAdmin = adminMapper.selectByUsername(admin.getUsername());
        if (ObjectUtil.isNotNull(dbAdmin)) {
            throw new CustomException("User does not exist");
        }
        if (ObjectUtil.isEmpty(admin.getPassword())) {
            admin.setPassword("admin");
        }
        if (ObjectUtil.isEmpty(admin.getName())) {
            admin.setName(admin.getUsername());
        }
        admin.setRole("admin");
        adminMapper.insert(admin);
    }

    /**
     * Delete
     */
    public void deleteById(Integer id) {
        adminMapper.deleteById(id);
    }

    /**
     * Update
     */
    public void updateById(Admin admin) {
        adminMapper.updateById(admin);
    }

    /**
     * Select by id
     */
    public Admin selectById(Integer id) {
        return adminMapper.selectById(id);
    }

    /**
     * Select all
     */
    public List<Admin> selectAll(Admin admin) {
        return adminMapper.selectAll(admin);
    }

    /**
     * Select page
     */
    public PageInfo<Admin> selectPage(Admin admin, Integer pageNum, Integer pageSize) {
        PageHelper.startPage(pageNum, pageSize);
        List<Admin> list = adminMapper.selectAll(admin);
        return PageInfo.of(list);
    }

    /**
     * login
     */
    public Account login(Account account) {
        Account dbAdmin = adminMapper.selectByUsername(account.getUsername());
        if (ObjectUtil.isNull(dbAdmin)) {
            throw new CustomException("User does not exist");
        }
        if (!account.getPassword().equals(dbAdmin.getPassword())) {
            throw new CustomException("Password or username is incorrect");
        }
        return dbAdmin;
    }

    /**
     * update password
     */
    public void updatePassword(Account account) {
        Admin dbAdmin = adminMapper.selectByUsername(account.getUsername());
        if (ObjectUtil.isNull(dbAdmin)) {
            throw new CustomException("User does not exist");
        }
        if (!account.getPassword().equals(dbAdmin.getPassword())) {
            throw new CustomException("Original password is incorrect");
        }
        dbAdmin.setPassword(account.getNewPassword());
        adminMapper.updateById(dbAdmin);
    }

}