/**
 * Author: Bintong
 * Date: 2024/7/17 13:54
 */

package com.project.springboot.service;

import com.project.springboot.entity.Reservation;
import com.project.springboot.mapper.ReservationMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ReservationService {

    @Autowired
    ReservationMapper reservationMapper;


    public void insertReservation(Reservation reservation) {
        reservationMapper.insert(reservation);
    }

    public void updateReservation(Reservation reservation) {
        reservationMapper.update(reservation);
    }

    public List<Reservation> selectAll() {
        return reservationMapper.selectAll();
    }
}
