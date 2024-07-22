/**
 * Author: Bintong
 * Date: 2024/7/17 13:52
 */

package com.project.springboot.controller;

import com.project.springboot.common.Result;
import com.project.springboot.entity.Customer;
import com.project.springboot.entity.Reservation;
import com.project.springboot.service.ReservationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.sql.SQLException;
import java.util.List;

@CrossOrigin
@RestController
@RequestMapping("/reservation")
public class ReservationController {

    @Autowired
    ReservationService reservationService;

    /**
     *  Create a new reservation
     */
    @PostMapping("/add")
    public Result add(@RequestBody Reservation reservation){
        try {
            reservationService.insertReservation(reservation);
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
     *  update an existing reservation
     */
    @PutMapping("/update")
    public Result update(@RequestBody Reservation reservation){
        try {
            reservationService.updateReservation(reservation);
        } catch(Exception e){
            return Result.error(e.getMessage());
        }
        return Result.success(reservation);
    }

    /**
     *  display all reservation
     */
    @GetMapping("/selectAll")
    public Result selectAll(){
        List<Reservation> reservationList = reservationService.selectAll();
        return Result.success(reservationList);
    }



}
