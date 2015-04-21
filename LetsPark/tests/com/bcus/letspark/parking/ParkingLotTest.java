package com.bcus.letspark.parking;

import org.junit.Test;

import static org.junit.Assert.assertNotNull;

/**
 * Created by sawai on 4/21/2015.
 */
public class ParkingLotTest {

    @Test
    public void createParkingLot()
    {
        ParkingLot parkingLot = new ParkingLot();
        assertNotNull(parkingLot);
    }

    @Test
    public void addCarToParkingLotShouldReturnParkingToken() throws Exception {
        ParkingLot parkingLot = new ParkingLot();
        Car car = new Car("some id");
        Integer parkingToken = parkingLot.parkMyCar(car);
        assertNotNull(parkingToken);
    }

    @Test
    public void parkingTokenShouldNotBeLessThanOne() throws Exception {
        ParkingLot parkingLot = new ParkingLot();
        Car car = new Car("some id");
        Integer parkingToken = parkingLot.parkMyCar(car);
        assert(parkingToken > 0);
    }

    @Test(expected = Exception.class)
    public void shouldNotParkSameCarTwice() throws Exception {
        ParkingLot parkingLot = new ParkingLot();
        Car carOne = new Car("some id");
        Car sameCar = new Car("some id");
        parkingLot.parkMyCar(sameCar);
        Integer parkingToken = parkingLot.parkMyCar(sameCar);
    }
}
