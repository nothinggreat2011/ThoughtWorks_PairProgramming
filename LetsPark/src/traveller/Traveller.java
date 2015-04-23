package traveller;

import com.bcus.letspark.parking.ParkingLot;

/**
 * Created by sawai on 4/22/2015.
 */
public class Traveller {
    public static final String NO_PARKING_LOT_SPECIFIED_TO_PARK_CAR = "No parking lot specified to park car";
    private ParkingLot parkingLot;
    private Car myCar = null;

    /*public boolean travel() throws Exception {


    }*/

    public boolean parkMyCar(ParkingLot parkingLot) throws Exception {
        if(parkingLot == null) {
            throw new Exception(NO_PARKING_LOT_SPECIFIED_TO_PARK_CAR);
        }
        myCar = new Car("some vehicle id");
        boolean isCarParked = parkingLot.parkCar(myCar);
        this.parkingLot = parkingLot;
        System.out.println("I am flying out to my client's site. My car is parked. ");
        return isCarParked;
    }

    public boolean getMyCar() throws Exception {

        System.out.println("Please get my car");
        Car carReturned = parkingLot.getCarFromParking(myCar.getVehicleIdentificationNumber());
        System.out.println("Driving my returned car.");
        return true;
    }
}
