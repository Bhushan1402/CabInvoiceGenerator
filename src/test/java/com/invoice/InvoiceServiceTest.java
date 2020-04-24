package com.invoice;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

public class InvoiceServiceTest {
    InvoiceService invoiceService=null;
    RideRepository rideRepository=null;
    @Before
    public void setUp(){
        invoiceService=new InvoiceService();
        rideRepository=new RideRepository();
    }


    @Test
    public void givenDistanceTime_ShouldReturnTotalFare() {
        InvoiceService invoiceGenerator = new InvoiceService();
        double distance = 2.0;
        int time = 5;
        double fare = invoiceGenerator.calculateFare(distance, time, InvoiceService.RideType.NORMAL);
        Assert.assertEquals(25,fare,0.0);
    }

    @Test
    public void givenDistanceAndTime_ShouldReturnMinFare() {
        InvoiceService invoiceGenerator = new InvoiceService();
        double distance = 0.1;
        int time = 1;
        double fare = invoiceGenerator.calculateFare(distance,time, InvoiceService.RideType.NORMAL);
        Assert.assertEquals(5,fare,0.0);
    }

    @Test
    public void givenMultipleRides_ShouldReturnTotalFare() {
        Ride[] rides = { new Ride(2.0, 5, InvoiceService.RideType.NORMAL),
                       new Ride(0.1, 1, InvoiceService.RideType.NORMAL),
                     };
        InvoiceSummary summary = invoiceService.calculateFare(rides);
        InvoiceSummary expectedInvoiceSummary = new InvoiceSummary(2, 30);
        Assert.assertEquals(expectedInvoiceSummary,summary);
    }

    @Test
    public void givenUserIdAndRids_ShouldReturnInvoiceSummary() {
        String userId = "a@b.com";
        Ride[] rides = { new Ride(2.0, 5, InvoiceService.RideType.NORMAL),
                new Ride(0.1, 1, InvoiceService.RideType.NORMAL),
        };
        invoiceService.addRides(userId, rides);
       InvoiceSummary summary = invoiceService.getInvoiceSummary(userId);
        InvoiceSummary expectedInvoiceSummary = new InvoiceSummary(2, 30);
        Assert.assertEquals(expectedInvoiceSummary,summary);
    }

    @Test
    public void givenMultipleRiderTOTheRideRepository_ShouldReturnNumberOfRides() {
        Ride[] rides = { new Ride(2.0, 5, InvoiceService.RideType.NORMAL),
                new Ride(0.1, 1, InvoiceService.RideType.NORMAL),
        };
        String userId = "a@b.com";
        invoiceService.addRides(userId, rides);
        Ride[] rides1 = { new Ride(2.0, 5, InvoiceService.RideType.NORMAL),
                new Ride(0.1, 1, InvoiceService.RideType.NORMAL),
        };
        invoiceService.addRides(userId, rides1);
        InvoiceSummary summary = invoiceService.getInvoiceSummary(userId);
        Assert.assertEquals(4,summary.noOfRides);
    }

    @Test
    public void givenPremiumRider_ShouldReturnNumberOfRides() {
       // invoiceService.setRideType(InvoiceService.RideType.PREMIUM);
        Ride[] rides = { new Ride(2.0, 5, InvoiceService.RideType.PREMIUM),
                new Ride(0.1, 1, InvoiceService.RideType.PREMIUM),
        };
        String userId = "a@b.com";
        invoiceService.addRides(userId, rides);
        Ride[] rides1 = { new Ride(2.0, 5,InvoiceService.RideType.PREMIUM),
                new Ride(0.1, 1, InvoiceService.RideType.PREMIUM),
        };
        invoiceService.addRides(userId, rides1);
        InvoiceSummary summary = invoiceService.getInvoiceSummary(userId);
        Assert.assertEquals(130,summary.totalFare,0.0);
    }

    @Test
    public void givenDistanceTime_ShouldReturnMinPrmiumTotalFare() {
       // invoiceService.setRideType(InvoiceService.RideType.PREMIUM);
        InvoiceService invoiceGenerator = new InvoiceService();
        double distance = 1.0;
        int time = 1;
        double fare = invoiceGenerator.calculateFare(distance, time, InvoiceService.RideType.PREMIUM);
        Assert.assertEquals(20,fare,0.0);
    }

    @Test
    public void givenMultipleRides_ShouldReturnPremiumTotalFare() {
        InvoiceService invoiceGenerator = new InvoiceService();
        double distance = 0.1;
        int time = 1;
        Ride[] rides = { new Ride(1.0, 1, InvoiceService.RideType.PREMIUM),
                new Ride(0.1, 1, InvoiceService.RideType.PREMIUM),
        };
        InvoiceSummary summary = invoiceService.calculateFare(rides);
        InvoiceSummary expectedInvoiceSummary = new InvoiceSummary(2, 40);
        Assert.assertEquals(expectedInvoiceSummary,summary);
    }

    @Test
    public void givenUserIdAndRids_ShouldReturnPremiumInvoiceSummary() {
        String userId = "a@b.com";
        Ride[] rides = { new Ride(1.0, 1, InvoiceService.RideType.PREMIUM),
                new Ride(0.1, 1, InvoiceService.RideType.PREMIUM),
        };
        invoiceService.addRides(userId, rides);
        InvoiceSummary summary = invoiceService.getInvoiceSummary(userId);
        InvoiceSummary expectedInvoiceSummary = new InvoiceSummary(2, 40);
        Assert.assertEquals(expectedInvoiceSummary,summary);
    }

    @Test
    public void givenMultipleRiderTOTheRideRepository_ShouldReturnPremiumNumberOfRides() {
       // invoiceService.setRideType(InvoiceService.RideType.PREMIUM);
        Ride[] rides = { new Ride(0.1, 1, InvoiceService.RideType.PREMIUM),
                new Ride(0.1, 1, InvoiceService.RideType.NORMAL),
        };
        String userId = "a@b.com";
        invoiceService.addRides(userId, rides);
        Ride[] rides1 = { new Ride(0.1, 1, InvoiceService.RideType.PREMIUM),
                new Ride(0.1, 1, InvoiceService.RideType.NORMAL),
        };
        invoiceService.addRides(userId, rides1);
        InvoiceSummary summary = invoiceService.getInvoiceSummary(userId);
        InvoiceSummary expectedSummary = new InvoiceSummary(4,50);
        Assert.assertEquals(expectedSummary,summary);
    }
}
