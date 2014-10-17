package greenfo.app;

import android.app.Activity;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.drawable.BitmapDrawable;
import android.view.View;

import model.FullSelection;
import model.User;
import services.DataManager;

class BackgroundHelper {

    public static void setBackground(Activity activity) {
        View layout = activity.findViewById(R.id.background);
        final BitmapFactory.Options blurOptions = new BitmapFactory.Options();
        blurOptions.inSampleSize = 15;
        Bitmap blurTemplate = BitmapFactory.decodeResource(activity.getResources(), BackgroundHelper.selectBackgroundImage(), blurOptions);
        BitmapDrawable drawableBackground = new BitmapDrawable(blurTemplate);
        layout.setBackground(drawableBackground);
    }

    public static int selectBackgroundImage() {
        int currentScore = getCurrentUserScore();
        switch (currentScore) {
            case 0:
                return R.drawable.aaa;
            case 1:
                return R.drawable.aab;
            case 2:
                return R.drawable.aac;
            case 10:
                return R.drawable.aba;
            case 11:
                return R.drawable.abb;
            case 12:
                return R.drawable.abc;
            case 20:
                return R.drawable.aca;
            case 21:
                return R.drawable.acb;
            case 22:
                return R.drawable.acc;
            case 100:
                return R.drawable.baa;
            case 101:
                return R.drawable.bab;
            case 102:
                return R.drawable.bac;
            case 110:
                return R.drawable.bba;
            case 111:
                return R.drawable.bbb;
            case 112:
                return R.drawable.bbc;
            case 120:
                return R.drawable.aca;
            case 121:
                return R.drawable.bcb;
            case 122:
                return R.drawable.bcc;
            case 200:
                return R.drawable.caa;
            case 201:
                return R.drawable.cab;
            case 202:
                return R.drawable.cac;
            case 210:
                return R.drawable.cba;
            case 211:
                return R.drawable.cbb;
            case 212:
                return R.drawable.cbc;
            case 220:
                return R.drawable.cca;
            case 221:
                return R.drawable.ccb;
            case 222:
                return R.drawable.ccc;
            default:
                return R.drawable.aaa;
        }
    }

    private static int getCurrentUserScore() {
        DataManager.prepareUserStats();
        User user = User.getInstance();
        int score;
        int electricPowerScore = user.getUserStats().getPowerUsage();
        if (electricPowerScore > FullSelection.getInstance().powerLevelLimits[0]) {
            score = 200;
        } else if (electricPowerScore > FullSelection.getInstance().powerLevelLimits[1]) {
            score = 100;
        } else {
            score = 0;
        }

        int waterScore = user.getUserStats().getWaterUsage();
        if (waterScore > FullSelection.getInstance().waterLevelLimits[0]) {
            score += 20;
        } else if (waterScore > FullSelection.getInstance().waterLevelLimits[1]) {
            score += 10;
        }

        int refuseScore = user.getUserStats().getRefuseProductionPoints();
        if (refuseScore < FullSelection.getInstance().refuseLevelLimits[0]) {
            score += 2;
        } else if (refuseScore < FullSelection.getInstance().refuseLevelLimits[1]) {
            score += 1;
        }

        return score;
    }
}
