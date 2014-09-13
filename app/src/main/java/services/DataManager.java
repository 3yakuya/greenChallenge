package services;

import java.util.ArrayList;

import model.ElectricDevice;
import model.RefuseProduction;
import model.User;
import model.WaterActivity;

public class DataManager {

    private static DataManager instance = null;
    private User user = User.getInstance();

    public static DataManager getInstance() {
        if (instance == null)
            instance = new DataManager();
        return instance;
    }

    public void storeElectricDeviceData(String name, int amount,
                                        int powerConsumption,
                                        int hoursPerDay,
                                        int standbyPowerConsumption,
                                        int standbyHoursPerDay) {
        ElectricDevice electricDevice = new ElectricDevice(name, amount, powerConsumption,
                hoursPerDay, standbyPowerConsumption, standbyHoursPerDay);
        user.insertElectricDevice(electricDevice);
    }

    public void storeWaterActivityData(String name, int litersUsed, int timesPerDay) {
        WaterActivity waterActivity = new WaterActivity(name, litersUsed, timesPerDay);
        user.insertWaterActivity(waterActivity);
    }

    public void storeRefuseProductionData(String name, int pointValue) {
        RefuseProduction refuseProduction = new RefuseProduction(name, pointValue);
        user.insertRefuseProduction(refuseProduction);
    }

    public ArrayList<ElectricDevice> fetchElectricDeviceData() {
        return user.getElectricDevices();
    }

    public ArrayList<WaterActivity> fetchWaterActivityData() {
        return user.getWaterActivities();
    }

    public ArrayList<RefuseProduction> fetchRefuseProductionData() {
        return user.getRefuseProductions();
    }

    public void prepareUserStats() {
        int powerUsage = UsageCalculator.calculateDailyPowerUsage(user.getElectricDevices());
        int standbyPowerUsage = UsageCalculator.calculateDailyStandbyPowerUsage(user.getElectricDevices());
        int waterUsage = UsageCalculator.calculateDailyWaterUsage(user.getWaterActivities());
        int refuseProductionPoints = UsageCalculator.calculateRefuseProductionPoints(user.getRefuseProductions());
        user.getUserStats().setPowerUsage(powerUsage);
        user.getUserStats().setStandbyPowerUsage(standbyPowerUsage);
        user.getUserStats().setWaterUsage(waterUsage);
        user.getUserStats().setRefuseProductionPoints(refuseProductionPoints);
    }

    public void resetAllUserElements() {
        user.reset();
    }

}
