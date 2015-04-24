package com.bcus.letspark.traveller;

import com.bcus.letspark.exceptions.EmptyInputException;
import com.bcus.letspark.parking.ParkingLot;
import com.bcus.letspark.parking.ParkingLotAttendant;
import com.bcus.letspark.parking.Ticket;


public class Traveller {
    public static final String NO_PARKING_LOT_SPECIFIED_TO_PARK_CAR = "No parking lot specified to park car";
    public static final String CAR_NOT_PARKED = "Car is not parked in the parking lot.";
    public static String NO_PARKING_ATTENDANT_ASSIGNED = "No parking lot attended assigned. ";
    private Car myCar = null;
    private Ticket ticket;
    public Traveller(Car car) {
        myCar = car;
    }



    public boolean parkMyCar(ParkingLotAttendant parkingLotAttendant) throws Exception {
        if(parkingLotAttendant == null){

            throw new EmptyInputException(NO_PARKING_ATTENDANT_ASSIGNED);
        }
        ticket = parkingLotAttendant.parkCar(myCar);
        System.out.println("I am flying out to my client's site. My car is parked. ");

        return true;
    }

    public Car getMyCar(ParkingLotAttendant parkingLotAttendant) throws Exception {
        if(ticket == null)
        {
            throw new Exception(CAR_NOT_PARKED);
        }
        System.out.println("Please get my car");
        Car carReturned = parkingLotAttendant.unparkCar(ticket);
        System.out.println("Driving my returned car.");
        return carReturned;
    }
}
