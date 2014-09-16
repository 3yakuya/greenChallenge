package greensaver.app;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.SeekBar;
import android.widget.Spinner;
import android.widget.TextView;

import services.DataManager;
import services.DataSaver;


public class AddElectricDeviceActivity extends Activity
        implements SeekBar.OnSeekBarChangeListener {

    private final int[] minPowerConsumptions = {50, 5, 30, 5, 3, 5, 10, 650};
    private final int[] maxPowerConsumptions = {100, 50, 300, 170, 10, 45, 40, 1750};
    private final int[] minStandbyPowerConsumptions = {1, 1, 1, 0, 3, 3, 5, 1};
    private final int[] maxStandbyPowerConsumptions = {6, 25, 98, 5, 8, 40, 15, 40};

    private boolean isRunningStandby = true;

    private TextView powerConsumptionBox;
    private TextView hoursPerDayBox;
    private TextView standbyPowerBox;
    private TextView electricDeviceNameBox;

    private SeekBar powerConsumptionBar;
    private SeekBar hoursPerDayBar;
    private SeekBar standbyPowerBar;

    private String selectedDeviceName;
    private String selectedDeviceFullName;
    private int selectedDeviceNumber = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_electric_device);
        Bundle bundle = getIntent().getExtras();
        if (bundle != null)
            this.selectedDeviceName = bundle.getString("selectionName");
        else
            this.selectedDeviceName = "tv"; //default.
        this.selectedDeviceNumber = this.getDeviceNumberFromName(selectedDeviceName);
        this.selectedDeviceFullName = getResources().getStringArray(R.array.electric_device_spinner_list)[this.selectedDeviceNumber];
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

    public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
        this.setTextBoxHavingSeekBar(seekBar);
    }

    public void onStartTrackingTouch(SeekBar seekBar) {
    }

    public void onStopTrackingTouch(SeekBar seekBar) {
    }

    private void initializeAllTextBoxes() {
        powerConsumptionBox = (TextView) findViewById(R.id.electric_device_power_consumption_box);
        hoursPerDayBox = (TextView) findViewById(R.id.electric_device_hours_per_day_box);
        standbyPowerBox = (TextView) findViewById(R.id.electric_device_standby_power_box);
        electricDeviceNameBox = (TextView) findViewById(R.id.electric_device_name_box);
        electricDeviceNameBox.setText(this.selectedDeviceFullName);
    }

    private void initializeAllSeekBars() {
        this.powerConsumptionBar = (SeekBar) findViewById(R.id.electric_device_power_consumption_bar);
        this.powerConsumptionBar.setOnSeekBarChangeListener(this);
        this.hoursPerDayBar = (SeekBar) findViewById(R.id.electric_device_hours_per_day_bar);
        this.hoursPerDayBar.setOnSeekBarChangeListener(this);
        this.standbyPowerBar = (SeekBar) findViewById(R.id.electric_device_standby_power_bar);
        this.standbyPowerBar.setOnSeekBarChangeListener(this);
    }

    private void setTextBoxHavingSeekBar(SeekBar seekBar) {
        int id = seekBar.getId();
        int percentage = seekBar.getProgress();
        int actualValue;
        switch (id) {
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

    private int calculateHoursForDevice(int percentage) {
        double preciseResult = percentage * 24;
        preciseResult /= 100;
        if (preciseResult < 1)
            preciseResult = 1;
        return (int) Math.round(preciseResult);
    }

    private void prepareAverageValuesForDevice() {
        this.setTextBoxHavingSeekBar(this.powerConsumptionBar);
        this.setTextBoxHavingSeekBar(this.hoursPerDayBar);
        this.setTextBoxHavingSeekBar(this.standbyPowerBar);
    }

    public void addElectricDevice(View v) {
        int powerConsumption = Integer.parseInt(this.powerConsumptionBox.getText().toString());
        int hoursPerDay = Integer.parseInt(this.hoursPerDayBox.getText().toString());
        int standbyPowerConsumption = Integer.parseInt(this.standbyPowerBox.getText().toString());
        int standbyHoursPerDay = this.getStandbyHoursPerDay(hoursPerDay);
        DataManager.storeElectricDeviceData(this.selectedDeviceFullName, powerConsumption, hoursPerDay,
                standbyPowerConsumption, standbyHoursPerDay);
        DataSaver.saveDataToFile(this);
        Intent intent = new Intent(getApplicationContext(), ShowUserActivity.class);
        startActivity(intent);
    }

    public void setStandby(View v) {
        this.isRunningStandby = !this.isRunningStandby;
        ImageButton setStandbyButton = (ImageButton) findViewById(R.id.set_standby_button);
        TextView setStandbyBox = (TextView) findViewById(R.id.set_standby_box);
        if (this.isRunningStandby) {        //TODO Change button images to proper ones standby on/off.
            setStandbyButton.setImageResource(R.drawable.prad);
            setStandbyBox.setText(R.string.standby_on);
        } else {
            setStandbyButton.setImageResource(R.drawable.woda);
            setStandbyBox.setText(R.string.standby_off);
        }
    }

    private int getStandbyHoursPerDay(int hoursPerDay) {
        int day = 24;
        if (this.isRunningStandby)
            return day - hoursPerDay;
        else
            return 0;
    }

    private int getDeviceNumberFromName(String name) {
        if (name.equals("tv"))
                return 0;
        else if (name.equals("radio"))
                return 1;
        else if (name.equals("computer"))
                return 2;
        else if (name.equals("printer"))
                return 3;
        else if (name.equals("router"))
                return 4;
        else if (name.equals("set_top_box"))
                return 5;
        else if (name.equals("dvd_set"))
                return 6;
        else if (name.equals("microwave"))
                return 7;
        else
                return 0;
    }

}
