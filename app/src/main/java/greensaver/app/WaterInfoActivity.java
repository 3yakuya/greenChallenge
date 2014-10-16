package greensaver.app;

import android.app.Activity;
import android.graphics.Color;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.TextView;

import java.text.DecimalFormat;
import java.util.Random;

import model.FullSelection;
import model.User;
import services.DataManager;


public class WaterInfoActivity extends Activity {

    private TextView waterInfo;
    private TextView waterHint;
    private TextView waterValue;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_water_info);
        this.showInfo();
        BackgroundHelper.setBackground(this);
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.water_info, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        return super.onOptionsItemSelected(item);
    }

    private void showInfo() {
        this.waterInfo = (TextView) findViewById(R.id.water_info);
        this.waterHint = (TextView) findViewById(R.id.water_hint);
        this.waterValue = (TextView) findViewById(R.id.water_value);
        User user = User.getInstance();
        FullSelection fullSelection = FullSelection.getInstance();
        this.waterInfo.setText(getUsageInfo());
        int index = new Random().nextInt(fullSelection.waterTips.length);
        this.waterHint.setText(fullSelection.waterTips[index]);
        this.waterValue.setText(this.getValue());
        int waterUsage = user.getUserStats().getWaterUsage();
        if (waterUsage > FullSelection.getInstance().waterLevelLimits[0])
            waterValue.setTextColor(Color.parseColor("#FF0000"));
        else if (waterUsage > FullSelection.getInstance().waterLevelLimits[1])
            waterValue.setTextColor(Color.parseColor("#FCC800"));
        else
            waterValue.setTextColor(Color.parseColor("#00FF00"));
    }

    private String getValue() {
        User user = User.getInstance();
        DataManager.prepareUserStats();
        int waterUsage = user.getUserStats().getWaterUsage();
        String value = Integer.toString(waterUsage) + " liters per day";
        return value;
    }

    private String getUsageInfo() {
        User user = User.getInstance();
        DataManager.prepareUserStats();
        int waterUsage = user.getUserStats().getWaterUsage();
        String info = "Every day you use about " + waterUsage + " liters of water.";
        info = info.concat("\n\nCheck the tip below to see how you can save it.");
        return info;
    }
}
