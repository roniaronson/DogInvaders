package com.example.myapplication;

import android.content.Intent;

import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;

import androidx.appcompat.app.AppCompatActivity;

public class Activity_GameOver extends AppCompatActivity {

    ImageButton[] playOrExit;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_gameover);
        findViews();

        playOrExit[0].setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openActivityPanel();
            }
        });

        playOrExit[1].setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                exitGame();
            }
        });

    }

    private void openActivityPanel() {
        finish();
        Intent gameScreen = new Intent(this, Activity_Settings.class);
        startActivity(gameScreen);

    }


    private void exitGame() {
        System.exit(0);
    }

    private void findViews() {
        playOrExit = new ImageButton[]{
                findViewById(R.id.gameOver_BTN_play), findViewById(R.id.gameOver_BTN_exit)
        };
    }
}
