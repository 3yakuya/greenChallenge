package greensaver.app;

import android.app.Activity;
import android.graphics.Color;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.TextView;

import java.text.DecimalFormat;
import java.util.Random;

import model.UserStats;
import services.DataManager;


public class ElectricInfoActivity extends Activity {

    private final int[] powerLimits = {6000, 3000};

    private final String[] tips = {
            "When selecting electric devices check their energy class.",
            "If you have a device you rarely use, pull its plug out of an outlet.",
            "Fridge and freezer should stand as far away from heaters as possible.",
            "A single bulb gives less light than two bulbs with half as much power each.",
            "Buy energy saver bulbs for all the rooms you spend a lot of time in.",
            "If you are going to walk away from your computer for a longer while, put it to sleep.",
            "More powerful kettles boil water faster and therefore loose less energy.",
            "Traditional or plasma-screen TVs are much more energy consuming than LED ones.",
            "Ink printers usually consume much less power than laser ones.",
            "Turn off the TV if you are not watching it. Do the same with the radio.",
            "Remember - saving power is not only good for the planet. It is also great for your wallet."
    };

    private TextView electricInfo;
    private TextView electricHint;
    private TextView electricValue;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_electric_info);
        this.showInfo();
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.electric_info, menu);
        this.showInfo();
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();
        return super.onOptionsItemSelected(item);
    }

    private void showInfo() {
        this.electricInfo = (TextView) findViewById(R.id.electric_info);
        this.electricHint = (TextView) findViewById(R.id.electric_hint);
        this.electricValue = (TextView) findViewById(R.id.electric_value);
        this.electricInfo.setText(getUsageInfo());
        int index = new Random().nextInt(tips.length);
        this.electricHint.setText(tips[index]);
        this.electricValue.setText(this.getValue());
        UserStats userStats = UserStats.getInstance();
        int powerUsage = userStats.getPowerUsage();
        if (powerUsage > this.powerLimits[0])
            electricValue.setTextColor(Color.parseColor("#FF0000"));
        else if (powerUsage > this.powerLimits[1])
            electricValue.setTextColor(Color.parseColor("#FCC800"));
        else
            electricValue.setTextColor(Color.parseColor("#00FF00"));

    }

    private String getValue() {
        DataManager dataManager = DataManager.getInstance();
        dataManager.prepareUserStats();
        UserStats userStats = UserStats.getInstance();
        int powerUsage = userStats.getPowerUsage();
        String value = Integer.toString(powerUsage) + " Wh per day";
        return value;
    }

    private String getUsageInfo() {
        DataManager dataManager = DataManager.getInstance();
        dataManager.prepareUserStats();
        UserStats userStats = UserStats.getInstance();
        int powerUsage = userStats.getPowerUsage();
        int standbyPowerUsage = userStats.getStandbyPowerUsage();
        DecimalFormat decimalFormat = new DecimalFormat("0.00");
        double approximateCost = (powerUsage/1000.0) * 0.16;
        String info = "Basic devices you selected consume approximately " + powerUsage + " Wh per day.";
        info = info.concat("This includes " + standbyPowerUsage + " Wh used by stand-by devices.");
        info = info.concat("\n\nAll this devices' power consumption costs approx. " +
                decimalFormat.format(approximateCost) + " USD per day.");
        if (standbyPowerUsage > 600) {
            double approximateStandbyCost = (standbyPowerUsage/1000.0) * 0.16;
            info = info.concat("\n\nYour devices remaining in stand-by mode cost you approx. " +
                    decimalFormat.format(approximateStandbyCost) + "USD per day.");
            info = info.concat("\n\nCheck if you really need your devices to run stand-by.");
        }
        return info;
    }
}
