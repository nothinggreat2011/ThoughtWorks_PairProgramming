package com.bcus.letspark.parking;

import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.*;

public class ParkingLotTest {

    @Rule
    public ExpectedException expectedException = ExpectedException.none();
    private int parkingSize = 2;

    @Test
    public void createParkingLot()
    {
        ParkingLot parkingLot = new ParkingLot(parkingSize);
        assertNotNull(parkingLot);
    }

    @Test
    public void addCarToParkingLotShouldReturnParkingToken() throws Exception {
        ParkingLot parkingLot = new ParkingLot(parkingSize);
        Car car = new Car("some id");
        boolean isCarParked = parkingLot.parkMyCar(car);
        assertTrue(isCarParked);
    }

    @Test
    public void parkingTokenShouldNotBeLessThanOne() throws Exception {
        ParkingLot parkingLot = new ParkingLot(parkingSize);
        Car car = new Car("some id");
        boolean isCarParked = parkingLot.parkMyCar(car);
        assertTrue(isCarParked);
    }

    @Test
    public void shouldNotParkSameCarTwice() throws Exception {
        expectedException.expect(Exception.class);
        expectedException.expectMessage(ParkingLot.SHOULD_NOT_PARK_SAME_CAR_TWICE);
        ParkingLot parkingLot = new ParkingLot(parkingSize);
        Car carOne = new Car("some id");
        parkingLot.parkMyCar(carOne);
        Car sameCar = carOne;
        parkingLot.parkMyCar(sameCar);
    }

    @Test
    public void getMyCarCorrectly() throws Exception {

        Car carToBeParked = new Car("some vehicle id");
        ParkingLot parkingLot = new ParkingLot(parkingSize);
        parkingLot.parkMyCar(carToBeParked);
        Car carReturedFromParkingLot = parkingLot.getMyCar(carToBeParked.getVehicleIdentificationNumber());
        assertEquals(carToBeParked, carReturedFromParkingLot);

    }

    @Test
    public void shouldNotBeAbleToGetMyCarTwice() throws Exception {
        expectedException.expect(Exception.class);
        expectedException.expectMessage(ParkingLot.CAR_NOT_PARKED_IN_PARKING_LOT);
        Car carToBeParked = new Car("some vehicle id");
        ParkingLot parkingLot = new ParkingLot(parkingSize);
        parkingLot.parkMyCar(carToBeParked);
        Car carReturnedFromParkingLot = parkingLot.getMyCar(carToBeParked.getVehicleIdentificationNumber());
        parkingLot.getMyCar(carToBeParked.getVehicleIdentificationNumber());
    }

    @Test
    public void shouldNotReturnCarIfNotParked() throws Exception {
        expectedException.expect(Exception.class);
        ParkingLot parkingLot = new ParkingLot(parkingSize);
        expectedException.expectMessage(ParkingLot.CAR_NOT_PARKED_IN_PARKING_LOT);
        parkingLot.getMyCar("SomeCarThatDoesNotExist");

    }

    @Test
    public void notAbleToParkWhenParkingLotIsFull() throws Exception {

        expectedException.expect(Exception.class);
        expectedException.expectMessage(ParkingLot.PARKING_LOT_IS_FULL);
        int parkingSize = 1;
        ParkingLot parkinglot = new ParkingLot(parkingSize);
        Car firstCar = new Car("first car id");
        parkinglot.parkMyCar(firstCar);

        Car secondCar = new Car("second car id");
        boolean isParked =  parkinglot.parkMyCar(secondCar);
    }

    @Test
    public void shouldAbleToParkANewCarWhenCarIsRemovedFromFullParkingLot() throws Exception {

        int parkingSize = 1;
        ParkingLot parkinglot = new ParkingLot(parkingSize);
        Car firstCar = new Car("first car id");
        parkinglot.parkMyCar(firstCar);
        parkinglot.getMyCar(firstCar.getVehicleIdentificationNumber());
        Car secondCar = new Car("second car id");
        boolean isParked =  parkinglot.parkMyCar(secondCar);
        assertThat(isParked, is(true));
    }

}
