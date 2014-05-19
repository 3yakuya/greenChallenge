package services;

import android.content.Context;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;

import model.ElectricDevice;
import model.RefuseProduction;
import model.User;
import model.WaterActivity;

public class DataLoader {

    private static DataLoader instance = null;

    private User user;

    private DataLoader() {
        this.user = User.getInstance();
    }

    public static DataLoader getInstance() {
        if (instance == null)
            instance = new DataLoader();
        return instance;
    }

    public boolean loadDataFromFile(Context context) {
        File file = new File(context.getFilesDir(), "userStats");
        boolean status = false;
        if (!file.exists()) {
            try {
                file.createNewFile();
                return true;
            } catch(IOException e) {
                e.printStackTrace();
                return false;
            }
        }
        try {
            BufferedReader br = new BufferedReader(new FileReader(file));
            status = parseDataFromFile(br);
            br.close();
        } catch (IOException e) {
            e.printStackTrace();
            return false;
        }
        return status;
    }

    private boolean parseDataFromFile(BufferedReader br) throws IOException{
        String line;
        while ((line = br.readLine()) != null) {
            switch (line.charAt(0)) {
                case 'E':
                    ElectricDevice electricDevice = readElectricDeviceDataFromFile(br);
                    if (electricDevice == null)
                        return false;
                    user.insertElectricDevice(electricDevice);
                    break;

                case 'W':
                    WaterActivity waterActivity = readWaterActivityDataFromFile(br);
                    if (waterActivity == null)
                        return false;
                    user.insertWaterActivity(waterActivity);
                    break;

                case 'R':
                    RefuseProduction refuseProduction = readRefuseProductionDataFromFile(br);
                    if (refuseProduction == null)
                        return false;
                    user.insertRefuseProduction(refuseProduction);
                    break;

                default:
                    return false;
            }
        }
        return true;
    }

    private ElectricDevice readElectricDeviceDataFromFile(BufferedReader br) {
        ElectricDevice electricDevice = null;
        try {
            String name = br.readLine();
            int amount = Integer.parseInt(br.readLine());
            int powerConsumption = Integer.parseInt(br.readLine());
            int hoursPerDay = Integer.parseInt(br.readLine());
            int standbyPowerConsumption = Integer.parseInt(br.readLine());
            int standbyHoursPerDay = Integer.parseInt(br.readLine());
            electricDevice = new ElectricDevice(name, amount, powerConsumption,
                    hoursPerDay, standbyPowerConsumption, standbyHoursPerDay);
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            return electricDevice;
        }
    }

    private WaterActivity readWaterActivityDataFromFile(BufferedReader br) {
        WaterActivity waterActivity = null;
        try {
            String name = br.readLine();
            int litersUsed = Integer.parseInt(br.readLine());
            int timesPerDay = Integer.parseInt(br.readLine());
            waterActivity = new WaterActivity(name, litersUsed, timesPerDay);
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            return waterActivity;
        }
    }

    private RefuseProduction readRefuseProductionDataFromFile(BufferedReader br) {
        RefuseProduction refuseProduction = null;
        try {
            String name = br.readLine();
            int pointValue = Integer.parseInt(br.readLine());
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            return refuseProduction;
        }
    }
}
