package com.example.demo.controller;

import com.example.demo.service.TravelCoordinatorService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@RestController
@RequestMapping("/api")
public class TravelBookingController {

    private final TravelCoordinatorService travelCoordinatorService;

    public TravelBookingController(TravelCoordinatorService travelCoordinatorService) {
        this.travelCoordinatorService = travelCoordinatorService;
    }

    @PostMapping("/book-travel")
    public ResponseEntity<String> bookTravel() {
        String bookingId = UUID.randomUUID().toString();
        boolean success = travelCoordinatorService.bookTravel(bookingId);

        if (success) {
            return ResponseEntity.ok("Travel booking successful with ID: " + bookingId);
        } else {
            return ResponseEntity.status(500).body("Travel booking failed. The transaction has been rolled back.");
        }
    }
}
