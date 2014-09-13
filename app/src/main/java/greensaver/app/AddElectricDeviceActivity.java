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
import services.DataSaver;


public class AddElectricDeviceActivity extends Activity
        implements AdapterView.OnItemSelectedListener, SeekBar.OnSeekBarChangeListener {

    private final int[] minPowerConsumptions = {50, 5, 30, 5, 3, 5, 10, 650};
    private final int[] maxPowerConsumptions = {100, 50, 300, 170, 10, 45, 40, 1750};
    private final int[] minStandbyPowerConsumptions = {1, 1, 1, 0, 3, 3, 5, 1};
    private final int[] maxStandbyPowerConsumptions = {6, 25, 98, 5, 8, 40, 15, 40};

    private TextView amountBox;
    private TextView powerConsumptionBox;
    private TextView hoursPerDayBox;
    private TextView standbyPowerBox;
    private TextView standbyHoursBox;

    private SeekBar amountBar;
    private SeekBar powerConsumptionBar;
    private SeekBar hoursPerDayBar;
    private SeekBar standbyPowerBar;
    private SeekBar standbyHoursBar;

    private Spinner electricDeviceSpinner;

    private int selectedDeviceNumber = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_electric_device);
        this.initializeElectricDeviceSpinner();
        this.initializeAllSeekBars();
        this.initializeAllTextBoxes();
        this.prepareAverageValuesForDevice();
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.add_electric_device, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        return super.onOptionsItemSelected(item);
    }

    public void onItemSelected(AdapterView<?> parent, View view, int pos, long id) {
        // An item was selected. You can retrieve the selected item using
        // parent.getItemAtPosition(pos)
        this.selectedDeviceNumber = pos;
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

    private void initializeElectricDeviceSpinner() {
        this.electricDeviceSpinner = (Spinner)findViewById(R.id.electric_device_spinner);
        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this,
                R.array.electric_device_spinner_list, android.R.layout.simple_spinner_item);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        this.electricDeviceSpinner.setAdapter(adapter);
        this.electricDeviceSpinner.setOnItemSelectedListener(this);
    }

    private void initializeAllTextBoxes() {
        amountBox = (TextView) findViewById(R.id.electric_device_amount_box);
        powerConsumptionBox = (TextView) findViewById(R.id.electric_device_power_consumption_box);
        hoursPerDayBox = (TextView) findViewById(R.id.electric_device_hours_per_day_box);
        standbyPowerBox = (TextView) findViewById(R.id.electric_device_standby_power_box);
        standbyHoursBox = (TextView) findViewById(R.id.electric_device_standby_hours_box);
    }

    private void initializeAllSeekBars() {
        this.amountBar = (SeekBar) findViewById(R.id.electric_device_amount_bar);
        this.amountBar.setOnSeekBarChangeListener(this);
        this.powerConsumptionBar = (SeekBar) findViewById(R.id.electric_device_power_consumption_bar);
        this.powerConsumptionBar.setOnSeekBarChangeListener(this);
        this.hoursPerDayBar = (SeekBar) findViewById(R.id.electric_device_hours_per_day_bar);
        this.hoursPerDayBar.setOnSeekBarChangeListener(this);
        this.standbyPowerBar = (SeekBar) findViewById(R.id.electric_device_standby_power_bar);
        this.standbyPowerBar.setOnSeekBarChangeListener(this);
        this.standbyHoursBar = (SeekBar) findViewById(R.id.electric_device_standby_hours_bar);
        this.standbyHoursBar.setOnSeekBarChangeListener(this);
    }

    private void setTextBoxHavingSeekBar(SeekBar seekBar) {
        int id = seekBar.getId();
        int percentage = seekBar.getProgress();
        int actualValue;
        switch (id) {
            case R.id.electric_device_amount_bar:
                actualValue = calculateAmountForDevice(percentage);
                amountBox.setText(Integer.toString(actualValue));
                break;
            case R.id.electric_device_power_consumption_bar:
                actualValue = this.calculatePowerForDevice(percentage, this.selectedDeviceNumber);
                powerConsumptionBox.setText(Integer.toString(actualValue));
                break;
            case R.id.electric_device_hours_per_day_bar:
                actualValue = calculateHoursForDevice(percentage);
                hoursPerDayBox.setText(Integer.toString(actualValue));
                break;
            case R.id.electric_device_standby_power_bar:
                actualValue = calculateStandbyPowerForDevice(percentage, this.selectedDeviceNumber);
                standbyPowerBox.setText(Integer.toString(actualValue));
                break;
            case R.id.electric_device_standby_hours_bar:
                actualValue = calculateHoursForDevice(percentage);
                standbyHoursBox.setText(Integer.toString(actualValue));
                break;
            default:
                break;
        }
    }

    private int calculatePowerForDevice(int percentage, int deviceNumber) {
        int difference = this.maxPowerConsumptions[deviceNumber]
                - this.minPowerConsumptions[deviceNumber];
        double preciseResult = percentage*difference;
        preciseResult /= 100;
        preciseResult += minPowerConsumptions[deviceNumber];
        return (int) Math.round(preciseResult);
    }

    private int calculateStandbyPowerForDevice(int percentage, int deviceNumber) {
        int difference = this.maxStandbyPowerConsumptions[deviceNumber]
                - this.minStandbyPowerConsumptions[deviceNumber];
        double preciseResult = percentage*difference;
        preciseResult /= 100;
        preciseResult += minStandbyPowerConsumptions[deviceNumber];
        return (int) Math.round(preciseResult);
    }

    private int calculateAmountForDevice(int percentage) {
        double preciseResult = percentage * 5;
        preciseResult /= 100;
        return (int) Math.round(preciseResult);
    }

    private int calculateHoursForDevice(int percentage) {
        double preciseResult = percentage * 24;
        preciseResult /= 100;
        if (preciseResult < 1)
            preciseResult = 1;
        return (int) Math.round(preciseResult);
    }

    private void prepareAverageValuesForDevice() {
        this.setTextBoxHavingSeekBar(this.amountBar);
        this.setTextBoxHavingSeekBar(this.powerConsumptionBar);
        this.setTextBoxHavingSeekBar(this.hoursPerDayBar);
        this.setTextBoxHavingSeekBar(this.standbyPowerBar);
        this.setTextBoxHavingSeekBar(this.standbyHoursBar);
    }

    public void addElectricDevice(View v) {
        DataManager dataManager = DataManager.getInstance();
        String name = this.electricDeviceSpinner.getSelectedItem().toString();
        int amount = Integer.parseInt(this.amountBox.getText().toString());
        int powerConsumption = Integer.parseInt(this.powerConsumptionBox.getText().toString());
        int hoursPerDay = Integer.parseInt(this.hoursPerDayBox.getText().toString());
        int standbyPowerConsumption = Integer.parseInt(this.standbyPowerBox.getText().toString());
        int standbyHoursPerDay = Integer.parseInt(this.standbyHoursBox.getText().toString());
        dataManager.storeElectricDeviceData(name, amount, powerConsumption, hoursPerDay,
                standbyPowerConsumption, standbyHoursPerDay);
        Intent intent = new Intent(getApplicationContext(), ShowUserActivity.class);
        DataSaver.saveDataToFile(this);
        startActivity(intent);
    }

}
