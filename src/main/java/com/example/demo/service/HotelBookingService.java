package com.example.demo.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

@Service
public class HotelBookingService {

    private static final Logger log = LoggerFactory.getLogger(HotelBookingService.class);

    public boolean prepare(String bookingId) {
        log.info("[HotelService] Preparing booking for {}", bookingId);
        log.info("[HotelService] Prepared booking for {}", bookingId);
        return true;
    }

    public void commit(String bookingId) {
        log.info("[HotelService] Committing booking for {}", bookingId);
        log.info("[HotelService] Committed booking for {}", bookingId);
    }

    public void rollback(String bookingId) {
        log.info("[HotelService] Rolling back booking for {}", bookingId);
        log.info("[HotelService] Rolled back booking for {}", bookingId);
    }
}
