package greensaver.app;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import model.FullSelection;
import model.NameAndType;
import services.DataManager;
import services.DataSaver;


public class AddElementBasicActivity extends Activity {

    private String selectionFullName;

    private TextView basicSettingsNameBox;
    private ImageButton advancedSettingsButton;
    private ImageButton removeElementButton;
    private ImageView basicSettingsElementImage;

    private ImageButton smallUsageButton;
    private ImageButton averageUsageButton;
    private ImageButton largeUsageButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_element_basic);
        BackgroundHelper.setBackground(this);
        Bundle bundle = getIntent().getExtras();
        if (bundle != null)
            this.selectionFullName = bundle.getString("selectionName");
        else
            this.selectionFullName = "TV"; //default.
        NameAndType selection = NameAndType.recognizeNameAndType(selectionFullName, FullSelection.getInstance());
        this.initializeAllControls();
        this.setBasicSettingsElementImage(selection.getName());
        this.setRemoveElementButtonOnClick(selection.getType());
        this.setAdvancedSettingsButtonOnClick(selection);
        this.setSmallUsageButtonOnClick(selection);
        this.setAverageUsageButtonOnClick(selection);
        this.setLargeUsageButtonOnClick(selection);
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.add_element_basic, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        return super.onOptionsItemSelected(item);
    }

    private void initializeAllControls() {
        this.basicSettingsNameBox = (TextView) findViewById(R.id.basic_settings_name_box);
        this.basicSettingsNameBox.setText(selectionFullName);
        this.advancedSettingsButton = (ImageButton) findViewById(R.id.advanced_settings_button);
        this.removeElementButton = (ImageButton) findViewById(R.id.remove_element_button);
        this.basicSettingsElementImage = (ImageView) findViewById(R.id.basic_settings_element_image);
        this.smallUsageButton = (ImageButton) findViewById(R.id.small_usage_button);
        this.averageUsageButton = (ImageButton) findViewById(R.id.medium_usage_button);
        this.largeUsageButton = (ImageButton) findViewById(R.id.large_usage_button);
    }

    private void setBasicSettingsElementImage(String name) {
        int drawableResourceId = this.getResources().getIdentifier(name, "drawable", this.getPackageName());
        this.basicSettingsElementImage.setImageResource(drawableResourceId);
    }

    private void setRemoveElementButtonOnClick(int selectionType) {
        switch (selectionType) {
            case 0:
                this.removeElementButton.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        DataManager.removeElectricDevice(selectionFullName);
                        DataSaver.saveDataToFile(v.getContext());
                        returnToShowUserActivity();
                    }
                });
                break;

            case 1:
                this.removeElementButton.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        DataManager.removeWaterActivity(selectionFullName);
                        DataSaver.saveDataToFile(v.getContext());
                        returnToShowUserActivity();
                    }
                });
                break;

            case 2:
                this.removeElementButton.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        DataManager.removeRefuseProduction(selectionFullName);
                        DataSaver.saveDataToFile(v.getContext());
                        returnToShowUserActivity();
                    }
                });
                break;

            default:
                break;
        }
    }

    private void setAdvancedSettingsButtonOnClick(NameAndType selection) {
        this.advancedSettingsButton.setTag(selectionFullName);

        switch (selection.getType()) {
            case 0:
                this.advancedSettingsButton.setOnClickListener(new ElectricDeviceDetailsClickListener());
                break;

            case 1:
                this.advancedSettingsButton.setOnClickListener(new WaterActivityDetailsClickListener());
                break;

            case 2:
                this.advancedSettingsButton.setOnClickListener(new RefuseProductionDetailsClickListener());
                break;

            default:
                break;
        }
    }

    private void setSmallUsageButtonOnClick(final NameAndType selection) {
        switch (selection.getType()) {
            case 0:
                this.smallUsageButton.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        int deviceNumber = selection.getIndex();
                        int powerConsumption = getAveragePowerConsumption(deviceNumber);
                        int hoursPerDay = getSmallDeviceTime(deviceNumber);
                        int standbyPowerConsumption = getAverageStandbyPowerConsumption(deviceNumber);
                        int standbyHoursPerDay = 24 - hoursPerDay;
                        DataManager.storeElectricDeviceData(selectionFullName, powerConsumption,
                                hoursPerDay,
                                standbyPowerConsumption,
                                standbyHoursPerDay);
                        DataSaver.saveDataToFile(v.getContext());
                        returnToShowUserActivity();
                    }
                });
                break;

            case 1:
                this.smallUsageButton.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        int activityNumber = selection.getIndex();
                        int litersUsed = getAverageWaterUsage(activityNumber);
                        int timesPerDay = getSmallWaterTimesPerDay(activityNumber);
                        DataManager.storeWaterActivityData(selectionFullName, litersUsed, timesPerDay);
                        DataSaver.saveDataToFile(v.getContext());
                        returnToShowUserActivity();
                    }
                });
                break;

            case 2:
                this.smallUsageButton.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        int productionNumber = selection.getIndex();
                        int pointValue = getSmallRefusePointValue(productionNumber);
                        DataManager.storeRefuseProductionData(selectionFullName, pointValue);
                        DataSaver.saveDataToFile(v.getContext());
                        returnToShowUserActivity();
                    }
                });
                break;

            default:
                break;
        }
    }

    private void setAverageUsageButtonOnClick(final NameAndType selection) {
        switch (selection.getType()) {
            case 0:
                this.averageUsageButton.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        int deviceNumber = selection.getIndex();
                        int powerConsumption = getAveragePowerConsumption(deviceNumber);
                        int hoursPerDay = getAverageDeviceTime(deviceNumber);
                        int standbyPowerConsumption = getAverageStandbyPowerConsumption(deviceNumber);
                        int standbyHoursPerDay = 24 - hoursPerDay;
                        DataManager.storeElectricDeviceData(selectionFullName, powerConsumption,
                                hoursPerDay,
                                standbyPowerConsumption,
                                standbyHoursPerDay);
                        DataSaver.saveDataToFile(v.getContext());
                        returnToShowUserActivity();
                    }
                });
                break;

            case 1:
                this.averageUsageButton.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        int activityNumber = selection.getIndex();
                        int litersUsed = getAverageWaterUsage(activityNumber);
                        int timesPerDay = getAverageWaterTimesPerDay(activityNumber);
                        DataManager.storeWaterActivityData(selectionFullName, litersUsed, timesPerDay);
                        DataSaver.saveDataToFile(v.getContext());
                        returnToShowUserActivity();
                    }
                });
                break;

            case 2:
                this.averageUsageButton.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        int productionNumber = selection.getIndex();
                        int pointValue = getAverageRefusePointValue(productionNumber);
                        DataManager.storeRefuseProductionData(selectionFullName, pointValue);
                        DataSaver.saveDataToFile(v.getContext());
                        returnToShowUserActivity();
                    }
                });
                break;

            default:
                break;
        }
    }

    private void setLargeUsageButtonOnClick(final NameAndType selection) {
        switch (selection.getType()) {
            case 0:
                this.largeUsageButton.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        int deviceNumber = selection.getIndex();
                        int powerConsumption = getAveragePowerConsumption(deviceNumber);
                        int hoursPerDay = getLargeDeviceTime(deviceNumber);
                        int standbyPowerConsumption = getAverageStandbyPowerConsumption(deviceNumber);
                        int standbyHoursPerDay = 24 - hoursPerDay;
                        DataManager.storeElectricDeviceData(selectionFullName, powerConsumption,
                                hoursPerDay,
                                standbyPowerConsumption,
                                standbyHoursPerDay);
                        DataSaver.saveDataToFile(v.getContext());
                        returnToShowUserActivity();
                    }
                });
                break;

            case 1:
                this.largeUsageButton.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        int activityNumber = selection.getIndex();
                        int litersUsed = getAverageWaterUsage(activityNumber);
                        int timesPerDay = getLargeWaterTimesPerDay(activityNumber);
                        DataManager.storeWaterActivityData(selectionFullName, litersUsed, timesPerDay);
                        DataSaver.saveDataToFile(v.getContext());
                        returnToShowUserActivity();
                    }
                });
                break;

            case 2:
                this.largeUsageButton.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        int productionNumber = selection.getIndex();
                        int pointValue = getLargeRefusePointValue(productionNumber);
                        DataManager.storeRefuseProductionData(selectionFullName, pointValue);
                        DataSaver.saveDataToFile(v.getContext());
                        returnToShowUserActivity();
                    }
                });
                break;

            default:
                break;
        }
    }

    private int getAveragePowerConsumption(int deviceNumber) {
        FullSelection fullSelection = FullSelection.getInstance();
        int sum = fullSelection.minPowerConsumptions[deviceNumber] + fullSelection.maxPowerConsumptions[deviceNumber];
        return (int)(sum/2);
    }

    private int getAverageStandbyPowerConsumption(int deviceNumber) {
        FullSelection fullSelection = FullSelection.getInstance();
        int sum = fullSelection.minStandbyPowerConsumptions[deviceNumber] + fullSelection.maxStandbyPowerConsumptions[deviceNumber];
        return (int) (sum/2);
    }

    private int getAverageWaterUsage(int activityNumber) {
        FullSelection fullSelection = FullSelection.getInstance();
        int sum = fullSelection.minWaterUsage[activityNumber] + fullSelection.maxWaterUsage[activityNumber];
        return (int) (sum/2);
    }

    private int getSmallRefusePointValue(int productionNumber) {
        FullSelection fullSelection = FullSelection.getInstance();
        int pointValue = fullSelection.refusePointValues[productionNumber];
        return (int) (pointValue*0.33);
    }

    private int getAverageRefusePointValue(int productionNumber) {
        FullSelection fullSelection = FullSelection.getInstance();
        int pointValue = fullSelection.refusePointValues[productionNumber];
        return (int) (pointValue*0.66);
    }

    private int getLargeRefusePointValue(int productionNumber) {
        FullSelection fullSelection = FullSelection.getInstance();
        return fullSelection.refusePointValues[productionNumber];
    }

    private int getSmallDeviceTime(int deviceNumber) {
        FullSelection fullSelection = FullSelection.getInstance();
        return fullSelection.minDeviceTime[deviceNumber];
    }

    private int getAverageDeviceTime(int deviceNumber) {
        FullSelection fullSelection = FullSelection.getInstance();
        int sum = fullSelection.minDeviceTime[deviceNumber] + fullSelection.maxDeviceTime[deviceNumber];
        return (int) (sum/2);
    }

    private int getLargeDeviceTime(int deviceNumber) {
        FullSelection fullSelection = FullSelection.getInstance();
        return fullSelection.maxDeviceTime[deviceNumber];
    }

    private int getSmallWaterTimesPerDay(int activityNumber) {
        FullSelection fullSelection = FullSelection.getInstance();
        return fullSelection.minWaterTimesPerDay[activityNumber];
    }

    private int getAverageWaterTimesPerDay(int activityNumber) {
        FullSelection fullSelection = FullSelection.getInstance();
        int sum = fullSelection.minWaterTimesPerDay[activityNumber] + fullSelection.maxWaterTimesPerDay[activityNumber];
        return (int) (sum/2);
    }

    private int getLargeWaterTimesPerDay(int activityNumber) {
        FullSelection fullSelection = FullSelection.getInstance();
        return fullSelection.maxWaterTimesPerDay[activityNumber];
    }

    private void returnToShowUserActivity() {
        Intent intent = new Intent(getApplicationContext(), ShowUserActivity.class);
        startActivity(intent);
    }
}
