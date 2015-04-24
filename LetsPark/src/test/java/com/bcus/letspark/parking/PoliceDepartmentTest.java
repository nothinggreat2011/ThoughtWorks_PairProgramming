package com.bcus.letspark.parking;

import com.bcus.letspark.traveller.Car;
import com.bcus.letspark.traveller.CarSize;
import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.assertSame;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

/**
 * Created by Jerry on 24-04-2015.
 */
public class PoliceDepartmentTest {

    PoliceDepartment policeDepartment;
    ParkingLot parkingLot;
    Car carOne;
    Car carTwo;
    Car carThree;
    List<Car> parkedCar;

    @Before
    public void setup() {
        parkingLot = mock(ParkingLot.class);
        policeDepartment = new PoliceDepartment();
        parkedCar = new ArrayList<>();
        carOne = new Car("Red", CarSize.BIG, "Car");
        carTwo = new Car("Red", CarSize.BIG, "Car");
        carThree = new Car("Red", CarSize.BIG, "Car");
        parkedCar.add(carOne);
        parkedCar.add(carTwo);
        parkedCar.add(carThree);
    }

    @Test
    public void shouldBeAbleToFindAllSameColoredCars() {

        when(parkingLot.getParkedCars()).thenReturn(parkedCar);
        List<Car> carList = policeDepartment.findAllCarsHavingSameColor(parkedCar, "Red");
        assertSame(3, carList.size());
    }

    @Test
    public void shouldBeAbleToFindAllCarsHavingPartialLicensePlate() {

        when(parkingLot.getParkedCars()).thenReturn(parkedCar);
        List<Car> carList = policeDepartment.findAllCarsHavingPartialLicensePlate(parkedCar);
        assertSame(3, carList.size());
    }




}