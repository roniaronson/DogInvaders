package com.example.myapplication.activities;

import android.content.Intent;

import android.os.Build;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageButton;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;

import com.example.myapplication.MSP;
import com.example.myapplication.R;

import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Collections;

import com.example.myapplication.call_backs.CallBack_List;
import com.example.myapplication.fragments.Fragment_List;
import com.example.myapplication.objects.RecordComparator;
import com.google.gson.Gson;
import com.example.myapplication.objects.MyDB;
import com.example.myapplication.objects.Record;

@RequiresApi(api = Build.VERSION_CODES.O)

public class Activity_GameOver extends AppCompatActivity {

    private Fragment_List fragmentList;

    ImageButton[] playOrExit;

    private String distanceCounter;
    private String name = "";

    private DateTimeFormatter dtf = DateTimeFormatter.ofPattern("HH:mm");

    private MyDB myDB;

    private ArrayList<Record> temp = new ArrayList<>();

    @RequiresApi(api = Build.VERSION_CODES.O)

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_gameover);

        findViews();
        initFragments();

        loadFromSP();

        temp = myDB.getRecords();

        Bundle b = getIntent().getExtras();
        if (b != null) {
            distanceCounter = b.getString("distance");
            name = b.getString("name");
            temp.add(new Record().setTime(String.valueOf(LocalDate.now()) + " " + String.valueOf(LocalTime.now().format(dtf)))
                    .setScore(Integer.parseInt(distanceCounter))
                    .setLat(5 * 10)
                    .setLon(5 * 10)
                    .setName(name));
        }

        myDB.setRecords(temp);

        saveToSP();

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

    CallBack_List callBackList = new CallBack_List() {
        @Override
        public ArrayList<Record> getRecords() {
            return myDB.getRecords();
        }
    };

    private void initFragments() {
        fragmentList = new Fragment_List();
        fragmentList.setActivity(Activity_GameOver.this);
        fragmentList.setCallBackList(callBackList);
        getSupportFragmentManager().beginTransaction().add(R.id.gameOver_frame_topTen, fragmentList).commit();
    }

    public void loadFromSP() {
        String js = MSP.getMe().getString("MY_DB", "");
        myDB = new Gson().fromJson(js, MyDB.class);
        Log.d("roni", "loadFromSP: " + js);
    }

    public void saveToSP() {
        pickTop10FromDB();
        String json = new Gson().toJson(myDB);
        MSP.getMe().putString("MY_DB", json);
        Log.d("ronii", "onCreate: " + json);

    }

    private void pickTop10FromDB() {
        temp = myDB.getRecords();
        Collections.sort(temp, new RecordComparator());
        ArrayList<Record> res = new ArrayList<>();
        int counter = 0;
        while (res.size() < 10 && temp.size() > counter) {
            res.add(temp.get(counter));
            counter++;
        }
        myDB.setRecords(res);
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

