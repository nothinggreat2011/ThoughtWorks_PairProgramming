package traveller;

import com.bcus.letspark.parking.Car;
import com.bcus.letspark.parking.ParkingLot;

/**
 * Created by sawai on 4/21/2015.
 */
public class Traveller {
    public static void main(String[] args) throws Exception {
        Car myCar = new Car("This is my car");
        ParkingLot parkingLot = new ParkingLot();
        boolean isCarParked = parkingLot.parkMyCar(myCar);
        System.out.println("I am flying out to my client's site. My car is parked with parking token ");
        System.out.println("Please get my car");
        Car carReturned = parkingLot.getMyCar(myCar.getVehicleIdentificationNumber());
        System.out.println("Driving my returned car.");
    }
}
