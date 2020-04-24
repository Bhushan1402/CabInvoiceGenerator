package com.invoice;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

public class RideRepository {
    Map<String, ArrayList<Ride>> userRide = null;

    public RideRepository() {
        this.userRide = new HashMap<>();
    }

    public void addRide(String userId, Ride[] rides) {
        ArrayList<Ride> rideList = this.userRide.get(userId);
        if (rideList == null)
            this.userRide.put(userId, new ArrayList<>(Arrays.asList(rides)));
        else{
            rideList.addAll(Arrays.asList(rides));
            this.userRide.put(userId,rideList);
        }
    }

    public Ride[] getRides(String userId) {
        return this.userRide.get(userId).toArray(new Ride[0]);
    }
}
