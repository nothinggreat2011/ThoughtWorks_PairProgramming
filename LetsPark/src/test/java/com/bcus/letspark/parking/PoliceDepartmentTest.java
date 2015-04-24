package com.bcus.letspark.parking;

import org.junit.Before;

import static org.junit.Assert.*;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

/**
 * Created by Jerry on 24-04-2015.
 */
public class PoliceDepartmentTest {

    PoliceDepartment policeDepartment;
    ParkingLot parkingLot;
    @Before
    public void setup(){
        parkingLot=mock(ParkingLot.class);
        policeDepartment = new PoliceDepartment();
    }

    public void shouldBeAbleToFindAllSameColoredCars(){

        when(parkingLot.getParkedCars).then();
         policeDepartment.findAllCarsHavingSameColor()


    }

}