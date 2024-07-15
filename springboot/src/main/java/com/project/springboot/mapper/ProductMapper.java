package com.project.springboot.mapper;

import com.project.springboot.entity.Product;
import org.apache.ibatis.annotations.*;

import java.util.List;

@Mapper
public interface ProductMapper {


    @Insert("INSERT INTO Product (ProductBarcode, ProductName, ProductCollection, ProductColour, ProductPrice, ProductReleaseYear, QuantityInStock, ReservedQuantity) "
            + "VALUES (#{productBarcode}, #{productName}, #{productCollection}, #{productColour}, #{productPrice}, #{productReleaseYear}, #{quantityInStock}, #{reservedQuantity})")
    void insert(Product product);

    @Update("UPDATE Product SET ProductName = #{productName}, ProductCollection = #{productCollection}, ProductColour = #{productColour}, "
            + "ProductPrice = #{productPrice}, ProductReleaseYear = #{productReleaseYear}, QuantityInStock = #{quantityInStock}, ReservedQuantity = #{reservedQuantity} "
            + "WHERE ProductBarcode = #{productBarcode}")
    void update(Product product);

    @Delete("DELETE FROM Product WHERE ProductBarcode = #{productBarcode}")
    void delete(String productBarcode);

    @Select("SELECT * FROM Product")
    List<Product> selectAll();

    @Select("SELECT * FROM PRODUCT WHERE ProductBarcode = #{productBarcode}")
    Product selectByProductBarcode(String productBarcode);

}
