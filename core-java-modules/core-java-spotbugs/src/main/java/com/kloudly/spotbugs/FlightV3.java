package com.kloudly.spotbugs;

import java.time.LocalDateTime;

/**
 * This Flight class uses LocalDateTime  to avoid
 * returning a reference to a mutable object.
 * LocalDateTime is by definition not mutable
 */
public class FlightV3 {
    private LocalDateTime departureTime;
    private LocalDateTime arrivalTime;

    public LocalDateTime getDepartureTime() {
        return departureTime;
    }

    public void setDepartureTime(LocalDateTime departureTime) {
        this.departureTime = departureTime;
    }

    public LocalDateTime getArrivalTime() {
        return arrivalTime;
    }

    public void setArrivalTime(LocalDateTime arrivalTime) {
        this.arrivalTime = arrivalTime;
    }
}