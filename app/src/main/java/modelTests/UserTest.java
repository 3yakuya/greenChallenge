package modelTests;

import android.test.InstrumentationTestCase;

import model.ElectricDevice;
import model.RefuseProduction;
import model.User;
import model.WaterActivity;

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

        ElectricDevice electricDeviceOneRenewed = new ElectricDevice("DeviceOne", 2, 500, 5, 0, 0);
        User.insertElectricDevice(electricDeviceOneRenewed);
        assertTrue(User.getElectricDevices().get(0).getAmount() == 2);
        assertTrue(User.getElectricDevices().get(0).getHoursPerDay() == 5);
        assertTrue(User.getElectricDevices().get(0).getPowerConsumption() == 500);
    }

    public void testInsertWaterActivity() {
        WaterActivity waterActivityOne = new WaterActivity("ActivityOne", 10, 10);
        WaterActivity waterActivityTwo = new WaterActivity("ActivityTwo", 5, 5);
        User.insertWaterActivity(waterActivityOne);
        User.insertWaterActivity(waterActivityTwo);
        assertEquals(User.getWaterActivities().get(0), waterActivityOne);
        assertTrue(User.getWaterActivities().get(0).getLitersUsed() == 10);
        assertTrue(User.getWaterActivities().get(0).getTimesPerDay() == 10);
        assertEquals(User.getWaterActivities().get(1), waterActivityTwo);

        WaterActivity waterActivityOneRenewed = new WaterActivity("ActivityOne", 20, 15);
        User.insertWaterActivity(waterActivityOneRenewed);
        assertTrue(User.getWaterActivities().get(0).getLitersUsed() == 20);
        assertTrue(User.getWaterActivities().get(0).getTimesPerDay() == 15);
    }

    public void testInsertRefuseProduction() {
        RefuseProduction refuseProductionOne = new RefuseProduction("ProductionOne", 10);
        RefuseProduction refuseProductionTwo = new RefuseProduction("ProductionTwo", 20);
        User.insertRefuseProduction(refuseProductionOne);
        User.insertRefuseProduction(refuseProductionTwo);
        assertEquals(User.getRefuseProductions().get(0), refuseProductionOne);
        assertTrue(User.getRefuseProductions().get(0).getPointValue() == 10);
        assertEquals(User.getRefuseProductions().get(1), refuseProductionTwo);

        RefuseProduction refuseProductionOneRenewed = new RefuseProduction("ProductionOne", 15);
        User.insertRefuseProduction(refuseProductionOneRenewed);
        assertTrue(User.getRefuseProductions().get(0).getPointValue() == 15);
    }

    public void testReset() {
        ElectricDevice electricDeviceOne = new ElectricDevice("DeviceOne", 1, 100, 2, 5, 22);
        ElectricDevice electricDeviceTwo = new ElectricDevice("DeviceTwo", 1, 100, 2, 5, 22);
        User.insertElectricDevice(electricDeviceOne);
        User.insertElectricDevice(electricDeviceTwo);
        WaterActivity waterActivityOne = new WaterActivity("ActivityOne", 10, 10);
        WaterActivity waterActivityTwo = new WaterActivity("ActivityTwo", 5, 5);
        User.insertWaterActivity(waterActivityOne);
        User.insertWaterActivity(waterActivityTwo);
        RefuseProduction refuseProductionOne = new RefuseProduction("ProductionOne", 10);
        RefuseProduction refuseProductionTwo = new RefuseProduction("ProductionTwo", 20);
        User.insertRefuseProduction(refuseProductionOne);
        User.insertRefuseProduction(refuseProductionTwo);
        assertFalse(User.getElectricDevices().isEmpty());
        assertFalse(User.getWaterActivities().isEmpty());
        assertFalse(User.getRefuseProductions().isEmpty());

        User.reset();
        assertTrue(User.getElectricDevices().isEmpty());
        assertTrue(User.getWaterActivities().isEmpty());
        assertTrue(User.getRefuseProductions().isEmpty());
    }

    public void testUserStatsModification() {
        User.getUserStats().setPowerUsage(10);
        User.getUserStats().setRefuseProductionPoints(20);
        User.getUserStats().setStandbyPowerUsage(30);
        User.getUserStats().setWaterUsage(40);
        assertTrue(User.getUserStats().getPowerUsage() == 10);
        assertTrue(User.getUserStats().getRefuseProductionPoints() == 20);
        assertTrue(User.getUserStats().getStandbyPowerUsage() == 30);
        assertTrue(User.getUserStats().getWaterUsage() == 40);

        User.getUserStats().setPowerUsage(4);
        User.getUserStats().setRefuseProductionPoints(3);
        User.getUserStats().setStandbyPowerUsage(2);
        User.getUserStats().setWaterUsage(1);
        assertTrue(User.getUserStats().getPowerUsage() == 4);
        assertTrue(User.getUserStats().getRefuseProductionPoints() == 3);
        assertTrue(User.getUserStats().getStandbyPowerUsage() == 2);
        assertTrue(User.getUserStats().getWaterUsage() == 1);
    }
}
