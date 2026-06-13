package com.example.controller;

import com.example.common.Result;
import com.example.entity.Account;
import com.example.entity.User;
import com.example.service.AdminService;
import com.example.service.UserService;
import jakarta.annotation.Resource;
import org.springframework.web.bind.annotation.*;


@RestController
public class WebController {

    @Resource
    private AdminService adminService;

    @Resource
    private UserService userService;


    /**
     * 默认请求接口
     */
    @GetMapping("/")
    public Result hello() {
        return Result.success();
    }

    /**
     * 登录
     */
    @PostMapping("/login")
    public Result login(@RequestBody Account account) {
        Account ac = null;
        if ("ADMIN".equals(account.getRole())) {
            ac = adminService.login(account);
        }
        if ("NORMALUSER".equals(account.getRole())) {
            ac = userService.login(account);
        }
        if (ac == null) {
            return Result.error("Invalid account or password");
        }
        return Result.success(ac);
    }

    /**
     * 注册
     */
    @PostMapping("/register")
    public Result register(@RequestBody User user) {
        if (!user.getPassword().equals(user.getNewPassword())) {
            return Result.error("Passwords do not match");
        }
        userService.add(user);
        return Result.success();
    }

    /**
     * 修改密码
     */
    @PutMapping("/updatePassword")
    public Result updatePassword(@RequestBody Account account) {
        if ("ADMIN".equals(account.getRole())) {
            adminService.updatePassword(account);
        }
        if ("NORMALUSER".equals(account.getRole())) {
            userService.updatePassword(account);
        }
        return Result.success();
    }

}
