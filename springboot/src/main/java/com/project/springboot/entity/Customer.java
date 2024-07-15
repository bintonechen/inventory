/**
 * Author: Bintong
 * Date: 2024/7/6 16:28
 */

package com.project.springboot.entity;

import lombok.Data;

@Data
public class Customer {
    private Integer customerID;
    private String customerFirstName;
    private String customerLastName;
    private String customerEmail;
    private String customerContactNum;
    private String customerBirthMonth;
}
