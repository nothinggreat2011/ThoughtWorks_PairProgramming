package com.bcus.letspark.parking;

import com.bcus.letspark.exceptions.EmptyInputException;

import java.util.Date;

/**
 * Created by sawai on 4/23/2015.
 */
public class Ticket {

    public static final String PARKING_TICKET_DATE_IS_EMPTY = "PARKING_TICKET_DATE_IS_EMPTY";
    public static final String PARKING_VEHICLE_ID_IS_EMPTY = "PARKING_VEHICLE_ID_IS_EMPTY";
    public static final String PARKING_LOT_ID_IS_EMPTY = "PARKING_LOT_ID_IS_EMPTY";
    private String parkingLotId;
    private String vehicleIdentificationNumber;
    private Date parkingTime;

    public Ticket(String parkingLotId, String vehicleIdentificationNumber,Date parkingTime) {
        if (parkingTime == null)
            throw new EmptyInputException(PARKING_TICKET_DATE_IS_EMPTY);
        else if(vehicleIdentificationNumber == null || vehicleIdentificationNumber.isEmpty())
            throw new EmptyInputException(PARKING_VEHICLE_ID_IS_EMPTY);
        else if(parkingLotId == null || parkingLotId.isEmpty())
            throw new EmptyInputException(PARKING_LOT_ID_IS_EMPTY);

        this.parkingLotId = parkingLotId;
        this.vehicleIdentificationNumber = vehicleIdentificationNumber;
        this.parkingTime = parkingTime;
    }
    public String getParkingLotId() {
        return parkingLotId;
    }

    public String getVehicleIdentificationNumber() {
        return vehicleIdentificationNumber;
    }


    public Date getParkingTime() {
        return parkingTime;
    }
}
