package servicesTests;

import android.test.InstrumentationTestCase;

import model.ElectricDevice;
import model.RefuseProduction;
import model.User;
import model.WaterActivity;
import services.UsageCalculator;

public class UsageCalculatorTest extends InstrumentationTestCase {

    public void testCalculateDailyPowerUsage() {
        User user = User.getInstance();
        this.prepareUser(user);
        int powerUsage = 0;
        for (ElectricDevice device : user.getElectricDevices()) {
            int deviceUsage = device.getPowerConsumption() * device.getHoursPerDay();
            deviceUsage += device.getStandbyPowerConsumption() * device.getStandbyHoursPerDay();
            powerUsage += deviceUsage;
        }
        assertEquals(UsageCalculator.calculateDailyPowerUsage(user.getElectricDevices()), powerUsage);
    }

    public void testCalculateDailyWaterUsage() {
        User user = User.getInstance();
        this.prepareUser(user);
        int waterUsage = 0;
        for (WaterActivity activity : user.getWaterActivities()) {
            waterUsage += activity.getLitersUsed() * activity.getTimesPerDay();
        }
        assertEquals(UsageCalculator.calculateDailyWaterUsage(user.getWaterActivities()), waterUsage);
    }

    public void testCalculateRefuseProductionPoints() {
        User user = User.getInstance();
        this.prepareUser(user);
        int points = 0;
        for (RefuseProduction production : user.getRefuseProductions()) {
            points += production.getPointValue();
        }
        assertEquals(UsageCalculator.calculateRefuseProductionPoints(user.getRefuseProductions()), points);
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
