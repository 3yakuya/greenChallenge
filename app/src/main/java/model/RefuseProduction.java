package model;

public class RefuseProduction {
    //TODO add all necessary fields and methods.
    //IF anything added - update DataLoader and DataSaver services.

    private String name;
    private int pointValue;

    public RefuseProduction(String name, int pointValue) {
        this.name = name;
        this.pointValue = pointValue;
    }

    public RefuseProduction() {
        this("DefaultRefuseProduction", 0);
    }

    public String getName() {
        return this.name;
    }

    public int getPointValue() {
        return pointValue;
    }

    public void setPointValue(int pointValue) {
        this.pointValue = pointValue;
    }
}
