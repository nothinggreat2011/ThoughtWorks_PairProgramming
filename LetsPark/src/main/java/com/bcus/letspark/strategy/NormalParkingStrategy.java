package com.bcus.letspark.strategy;

import com.bcus.letspark.parking.ParkingLot;
import com.bcus.letspark.traveller.CarSize;

import java.util.List;

/**
 * Created by sawai on 4/23/2015.
 */
public class NormalParkingStrategy implements ParkingStrategy {
    @Override
    public ParkingLot findParkingLot(List<ParkingLot> parkingLots, CarSize carSize) {

        if(carSize.equals(CarSize.BIG))
            return getParkingLotWithMostFreeParkingLeft(parkingLots);
        else if(carSize.equals(CarSize.SMALL))
            return getParkingLotWithFreeSpaceAvailable(parkingLots);

        return null;
    }

    private ParkingLot getParkingLotWithFreeSpaceAvailable(List<ParkingLot> parkingLots) {
        for (ParkingLot parkingLot : parkingLots) {
            if (!parkingLot.isFull())
                return parkingLot;
        }
        return null;
    }

    public ParkingLot getParkingLotWithMostFreeParkingLeft(List<ParkingLot> parkingLots) {
        ParkingLot parkingLotWithMostFreeParking = null;
        for (ParkingLot parkingLot : parkingLots) {
            if(!parkingLot.isFull()) {
                if (parkingLotWithMostFreeParking == null)
                    parkingLotWithMostFreeParking = parkingLot;

                else if (parkingLotWithMostFreeParking.getAvailableParkingSpace() < parkingLot.getAvailableParkingSpace())
                    parkingLotWithMostFreeParking = parkingLot;
            }
        }
        return parkingLotWithMostFreeParking;
    }


}
