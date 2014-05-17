package greensaver.app.model;

import java.util.ArrayList;

public class User {
    //TODO decide on refuseProduction units;

    private String name;
    private int powerUsage;         /** kWh/week */
    private int standbyPowerUsage;  /** kWh/week */
    private int waterUsage;         /** Liters/week */
    private int refuseProduction;   /** own units */

    private ArrayList<ElectricDevice> electricDevices;
    private ArrayList<WaterActivity> waterActivities;


}