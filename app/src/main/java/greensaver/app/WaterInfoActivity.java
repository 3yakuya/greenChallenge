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

    private final String[] tips = {
            "Turn on the washing machine or dishwasher only after it is full, or use energy-saving mode.",
            "Gather rain water and use it to water the plants.",
            "Always turn off the tap while brushing your teeth or shaving.",
            "When washing your car use a sponge and a bucket of water. Use a garden hose only to rinse the car.",
            "Do not use running water to defrost food (place it in a fridge the night before instead.)",
            "Don't pour out water you can use to wash something or to water the plants.",
            "Avoid unnecessary flushing the toilet (for example, after you throw a single tissue in.)",
            "Water the plants late in the evening to avoid excessive transpiration (this is good for plants too!)",
            "Fix a leaking tap, check all the valves and gaskets.",
            "Use a single-handle tap. Using two spigots to regulate temperature wastes both water and time.",
            "Don't make a mistake to think that water is an infinite resource - it is not."
    };

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
        this.waterInfo.setText(getUsageInfo());
        int index = new Random().nextInt(tips.length);
        this.waterHint.setText(tips[index]);
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
        DecimalFormat decimalFormat = new DecimalFormat("0.00");
        double approximateCost = (waterUsage/1000.0) * 1.28;
        String info = "Every day you use about " + waterUsage + " liters of water.";
        info = info.concat("\n\nThis used water cost you approx. " +
                decimalFormat.format(approximateCost) + " USD per day.");
        double approximateCost2 = (waterUsage/1000.0) * 2.26;
        info = info.concat("\n\nIf you use public sewage system, total cost of the water you use" +
                " every day is increased by approx. " + decimalFormat.format(approximateCost2) + " USD.");
        info = info.concat("  This gives you a total of " +
                decimalFormat.format(approximateCost2 + approximateCost) + " USD every day.");
        info = info.concat("\n\nCheck the tip below to see how you can save water.");
        return info;
    }
}
