package com.bcus.letspark.parking;

import java.util.List;

/**
 * Created by sawai on 4/23/2015.
 */
public interface ParkingStrategy {


    public ParkingLot findParkingLot(List<ParkingLot> parkingLots);
}
