/**
 * Author: Bintong
 * Date: 2024/7/6 16:26
 */

package com.project.springboot.controller;

import com.project.springboot.common.Result;
import com.project.springboot.entity.Customer;
import com.project.springboot.entity.User;
import com.project.springboot.service.CustomerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.sql.SQLException;
import java.util.List;

@CrossOrigin
@RestController
@RequestMapping("/customer")
public class CustomerController {

    @Autowired
    CustomerService customerService;

    /**
     *  Add a new customer
     */
    @PostMapping("/add")
    public Result add(@RequestBody Customer customer){
        try {
            customerService.insertCustomer(customer);
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
    public Result registry(@RequestBody Customer customer){
        customer =customerService.register(customer);
        return Result.success(customer);
    }

    /**
     *  update an existing customer
     */
    @PutMapping("/update")
    public Result update(@RequestBody Customer customer){
        try {
            customerService.updateCustomer(customer);
        } catch(Exception e){
            return Result.error(e.getMessage());
//            if(e instanceof SQLException){
//                return Result.error("insert into database error");
//            } else {
//                return Result.error("system error");
//            }
        }
        return Result.success(customer);
    }

    /**
     *  delete an existing user
     */
    @DeleteMapping("/delete/{id}")
    public Result delete(@PathVariable Integer id){
        customerService.deleteCustomer(id);
        return Result.success();
    }

    /**
     *  delete several existing customers
     */
    @DeleteMapping("/delete/batch")
    public Result batchDelete(@RequestBody List<Integer> ids){
        customerService.batchDeleteCustomer(ids);
        return Result.success();
    }

    /**
     *  display all customer
     */
    @GetMapping("/selectAll")
    public Result selectAll(){
        List<Customer> customerList = customerService.selectAll();
        return Result.success(customerList);
    }


}
