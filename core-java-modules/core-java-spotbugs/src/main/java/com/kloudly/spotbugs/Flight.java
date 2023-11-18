package com.kloudly.spotbugs;

import java.util.Date;

/**
 * This Flight class uses java.util.Date which is a mutable class.
 * The state of the fields departureTime and arrivalTime are exposed
 * to the external world, and can hence be updated.
 */
public class Flight {
    private Date departureTime;
    private Date arrivalTime;

    public Date getDepartureTime() {
        return departureTime;
    }

    public void setDepartureTime(Date departureTime) {
        this.departureTime = departureTime;
    }

    public Date getArrivalTime() {
        return arrivalTime;
    }

    public void setArrivalTime(Date arrivalTime) {
        this.arrivalTime = arrivalTime;
    }

}