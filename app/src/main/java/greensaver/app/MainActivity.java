package greensaver.app;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.RelativeLayout;

import model.User;
import services.DataLoader;
import services.DataManager;


public class MainActivity extends Activity {

    private final int[] powerLimits = {6000, 3000};
    private final int[] waterLimits = {160, 100};
    private final int[] refuseLimits = {14, 30};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        RelativeLayout layout =(RelativeLayout) findViewById(R.id.background);
        DataLoader.getInstance().loadDataFromFile(this);
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
        Intent intent;
        switch (item.getItemId()) {
            case R.id.action_show_user:
                intent = new Intent(getApplicationContext(), ShowUserActivity.class);
                startActivity(intent);
                return true;

            case R.id.action_show_authors:
                intent = new Intent(getApplicationContext(), AboutActivity.class);
                startActivity(intent);
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
            case 11:
                return R.drawable.abb;
            case 12:
                return R.drawable.abc;
            case 20:
                return R.drawable.aca;
            case 21:
                return R.drawable.acb;
            case 22:
                return R.drawable.acc;
            case 100:
                return R.drawable.baa;
            case 101:
                return R.drawable.bab;
            case 102:
                return R.drawable.bac;
            case 110:
                return R.drawable.bba;
            case 111:
                return R.drawable.bbb;
            case 112:
                return R.drawable.bbc;
            case 120:
                return R.drawable.aca;
            case 121:
                return R.drawable.bcb;
            case 122:
                return R.drawable.bcc;
            case 200:
                return R.drawable.caa;
            case 201:
                return R.drawable.cab;
            case 202:
                return R.drawable.cac;
            case 210:
                return R.drawable.cba;
            case 211:
                return R.drawable.cbb;
            case 212:
                return R.drawable.cbc;
            case 220:
                return R.drawable.cca;
            case 221:
                return R.drawable.ccb;
            case 222:
                return R.drawable.ccc;
            default:
                return R.drawable.aaa;
        }
    }

    private int getCurrentUserScore() {
        DataManager dataManager = DataManager.getInstance();
        dataManager.prepareUserStats();
        int score;
        int electricPowerScore = User.getUserStats().getPowerUsage();
        if (electricPowerScore > powerLimits[0]) {
            score = 200;
        } else if (electricPowerScore > powerLimits[1]) {
            score = 100;
        } else {
            score = 0;
        }

        int waterScore = User.getUserStats().getWaterUsage();
        if (waterScore > waterLimits[0]) {
            score += 20;
        } else if (waterScore > waterLimits[1]) {
            score += 10;
        }

        int refuseScore = User.getUserStats().getRefuseProductionPoints();
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

    public void showRefuseInfo(View v) {
        Intent intent = new Intent(getApplicationContext(), RefuseInfoActivity.class);
        startActivity(intent);
    }
}
