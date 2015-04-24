package com.bcus.letspark.parking;

import com.bcus.letspark.strategy.NormalParkingStrategy;
import com.bcus.letspark.traveller.CarSize;
import com.bcus.letspark.traveller.Traveller;
import junit.framework.Assert;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;
import com.bcus.letspark.traveller.Car;

import java.util.ArrayList;
import java.util.List;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class ParkingAttendantTest {

    @Rule
    public ExpectedException expectedException = ExpectedException.none();


    @Test
    public void shouldNotBeAbleToHaveAttendantWithNoParkingLots() throws Exception {
        expectedException.expect(Exception.class);
        expectedException.expectMessage(ParkingLotAttendant.PARKING_LOT_IS_EMPTY);
        List<ParkingLot> parkingLots = null;
        ParkingLotAttendant parkingLotAttendant = new ParkingLotAttendant(parkingLots);
    }

    @Test
    public void shouldNotBeAbleToReturnAParkingLotWhenAllAreParkingFull() throws Exception {
        List<ParkingLot> parkingLots = new ArrayList<>();
        int parkingLotSize = 5;
        ParkingLot parkingLot = mock(ParkingLot.class);
        when(parkingLot.isFull()).thenReturn(true);
        parkingLots.add(parkingLot);
        Car car = new Car("Red", CarSize.SMALL, "some car id");
        ParkingLotAttendant parkingLotAttendant = new ParkingLotAttendant(parkingLots);


        ParkingLot parkingLotReturned = parkingLotAttendant.getMeFreeParkingLot(car);
        Assert.assertNull(parkingLotReturned);

    }

    @Test
    public void shouldBeAbleToParkInFreeParkingSlotAvailable() throws Exception {
        List<ParkingLot> parkingLots = new ArrayList<>();
        int parkingLotSize = 5;
        ParkingLot parkingLot = mock(ParkingLot.class);
        when(parkingLot.isFull()).thenReturn(true);
        parkingLots.add(parkingLot);
        Car car = new Car("Red", CarSize.SMALL, "some car id");

        ParkingLotAttendant parkingLotAttendant = new ParkingLotAttendant(parkingLots);


        ParkingLot parkingLotReturned = parkingLotAttendant.getMeFreeParkingLot(car);
        Assert.assertNull(parkingLotReturned);

    }




    @Test
    public void shouldBeAbleToUnparkCarFromParkingTicket() throws Exception {
        List<ParkingLot> parkingLots = new ArrayList<>();
        int parkingLotSize = 5;
        ParkingLot parkingLot = new ParkingLot("parking id", parkingLotSize);
        parkingLots.add(parkingLot);
        Car car = new Car("Red", CarSize.SMALL,"some car id");
        ParkingLotAttendant parkingLotAttendant = new ParkingLotAttendant(parkingLots);
        Ticket ticket = parkingLot.parkCar(car);
        Car carReturned = parkingLotAttendant.unparkCar(ticket);
        Assert.assertEquals(carReturned, car);
    }


    @Test
    public void shouldBeAbleToParkBigCarInAParkingLotWithMostFreeSpace() throws Exception {
        List<ParkingLot> parkingLots = new ArrayList<>();
        int parkingLotSize = 5;
        ParkingLot parkingLotOne = new ParkingLot("parking id_one", parkingLotSize);
        parkingLotSize = 2;
        ParkingLot parkingLotTwo = new ParkingLot("parking id_two", parkingLotSize);
        parkingLotSize = 10;
        ParkingLot parkingLotMostFreeSpace = new ParkingLot("parking id_three", parkingLotSize);
        parkingLots.add(parkingLotOne);
        parkingLots.add(parkingLotTwo);
        parkingLots.add(parkingLotMostFreeSpace);
        Car carToBeParked = new Car("Red", CarSize.BIG,"some car id");
        ParkingLotAttendant parkingLotAttendant = new ParkingLotAttendant(parkingLots);
        parkingLotAttendant.setParkingStrategy(new NormalParkingStrategy());
        Ticket ticket = parkingLotAttendant.parkCar(carToBeParked);
        Assert.assertEquals(parkingLotMostFreeSpace.getParkingLotId(),ticket.getParkingLotId());
    }



}
