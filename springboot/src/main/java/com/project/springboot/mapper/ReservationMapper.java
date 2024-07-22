package com.project.springboot.mapper;

import com.project.springboot.entity.Reservation;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import java.util.List;

@Mapper
public interface ReservationMapper {

    @Insert("INSERT INTO Reservation (UserName, CustomerEmail, CustomerFirstName, ProductBarcode, CreatedDate, ReservationStatus) " +
            "VALUES (#{userName}, #{customerEmail}, #{customerFirstName}, #{productBarcode}, #{createdDate}, #{reservationStatus})")
    void insert(Reservation reservation);

    @Update("UPDATE Reservation SET UserName = #{userName}, CustomerEmail = #{customerEmail}, CustomerFirstName = #{customerFirstName}, " +
            "ProductBarcode = #{productBarcode}, CreatedDate = #{createdDate}, ReservationStatus = #{reservationStatus} " +
            "WHERE ReservationID = #{reservationID}")
    void update(Reservation reservation);

    @Select("SELECT * FROM Reservation ORDER BY ReservationID DESC")
    List<Reservation> selectAll();


}
