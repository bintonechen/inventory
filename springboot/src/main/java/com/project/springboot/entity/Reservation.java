/**
 * Author: Bintong
 * Date: 2024/7/17 13:45
 */

package com.project.springboot.entity;

import lombok.Data;

@Data
public class Reservation {
    private String reservationID;
    private String userName;
    private String customerEmail;
    private String customerFirstName;
    private String productBarcode;
    private String createdDate;
    private String reservationStatus;
}
