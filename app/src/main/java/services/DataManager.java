package services;

import java.util.ArrayList;

import model.ElectricDevice;
import model.RefuseProduction;
import model.User;
import model.UserStats;
import model.WaterActivity;

public class DataManager {

    private static DataManager instance = null;

    private UserStats userStats;
    private UsageCalculator usageCalculator;

    private DataManager() {
        this.userStats = UserStats.getInstance();
        this.usageCalculator = UsageCalculator.getInstance();
    }

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
        User.insertElectricDevice(electricDevice);
    }

    public void storeWaterActivityData(String name, int litersUsed, int timesPerDay) {
        WaterActivity waterActivity = new WaterActivity(name, litersUsed, timesPerDay);
        User.insertWaterActivity(waterActivity);
    }

    public void storeRefuseProductionData(String name, int pointValue) {
        RefuseProduction refuseProduction = new RefuseProduction(name, pointValue);
        User.insertRefuseProduction(refuseProduction);
    }

    public ArrayList<ElectricDevice> fetchElectricDeviceData() {
        return User.getElectricDevices();
    }

    public ArrayList<WaterActivity> fetchWaterActivityData() {
        return User.getWaterActivities();
    }

    public ArrayList<RefuseProduction> fetchRefuseProductionData() {
        return User.getRefuseProductions();
    }

    public void prepareUserStats() {
        int powerUsage = this.usageCalculator.calculateDailyPowerUsage(User.getElectricDevices());
        int standbyPowerUsage = this.usageCalculator.calculateDailyStandbyPowerUsage(User.getElectricDevices());
        int waterUsage = this.usageCalculator.calculateDailyWaterUsage(User.getWaterActivities());
        int refuseProductionPoints = this.usageCalculator.calculateRefuseProductionPoints(User.getRefuseProductions());
        this.userStats.setPowerUsage(powerUsage);
        this.userStats.setStandbyPowerUsage(standbyPowerUsage);
        this.userStats.setWaterUsage(waterUsage);
        this.userStats.setRefuseProductionPoints(refuseProductionPoints);
    }

    public void resetAllUserElements() {
        User.reset();
    }

}
