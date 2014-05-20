package model;

public class UserStats {

    private static UserStats instance;

    private int powerUsage;
    private int standbyPowerUsage;
    private int waterUsage;
    private int refuseProductionPoints;

    private UserStats() {
        this.powerUsage = 0;
        this.standbyPowerUsage = 0;
        this.waterUsage = 0;
        this.refuseProductionPoints = 0;
    }

    public static UserStats getInstance() {
        if (instance == null)
            instance = new UserStats();
        return instance;
    }

    public void setPowerUsage(int powerUsage) {
        this.powerUsage = powerUsage;
    }

    public void setStandbyPowerUsage(int standbyPowerUsage) {
        this.standbyPowerUsage = standbyPowerUsage;
    }

    public void setWaterUsage(int waterUsage) {
        this.waterUsage = waterUsage;
    }

    public void setRefuseProductionPoints(int refuseProductionPoints) {
        this.refuseProductionPoints = refuseProductionPoints;
    }

    public int getPowerUsage() {
        return this.powerUsage;
    }

    public int getStandbyPowerUsage() {
        return this.standbyPowerUsage;
    }

    public int getWaterUsage() {
        return this.waterUsage;
    }

    public int getRefuseProductionPoints() {
        return this.refuseProductionPoints;
    }
}
