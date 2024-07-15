/**
 * Author: Bintong
 * Date: 2024/7/6 11:15
 */

package com.project.springboot.entity;

import lombok.*;


@Data
@Setter
@Getter
public class User {
    private Integer userID;
    private String userName;
    private String userFirstName;
    private String userLastName;
    private String userRole;
    private String userPassword;
    private String userEmail;
    private String userStatus;

    private String token;
}
