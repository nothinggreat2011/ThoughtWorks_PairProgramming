package com.bcus.letspark.strategy;

import com.bcus.letspark.parking.ParkingLot;

import java.util.List;

/**
 * Created by sawai on 4/23/2015.
 */
public interface ParkingStrategy {


    public ParkingLot findParkingLot(List<ParkingLot> parkingLots);
}
