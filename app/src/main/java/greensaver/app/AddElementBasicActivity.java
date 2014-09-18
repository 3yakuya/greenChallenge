package greensaver.app;

import android.app.Activity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageButton;
import android.widget.ImageView;

import services.DataManager;


public class AddElementBasicActivity extends Activity {

    private final int[] averagePowerConsumptions = {75, 27, 100, 70, 6, 25, 25, 1200};
    private final int[] averageStandbyPowerConsumptions = {3, 10, 7, 2, 4, 10, 8, 10};
    private final int[] averageWaterUsage = {50, 12, 50, 130, 35, 1, 12, 100};
    private final int[] pointValues = {10, 8, 4, 5, 5, 5, 5};

    private String selectionFullName;

    private ImageButton advancedSettingsButton;
    private ImageButton removeElementButton;
    private ImageView basicSettingsElementImage;

    private ImageButton smallUsageButton;
    private ImageButton mediumUsageButton;
    private ImageButton largeUsageButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_element_basic);
        Bundle bundle = getIntent().getExtras();
        if (bundle != null)
            this.selectionFullName = bundle.getString("selectionName");
        else
            this.selectionFullName = "TV"; //default.
        NameAndType selection = NameAndType.recognizeNameAndType(selectionFullName);
        this.initializeAllControls();
        this.setBasicSettingsElementImage(selection.getName());
        this.setRemoveElementButtonOnClick(selection.getType());
        this.setAdvancedSettingsButtonOnClick(selection);
        this.setSmallUsageButtonOnClick(selection);
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.add_element_basic, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();
        if (id == R.id.action_settings) {
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

    private void initializeAllControls() {
        this.advancedSettingsButton = (ImageButton) findViewById(R.id.advanced_settings_button);
        this.removeElementButton = (ImageButton) findViewById(R.id.remove_element_button);
        this.basicSettingsElementImage = (ImageView) findViewById(R.id.basic_settings_element_image);
        this.smallUsageButton = (ImageButton) findViewById(R.id.small_usage_button);
        this.mediumUsageButton = (ImageButton) findViewById(R.id.medium_usage_button);
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
                    }
                });
                break;

            case 1:
                this.removeElementButton.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        DataManager.removeWaterActivity(selectionFullName);
                    }
                });
                break;

            case 2:
                this.removeElementButton.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        DataManager.removeRefuseProduction(selectionFullName);
                    }
                });
                break;

            default:
                break;
        }
    }

    private void setAdvancedSettingsButtonOnClick(NameAndType selection) {
        this.advancedSettingsButton.setTag(selection.getName());

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
                        int deviceNumber = NameAndType.getElectricDeviceNumberFromName(selection.getName());
                        int powerConsumpton = averagePowerConsumptions[deviceNumber];
                        int hoursPerDay = 12;
                        int standbyPowerConsumption = averageStandbyPowerConsumptions[deviceNumber];
                        int standbyHoursPerDay = 12;
                        DataManager.storeElectricDeviceData(selectionFullName, powerConsumpton,
                                hoursPerDay,
                                standbyPowerConsumption,
                                standbyHoursPerDay);
                    }
                });
                break;

            case 1:
                this.smallUsageButton.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        int activityNumber = NameAndType.getWaterActivityNumberFromName(selection.getName());
                        int litersUsed = averageWaterUsage[activityNumber];
                        int timesPerDay = 3;
                        DataManager.storeWaterActivityData(selectionFullName, litersUsed, timesPerDay);
                    }
                });
                break;

            case 2:
                this.smallUsageButton.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        int productionNumber = NameAndType.getRefuseProductionNumberFromName(selection.getName());
                        int pointValue = pointValues[productionNumber];
                        DataManager.storeRefuseProductionData(selectionFullName, pointValue);
                    }
                });
                break;

            default:
                break;
        }
    }
}
