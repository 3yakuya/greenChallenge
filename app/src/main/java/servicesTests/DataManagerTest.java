package servicesTests;

import android.test.InstrumentationTestCase;

import model.ElectricDevice;
import model.RefuseProduction;
import model.User;
import model.WaterActivity;
import services.DataManager;
import services.UsageCalculator;

public class DataManagerTest extends InstrumentationTestCase {

    public void testStoreElectricDeviceData() {
        User.getInstance().reset();
        DataManager.storeElectricDeviceData("DeviceOne", 20, 20, 40, 4);
        assertEquals(User.getInstance().getElectricDevices().get(0).getName(), "DeviceOne");
        assertEquals(User.getInstance().getElectricDevices().get(0).getPowerConsumption(), 20);
        assertEquals(User.getInstance().getElectricDevices().get(0).getHoursPerDay(), 20);
        assertEquals(User.getInstance().getElectricDevices().get(0).getStandbyPowerConsumption(), 40);
        assertEquals(User.getInstance().getElectricDevices().get(0).getStandbyHoursPerDay(), 4);
    }

    public void testStoreWaterActivityData() {
        User.getInstance().reset();
        DataManager.storeWaterActivityData("ActivityOne", 10, 20);
        assertEquals(User.getInstance().getWaterActivities().get(0).getName(), "ActivityOne");
        assertEquals(User.getInstance().getWaterActivities().get(0).getLitersUsed(), 10);
        assertEquals(User.getInstance().getWaterActivities().get(0).getTimesPerDay(), 20);
    }

    public void testStoreRefuseProductionData() {
        User.getInstance().reset();
        DataManager.storeRefuseProductionData("ProductionOne", 10);
        assertEquals(User.getInstance().getRefuseProductions().get(0).getName(), "ProductionOne");
        assertEquals(User.getInstance().getRefuseProductions().get(0).getPointValue(), 10);
    }

    public void testPrepareUserStats() {
        User user = User.getInstance();
        this.prepareUser(user);
        int userPowerUsage = UsageCalculator.calculateDailyPowerUsage(user.getElectricDevices());
        int userStandbyPowerUsage = UsageCalculator.calculateDailyStandbyPowerUsage(user.getElectricDevices());
        int userWaterUsage = UsageCalculator.calculateDailyWaterUsage(user.getWaterActivities());
        int userRefusePoints = UsageCalculator.calculateRefuseProductionPoints(user.getRefuseProductions());

        DataManager.prepareUserStats();
        assertEquals(user.getUserStats().getPowerUsage(), userPowerUsage);
        assertEquals(user.getUserStats().getStandbyPowerUsage(), userStandbyPowerUsage);
        assertEquals(user.getUserStats().getWaterUsage(), userWaterUsage);
        assertEquals(user.getUserStats().getRefuseProductionPoints(), userRefusePoints);
    }

    private void prepareUser(User user) {
        ElectricDevice electricDeviceOne = new ElectricDevice("DeviceOne", 100, 2, 5, 22);
        ElectricDevice electricDeviceTwo = new ElectricDevice("DeviceTwo", 100, 2, 5, 22);
        user.insertElectricDevice(electricDeviceOne);
        user.insertElectricDevice(electricDeviceTwo);
        WaterActivity waterActivityOne = new WaterActivity("ActivityOne", 10, 10);
        WaterActivity waterActivityTwo = new WaterActivity("ActivityTwo", 5, 5);
        user.insertWaterActivity(waterActivityOne);
        user.insertWaterActivity(waterActivityTwo);
        RefuseProduction refuseProductionOne = new RefuseProduction("ProductionOne", 10);
        RefuseProduction refuseProductionTwo = new RefuseProduction("ProductionTwo", 20);
        user.insertRefuseProduction(refuseProductionOne);
        user.insertRefuseProduction(refuseProductionTwo);
    }
}
