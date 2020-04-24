package com.invoice;

public class InvoiceService {
    private static final double MINIMUM_COST_PER_KILOMETER = 10;
    private static final double COST_PER_TIME = 1;
    private static final double MINIMUM_FARE = 5;
    private static final double PREMIUM_MINIMUM_COST_PER_KILOMETER = 10.0;
    private static final double PREMIUM_COST_PER_TIME = 5;
    private static final double PREMIUM_MINIMUM_FARE = 20;
    private RideRepository rideRepository=null;
    public enum RideType{
        NORMAL,PREMIUM;
    }
    
    public InvoiceService() {
        this.rideRepository = new RideRepository();
    }

    public double calculateFare(double distance, int time, RideType rideType) {
        if (rideType == RideType.PREMIUM) {
            double totalFare = distance * PREMIUM_MINIMUM_COST_PER_KILOMETER + time * PREMIUM_COST_PER_TIME;
            return Math.max(totalFare, PREMIUM_MINIMUM_FARE);
        } else {
            double totalFare = distance * MINIMUM_COST_PER_KILOMETER + time * COST_PER_TIME;
            return Math.max(totalFare, MINIMUM_FARE);
        }
    }
    public InvoiceSummary calculateFare(Ride[] rides) {
        double totalFare=0;
        for (Ride ride: rides){
            totalFare+=this.calculateFare(ride.distance, ride.time,ride.rideType);
        }
        return new InvoiceSummary(rides.length, totalFare);
    }

    public void addRides(String userId, Ride[] rides) {
        rideRepository.addRide(userId, rides);
    }

    public InvoiceSummary getInvoiceSummary(String userId) {
        return this.calculateFare(rideRepository.getRides(userId));
    }
}
