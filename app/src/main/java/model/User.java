package model;

import java.util.ArrayList;

public class User {

    private static User instance;

    public static User getInstance() {
        if (instance == null)
            instance = new User();
        return instance;
    }

    private ArrayList<ElectricDevice> electricDevices;
    private ArrayList<WaterActivity> waterActivities;
    private ArrayList<RefuseProduction> refuseProductions;
    private UserStats userStats;

    private User() {
        this.electricDevices = new ArrayList<ElectricDevice>();
        this.waterActivities = new ArrayList<WaterActivity>();
        this.refuseProductions = new ArrayList<RefuseProduction>();
        this.userStats = new UserStats();
    }

    public ArrayList<ElectricDevice> getElectricDevices() {
        return electricDevices;
    }

    public ArrayList<WaterActivity> getWaterActivities() {
        return waterActivities;
    }

    public ArrayList<RefuseProduction> getRefuseProductions() {
        return refuseProductions;
    }

    public UserStats getUserStats() {
        return userStats;
    }

    public void insertElectricDevice(ElectricDevice electricDevice) {
        int index = electricDevices.indexOf(electricDevice);
        if (index >= 0) {
            electricDevices.get(index).cloneElectricDevice(electricDevice);
        } else {
            electricDevices.add(electricDevice);
        }
    }

    public void insertWaterActivity(WaterActivity waterActivity) {
        int index = waterActivities.indexOf(waterActivity);
        if (index >= 0) {
            waterActivities.get(index).cloneWaterActivity(waterActivity);
        } else {
            waterActivities.add(waterActivity);
        }
    }

    public void insertRefuseProduction(RefuseProduction refuseProduction) {
        int index = refuseProductions.indexOf(refuseProduction);
        if (index >= 0) {
            refuseProductions.get(index).cloneRefuseProduction(refuseProduction);
        } else {
            refuseProductions.add(refuseProduction);
        }
    }

    public void reset() {
        electricDevices.clear();
        waterActivities.clear();
        refuseProductions.clear();
    }

}