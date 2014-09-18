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
        this.electricDevices.add(new ElectricDevice("TV"));
        this.electricDevices.add(new ElectricDevice("Radio"));
        this.electricDevices.add(new ElectricDevice("Computer"));
        this.electricDevices.add(new ElectricDevice("Printer"));
        this.electricDevices.add(new ElectricDevice("Router"));
        this.electricDevices.add(new ElectricDevice("Set-Top Box"));
        this.electricDevices.add(new ElectricDevice("DVD set"));
        this.electricDevices.add(new ElectricDevice("Microwave"));
    }

    private void prepareWaterActivities() {
        this.waterActivities.add(new WaterActivity("Washing machine"));
        this.waterActivities.add(new WaterActivity("Dishwasher"));
        this.waterActivities.add(new WaterActivity("Washing up"));
        this.waterActivities.add(new WaterActivity("Bath"));
        this.waterActivities.add(new WaterActivity("Shower"));
        this.waterActivities.add(new WaterActivity("Cleaning hands"));
        this.waterActivities.add(new WaterActivity("Brushing teeth or shaving"));
        this.waterActivities.add(new WaterActivity("Washing the car"));
    }

    private void prepareRefuseProductions() {
        this.refuseProductions.add(new RefuseProduction("Segregation"));
        this.refuseProductions.add(new RefuseProduction("Plastic bags"));
        this.refuseProductions.add(new RefuseProduction("Pressing bottles"));
        this.refuseProductions.add(new RefuseProduction("Medicine"));
        this.refuseProductions.add(new RefuseProduction("Batteries and bulbs"));
        this.refuseProductions.add(new RefuseProduction("Household facilities"));
        this.refuseProductions.add(new RefuseProduction("Big size waste"));
    }
}
