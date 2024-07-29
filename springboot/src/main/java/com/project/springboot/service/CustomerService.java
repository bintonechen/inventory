/**
 * Author: Bintong
 * Date: 2024/7/6 16:27
 */

package com.project.springboot.service;

import com.project.springboot.entity.Customer;
import com.project.springboot.entity.User;
import com.project.springboot.exception.ServiceException;
import com.project.springboot.mapper.CustomerMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CustomerService {

    @Autowired CustomerMapper customerMapper;

    public Customer register(Customer customer) {
        // to check if the customer email already exists
        Customer databaseCustomerEmail = customerMapper.selectByCustomerEmail(customer.getCustomerEmail());
        if(databaseCustomerEmail != null){
            throw new ServiceException("Customer Email already exists. ");
        }
        // to check if the customer email already exists
        Customer databaseCustomerContactNum = customerMapper.selectByCustomerContactNum(customer.getCustomerContactNum());
        if(databaseCustomerContactNum != null){
            throw new ServiceException("Customer contact number already exists. ");
        }
        customerMapper.insert(customer);
        return null;
    }

    public void insertCustomer(Customer customer) {
        customerMapper.insert(customer);
    }


    public void deleteCustomer(Integer id) {
        customerMapper.delete(id);
    }

    public void batchDeleteCustomer(List<Integer> ids) {
        for (Integer id : ids){
            customerMapper.delete(id);
        }
    }

    public List<Customer> selectAll() {
        return customerMapper.selectAll();
    }


    public void updateCustomer(Customer customer) {
        customerMapper.update(customer);
    }


}
