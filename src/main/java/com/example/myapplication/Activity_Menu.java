package com.example.myapplication;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;

import androidx.appcompat.app.AppCompatActivity;


public class Activity_Menu  extends AppCompatActivity {

    ImageButton[] dogs;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu);
        findViews();

        dogs[0].setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openActivityPanelPoodle();
            }
        });

        dogs[1].setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openActivityPanelBulldog();
            }
        });

        dogs[2].setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openActivityPanelBorderCollie();
            }
        });

    }

    private void openActivityPanelPoodle() {
        Intent gameScreen = new Intent(this, Activity_Panel.class);
        gameScreen.putExtra("dog_id", R.drawable.poodlenew);
        startActivity(gameScreen);
        finish();
    }

    private void openActivityPanelBulldog() {
        Intent gameScreen = new Intent(this, Activity_Panel.class);
        gameScreen.putExtra("dog_id", R.drawable.img_bulldog);
        startActivity(gameScreen);
        finish();
    }

    private void openActivityPanelBorderCollie() {
        Intent gameScreen = new Intent(this, Activity_Panel.class);
        gameScreen.putExtra("dog_id", R.drawable.img_bordercollie);
        startActivity(gameScreen);
        finish();
    }

    private void findViews() {
        dogs = new ImageButton[]{
                findViewById(R.id.Menu_BTN_poodle), findViewById(R.id.Menu_BTN_bulldog), findViewById(R.id.Menu_BTN_borderCollie)
        };
    }
}


