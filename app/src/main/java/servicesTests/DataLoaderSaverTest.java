package servicesTests;

import android.test.InstrumentationTestCase;

import java.util.ArrayList;

import model.ElectricDevice;
import model.RefuseProduction;
import model.User;
import model.WaterActivity;
import services.DataManager;

public class DataLoaderSaverTest extends InstrumentationTestCase {

    public void testSaveLoadElectricDevicesData() {
        User user = User.getInstance();
        this.prepareUser(user);
        ArrayList<ElectricDevice> electricDevices = user.getElectricDevices();
        DataManager.saveUserDataToFile(this.getInstrumentation().getContext());

        user.reset();
        assertTrue(user.getElectricDevices().isEmpty());

        DataManager.loadUserDataFromFile(this.getInstrumentation().getContext());
        assertEquals(electricDevices.size(), user.getElectricDevices().size());
        for (int i = 0; i < electricDevices.size(); i++) {
            assertTrue(electricDevices.get(i).getHoursPerDay() == user.getElectricDevices().get(i).getHoursPerDay());
            assertTrue(electricDevices.get(i).getPowerConsumption() == user.getElectricDevices().get(i).getPowerConsumption());
            assertTrue(electricDevices.get(i).getStandbyHoursPerDay() == user.getElectricDevices().get(i).getStandbyHoursPerDay());
            assertTrue(electricDevices.get(i).getStandbyPowerConsumption() == user.getElectricDevices().get(i).getStandbyPowerConsumption());
            assertEquals(electricDevices.get(i), user.getElectricDevices().get(i));
        }
    }

    public void testSaveLoadWaterActivitiesData() {
        User user = User.getInstance();
        this.prepareUser(user);
        ArrayList<WaterActivity> waterActivities = user.getWaterActivities();
        DataManager.saveUserDataToFile(this.getInstrumentation().getContext());

        user.reset();
        assertTrue(user.getWaterActivities().isEmpty());

        DataManager.loadUserDataFromFile(this.getInstrumentation().getContext());
        assertEquals(waterActivities.size(), user.getWaterActivities().size());
        for (int i = 0; i < waterActivities.size(); i++) {
            assertTrue(waterActivities.get(i).getLitersUsed() == user.getWaterActivities().get(i).getLitersUsed());
            assertTrue(waterActivities.get(i).getTimesPerDay() == user.getWaterActivities().get(i).getTimesPerDay());
            assertEquals(waterActivities.get(i), user.getWaterActivities().get(i));
        }
    }

    public void testSaveLoadRefuseProductionsData() {
        User user = User.getInstance();
        this.prepareUser(user);
        ArrayList<RefuseProduction> refuseProductions = user.getRefuseProductions();
        DataManager.saveUserDataToFile(this.getInstrumentation().getContext());

        user.reset();
        assertTrue(user.getRefuseProductions().isEmpty());

        DataManager.loadUserDataFromFile(this.getInstrumentation().getContext());
        assertEquals(refuseProductions.size(), user.getRefuseProductions().size());
        for (int i = 0; i < refuseProductions.size(); i++) {
            assertTrue(refuseProductions.get(i).getPointValue() == user.getRefuseProductions().get(i).getPointValue());
            assertEquals(refuseProductions.get(i), user.getRefuseProductions().get(i));
        }
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
