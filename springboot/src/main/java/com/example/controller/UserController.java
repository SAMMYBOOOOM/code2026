/**
 * Functionality:
 * Author: Sam Hsu
 * Date: 6/9/2026 7:14 PM
 */

package com.example.controller;

import com.example.common.Result;
import com.example.entity.User;
import com.example.service.UserService;
import com.github.pagehelper.PageInfo;
import jakarta.annotation.Resource;
import org.springframework.web.bind.annotation.*;

// Means this is an API, return json
@RestController
@RequestMapping("/user")
public class UserController {

    // To request @Service
    @Resource
    private UserService userService;

    // API request method: http://localhost:9090/user/selectPage?pageNum=1&pageSize=10
    // get request
    @GetMapping("/selectPage")
    public Result selectPage(@RequestParam(defaultValue = "1") Integer pageNum,
                             @RequestParam(defaultValue = "10") Integer pageSize,
                             @RequestParam(required = false) String name) {
        PageInfo<User> pageInfo = userService.selectPage(pageNum, pageSize, name);
        return Result.success(pageInfo);
    }

    /**
     * Delete user
     * path: /user/delete/{id}
     */
    @DeleteMapping("/delete/{id}")
    public Result delete(@PathVariable Integer id) {
        userService.deleteById(id);
        return Result.success();
    }

    /**
     * Add user
     *
     * @param user object
     * @return
     */
    @PostMapping("/add")
    public Result add(@RequestBody User user) {
        userService.add(user);
        return Result.success();
    }

    /**
     * Update user
     */
    @PutMapping("/update")
    public Result update(@RequestBody User user) {
        userService.update(user);
        return Result.success();
    }

    /**
     * Search by id
     */
    @GetMapping("/selectById/{id}")
    public Result selectById(@PathVariable Integer id) {
        User user = userService.selectById(id);
        return Result.success(user);
    }
}
