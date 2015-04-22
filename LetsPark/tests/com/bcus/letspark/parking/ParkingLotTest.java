package com.bcus.letspark.parking;

import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

public class ParkingLotTest {

    @Rule
    public ExpectedException expectedException = ExpectedException.none();

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
        boolean isCarParked = parkingLot.parkMyCar(car);
        assertTrue(isCarParked);
    }

    @Test
    public void parkingTokenShouldNotBeLessThanOne() throws Exception {
        ParkingLot parkingLot = new ParkingLot();
        Car car = new Car("some id");
        boolean isCarParked = parkingLot.parkMyCar(car);
        assertTrue(isCarParked);
    }

    @Test
    public void shouldNotParkSameCarTwice() throws Exception {
        expectedException.expect(Exception.class);
        expectedException.expectMessage(ParkingLot.SHOULD_NOT_PARK_SAME_CAR_TWICE);
        ParkingLot parkingLot = new ParkingLot();
        Car carOne = new Car("some id");
        Car sameCar = new Car("some id");
        parkingLot.parkMyCar(sameCar);
    }

    @Test
    public void getMyCarCorrectly() throws Exception {

        Car carToBeParked = new Car("some vehicle id");
        ParkingLot parkingLot = new ParkingLot();
        parkingLot.parkMyCar(carToBeParked);
        Car carReturedFromParkingLot = parkingLot.getMyCar(carToBeParked.getVehicleIdentificationNumber());
        assertEquals(carToBeParked, carReturedFromParkingLot);

    }

    @Test
    public void shouldNotBeAbleToGetMyCarTwice() throws Exception {
        expectedException.expect(Exception.class);
        expectedException.expectMessage(ParkingLot.CAR_NOT_PARKED_IN_PARKING_LOT);
        Car carToBeParked = new Car("some vehicle id");
        ParkingLot parkingLot = new ParkingLot();
        parkingLot.parkMyCar(carToBeParked);
        Car carReturnedFromParkingLot = parkingLot.getMyCar(carToBeParked.getVehicleIdentificationNumber());
        parkingLot.getMyCar(carToBeParked.getVehicleIdentificationNumber());
    }

    @Test
    public void shouldNotReturnCarIfNotParked() throws Exception {
        expectedException.expect(Exception.class);
        ParkingLot parkingLot = new ParkingLot();
        expectedException.expectMessage(ParkingLot.CAR_NOT_PARKED_IN_PARKING_LOT);
        parkingLot.getMyCar("SomeCarThatDoesNotExist");

    }


}
