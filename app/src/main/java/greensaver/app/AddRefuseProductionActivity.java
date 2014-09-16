package greensaver.app;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Spinner;
import android.widget.TextView;

import services.DataManager;
import services.DataSaver;


public class AddRefuseProductionActivity extends Activity {

    private final int[] pointValues = {10, 8, 4, 5, 5, 5, 5};

    private TextView pointValueBox;
    private TextView refuseProductionNameBox;

    private String selectedProductionName;
    private String selectedProductionFullName;
    private int selectedProductionNumber = 0;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_refuse_production);
        Bundle bundle = getIntent().getExtras();
        if (bundle != null)
            this.selectedProductionName = bundle.getString("selectionName");
        else
            this.selectedProductionName = "segregation"; //default.
        this.selectedProductionNumber = this.getProductionNumberFromName(selectedProductionName);
        this.selectedProductionFullName = getResources().getStringArray(R.array.refuse_production_spinner_list)[this.selectedProductionNumber];
        this.initializeAllTextBoxes();
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.add_refuse_production, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        return super.onOptionsItemSelected(item);
    }

    private void initializeAllTextBoxes() {
        this.pointValueBox = (TextView) findViewById(R.id.refuse_production_point_value_box);
        this.pointValueBox.setText(Integer.toString(pointValues[this.selectedProductionNumber]));
        this.refuseProductionNameBox = (TextView) findViewById(R.id.refuse_production_name_box);
        this.refuseProductionNameBox.setText(this.selectedProductionFullName);
    }

    public void addRefuseProduction(View v) {
        int pointValue = Integer.parseInt(this.pointValueBox.getText().toString());
        DataManager.storeRefuseProductionData(this.selectedProductionFullName, pointValue);
        Intent intent = new Intent(getApplicationContext(), ShowUserActivity.class);
        DataSaver.saveDataToFile(this);
        startActivity(intent);
    }

    private int getProductionNumberFromName(String name) {
        if (name.equals("segregation"))
            return 0;
        else if (name.equals("plastic_bags"))
            return 1;
        else if (name.equals("pressing_bottles"))
            return 2;
        else if (name.equals("medicine"))
            return 3;
        else if (name.equals("batteries_and_bulbs"))
            return 4;
        else if (name.equals("household"))
            return 5;
        else if (name.equals("big_size_waste"))
            return 6;
        else
            return 0;
    }
}
