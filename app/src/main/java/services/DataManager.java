package services;

import java.util.ArrayList;

import model.ElectricDevice;
import model.RefuseProduction;
import model.User;
import model.UserStats;
import model.WaterActivity;

public class DataManager {

    private static DataManager instance = null;

    private User user;
    private UserStats userStats;
    private UsageCalculator usageCalculator;

    private DataManager() {
        this.user = User.getInstance();
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
        this.user.insertElectricDevice(electricDevice);
    }

    public void storeWaterActivityData(String name, int litersUsed, int timesPerDay) {
        WaterActivity waterActivity = new WaterActivity(name, litersUsed, timesPerDay);
        this.user.insertWaterActivity(waterActivity);
    }

    public void storeRefuseProductionData(String name, int pointValue) {
        RefuseProduction refuseProduction = new RefuseProduction(name, pointValue);
        this.user.insertRefuseProduction(refuseProduction);
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
        int powerUsage = this.usageCalculator.calculateDailyPowerUsage(this.user.getElectricDevices());
        int standbyPowerUsage = this.usageCalculator.calculateDailyStandbyPowerUsage(this.user.getElectricDevices());
        int waterUsage = this.usageCalculator.calculateDailyWaterUsage(this.user.getWaterActivities());
        int refuseProductionPoints = this.usageCalculator.calculateRefuseProductionPoints(this.user.getRefuseProductions());
        this.userStats.setPowerUsage(powerUsage);
        this.userStats.setStandbyPowerUsage(standbyPowerUsage);
        this.userStats.setWaterUsage(waterUsage);
        this.userStats.setRefuseProductionPoints(refuseProductionPoints);
    }

    public void resetAllUserElements() {
        User user = User.getInstance();
        user.reset();
    }

}
