package traveller;

import com.bcus.letspark.parking.ParkingLot;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;

import static junit.framework.TestCase.assertNotNull;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.core.Is.is;
import static org.mockito.Matchers.any;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class TravellerTest {


    ParkingLot parkingLot;

    @Before
    public void setUp() throws Exception {
        parkingLot = mock(ParkingLot.class);


    }

    @Rule
    public ExpectedException expectedException = ExpectedException.none();

    @Test
    public void testParkMyCar() throws Exception {
        int parkingSize = 2;
        ParkingLot parkingLot = new ParkingLot(parkingSize);
        Traveller traveller = new Traveller();
        boolean isCarParked = traveller.parkMyCar(parkingLot);
        assertThat(isCarParked, is(true));
    }

    @Test
    public void testParkMyCarWhenThereIsNoParkingLot() throws Exception {
        expectedException.expect(Exception.class);
        expectedException.expectMessage(Traveller.NO_PARKING_LOT_SPECIFIED_TO_PARK_CAR);
        Traveller traveller = new Traveller();
        boolean isCarParked = traveller.parkMyCar(null);
        assertThat(isCarParked, is(true));
    }

    @Test
    public void testParkMyCarWhenUnableToParkMyCar() throws Exception {
        when(parkingLot.parkCar((Car) any())).thenReturn(false);
        int parkingSize = 2;
        Traveller traveller = new Traveller();
        boolean isCarParked = traveller.parkMyCar(parkingLot);
        assertThat(isCarParked, is(false));
    }

    @Test
    public void testGetMyCarAfterParkingIt() throws Exception {
        Traveller traveller = new Traveller();
        ParkingLot parkingLot = mock(ParkingLot.class);
        traveller.parkMyCar(parkingLot);

        boolean wasCarReturnedFromParking = traveller.getMyCar();
        assertThat(wasCarReturnedFromParking, is(true));
    }

}