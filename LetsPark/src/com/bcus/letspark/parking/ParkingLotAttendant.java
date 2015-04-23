package com.bcus.letspark.parking;

import java.util.List;


public class ParkingLotAttendant {
    public static final String CAN_NOT_CREATE_ATTENDANT = "Parking Lot Required";

    public ParkingLotAttendant(List<ParkingLot> parkingLots) throws Exception {
        if(parkingLots == null || parkingLots.isEmpty()){
            throw  new Exception(CAN_NOT_CREATE_ATTENDANT);
        }
        this.parkingLots = parkingLots;
    }


    List<ParkingLot> parkingLots = null;

    public ParkingLot getMeFreeParkingLot() {
        for(ParkingLot parkingLot : parkingLots )
        {
            if(!parkingLot.isFull())
            {
                return parkingLot;
            }
        }
        return null;
    }
}