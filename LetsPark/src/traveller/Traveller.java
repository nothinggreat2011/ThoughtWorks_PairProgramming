package traveller;

import com.bcus.letspark.parking.ParkingLot;
import com.bcus.letspark.parking.ParkingLotAttendant;


public class Traveller {
    public static final String NO_PARKING_LOT_SPECIFIED_TO_PARK_CAR = "No parking lot specified to park car";
    public static final String CAR_NOT_PARKED = "Car is not parked in the parking lot.";
    public static String NO_PARKING_ATTENDANT_ASSIGNED = "No parking lot attended assigned. ";
    private ParkingLot parkingLot;
    private Car myCar = null;

    public Traveller(Car car) {
        myCar = car;
    }


    /*public boolean travel() throws Exception {


    }*/

    public boolean parkMyCar(ParkingLotAttendant parkingLotAttendant) throws Exception {
        if(parkingLotAttendant == null)
        {
            throw new Exception(NO_PARKING_ATTENDANT_ASSIGNED);
        }
        parkingLot = parkingLotAttendant.getMeFreeParkingLot();
        if(parkingLot == null) {
            throw new Exception(NO_PARKING_LOT_SPECIFIED_TO_PARK_CAR);
        }
        boolean isCarParked = parkingLot.parkCar(myCar);
        System.out.println("I am flying out to my client's site. My car is parked. ");
        return isCarParked;
    }

    public Car getMyCar() throws Exception {
        if(parkingLot == null)
        {
            throw new Exception(CAR_NOT_PARKED);
        }
        System.out.println("Please get my car");
        Car carReturned = parkingLot.getCarFromParking(myCar.getVehicleIdentificationNumber());
        System.out.println("Driving my returned car.");
        return carReturned;
    }
}
