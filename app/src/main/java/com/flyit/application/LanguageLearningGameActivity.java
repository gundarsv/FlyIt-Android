package com.flyit.application;

import android.content.Intent;
import android.os.Bundle;

import com.flyit.application.fragments.EntertainmentFragment;
import com.flyit.languagelearninggame.OverrideUnityActivity;

public class LanguageLearningGameActivity extends OverrideUnityActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    protected void showMainActivity() {
        Intent intent = new Intent(this, MainActivity.class);
        intent.addFlags(Intent.FLAG_ACTIVITY_NO_ANIMATION);
        startActivity(intent);
        overridePendingTransition(0, 0);
    }
}
