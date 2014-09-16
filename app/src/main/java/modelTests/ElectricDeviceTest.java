package modelTests;

import android.test.InstrumentationTestCase;

import model.ElectricDevice;

public class ElectricDeviceTest extends InstrumentationTestCase {

    public void testCloneElectricDevice() {
        ElectricDevice electricDeviceOne = new ElectricDevice("TestDevice", 100, 10, 10, 20);
        ElectricDevice electricDeviceTwo = new ElectricDevice();
        electricDeviceTwo.cloneElectricDevice(electricDeviceOne);
        assertEquals(electricDeviceOne.getPowerConsumption(), electricDeviceTwo.getPowerConsumption());
        assertEquals(electricDeviceOne.getHoursPerDay(), electricDeviceTwo.getHoursPerDay());
        assertEquals(electricDeviceOne.getStandbyPowerConsumption(), electricDeviceTwo.getStandbyPowerConsumption());
        assertEquals(electricDeviceOne.getStandbyHoursPerDay(), electricDeviceTwo.getStandbyHoursPerDay());
    }

}
