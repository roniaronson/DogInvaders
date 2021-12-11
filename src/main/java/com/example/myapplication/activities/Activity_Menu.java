package com.example.myapplication.activities;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;

import com.example.myapplication.R;

public class Activity_Menu extends AppCompatActivity {

    private Button menu_BTN_startGame;
    private Button menu_BTN_topTen;
    private Button menu_BTN_Exit;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_open_menu);

        findViews();

        menu_BTN_startGame.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openActivitySettings();
            }
        });

        menu_BTN_topTen.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openActivityTopTen();
            }
        });

        menu_BTN_Exit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                exitGame();
            }
        });

    }

    private void openActivitySettings() {
        finish();
        Intent gameScreen = new Intent(this, Activity_Settings.class);
        startActivity(gameScreen);

    }

    private void openActivityTopTen() {
        finish();
        Intent gameOverScreen = new Intent(this, Activity_GameOver.class);
        startActivity(gameOverScreen);
    }

    private void exitGame() {
        System.exit(0);
    }

    private void findViews() {
        menu_BTN_startGame = findViewById(R.id.menu_BTN_start);
        menu_BTN_topTen = findViewById(R.id.menu_BTN_topTen);
        menu_BTN_Exit = findViewById(R.id.menu_BTN_exit);
    }

}
