package com.bcus.letspark.parking;

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
}
