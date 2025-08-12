package com.example.demo.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

@Service
public class FlightBookingService {

    private static final Logger log = LoggerFactory.getLogger(FlightBookingService.class);

    public boolean prepare(String bookingId) {
        log.info("[FlightService] Preparing booking for {}", bookingId);
        // In a real scenario, we would reserve the flight here
        log.info("[FlightService] Prepared booking for {}", bookingId);
        return true;
    }

    public void commit(String bookingId) {
        log.info("[FlightService] Committing booking for {}", bookingId);
        // In a real scenario, we would confirm the flight reservation
        log.info("[FlightService] Committed booking for {}", bookingId);
    }

    public void rollback(String bookingId) {
        log.info("[FlightService] Rolling back booking for {}", bookingId);
        // In a real scenario, we would cancel the flight reservation
        log.info("[FlightService] Rolled back booking for {}", bookingId);
    }
}
