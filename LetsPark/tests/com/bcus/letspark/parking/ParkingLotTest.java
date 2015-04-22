package com.bcus.letspark.parking;

import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;
import traveller.Car;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.*;

public class ParkingLotTest {

    @Rule
    public final ExpectedException expectedException = ExpectedException.none();
    private final int parkingSize = 2;

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
        boolean isCarParked = parkingLot.parkCar(car);
        assertTrue(isCarParked);
    }

    @Test
    public void parkingTokenShouldNotBeLessThanOne() throws Exception {
        ParkingLot parkingLot = new ParkingLot(parkingSize);
        Car car = new Car("some id");
        boolean isCarParked = parkingLot.parkCar(car);
        assertTrue(isCarParked);
    }

    @Test
    public void shouldNotParkSameCarTwice() throws Exception {
        expectedException.expect(Exception.class);
        expectedException.expectMessage(ParkingLot.SHOULD_NOT_PARK_SAME_CAR_TWICE);
        ParkingLot parkingLot = new ParkingLot(parkingSize);
        Car carOne = new Car("some id");
        parkingLot.parkCar(carOne);
        Car sameCar = carOne;
        parkingLot.parkCar(sameCar);
    }

    @Test
    public void getMyCarCorrectly() throws Exception {

        Car carToBeParked = new Car("some vehicle id");
        ParkingLot parkingLot = new ParkingLot(parkingSize);
        parkingLot.parkCar(carToBeParked);
        Car carReturnedFromParkingLot = parkingLot.getCarFromParking(carToBeParked.getVehicleIdentificationNumber());
        assertEquals(carToBeParked, carReturnedFromParkingLot);

    }

    @Test
    public void shouldNotBeAbleToGetMyCarTwice() throws Exception {
        expectedException.expect(Exception.class);
        expectedException.expectMessage(ParkingLot.CAR_NOT_PARKED_IN_PARKING_LOT);
        Car carToBeParked = new Car("some vehicle id");
        ParkingLot parkingLot = new ParkingLot(parkingSize);
        parkingLot.parkCar(carToBeParked);
        Car carReturnedFromParkingLot = parkingLot.getCarFromParking(carToBeParked.getVehicleIdentificationNumber());
        parkingLot.getCarFromParking(carToBeParked.getVehicleIdentificationNumber());
    }

    @Test
    public void shouldNotReturnCarIfNotParked() throws Exception {
        expectedException.expect(Exception.class);
        ParkingLot parkingLot = new ParkingLot(parkingSize);
        expectedException.expectMessage(ParkingLot.CAR_NOT_PARKED_IN_PARKING_LOT);
        parkingLot.getCarFromParking("SomeCarThatDoesNotExist");

    }

    @Test
    public void notAbleToParkWhenParkingLotIsFull() throws Exception {

        expectedException.expect(Exception.class);
        expectedException.expectMessage(ParkingLot.PARKING_LOT_IS_FULL);
        int parkingSize = 1;
        ParkingLot parkinglot = new ParkingLot(parkingSize);
        Car firstCar = new Car("first car id");
        parkinglot.parkCar(firstCar);

        Car secondCar = new Car("second car id");
        boolean isParked =  parkinglot.parkCar(secondCar);
    }

    @Test
    public void shouldAbleToParkANewCarWhenCarIsRemovedFromFullParkingLot() throws Exception {

        int parkingSize = 1;
        ParkingLot parkinglot = new ParkingLot(parkingSize);
        Car firstCar = new Car("first car id");
        parkinglot.parkCar(firstCar);
        parkinglot.getCarFromParking(firstCar.getVehicleIdentificationNumber());
        Car secondCar = new Car("second car id");
        boolean isParked =  parkinglot.parkCar(secondCar);
        assertThat(isParked, is(true));
    }

}
