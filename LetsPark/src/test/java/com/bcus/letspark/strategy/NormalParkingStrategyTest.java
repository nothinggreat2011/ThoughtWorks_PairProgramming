package com.bcus.letspark.strategy;

import com.bcus.letspark.parking.ParkingLot;
import com.bcus.letspark.parking.ParkingLotAttendant;
import junit.framework.Assert;
import junit.framework.TestCase;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by sawai on 4/23/2015.
 */
public class NormalParkingStrategyTest {

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


}