package com.kloudly.spotbugs;

import java.util.Date;

/**
 * This Flight class uses clone() method to avoid
 * returning a reference to a mutable object
 */
public class FlightV2 {
    private Date departureTime;
    private Date arrivalTime;

    public Date getDepartureTime() {
        if(departureTime != null){
            return (Date) departureTime.clone();
        }
        return null;
    }

    public void setDepartureTime(Date departureTime) {
        if(departureTime != null){
            this.departureTime = (Date) departureTime.clone();
        }
    }

    public Date getArrivalTime() {
        if(arrivalTime != null){
            return (Date) arrivalTime.clone();
        }
        return null;
    }

    public void setArrivalTime(Date arrivalTime) {
        if(arrivalTime != null){
            this.arrivalTime = (Date) arrivalTime.clone();
        }
    }
}