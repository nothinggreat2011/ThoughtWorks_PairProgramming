package com.bcus.letspark.parking;

import com.bcus.letspark.traveller.Car;

import java.util.ArrayList;
import java.util.List;
import java.util.Observable;
import java.util.Observer;

/**
 * Created by sawai on 4/24/2015.
 */
public class PoliceDepartment implements Observer{

    @Override
    public void update(Observable parkingLot, Object message) {
        System.out.println(message);
    }

    public List<Car> findAllCarsHavingSameColor(List<Car> parkedCar, String color) {
        List<Car> carList = new ArrayList<Car>();

        for (Car car : parkedCar) {
            if(car.getColor().equalsIgnoreCase(color)){
                carList.add(car);
            }
        }
       return carList;
    }

    public List<Car> findAllCarsHavingPartialLicensePlate(List<Car> parkedCar) {

        List<Car> partialLicensePlateCarList = new ArrayList<>();
        for (Car car : parkedCar) {
            if(car.getVehicleIdentificationNumber().length() <4){
                partialLicensePlateCarList.add(car);
            }
        }
        return partialLicensePlateCarList;
    }
}
