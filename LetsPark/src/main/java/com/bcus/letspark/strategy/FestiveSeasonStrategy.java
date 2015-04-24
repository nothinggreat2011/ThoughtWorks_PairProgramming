package com.bcus.letspark.strategy;

import com.bcus.letspark.parking.ParkingLot;
import com.bcus.letspark.traveller.CarSize;

import java.util.List;

/**
 * Created by sawai on 4/23/2015.
 */
public class FestiveSeasonStrategy implements ParkingStrategy {
    @Override
    public ParkingLot findParkingLot(List<ParkingLot> parkingLots, CarSize carSize) {
        ParkingLot parkingLotWithMaxSize = null;
        for (ParkingLot parkingLot : parkingLots) {
            if (!parkingLot.isFull()) {
                if (parkingLotWithMaxSize == null)
                    parkingLotWithMaxSize = parkingLot;
                else if (parkingLotWithMaxSize.getParkingSize() < parkingLot.getParkingSize())
                    parkingLotWithMaxSize = parkingLot;
            }
        }
        return parkingLotWithMaxSize;
    }
}