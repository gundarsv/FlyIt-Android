package com.flyit.application;


import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

public class EntertainmentActivity extends AppCompatActivity {
    private ImageView languageLearningGame;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_entertainment);
        languageLearningGame = findViewById(R.id.language_game_logo_image);
    }

    public void btnLoadUnity(View v) {
        Intent intent = new Intent(this, LanguageLearningGameActivity.class);
        intent.addFlags(Intent.FLAG_ACTIVITY_NO_ANIMATION);
        startActivity(intent);
        finish();
        overridePendingTransition(0, 0);
    }
}
