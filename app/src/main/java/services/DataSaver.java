package services;

import android.content.Context;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;

import model.ElectricDevice;
import model.RefuseProduction;
import model.User;
import model.WaterActivity;

public class DataSaver {

    public static boolean saveDataToFile(Context context) {
        File file = new File(context.getFilesDir(), "userStats");
        if (!clearFileContent(file))
            return false;
        if (!file.exists()) {
            try {
                file.createNewFile();
            } catch(IOException e) {
                e.printStackTrace();
                return false;
            }
        }
        try {
            BufferedWriter bw = new BufferedWriter(new FileWriter(file, true));
            User user = User.getInstance();
            saveElectricDevicesToFile(bw, user);
            saveWaterActivitiesToFile(bw, user);
            saveRefuseProductionsToFile(bw, user);
            bw.close();
        } catch (IOException e) {
            e.printStackTrace();
            return false;
        }
        return true;
    }

    private static void saveElectricDevicesToFile(BufferedWriter bw, User user) throws IOException {
        for (ElectricDevice electricDevice : user.getElectricDevices()) {
            bw.write("E");
            bw.newLine();
            bw.write(electricDevice.getName());
            bw.newLine();
            bw.write(String.valueOf(electricDevice.getPowerConsumption()));
            bw.newLine();
            bw.write(String.valueOf(electricDevice.getHoursPerDay()));
            bw.newLine();
            bw.write(String.valueOf(electricDevice.getStandbyPowerConsumption()));
            bw.newLine();
            bw.write(String.valueOf(electricDevice.getStandbyHoursPerDay()));
            bw.newLine();
        }
    }

    private static void saveWaterActivitiesToFile(BufferedWriter bw, User user) throws IOException {
        for (WaterActivity waterActivity : user.getWaterActivities()) {
            bw.write("W");
            bw.newLine();
            bw.write(waterActivity.getName());
            bw.newLine();
            bw.write(String.valueOf(waterActivity.getLitersUsed()));
            bw.newLine();
            bw.write(String.valueOf(waterActivity.getTimesPerDay()));
            bw.newLine();
        }
    }

    private static void saveRefuseProductionsToFile(BufferedWriter bw, User user) throws IOException {
        for (RefuseProduction refuseProduction : user.getRefuseProductions()) {
            bw.write("R");
            bw.newLine();
            bw.write(refuseProduction.getName());
            bw.newLine();
            bw.write(String.valueOf(refuseProduction.getPointValue()));
            bw.newLine();
        }
    }
    
    private static boolean clearFileContent(File file) {
        try {
            PrintWriter fileClearer = new PrintWriter(file);
            fileClearer.write("");
            fileClearer.close();
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
        return true;
    }

}
