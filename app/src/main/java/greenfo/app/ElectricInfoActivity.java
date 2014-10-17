package greenfo.app;

import android.app.Activity;
import android.graphics.Color;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.TextView;

import java.util.Random;

import model.FullSelection;
import model.User;
import services.DataManager;


public class ElectricInfoActivity extends Activity {

    private TextView electricInfo;
    private TextView electricHint;
    private TextView electricValue;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_electric_info);
        this.showInfo();
        BackgroundHelper.setBackground(this);
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
        return super.onOptionsItemSelected(item);
    }

    private void showInfo() {
        this.electricInfo = (TextView) findViewById(R.id.electric_info);
        this.electricHint = (TextView) findViewById(R.id.electric_hint);
        this.electricValue = (TextView) findViewById(R.id.electric_value);
        User user = User.getInstance();
        FullSelection fullSelection = FullSelection.getInstance();
        this.electricInfo.setText(getUsageInfo());
        int index = new Random().nextInt(fullSelection.electricTips.length);
        this.electricHint.setText(fullSelection.electricTips[index]);
        this.electricValue.setText(this.getValue());
        int powerUsage = user.getUserStats().getPowerUsage();
        if (powerUsage > FullSelection.getInstance().powerLevelLimits[0])
            electricValue.setTextColor(Color.parseColor("#FF0000"));
        else if (powerUsage > FullSelection.getInstance().powerLevelLimits[1])
            electricValue.setTextColor(Color.parseColor("#FCC800"));
        else
            electricValue.setTextColor(Color.parseColor("#00FF00"));

    }

    private String getValue() {
        User user = User.getInstance();
        DataManager.prepareUserStats();
        int powerUsage = user.getUserStats().getPowerUsage();
        String value = Integer.toString(powerUsage) + " Wh per day";
        return value;
    }

    private String getUsageInfo() {
        User user = User.getInstance();
        DataManager.prepareUserStats();
        int powerUsage = user.getUserStats().getPowerUsage();
        int standbyPowerUsage = user.getUserStats().getStandbyPowerUsage();
        String info = "Basic devices you selected consume approximately " + powerUsage + " Wh per day.";
        info = info.concat("\nThis includes " + standbyPowerUsage + " Wh used by ones running stand-by.");
        return info;
    }
}
