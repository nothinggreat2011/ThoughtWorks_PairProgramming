package com.bcus.letspark.strategy;

import com.bcus.letspark.parking.ParkingLot;
import com.bcus.letspark.traveller.CarSize;

import java.util.List;

public interface ParkingStrategy {


    public ParkingLot findParkingLot(List<ParkingLot> parkingLots, CarSize carSize);
}
