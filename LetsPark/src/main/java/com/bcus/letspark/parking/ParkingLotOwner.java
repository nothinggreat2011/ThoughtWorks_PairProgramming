package com.bcus.letspark.parking;

import java.util.Observable;
import java.util.Observer;

public class ParkingLotOwner implements Observer {

    private final String PARKING_FULL = "PARKING_FULL";
    private final String PARKING_AVAILABLE = "PARKING_AVAILABLE";
    private boolean isParkingFull;

    @Override
    public void update(Observable o, Object parkingState) {
        if(parkingState instanceof String)
        {
            String currentState = (String) parkingState;
            isParkingFull = currentState.equals(PARKING_FULL);
            System.out.println(currentState);
        }
    }
}
