package com.bcus.letspark.parking;

import junit.framework.Assert;
import junit.framework.TestCase;
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