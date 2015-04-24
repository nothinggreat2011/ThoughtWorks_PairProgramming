package com.bcus.letspark.strategy;

import com.bcus.letspark.parking.ParkingLot;
import com.bcus.letspark.parking.ParkingLotAttendant;
import com.bcus.letspark.strategy.FestiveSeasonStrategy;
import com.bcus.letspark.traveller.Car;
import com.bcus.letspark.traveller.CarSize;
import junit.framework.Assert;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class FestiveSeasonStrategyTest {

    @Test
    public void optimizeParkingLotsWhenfestiveSeason() throws Exception {
        List<ParkingLot> parkingLots = new ArrayList<>();
        int parkingLotSize = 5;

        Car car = new Car("Red", CarSize.SMALL, "some car id");

        ParkingLot parkingLotA = new ParkingLot("Parking A", 1);
        ParkingLot parkingLotB = new ParkingLot("Parking B", 2);
        ParkingLot parkingLotC = new ParkingLot("Parking C", 3);

        parkingLots.add(parkingLotA);
        parkingLots.add(parkingLotB);
        parkingLots.add(parkingLotC);

        ParkingLotAttendant parkingLotAttendant = new ParkingLotAttendant(parkingLots);
        parkingLotAttendant.setParkingStrategy(new FestiveSeasonStrategy());
        ParkingLot parkingLotReturned = parkingLotAttendant.getMeFreeParkingLot(car);
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
        Car car = new Car("Red", CarSize.SMALL, "some car id");


        when(parkingLotC.isFull()).thenReturn(true);
        ParkingLotAttendant parkingLotAttendant = new ParkingLotAttendant(parkingLots);
        parkingLotAttendant.setParkingStrategy(new FestiveSeasonStrategy());
        ParkingLot parkingLotReturned = parkingLotAttendant.getMeFreeParkingLot(car);
        Assert.assertEquals(parkingLotB, parkingLotReturned);
    }


}