package greenfo.app;

import android.app.ActionBar;
import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.NavUtils;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;

import model.FullSelection;
import model.NameAndType;
import services.DataManager;


public class AddRefuseProductionActivity extends Activity {

    private TextView pointValueBox;
    private TextView refuseProductionNameBox;

    private String selectedProductionName;
    private String selectedProductionFullName;
    private int selectedProductionNumber = 0;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_refuse_production);
        ActionBar actionBar = getActionBar();
        actionBar.setDisplayHomeAsUpEnabled(true);
        BackgroundHelper.setBackground(this);
        Bundle bundle = getIntent().getExtras();
        if (bundle != null)
            this.selectedProductionName = bundle.getString("selectionName");
        else
            this.selectedProductionName = "general_segregation"; //default.
        NameAndType selection = NameAndType.recognizeNameAndType(selectedProductionName, FullSelection.getInstance());
        this.selectedProductionNumber = selection.getIndex();
        this.selectedProductionFullName = FullSelection.getInstance().refuseProductionNames[selectedProductionNumber];
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
        switch (item.getItemId()) {
            case android.R.id.home:
                NavUtils.navigateUpFromSameTask(this);
                return true;
        }
        return super.onOptionsItemSelected(item);
    }

    private void initializeAllTextBoxes() {
        this.pointValueBox = (TextView) findViewById(R.id.refuse_production_point_value_box);
        int refusePointValue = FullSelection.getInstance().refusePointValues[this.selectedProductionNumber];
        this.pointValueBox.setText(Integer.toString(refusePointValue));
        this.refuseProductionNameBox = (TextView) findViewById(R.id.refuse_production_name_box);
        this.refuseProductionNameBox.setText(this.selectedProductionFullName);
    }

    public void addRefuseProduction(View v) {
        int pointValue = Integer.parseInt(this.pointValueBox.getText().toString());
        DataManager.storeRefuseProductionData(this.selectedProductionFullName, pointValue);
        Intent intent = new Intent(getApplicationContext(), ShowUserActivity.class);
        DataManager.saveUserDataToFile(this);
        startActivity(intent);
    }
}
