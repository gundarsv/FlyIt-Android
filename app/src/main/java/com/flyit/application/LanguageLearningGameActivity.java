package com.flyit.application;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.FrameLayout;

import com.flyit.languagelearninggame.OverrideUnityActivity;

public class LanguageLearningGameActivity extends OverrideUnityActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }


    @Override
    protected void showMainActivity() {
        Intent intent = new Intent(this, EntertainmentActivity.class);
        intent.addFlags(Intent.FLAG_ACTIVITY_NO_ANIMATION);
        startActivity(intent);
        finish();
        overridePendingTransition(0, 0);
    }
}
