package greensaver.app;

import android.app.ActionBar;
import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.provider.ContactsContract;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageButton;
import android.widget.RelativeLayout;

import java.util.Arrays;

import model.User;
import model.UserStats;
import services.DataLoader;
import services.DataManager;
import services.DataSaver;


public class MainActivity extends Activity {

    private String[] bckgrnd = {"0", "1", "2", "10", "20", "baa", "caa"};

    private final int[] powerLimits = {6000, 3000};
    private final int[] waterLimits = {160, 100};
    private final int[] refuseLimits = {14, 30};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        //////////////////////////////////////////TEST stuff /////////////////////////////
        RelativeLayout layout =(RelativeLayout) findViewById(R.id.background);
        layout.setBackgroundResource(this.selectBackgroundImage());
        DataManager dataManager = DataManager.getInstance();
//        dataManager.storeElectricDeviceData("TV", 0, 0, 0, 0, 0);
//        dataManager.storeElectricDeviceData("Computer", 10, 1000, 12, 320, 13);
//        dataManager.storeElectricDeviceData("Router", 10, 1000, 10, 10, 10);
//        dataManager.storeElectricDeviceData("DVD set", 10, 1000, 12, 320, 13);
//        dataManager.storeElectricDeviceData("Radio", 10, 1000, 12, 320, 13);
        dataManager.storeWaterActivityData("Bath", 100, 1);
        dataManager.storeWaterActivityData("Washing Machine", 50, 1);
        dataManager.storeRefuseProductionData("Bags", 10);
        DataSaver dataSaver = DataSaver.getInstance();
        dataSaver.saveDataToFile(this);
        DataLoader dataLoader = DataLoader.getInstance();
        dataLoader.loadDataFromFile(this);
        layout.setBackgroundResource(this.selectBackgroundImage());
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu items for use in the action bar
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.main, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.action_show_user:
                Intent intent = new Intent(getApplicationContext(), ShowUserActivity.class);
                startActivity(intent);
                return true;

            case R.id.action_show_authors:
                //TODO call method to show authors.
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }

    private int selectBackgroundImage() {
        int currentScore = this.getCurrentUserScore();
        switch (currentScore) {
            case 0:
                return R.drawable.aaa;
            case 1:
                return R.drawable.aab;
            case 2:
                return R.drawable.aac;
            case 10:
                return R.drawable.aba;
            case 20:
                return R.drawable.aca;
            case 100:
                return R.drawable.baa;
            case 200:
                return R.drawable.caa;
            default:
                return R.drawable.caa;
        }
    }

    private int getCurrentUserScore() {
        DataManager dataManager = DataManager.getInstance();
        dataManager.prepareUserStats();
        UserStats userStats = UserStats.getInstance();
        int score;
        int electricPowerScore = userStats.getPowerUsage();
        if (electricPowerScore > powerLimits[0]) {
            score = 200;
        } else if (electricPowerScore > powerLimits[1]) {
            score = 100;
        } else {
            score = 0;
        }

        int waterScore = userStats.getWaterUsage();
        if (waterScore > waterLimits[0]) {
            score += 20;
        } else if (waterScore > waterLimits[1]) {
            score += 10;
        }

        int refuseScore = userStats.getRefuseProductionPoints();
        if (refuseScore < refuseLimits[0]) {
            score += 2;
        } else if (refuseScore < refuseLimits[1]) {
            score += 1;
        }

        return score;
    }

    public void showElectricInfo(View v) {
        Intent intent = new Intent(getApplicationContext(), ElectricInfoActivity.class);
        startActivity(intent);
    }

    public void showWaterInfo(View v) {
        Intent intent = new Intent(getApplicationContext(), WaterInfoActivity.class);
        startActivity(intent);
    }
}
