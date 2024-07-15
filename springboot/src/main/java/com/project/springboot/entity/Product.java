/**
 * Author: Bintong
 * Date: 2024/7/8 10:26
 */

package com.project.springboot.entity;

import lombok.Data;

@Data
public class Product {
    private String productBarcode;
    private String productName;
    private String productCollection;
    private String productColour;
    private Double productPrice;
    private Integer productReleaseYear;
    private Integer quantityInStock;
    private Integer reservedQuantity;
}
