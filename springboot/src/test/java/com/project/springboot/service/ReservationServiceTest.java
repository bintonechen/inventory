/**
 * Author: Bintong
 * Date: 2024/7/17 13:54
 */

package com.project.springboot.service;

import com.project.springboot.entity.Reservation;
import com.project.springboot.mapper.ReservationMapper;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class ReservationServiceTest {

    @Mock
    private ReservationMapper reservationMapper;

    @InjectMocks
    private ReservationService reservationService;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    public void testInsertReservation() {
        Reservation reservation = new Reservation();
        reservation.setUserName("testUser");
        reservation.setCustomerEmail("testUser@example.com");
        reservation.setCustomerFirstName("Test");
        reservation.setProductBarcode("123456");
        reservation.setCreatedDate("2024-07-17");
        reservation.setReservationStatus("Active");

        reservationService.insertReservation(reservation);

        verify(reservationMapper, times(1)).insert(reservation);
    }

    @Test
    public void testUpdateReservation() {
        Reservation reservation = new Reservation();
        reservation.setReservationID("1");
        reservation.setUserName("testUser");
        reservation.setCustomerEmail("testUser@example.com");
        reservation.setCustomerFirstName("Test");
        reservation.setProductBarcode("123456");
        reservation.setCreatedDate("2024-07-17");
        reservation.setReservationStatus("Active");

        reservationService.updateReservation(reservation);

        verify(reservationMapper, times(1)).update(reservation);
    }

    @Test
    public void testSelectAllReservations() {
        List<Reservation> reservations = Arrays.asList(
                new Reservation(),
                new Reservation()
        );

        when(reservationMapper.selectAll()).thenReturn(reservations);

        List<Reservation> result = reservationService.selectAll();

        assertEquals(2, result.size());
        verify(reservationMapper, times(1)).selectAll();
    }
}
