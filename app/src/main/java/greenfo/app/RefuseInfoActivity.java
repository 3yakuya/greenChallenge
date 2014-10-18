package greenfo.app;

import android.app.ActionBar;
import android.app.Activity;
import android.graphics.Color;
import android.os.Bundle;
import android.support.v4.app.NavUtils;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.TextView;

import java.util.Random;

import model.FullSelection;
import model.User;
import services.DataManager;


public class RefuseInfoActivity extends Activity {

    private TextView refuseInfo;
    private TextView refuseHint;
    private TextView refuseValue;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_refuse_info);
        ActionBar actionBar = getActionBar();
        actionBar.setDisplayHomeAsUpEnabled(true);
        this.showInfo();
        BackgroundHelper.setBackground(this);
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.refuse_info, menu);
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

    private void showInfo() {
        this.refuseInfo = (TextView) findViewById(R.id.refuse_info);
        this.refuseHint = (TextView) findViewById(R.id.refuse_hint);
        this.refuseValue = (TextView) findViewById(R.id.refuse_value);
        User user = User.getInstance();
        FullSelection fullSelection = FullSelection.getInstance();
        this.refuseInfo.setText(getUsageInfo());
        int index = new Random().nextInt(fullSelection.refuseTips.length);
        this.refuseHint.setText(fullSelection.refuseTips[index]);
        this.refuseValue.setText(this.getValue());
        int refusePoints = user.getUserStats().getRefuseProductionPoints();
        if (refusePoints < FullSelection.getInstance().refuseLevelLimits[0])
            refuseValue.setTextColor(Color.parseColor("#FF0000"));
        else if (refusePoints < FullSelection.getInstance().refuseLevelLimits[1])
            refuseValue.setTextColor(Color.parseColor("#FFC900"));
        else
            refuseValue.setTextColor(Color.parseColor("#35C30A"));
    }

    private String getValue() {
        User user = User.getInstance();
        DataManager.prepareUserStats();
        int refusePoints = user.getUserStats().getRefuseProductionPoints();
        String value = Integer.toString(refusePoints) + " points";
        return value;
    }

    private String getUsageInfo() {
        User user = User.getInstance();
        DataManager.prepareUserStats();
        int refusePoints = user.getUserStats().getRefuseProductionPoints();
        String info = "Your way of dealing with waste was graded for " + refusePoints + " points.";
        info = info.concat(" In short: the more points the better for the environment.");
        info = info.concat("\n\nCheck the tip below to see how to deal with different kinds of waste.");
        return info;
    }
}
