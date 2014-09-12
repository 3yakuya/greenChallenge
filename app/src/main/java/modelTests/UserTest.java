package modelTests;

import android.test.InstrumentationTestCase;

import model.ElectricDevice;
import model.User;

public class UserTest extends InstrumentationTestCase {

    public void testInsertElectricDevice() {
        ElectricDevice electricDeviceOne = new ElectricDevice("DeviceOne", 1, 100, 2, 5, 22);
        ElectricDevice electricDeviceTwo = new ElectricDevice("DeviceTwo", 1, 100, 2, 5, 22);
        User.insertElectricDevice(electricDeviceOne);
        User.insertElectricDevice(electricDeviceTwo);
        assertEquals(User.getElectricDevices().get(0), electricDeviceOne);
        assertTrue(User.getElectricDevices().get(0).getAmount() == 1);
        assertTrue(User.getElectricDevices().get(0).getPowerConsumption() == 100);
        assertTrue(User.getElectricDevices().get(0).getHoursPerDay() == 2);
        assertEquals(User.getElectricDevices().get(1), electricDeviceTwo);

        electricDeviceOne.setAmount(2);
        electricDeviceOne.setHoursPerDay(5);
        electricDeviceOne.setPowerConsumption(500);
        assertTrue(User.getElectricDevices().get(0).getAmount() == 2);
        assertTrue(User.getElectricDevices().get(0).getHoursPerDay() == 5);
        assertTrue(User.getElectricDevices().get(0).getPowerConsumption() == 500);
    }

}
