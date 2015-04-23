package traveller;

import com.bcus.letspark.parking.ParkingLot;
import com.bcus.letspark.parking.ParkingLotAttendant;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.core.Is.is;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class TravellerTest {


    ParkingLot parkingLot;
    Car car;

    @Before
    public void setUp() throws Exception {
        parkingLot = mock(ParkingLot.class);
        car = new Car("My car");

    }

    @Rule
    public ExpectedException expectedException = ExpectedException.none();


    @Test
    public void createTravelerWithANewCar()
    {
        Car travellerCar = new Car("My car");
        Traveller traveller = new Traveller(travellerCar);
        Assert.assertNotNull(traveller);
    }

    @Test
    public void shouldBeAbleToParkMyCar() throws Exception {
        ParkingLotAttendant parkingLotAttendant = mock(ParkingLotAttendant.class);
        int parkingLotSize = 5;
        when(parkingLotAttendant.getMeFreeParkingLot()).thenReturn(new ParkingLot(parkingLotSize));
        Traveller traveller = new Traveller(car);
        boolean isCarParked = traveller.parkMyCar(parkingLotAttendant);
        assertThat(isCarParked, is(true));
    }

    @Test
    public void shouldNotBeAbleToParkMyCarWhenThereIsNoAttendant() throws Exception {
        expectedException.expect(Exception.class);
        expectedException.expectMessage(Traveller.NO_PARKING_ATTENDANT_ASSIGNED);
        Traveller traveller = new Traveller(car);
        boolean isCarParked = traveller.parkMyCar(null);
        assertThat(isCarParked, is(true));
    }

    @Test
    public void shouldNotBeAbleToParkMyCarWhenThereIsNoParkingLot() throws Exception {
        expectedException.expect(Exception.class);
        expectedException.expectMessage(Traveller.NO_PARKING_LOT_SPECIFIED_TO_PARK_CAR);

        ParkingLotAttendant parkingLotAttendant = mock(ParkingLotAttendant.class);
        when(parkingLotAttendant.getMeFreeParkingLot()).thenReturn(null);
        Traveller traveller = new Traveller(car);
        boolean isCarParked = traveller.parkMyCar(parkingLotAttendant);
        assertThat(isCarParked, is(true));
    }


    @Test
    public void shouldNotBeAbleToUnParkCarWithoutParking() throws Exception {
        expectedException.expect(Exception.class);
        expectedException.expectMessage(Traveller.CAR_NOT_PARKED);
        Traveller traveller = new Traveller(car);
        ParkingLotAttendant parkingLotAttendant = mock(ParkingLotAttendant.class);

        when(parkingLot.getCarFromParking(car.getVehicleIdentificationNumber())).thenReturn(null);
        traveller.getMyCar();
    }

}