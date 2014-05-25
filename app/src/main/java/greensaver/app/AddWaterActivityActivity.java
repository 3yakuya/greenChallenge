package greensaver.app;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.SeekBar;
import android.widget.Spinner;
import android.widget.TextView;

import services.DataManager;


public class AddWaterActivityActivity extends Activity
        implements AdapterView.OnItemSelectedListener, SeekBar.OnSeekBarChangeListener {

    private final int[] minWaterUsage = {45, 9, 30, 120, 30, 1, 10, 50};
    private final int[] maxWaterUsage = {60, 25, 80, 150, 40, 2, 15, 150};

    private TextView litersUsedBox;
    private TextView timesPerDayBox;

    private SeekBar litersUsedBar;
    private SeekBar timesPerDayBar;

    private Spinner waterActivitySpinner;

    private int selectedActivityNumber = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_water);
        this.initializeWaterActivitySpinner();
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
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();
        return super.onOptionsItemSelected(item);
    }

    public void onItemSelected(AdapterView<?> parent, View view, int pos, long id) {
        // An item was selected. You can retrieve the selected item using
        // parent.getItemAtPosition(pos)
        this.selectedActivityNumber = pos;
        this.prepareAverageValuesForDevice();
    }

    public void onNothingSelected(AdapterView<?> parent) {
    }

    public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
        this.setTextBoxHavingSeekBar(seekBar);
    }

    public void onStartTrackingTouch(SeekBar seekBar) {
    }

    public void onStopTrackingTouch(SeekBar seekBar) {
    }

    private void initializeWaterActivitySpinner() {
        this.waterActivitySpinner = (Spinner)findViewById(R.id.water_activity_spinner);
        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this,
                R.array.water_activity_spinner_list, android.R.layout.simple_spinner_item);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        this.waterActivitySpinner.setAdapter(adapter);
        this.waterActivitySpinner.setOnItemSelectedListener(this);
    }

    private void initializeAllTextBoxes() {
        this.litersUsedBox = (TextView) findViewById(R.id.water_activity_liters_used_box);
        this.timesPerDayBox = (TextView) findViewById(R.id.water_activity_times_per_day_box);
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
        int difference = this.maxWaterUsage[activityNumber]
                - this.minWaterUsage[activityNumber];
        double preciseResult = percentage*difference;
        preciseResult /= 100;
        preciseResult += minWaterUsage[activityNumber];
        return (int) Math.round(preciseResult);
    }

    private int calculateTimesPerDayForActivity(int percentage) {
        double preciseResult = percentage * 5;
        preciseResult /= 100;
        if (preciseResult < 1)
            preciseResult = 1;
        return (int) Math.round(preciseResult);
    }

    private void prepareAverageValuesForDevice() {
        this.setTextBoxHavingSeekBar(this.litersUsedBar);
        this.setTextBoxHavingSeekBar(this.timesPerDayBar);
    }

    public void addWaterActivity(View v) {
        DataManager dataManager = DataManager.getInstance();
        String name = this.waterActivitySpinner.getSelectedItem().toString();
        int litersUsed = Integer.parseInt(this.litersUsedBox.getText().toString());
        int timesPerDay = Integer.parseInt(this.timesPerDayBox.getText().toString());
        dataManager.storeWaterActivityData(name, litersUsed, timesPerDay);
        Intent intent = new Intent(getApplicationContext(), ShowUserActivity.class);
        startActivity(intent);
    }
}
