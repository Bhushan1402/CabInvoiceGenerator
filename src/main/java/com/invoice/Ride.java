package com.invoice;

public class Ride {
    public final double distance;
    public final int time;
    InvoiceService.RideType rideType;
    public Ride(double distance, int time,InvoiceService.RideType rideType) {
        this.distance=distance;
        this.time=time;
        this.rideType = rideType;
    }

}
