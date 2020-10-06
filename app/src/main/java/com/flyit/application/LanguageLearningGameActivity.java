package com.flyit.application;

import android.content.Intent;
import android.util.Log;

import com.flyit.languagelearninggame.OverrideUnityActivity;

public class LanguageLearningGameActivity extends OverrideUnityActivity {

    @Override
    protected void showMainActivity() {
        Log.d("gameFlow", "called");
        Intent intentEntertainmentActivity = new Intent(this, EntertainmentActivity.class);
        startActivity(intentEntertainmentActivity);
    }
}
