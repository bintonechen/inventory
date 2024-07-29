/**
 * Purpose: to study HTTP and SpringBoot
 * Author: Bintong
 * Date: 2024/7/4 14:16
 * * This class is for testing propose only. Not relevant to the inventory project
 */

package com.project.springboot.controller;


import com.project.springboot.common.Result;
import com.project.springboot.entity.Obj;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/web")
public class WebController {

    @GetMapping("/hello")
    public Result hello(){
        return Result.success("hello hello hello");
    }

    @PostMapping("/post")
    public Result post(@RequestBody Obj obj){
        return Result.success(obj);
    }

    @PutMapping("/put")
    public Result put(@RequestBody Obj obj){
        return Result.success(obj);
    }

    @DeleteMapping("/delete/{id}")
    public Result delete(@PathVariable Integer id){
        return Result.success(id);
    }


}
