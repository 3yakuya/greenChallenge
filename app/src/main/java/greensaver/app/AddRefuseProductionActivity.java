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


public class AddRefuseProductionActivity extends Activity
        implements AdapterView.OnItemSelectedListener {

    private final int[] pointValues = {10, 8, 4, 5, 5, 5, 5};

    private TextView pointValueBox;

    private Spinner refuseProductionSpinner;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_refuse_production);
        this.initializeRefuseProductionSpinner();
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

    public void onItemSelected(AdapterView<?> parent, View view, int pos, long id) {
        pointValueBox.setText(Integer.toString(pointValues[pos]));
    }

    public void onNothingSelected(AdapterView<?> parent) {
    }

    private void initializeRefuseProductionSpinner() {
        this.refuseProductionSpinner = (Spinner)findViewById(R.id.refuse_production_spinner);
        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this,
                R.array.refuse_production_spinner_list, android.R.layout.simple_spinner_item);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        this.refuseProductionSpinner.setAdapter(adapter);
        this.refuseProductionSpinner.setOnItemSelectedListener(this);
    }

    private void initializeAllTextBoxes() {
        this.pointValueBox = (TextView) findViewById(R.id.refuse_production_point_value_box);
    }

    public void addRefuseProduction(View v) {
        DataManager dataManager = DataManager.getInstance();
        String name = this.refuseProductionSpinner.getSelectedItem().toString();
        int pointValue = Integer.parseInt(this.pointValueBox.getText().toString());
        dataManager.storeRefuseProductionData(name, pointValue);
        Intent intent = new Intent(getApplicationContext(), ShowUserActivity.class);
        DataSaver.saveDataToFile(this);
        startActivity(intent);
    }
}
