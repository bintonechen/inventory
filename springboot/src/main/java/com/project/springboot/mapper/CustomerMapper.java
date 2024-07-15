package com.project.springboot.mapper;

import com.project.springboot.entity.Customer;
import org.apache.ibatis.annotations.*;

import java.util.List;

@Mapper
public interface CustomerMapper {

    @Insert("INSERT INTO Customer (CustomerFirstName, CustomerLastName, CustomerEmail, CustomerContactNum, CustomerBirthMonth) "
            + " VALUES (#{customerFirstName}, #{customerLastName}, #{customerEmail}, #{customerContactNum}, #{customerBirthMonth})")
    void insert(Customer customer);

    @Update("UPDATE Customer SET CustomerFirstName = #{customerFirstName}, CustomerLastName = #{customerLastName}, "
            + "CustomerEmail = #{customerEmail}, CustomerContactNum = #{customerContactNum}, CustomerBirthMonth = #{customerBirthMonth} "
            + "WHERE CustomerID = #{customerID}")
    void update(Customer customer);

    @Delete("DELETE FROM Customer WHERE CustomerID = #{customerID}")
    void delete(Integer customerID);

    @Select("SELECT * FROM Customer ORDER BY CustomerID DESC")
    List<Customer> selectAll();

    @Select("SELECT * FROM CUSTOMER WHERE CustomerEmail = #{customerEmail}")
    Customer selectByCustomerEmail(String customerEmail);

    @Select("SELECT * FROM CUSTOMER WHERE CustomerContactNum = #{customerContactNum}")
    Customer selectByCustomerContactNum(String customerContactNum);




}
