package com.bcus.letspark.parking;

import java.util.Observable;
import java.util.Observer;

/**
 * Created by sawai on 4/22/2015.
 */
public class ParkingLotOwner implements Observer {

    private final String PARKING_FULL = "PARKING_FULL";
    private final String PARKING_AVAILABLE = "PARKING_AVAILABLE";
    private boolean isFull;
    public boolean isParkingFull() {
        return isFull;
    }

    @Override
    public void update(Observable o, Object parkingState) {
        if(parkingState instanceof String)
        {
            String currentState = (String) parkingState;
            isFull = currentState.equals(PARKING_FULL);
            System.out.println(currentState);
        }
    }
}
