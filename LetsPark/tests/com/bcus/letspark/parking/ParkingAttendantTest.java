package com.bcus.letspark.parking;

import junit.framework.Assert;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;
import traveller.Car;

import java.util.ArrayList;
import java.util.List;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class ParkingAttendantTest {

    @Rule
    public ExpectedException expectedException = ExpectedException.none();

    @Test
    public void shouldBeAbleToProvideAFreeParkingLot() throws Exception {
        List<ParkingLot> parkingLots = new ArrayList<>();
        int parkingLotSize = 5;
        ParkingLot parkingLot = new ParkingLot("some parking id", parkingLotSize);
        parkingLots.add(parkingLot);

        ParkingLotAttendant parkingLotAttendant = new ParkingLotAttendant(parkingLots);
        ParkingLot parkingLotReturned = parkingLotAttendant.getMeFreeParkingLot();
        Assert.assertEquals(parkingLot, parkingLotReturned);

    }

    @Test
    public void shouldNotBeAbleToHaveAttendantWithNoParkingLots() throws Exception {
        expectedException.expect(Exception.class);
        expectedException.expectMessage(ParkingLotAttendant.CAN_NOT_CREATE_ATTENDANT);
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

        ParkingLotAttendant parkingLotAttendant = new ParkingLotAttendant(parkingLots);


        ParkingLot parkingLotReturned = parkingLotAttendant.getMeFreeParkingLot();
        Assert.assertNull(parkingLotReturned);

    }

    @Test
    public void shouldBeAbleToParkInFreeParkingSlotAvailable() throws Exception {
        List<ParkingLot> parkingLots = new ArrayList<>();
        int parkingLotSize = 5;
        ParkingLot parkingLot = mock(ParkingLot.class);
        when(parkingLot.isFull()).thenReturn(true);
        parkingLots.add(parkingLot);

        ParkingLotAttendant parkingLotAttendant = new ParkingLotAttendant(parkingLots);


        ParkingLot parkingLotReturned = parkingLotAttendant.getMeFreeParkingLot();
        Assert.assertNull(parkingLotReturned);

    }




    @Test
    public void shouldBeAbleToUnparkCarFromParkingTicket() throws Exception {
        List<ParkingLot> parkingLots = new ArrayList<>();
        int parkingLotSize = 5;
        ParkingLot parkingLot = new ParkingLot("parking id", parkingLotSize);
        parkingLots.add(parkingLot);
        Car car = new Car("some car id");
        ParkingLotAttendant parkingLotAttendant = new ParkingLotAttendant(parkingLots);
        Ticket ticket = parkingLot.parkCar(car);
        Car carReturned = parkingLotAttendant.unparkCar(ticket);
        Assert.assertEquals(carReturned, car);
    }

    @Test
    public void optimizeParkingLotsWhenfestiveSeason() throws Exception {
        List<ParkingLot> parkingLots = new ArrayList<>();
        int parkingLotSize = 5;
        ParkingLot parkingLotA = new ParkingLot("Parking A", 1);
        ParkingLot parkingLotB = new ParkingLot("Parking B", 2);
        ParkingLot parkingLotC = new ParkingLot("Parking C", 3);

        parkingLots.add(parkingLotA);
        parkingLots.add(parkingLotB);
        parkingLots.add(parkingLotC);

        ParkingLotAttendant parkingLotAttendant = new ParkingLotAttendant(parkingLots);
        parkingLotAttendant.setParkingStrategy(new FestiveSeasonStrategy());
        ParkingLot parkingLotReturned = parkingLotAttendant.getMeFreeParkingLot();
        Assert.assertEquals(parkingLotC, parkingLotReturned);
    }

    @Test
    public void shouldReturnSecondMaxParkingLotIfTheParkingLotWithMaxSizeIsFull() throws Exception {
        List<ParkingLot> parkingLots = new ArrayList<>();
        int parkingLotSize = 5;
        ParkingLot parkingLotA = new ParkingLot("Parking A", 1);
        ParkingLot parkingLotB = new ParkingLot("Parking B", 2);
        ParkingLot parkingLotC = mock(ParkingLot.class);

        parkingLots.add(parkingLotA);
        parkingLots.add(parkingLotB);
        parkingLots.add(parkingLotC);

        when(parkingLotC.isFull()).thenReturn(true);
        ParkingLotAttendant parkingLotAttendant = new ParkingLotAttendant(parkingLots);
        parkingLotAttendant.setParkingStrategy(new FestiveSeasonStrategy());
        ParkingLot parkingLotReturned = parkingLotAttendant.getMeFreeParkingLot();
        Assert.assertEquals(parkingLotB, parkingLotReturned);
    }


}
