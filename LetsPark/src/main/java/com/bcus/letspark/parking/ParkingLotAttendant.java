package com.bcus.letspark.parking;

import com.bcus.letspark.strategy.NormalParkingStrategy;
import com.bcus.letspark.strategy.ParkingStrategy;
import com.bcus.letspark.traveller.Car;

import java.util.List;


public class ParkingLotAttendant {
    public static final String CAN_NOT_CREATE_ATTENDANT = "Parking Lot Required";
    private static final String EMPTY_TICKET_PROVIDED = "No ticket is provided toun park the car.";
    private ParkingStrategy parkingStrategy;
    public ParkingLotAttendant(List<ParkingLot> parkingLots) throws Exception {

        if(parkingLots == null || parkingLots.isEmpty()){
            throw  new Exception(CAN_NOT_CREATE_ATTENDANT);
        }
        this.parkingLots = parkingLots;
        this.parkingStrategy = new NormalParkingStrategy();
    }


    List<ParkingLot> parkingLots = null;

    public ParkingLot getMeFreeParkingLot() {

        return parkingStrategy.findParkingLot(parkingLots);
    }

    public Car unparkCar(Ticket parkingTicket) throws Exception {

        if(parkingTicket == null)
        {
            throw new Exception(EMPTY_TICKET_PROVIDED);
        }
        for(ParkingLot parkingLot : parkingLots)
        {
            if(parkingLot.getParkingLotId().equals(parkingTicket.getParkingLotId()))
            {
                return parkingLot.getCarFromParking(parkingTicket.getVehicleIdentificationNumber());
            }
        }
        return null;
    }
    public void setParkingStrategy(ParkingStrategy strategy) {
        this.parkingStrategy = strategy;
    }

}