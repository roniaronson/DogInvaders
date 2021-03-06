package com.example.myapplication.activities;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.RadioButton;
import android.widget.RadioGroup;

import androidx.appcompat.app.AppCompatActivity;

import com.example.myapplication.R;
import com.example.myapplication.activities.Activity_Panel;
import com.google.android.material.switchmaterial.SwitchMaterial;
import com.google.android.material.textfield.TextInputEditText;


public class Activity_Settings extends AppCompatActivity {

    ImageButton[] dogs;
    RadioButton rb_slow;
    RadioButton rb_fast;
    RadioGroup rg_slowOrFast;
    SwitchMaterial sw_sensors;
    EditText name;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_settings);
        findViews();

        dogs[0].setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openActivityPanel(R.drawable.poodlenew);
            }
        });

        dogs[1].setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openActivityPanel(R.drawable.img_bulldog);
            }
        });

        dogs[2].setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) { openActivityPanel(R.drawable.img_bordercollie); }
        });

    }

    private void openActivityPanel(int dog_id) {
        finish();
        Intent gameScreen = new Intent(this, Activity_Panel.class);
        gameScreen.putExtra("dog_id", dog_id);
        gameScreen.putExtra("sensors", sw_sensors.isChecked());
        gameScreen.putExtra("isSlow", rb_slow.isChecked());
        gameScreen.putExtra("name", name.getText().toString());
        startActivity(gameScreen);

    }

    private void findViews() {
        dogs = new ImageButton[]{
                findViewById(R.id.Menu_BTN_poodle), findViewById(R.id.Menu_BTN_bulldog), findViewById(R.id.Menu_BTN_borderCollie)
        };
        rg_slowOrFast = findViewById(R.id.settings_RG_slowOrFast);
        rb_slow = findViewById(R.id.settings_RB_slow);
        rb_fast = findViewById(R.id.settings_RB_Fast);
        sw_sensors = findViewById(R.id.settings_SW_sensors);
        name = findViewById(R.id.Settings_Etxt_name);
    }
}


