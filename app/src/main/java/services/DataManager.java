package services;

import android.content.Context;

import java.util.ArrayList;

import model.ElectricDevice;
import model.RefuseProduction;
import model.User;
import model.WaterActivity;

public class DataManager {

    public static void storeElectricDeviceData(String name,
                                        int powerConsumption,
                                        int hoursPerDay,
                                        int standbyPowerConsumption,
                                        int standbyHoursPerDay) {
        ElectricDevice electricDevice = new ElectricDevice(name, powerConsumption,
                hoursPerDay, standbyPowerConsumption, standbyHoursPerDay);
        User.getInstance().insertElectricDevice(electricDevice);
    }

    public static void storeWaterActivityData(String name, int litersUsed, int timesPerDay) {
        WaterActivity waterActivity = new WaterActivity(name, litersUsed, timesPerDay);
        User.getInstance().insertWaterActivity(waterActivity);
    }

    public static void storeRefuseProductionData(String name, int pointValue) {
        RefuseProduction refuseProduction = new RefuseProduction(name, pointValue);
        User.getInstance().insertRefuseProduction(refuseProduction);
    }

    public static void removeElectricDevice(String name) {
        ElectricDevice electricDevice = new ElectricDevice(name);
        User.getInstance().removeElectricDevice(electricDevice);
    }

    public static void removeWaterActivity(String name) {
        WaterActivity waterActivity = new WaterActivity(name);
        User.getInstance().removeWaterActivity(waterActivity);
    }

    public static void removeRefuseProduction(String name) {
        RefuseProduction refuseProduction = new RefuseProduction(name);
        User.getInstance().removeRefuseProduction(refuseProduction);
    }

    public static ArrayList<ElectricDevice> fetchElectricDeviceData() {
        return User.getInstance().getElectricDevices();
    }

    public static ArrayList<WaterActivity> fetchWaterActivityData() {
        return User.getInstance().getWaterActivities();
    }

    public static ArrayList<RefuseProduction> fetchRefuseProductionData() {
        return User.getInstance().getRefuseProductions();
    }

    public static void prepareUserStats() {
        User user = User.getInstance();
        int powerUsage = UsageCalculator.calculateDailyPowerUsage(user.getElectricDevices());
        int standbyPowerUsage = UsageCalculator.calculateDailyStandbyPowerUsage(user.getElectricDevices());
        int waterUsage = UsageCalculator.calculateDailyWaterUsage(user.getWaterActivities());
        int refuseProductionPoints = UsageCalculator.calculateRefuseProductionPoints(user.getRefuseProductions());
        user.getUserStats().setPowerUsage(powerUsage);
        user.getUserStats().setStandbyPowerUsage(standbyPowerUsage);
        user.getUserStats().setWaterUsage(waterUsage);
        user.getUserStats().setRefuseProductionPoints(refuseProductionPoints);
    }

    public static void resetAllUserElements() {
        User.getInstance().reset();
    }

    public static void saveUserDataToFile(Context context) {
        DataSaver.saveDataToFile(context, User.getInstance());
    }

    public static void loadUserDataFromFile(Context context) {
        DataLoader.loadDataFromFile(context, User.getInstance());
    }

}
