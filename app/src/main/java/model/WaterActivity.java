package model;

public class WaterActivity implements INamable {

    private String name;
    private int litersUsed;
    private int timesPerDay;

    public WaterActivity(String name, int litersUsed, int timesPerDay) {
        this.name = name;
        this.litersUsed = litersUsed;
        this.timesPerDay = timesPerDay;
    }

    public WaterActivity(String name) {
        this.name = name;
        this.litersUsed = 0;
        this.timesPerDay = 0;
    }

    public WaterActivity() {
        this("DefaultWaterActivity", 0, 0);
    }

    public String getName() {
        return this.name;
    }

    public int getLitersUsed() {
        return this.litersUsed;
    }

    public int getTimesPerDay() {
        return this.timesPerDay;
    }

    public void setLitersUsed(int litersUsed) {
        this.litersUsed = litersUsed;
    }

    public void setTimesPerDay(int timesPerDay) {
        this.timesPerDay = timesPerDay;
    }

    @Override
    public boolean equals(Object other) {
        if (other == null) return false;
        if (!(other instanceof WaterActivity)) return false;
        WaterActivity otherWaterActivity = (WaterActivity)other;
        return (otherWaterActivity.getName().equals(this.getName()));
    }

    public void cloneWaterActivity(WaterActivity source) {
        this.setLitersUsed(source.getLitersUsed());
        this.setTimesPerDay(source.getTimesPerDay());
    }

}
