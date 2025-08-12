package com.example.demo.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

@Service
public class TravelCoordinatorService {

    private static final Logger log = LoggerFactory.getLogger(TravelCoordinatorService.class);

    private final FlightBookingService flightService;
    private final CarBookingService carService;
    private final HotelBookingService hotelService;

    public TravelCoordinatorService(FlightBookingService flightService, CarBookingService carService, HotelBookingService hotelService) {
        this.flightService = flightService;
        this.carService = carService;
        this.hotelService = hotelService;
    }

    public boolean bookTravel(String bookingId) {
        log.info("Starting travel booking transaction for ID: {}", bookingId);

        // Phase 1: Prepare
        boolean flightPrepared = false;
        boolean carPrepared = false;
        boolean hotelPrepared = false;

        try {
            log.info("Coordinator: Preparing Flight Service...");
            flightPrepared = flightService.prepare(bookingId);
            if (!flightPrepared) {
                throw new Exception("Flight service failed to prepare.");
            }

            log.info("Coordinator: Preparing Car Service...");
            carPrepared = carService.prepare(bookingId);
            if (!carPrepared) {
                throw new Exception("Car service failed to prepare.");
            }

            log.info("Coordinator: Preparing Hotel Service...");
            hotelPrepared = hotelService.prepare(bookingId);
            if (!hotelPrepared) {
                throw new Exception("Hotel service failed to prepare.");
            }

        } catch (Exception e) {
            log.error("Coordinator: An error occurred during the prepare phase: {}. Starting rollback.", e.getMessage());
            // Phase 2: Rollback
            if (flightPrepared) {
                flightService.rollback(bookingId);
            }
            if (carPrepared) {
                carService.rollback(bookingId);
            }
            // hotelPrepared will be false if it's the one that failed, so no need to check

            log.info("Coordinator: Transaction for {} has been rolled back.", bookingId);
            return false;
        }

        // Phase 2: Commit
        log.info("Coordinator: All services prepared successfully. Committing transaction.");
        flightService.commit(bookingId);
        carService.commit(bookingId);
        hotelService.commit(bookingId);
        log.info("Coordinator: Transaction for {} has been committed successfully.", bookingId);

        return true;
    }
}
