package traveller;

import com.bcus.letspark.parking.ParkingLot;
import com.bcus.letspark.parking.ParkingLotAttendant;

import java.util.ArrayList;
import java.util.List;

public class ParkingApplication {
    public static void main(String[] args) throws Exception {

        ParkingLot wingAParkingLot = new ParkingLot("Wing A",2);
        ParkingLot wingBParkingLot = new ParkingLot("Wing B", 5);
        List<ParkingLot> parkingLotList = new ArrayList<>();
        parkingLotList.add(wingAParkingLot);
        parkingLotList.add(wingBParkingLot);
        ParkingLotAttendant parkingLotAttendant = new ParkingLotAttendant(parkingLotList);
        Car travellerCar = new Car("My car");
        Traveller traveller = new Traveller(travellerCar);
        traveller.parkMyCar(parkingLotAttendant);
        traveller.getMyCar(parkingLotAttendant);
    }
}
