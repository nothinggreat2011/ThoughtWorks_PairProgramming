package com.bcus.letspark.parking;

/**
 * Created by sawai on 4/23/2015.
 */
public class Ticket {

    private String parkingLotId;
    private String vehicleIdentificationNumber;

    public Ticket(String parkingLotId, String vehicleIdentificationNumber) {
        this.parkingLotId = parkingLotId;
        this.vehicleIdentificationNumber = vehicleIdentificationNumber;
    }
    public String getParkingLotId() {
        return parkingLotId;
    }

    public String getVehicleIdentificationNumber() {
        return vehicleIdentificationNumber;
    }

}
