package com.example.tic_tac_toe;

import androidx.appcompat.app.AppCompatActivity;

import android.app.UiModeManager;
import android.os.Bundle;
import android.view.View;


public class darkThemeToggler extends AppCompatActivity {
    private UiModeManager uiModeManager;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dark_theme_toggler);

        uiModeManager = (UiModeManager) getSystemService(UI_MODE_SERVICE);
    }

    public void NightModeON(View view){
        uiModeManager.setNightMode(UiModeManager.MODE_NIGHT_YES);
    }

    public void NightModeOFF(View view){
        uiModeManager.setNightMode(UiModeManager.MODE_NIGHT_NO);
    }
}

