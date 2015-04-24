package com.bcus.letspark.traveller;


import org.junit.Test;
import static org.junit.Assert.*;

public class CarTest {

    private Car car;

    @Test
    public void createCar() {

        car = new Car(CarSize.SMALL, "car_id");
        assertNotNull(car);

    }

    @Test
    public void createCarAndGetCarId() {

        car = new Car(CarSize.SMALL, "car_id");
        assertEquals("car_id", car.getVehicleIdentificationNumber());
    }

    @Test
    public void createALargeCar()
    {
        car = new Car(CarSize.BIG, "car id");
        assertEquals(car.getSize(), CarSize.BIG);
    }

}
