package com.example.demo.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.util.Random;

@Service
public class CarBookingService {

    private static final Logger log = LoggerFactory.getLogger(CarBookingService.class);
    private final Random random = new Random();

    public boolean prepare(String bookingId) {
        log.info("[CarService] Preparing booking for {}", bookingId);
        // Simulate a random failure
        if (random.nextBoolean()) {
            log.error("[CarService] Failed to prepare booking for {}", bookingId);
            return false;
        }
        log.info("[CarService] Prepared booking for {}", bookingId);
        return true;
    }

    public void commit(String bookingId) {
        log.info("[CarService] Committing booking for {}", bookingId);
        log.info("[CarService] Committed booking for {}", bookingId);
    }

    public void rollback(String bookingId) {
        log.info("[CarService] Rolling back booking for {}", bookingId);
        log.info("[CarService] Rolled back booking for {}", bookingId);
    }
}
