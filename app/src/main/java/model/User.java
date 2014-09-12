package model;

import java.util.ArrayList;

public class User {

    private static ArrayList<ElectricDevice> electricDevices = new ArrayList<ElectricDevice>();
    private static ArrayList<WaterActivity> waterActivities = new ArrayList<WaterActivity>();
    private static ArrayList<RefuseProduction> refuseProductions = new ArrayList<RefuseProduction>();

    public static ArrayList<ElectricDevice> getElectricDevices() {
        return electricDevices;
    }

    public static ArrayList<WaterActivity> getWaterActivities() {
        return waterActivities;
    }

    public static ArrayList<RefuseProduction> getRefuseProductions() {
        return refuseProductions;
    }

    public static void insertElectricDevice(ElectricDevice electricDevice) {
        int index = electricDevices.indexOf(electricDevice);
        if (index >= 0) {
            electricDevices.get(index).cloneElectricDevice(electricDevice);
        } else {
            electricDevices.add(electricDevice);
        }
    }

    public static void insertWaterActivity(WaterActivity waterActivity) {
        int index = waterActivities.indexOf(waterActivity);
        if (index >= 0) {
            waterActivities.get(index).cloneWaterActivity(waterActivity);
        } else {
            waterActivities.add(waterActivity);
        }
    }

    public static void insertRefuseProduction(RefuseProduction refuseProduction) {
        int index = refuseProductions.indexOf(refuseProduction);
        if (index >= 0) {
            refuseProductions.get(index).cloneRefuseProduction(refuseProduction);
        } else {
            refuseProductions.add(refuseProduction);
        }
    }

    public static void reset() {
        electricDevices.clear();
        waterActivities.clear();
        refuseProductions.clear();
    }

}