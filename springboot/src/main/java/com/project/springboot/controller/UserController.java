/**
 * Author: Bintong
 * Date: 2024/7/6 11:31
 */

package com.project.springboot.controller;

import cn.hutool.core.util.StrUtil;
import com.project.springboot.common.Result;
import com.project.springboot.entity.User;
import com.project.springboot.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.sql.SQLException;
import java.util.List;

@CrossOrigin
@RestController
@RequestMapping("/manageaccount")
public class UserController {

    @Autowired
    UserService userService;


    /**
     *  Add a new user
     */
    @PostMapping("/add")
    public Result add(@RequestBody User user){
        try {
            userService.insertUser(user);
        } catch(Exception e){
            if(e instanceof SQLException){
                return Result.error("insert into database error");
            } else {
                return Result.error("system error");
            }
        }
        return Result.success();
    }


    /**
     *  register a new user
     */
    @PostMapping("/register")
    public Result registry(@RequestBody User user){
        user = userService.register(user);
        return Result.success(user);
    }

    /**
     *  update an existing user
     */
    @PutMapping("/update")
    public Result update(@RequestBody User user){
        try {
            userService.updateUser(user);
        } catch(Exception e){
            return Result.error(e.getMessage());
//            if(e instanceof SQLException){
//                return Result.error("insert into database error");
//            } else {
//                return Result.error("system error");
//            }
        }
        return Result.success(user);
    }

    /**
     *  delete an existing user
     */
    @DeleteMapping("/delete/{id}")
    public Result delete(@PathVariable Integer id){
            userService.deleteUser(id);
        return Result.success();
    }

    /**
     *  delete several existing users
     */
    @DeleteMapping("/delete/batch")
    public Result batchDelete(@RequestBody List<Integer> ids){
        userService.batchDeleteUser(ids);
        return Result.success();
    }

    /**
     *  display all users
     */
    @GetMapping("/selectAll")
    public Result selectAll(){
        List<User> userList = userService.selectAll();
        return Result.success(userList);
    }


}
