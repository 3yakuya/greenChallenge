package greensaver.app;

import android.app.Activity;
import android.graphics.Color;
import android.os.Bundle;
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
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
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
            refuseValue.setTextColor(Color.parseColor("#FCC800"));
        else
            refuseValue.setTextColor(Color.parseColor("#00FF00"));
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
        info = info.concat("\n\n The more points the better for the environment.");
        info = info.concat("\n\nIf you are unsure what to do with some kind of rubbish or waste, " +
                "check it online. Intuition is not a good advisor here.");
        info = info.concat("\n\nCheck the tip below to see how to deal with different kinds of waste.");
        return info;
    }
}
