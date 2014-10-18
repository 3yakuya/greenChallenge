package greenfo.app;

import android.app.ActionBar;
import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.NavUtils;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.SeekBar;
import android.widget.TextView;

import model.FullSelection;
import model.NameAndType;
import services.DataManager;


public class AddWaterActivityActivity extends Activity implements SeekBar.OnSeekBarChangeListener {

    private TextView litersUsedBox;
    private TextView timesPerDayBox;
    private TextView waterActivityNameBox;

    private SeekBar litersUsedBar;
    private SeekBar timesPerDayBar;

    private String selectedActivityName;
    private String selectedActivityFullName;
    private int selectedActivityNumber = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_water);
        ActionBar actionBar = getActionBar();
        actionBar.setDisplayHomeAsUpEnabled(true);
        BackgroundHelper.setBackground(this);
        Bundle bundle = getIntent().getExtras();
        if (bundle != null)
            this.selectedActivityName = bundle.getString("selectionName");
        else
            this.selectedActivityName = "washing_machine"; //default.
        NameAndType selection = NameAndType.recognizeNameAndType(selectedActivityName, FullSelection.getInstance());
        this.selectedActivityNumber = selection.getIndex();
        this.selectedActivityFullName = FullSelection.getInstance().waterActivityNames[selectedActivityNumber];
        this.initializeAllSeekBars();
        this.initializeAllTextBoxes();
        this.prepareAverageValuesForDevice();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.add_water, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                NavUtils.navigateUpFromSameTask(this);
                return true;
        }
        return super.onOptionsItemSelected(item);
    }

    public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
        this.setTextBoxHavingSeekBar(seekBar);
    }

    public void onStartTrackingTouch(SeekBar seekBar) {
    }

    public void onStopTrackingTouch(SeekBar seekBar) {
    }

    private void initializeAllTextBoxes() {
        this.litersUsedBox = (TextView) findViewById(R.id.water_activity_liters_used_box);
        this.timesPerDayBox = (TextView) findViewById(R.id.water_activity_times_per_day_box);
        this.waterActivityNameBox = (TextView) findViewById(R.id.water_activity_name_box);
        this.waterActivityNameBox.setText(this.selectedActivityFullName);
    }

    private void initializeAllSeekBars() {
        this.litersUsedBar = (SeekBar) findViewById(R.id.water_activity_liters_used_bar);
        this.litersUsedBar.setOnSeekBarChangeListener(this);
        this.timesPerDayBar = (SeekBar) findViewById(R.id.water_activity_times_per_day_bar);
        this.timesPerDayBar.setOnSeekBarChangeListener(this);
    }

    private void setTextBoxHavingSeekBar(SeekBar seekBar) {
        int id = seekBar.getId();
        int percentage = seekBar.getProgress();
        int actualValue;
        switch (id) {
            case R.id.water_activity_liters_used_bar:
                actualValue = calculateLitersUsedForActivity(percentage, this.selectedActivityNumber);
                litersUsedBox.setText(Integer.toString(actualValue));
                break;
            case R.id.water_activity_times_per_day_bar:
                actualValue = calculateTimesPerDayForActivity(percentage);
                timesPerDayBox.setText(Integer.toString(actualValue));
                break;
            default:
                break;
        }
    }

    private int calculateLitersUsedForActivity(int percentage, int activityNumber) {
        int difference = FullSelection.getInstance().maxWaterUsage[activityNumber]
                - FullSelection.getInstance().minWaterUsage[activityNumber];
        double preciseResult = percentage*difference;
        preciseResult /= 100;
        preciseResult += FullSelection.getInstance().minWaterUsage[activityNumber];
        return (int) Math.round(preciseResult);
    }

    private int calculateTimesPerDayForActivity(int percentage) {
        double preciseResult = percentage * 5;
        preciseResult /= 100;
        return (int) Math.round(preciseResult);
    }

    private void prepareAverageValuesForDevice() {
        this.setTextBoxHavingSeekBar(this.litersUsedBar);
        this.setTextBoxHavingSeekBar(this.timesPerDayBar);
    }

    public void addWaterActivity(View v) {
        int litersUsed = Integer.parseInt(this.litersUsedBox.getText().toString());
        int timesPerDay = Integer.parseInt(this.timesPerDayBox.getText().toString());
        DataManager.storeWaterActivityData(this.selectedActivityFullName, litersUsed, timesPerDay);
        Intent intent = new Intent(getApplicationContext(), ShowUserActivity.class);
        DataManager.saveUserDataToFile(this);
        startActivity(intent);
    }

}
