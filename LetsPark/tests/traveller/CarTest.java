package traveller;


import org.junit.Test;
import static org.junit.Assert.*;

public class CarTest {

    private Car car;

    @Test
    public void createCar() {

        car = new Car("car_id");
        assertNotNull(car);

    }

    @Test
    public void createCarAndGetCarId() {

        car = new Car("car_id");
        assertEquals("car_id", car.getVehicleIdentificationNumber());

    }

}
