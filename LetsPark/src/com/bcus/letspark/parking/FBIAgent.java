package com.bcus.letspark.parking;

import java.util.Observable;
import java.util.Observer;

/**
 * Created by sawai on 4/23/2015.
 */
public class FBIAgent implements Observer{
    @Override
    public void update(Observable parkingLot, Object message) {
        System.out.println(message);

    }
}
