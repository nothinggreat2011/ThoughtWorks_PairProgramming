package com.bcus.letspark.strategy;

import com.bcus.letspark.parking.ParkingLot;

import java.util.List;

/**
 * Created by sawai on 4/23/2015.
 */
public class NormalParkingStrategy implements ParkingStrategy {
    @Override
    public ParkingLot findParkingLot(List<ParkingLot> parkingLots) {
        for(ParkingLot parkingLot : parkingLots )
        {
            if(!parkingLot.isFull())
                return parkingLot;
        }
        return null;
    }
}