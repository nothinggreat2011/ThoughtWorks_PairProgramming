package com.bcus.letspark.parking;

import com.bcus.letspark.exceptions.EmptyInputException;
import com.bcus.letspark.traveller.CarSize;
import junit.framework.Assert;
import org.junit.Rule;
import org.junit.Test;
import com.bcus.letspark.traveller.Car;
import org.junit.rules.ExpectedException;

import java.util.Date;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.junit.Assert.assertEquals;

/**
 * Created by sawai on 4/23/2015.
 */
public class TicketTest {

    @Rule
    public ExpectedException expectedException = ExpectedException.none();

    @Test
    public void shouldBeAbleToCreateTicket(){
        Car car = new Car("Red", CarSize.SMALL,"vechile Number");
        Assert.assertNotNull(new Ticket("parkingLotId", car.getVehicleIdentificationNumber(), new Date()));
    }

    @Test
    public void shouldBeAbleToRetrieveParkingLotIdAndVehicleIdentificationNumber(){
        Car car = new Car("Red", CarSize.SMALL,"vechile Number");
        String parkingLotId = "parkingLotId";
        Ticket ticket = new Ticket(parkingLotId ,car.getVehicleIdentificationNumber(), new Date());
        assertEquals(car.getVehicleIdentificationNumber(), ticket.getVehicleIdentificationNumber());
        assertEquals(parkingLotId, ticket.getParkingLotId());
    }

    @Test
    public void shouldNotBeAbleToMakeTicketWithEmptyParkingLotId()
    {
        expectedException.expect(EmptyInputException.class);
        expectedException.expectMessage(Ticket.PARKING_LOT_ID_IS_EMPTY);
        Car car = new Car("Red", CarSize.SMALL,"vechile Number");
       new Ticket(null, car.getVehicleIdentificationNumber(), new Date());
    }

    @Test
    public void shouldNotBeAbleToMakeTicketWithEmptyTicketDateId()
    {
        expectedException.expect(EmptyInputException.class);
        expectedException.expectMessage(Ticket.PARKING_TICKET_DATE_IS_EMPTY);
        Car car = new Car("Red", CarSize.SMALL,"vechile Number");
        new Ticket("parking lot id", car.getVehicleIdentificationNumber(),null);
    }

    @Test
    public void shouldNotBeAbleToMakeTicketWithEmptyVehicleId()
    {
        expectedException.expect(EmptyInputException.class);
        expectedException.expectMessage(Ticket.PARKING_VEHICLE_ID_IS_EMPTY);
        Car car = new Car("Red", CarSize.SMALL,"vechile Number");
        new Ticket("parking lot id",null, new Date());
    }
}
