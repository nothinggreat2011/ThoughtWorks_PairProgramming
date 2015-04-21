package traveller;

import com.bcus.letspark.parking.Car;
import com.bcus.letspark.parking.ParkingLot;

/**
 * Created by sawai on 4/21/2015.
 */
public class Traveller {
    public static void main(String[] args) throws Exception {
        Car myCar = new Car("Yadu's Car");
        ParkingLot parkingLot = new ParkingLot();
        int parkingToken = parkingLot.parkMyCar(myCar);
        parkingToken = parkingLot.parkMyCar(myCar);
        System.out.println("I am flying out to my client's site. My car is parked with parking token " + parkingToken);
    }
}
