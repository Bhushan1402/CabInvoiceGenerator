package com.invoice;

import org.junit.Assert;
import org.junit.Test;

public class RideRepositoryTest
{
   String userId = "a@b";

   @Test
   public void givenUserIdAndRides_ShouldAddDataToRepositoryAndGivesCount()
   {
      RideRepository rideRepository = new RideRepository();
      Ride[] ride = {new Ride(2.0, 5, InvoiceService.RideType.PREMIUM),
            new Ride(0.1, 1, InvoiceService.RideType.NORMAL),
            new Ride(0.1, 1, InvoiceService.RideType.NORMAL)
      };
      rideRepository.addRide(userId, ride);
      Ride[] rides1 = rideRepository.getRides(userId);
      int size = rides1.length;
      Assert.assertEquals(3, size);

   }

   @Test
   public void givenUserIdAndNullRides_ShouldReturnCountZero()
   {
      RideRepository rideRepository = new RideRepository();
      Ride[] ride = {};
      rideRepository.addRide(userId, ride);
      Ride[] rides1 = rideRepository.getRides(userId);
      int size = rides1.length;
      Assert.assertEquals(0, size);
   }


   @Test
   public void givenUserIdAndMultipleRides_ShouldReturnExactCount()
   {
      RideRepository rideRepository = new RideRepository();
      Ride[] firstSetOfRides = {
            new Ride(2.0, 5, InvoiceService.RideType.PREMIUM),
            new Ride(0.1, 1, InvoiceService.RideType.NORMAL),
            new Ride(0.1, 1, InvoiceService.RideType.NORMAL)
      };
      Ride[] secondSetOfRide = {
            new Ride(2.0, 5, InvoiceService.RideType.PREMIUM),
            new Ride(0.1, 1, InvoiceService.RideType.NORMAL),
            new Ride(0.1, 1, InvoiceService.RideType.NORMAL)
      };
      rideRepository.addRide(userId, firstSetOfRides);
      rideRepository.addRide(userId, secondSetOfRide);
      Ride[] rides1= rideRepository.getRides(userId);
      int actualValue = rides1.length;
      Assert.assertEquals(6, actualValue);
   }

   @Test
   public void givenMultipleUserIdAndRides_ShouldReturnExactCountOfDefinedUserId()
   {
      RideRepository rideRepository = new RideRepository();
      Ride[] firstSetOfRides = {
            new Ride(2.0, 5, InvoiceService.RideType.PREMIUM),
            new Ride(0.1, 1, InvoiceService.RideType.NORMAL),
            new Ride(0.1, 1, InvoiceService.RideType.NORMAL)
      };
      Ride[] secondSetOfRide = {
            new Ride(2.0, 5, InvoiceService.RideType.PREMIUM),
            new Ride(0.1, 1, InvoiceService.RideType.NORMAL),
            new Ride(0.1, 1, InvoiceService.RideType.NORMAL)
      };
      rideRepository.addRide("a@b", firstSetOfRides);
      rideRepository.addRide("b@a", secondSetOfRide);
      Ride[] rides1 = rideRepository.getRides("a@b");
      int size = rides1.length;
      Assert.assertEquals(3, size);
   }
}
