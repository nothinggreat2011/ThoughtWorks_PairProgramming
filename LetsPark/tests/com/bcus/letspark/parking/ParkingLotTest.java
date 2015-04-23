package com.bcus.letspark.parking;

import org.junit.Assert;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;
import traveller.Car;

import java.util.Observable;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.core.Is.is;
import static org.junit.Assert.*;
import static org.mockito.Matchers.any;
import static org.mockito.Matchers.anyString;
import static org.mockito.Mockito.*;

public class ParkingLotTest {

    ParkingLotOwner parkingLotOwner;

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

    @Test
    public void notifyOwnerWhenParkingIsFull() throws Exception {
        int parkingLotSize = 2;
        ParkingLot parkingLot = new ParkingLot(2);
        parkingLotOwner = mock(ParkingLotOwner.class);
        parkingLot.addObserver(parkingLotOwner);
        Car carOne = new Car("One");
        Car carTwo = new Car("two");
        parkingLot.parkCar(carOne);
        parkingLot.parkCar(carTwo);
        verify(parkingLotOwner,times(1)).update(parkingLot, "PARKING_FULL");
    }

    @Test
    public void notifyOwnerWhenParkingIsEmpty() throws Exception {
        int parkingLotSize = 2;
        ParkingLot parkingLot = new ParkingLot(parkingLotSize);
        parkingLotOwner = mock(ParkingLotOwner.class);
        parkingLot.addObserver(parkingLotOwner);

        Car carOne = new Car("One");
        Car carTwo = new Car("two");
        parkingLot.parkCar(carOne);
        parkingLot.parkCar(carTwo);

        parkingLot.getCarFromParking(carOne.getVehicleIdentificationNumber());
        parkingLot.getCarFromParking(carTwo.getVehicleIdentificationNumber());
        verify(parkingLotOwner,times(2)).update((Observable) any(), anyString());

    }

    @Test
    public void shouldNotNotifyOwnerWhenParkingIsEmpty() throws Exception {
        int parkingLotSize = 2;
        ParkingLot parkingLot = new ParkingLot(parkingLotSize);
        parkingLotOwner = mock(ParkingLotOwner.class);
        parkingLot.addObserver(parkingLotOwner);

        Car carOne = new Car("One");
        parkingLot.parkCar(carOne);

        parkingLot.getCarFromParking(carOne.getVehicleIdentificationNumber());
        verify(parkingLotOwner,never()).update((Observable) any(), anyString());

    }
    @Test
    public void ShouldBeAbleToValidateWhenGarageIs80PercentFull() throws Exception {
        int parkingLotSize = 5;
        ParkingLot parkingLot = new ParkingLot(parkingLotSize);
        Car carOne = new Car("One");
        Car carTwo = new Car("Two");
        Car carThree = new Car("Three");
        Car carFour = new Car("Four");
        parkingLot.parkCar(carOne);
        parkingLot.parkCar(carTwo);
        parkingLot.parkCar(carThree);
        parkingLot.parkCar(carFour);
        Assert.assertTrue(parkingLot.verify80PercentFull());
    }

    @Test
    public void ShouldBeAbleToValidateWhenGarageIsNot80PercentFull() throws Exception {
        int parkingLotSize = 4;
        ParkingLot parkingLot = new ParkingLot(parkingLotSize);
        Car carOne = new Car("One");
        Car carTwo = new Car("Two");
        parkingLot.parkCar(carOne);
        parkingLot.parkCar(carTwo);

        Assert.assertFalse(parkingLot.verify80PercentFull());
    }

    @Test
    public void shouldBeAbleNotifyFBIAgentWhenParkingIs80PercentFull() throws Exception {
        int parkingLotSize = 5;
        ParkingLot parkingLot = new ParkingLot(parkingLotSize);
        FBIAgent fbiAgent = mock(FBIAgent.class);
        parkingLot.addObserver((fbiAgent));

        Car carOne = new Car("One");
        Car carTwo = new Car("Two");
        Car carThree = new Car("Three");
        Car carFour = new Car("Four");
        parkingLot.parkCar(carOne);
        parkingLot.parkCar(carTwo);
        parkingLot.parkCar(carThree);
        parkingLot.parkCar(carFour);
        verify(fbiAgent,times(1)).update(parkingLot, "PARKING_EIGHTY_PERCENT_FULL");
    }



}
