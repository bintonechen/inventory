/**
 * Author: Bintong
 * Date: 2024/7/8 14:14
 */

package com.project.springboot.controller;

import cn.hutool.core.util.StrUtil;
import com.project.springboot.common.Result;
import com.project.springboot.entity.User;
import com.project.springboot.service.UserService;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;

@CrossOrigin
@RestController
@RequestMapping("/login")
public class LoginController {

    @Resource
    UserService userService;

    @PostMapping("/login")
    public Result login(@RequestBody User user){
        if(StrUtil.isBlank(user.getUserName()) || StrUtil.isBlank(user.getUserPassword())){
            return Result.error("username and password must be entered");
        }
        user = userService.login(user);
        return Result.success(user);
    }

}
