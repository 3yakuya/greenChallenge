package model;

import java.util.ArrayList;

public class FullSelection {

    private static FullSelection instance;

    private ArrayList<ElectricDevice> electricDevices;
    private ArrayList<WaterActivity> waterActivities;
    private ArrayList<RefuseProduction> refuseProductions;

    private FullSelection() {
        this.electricDevices = new ArrayList<ElectricDevice>();
        this.waterActivities = new ArrayList<WaterActivity>();
        this.refuseProductions = new ArrayList<RefuseProduction>();
        this.prepareElectricDevices();
        this.prepareWaterActivities();
        this.prepareRefuseProductions();
    }

    public static FullSelection getInstance() {
        if (instance == null)
            instance = new FullSelection();
        return instance;
    }

    public ArrayList<ElectricDevice> getAllElectricDevices() {
        return this.electricDevices;
    }

    public ArrayList<WaterActivity> getAllWaterActivities() {
        return this.waterActivities;
    }

    public ArrayList<RefuseProduction> getAllRefuseProductions() {
        return this.refuseProductions;
    }

    private void prepareElectricDevices() {
        this.electricDevices.add(new ElectricDevice("TV", 0, 0, 0, 0));
        this.electricDevices.add(new ElectricDevice("Radio", 0, 0, 0, 0));
        this.electricDevices.add(new ElectricDevice("Computer", 0, 0, 0, 0));
        this.electricDevices.add(new ElectricDevice("Printer", 0, 0, 0, 0));
        this.electricDevices.add(new ElectricDevice("Router", 0, 0, 0, 0));
        this.electricDevices.add(new ElectricDevice("Set-Top Box", 0, 0, 0, 0));
        this.electricDevices.add(new ElectricDevice("DVD set", 0, 0, 0, 0));
        this.electricDevices.add(new ElectricDevice("Microwave", 0, 0, 0, 0));
    }

    private void prepareWaterActivities() {
        this.waterActivities.add(new WaterActivity("Washing machine", 0, 0));
        this.waterActivities.add(new WaterActivity("Dishwasher", 0, 0));
        this.waterActivities.add(new WaterActivity("Washing up", 0, 0));
        this.waterActivities.add(new WaterActivity("Bath", 0, 0));
        this.waterActivities.add(new WaterActivity("Shower", 0, 0));
        this.waterActivities.add(new WaterActivity("Cleaning hands", 0, 0));
        this.waterActivities.add(new WaterActivity("Brushing teeth or shaving", 0, 0));
        this.waterActivities.add(new WaterActivity("Washing the car", 0, 0));
    }

    private void prepareRefuseProductions() {
        this.refuseProductions.add(new RefuseProduction("Segregation", 0));
        this.refuseProductions.add(new RefuseProduction("Plastic bags", 0));
        this.refuseProductions.add(new RefuseProduction("Pressing bottles", 0));
        this.refuseProductions.add(new RefuseProduction("Medicine", 0));
        this.refuseProductions.add(new RefuseProduction("Batteries and bulbs", 0));
        this.refuseProductions.add(new RefuseProduction("Household facilities", 0));
        this.refuseProductions.add(new RefuseProduction("Big size waste", 0));
    }
}
