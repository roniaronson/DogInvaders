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
import com.example.myapplication.call_backs.CallBack_Map;
import com.example.myapplication.fragments.Fragment_List;
import com.example.myapplication.fragments.Fragment_Map;
import com.example.myapplication.objects.RecordComparator;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;
import com.google.gson.Gson;
import com.example.myapplication.objects.MyDB;
import com.example.myapplication.objects.Record;

@RequiresApi(api = Build.VERSION_CODES.O)

public class Activity_GameOver extends AppCompatActivity {

    private Fragment_List fragmentList;
    private Fragment_Map fragmentMap;


    ImageButton[] playOrExit;
    double[] latLon = new double[2];
    private String distanceCounter;
    private String name = "";

    private DateTimeFormatter dtf = DateTimeFormatter.ofPattern("HH:mm");

    private MyDB myDB = new MyDB();

    private ArrayList<Record> temp;

    @RequiresApi(api = Build.VERSION_CODES.O)

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_gameover);
        findViews();
        initFragments();

        loadFromSP();
        if (myDB == null)
            myDB = new MyDB();

        Bundle b = getIntent().getExtras();
        if (b != null) {
            distanceCounter = b.getString("distance");
            name = b.getString("name");
            latLon[0] = b.getDouble("lat");
            latLon[1] = b.getDouble("lon");
            myDB.getRecords().add(new Record().setTime(String.valueOf(LocalDate.now()) + " " + String.valueOf(LocalTime.now().format(dtf)))
                    .setScore(Integer.parseInt(distanceCounter))
                    .setLat(latLon[0])
                    .setLon(latLon[1])
                    .setName(name));
        }


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

    CallBack_Map callBack_map = (lat, lon) -> {
        //Zoom to place
        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager().findFragmentById(R.id.gameOver_fragment_map);
        mapFragment.getMapAsync(googleMap -> {
            LatLng latLng = new LatLng(lat, lon);
            googleMap.clear();
            googleMap.addMarker(new MarkerOptions().position(latLng).title("Played Here!"));
            googleMap.animateCamera(CameraUpdateFactory.newLatLngZoom(latLng, 15), 5000, null);
        });
    };

    CallBack_List callBackList = new CallBack_List() {
        @Override
        public ArrayList<Record> getRecords() {
            return myDB.getRecords();
        }

        @Override
        public void ZoomOnMap(double lat, double lon) {
            callBack_map.mapClicked(lat, lon);
        }
    };

    private void initFragments() {
        fragmentList = new Fragment_List();
        fragmentList.setActivity(Activity_GameOver.this);
        fragmentList.setCallBackList(callBackList);
        callBackList.ZoomOnMap(latLon[0], latLon[1]);
        getSupportFragmentManager().beginTransaction().add(R.id.gameOver_frame_topTen, fragmentList).commit();

        fragmentMap = new Fragment_Map();
        fragmentMap.setActivity(Activity_GameOver.this);
        fragmentMap.setCallBack_map(callBack_map);
        getSupportFragmentManager().beginTransaction().add(R.id.gameOver_frame_map, fragmentMap).commit();
    }

    public void loadFromSP() {
        if (myDB == null)
            myDB = new MyDB();
        String js = MSP.getMe().getString("MY_DB", "");
        myDB = new Gson().fromJson(js, MyDB.class);
    }

    public void saveToSP() {
        if (myDB == null)
            myDB = new MyDB();
        pickTop10FromDB();
        String json = new Gson().toJson(myDB);
        MSP.getMe().putString("MY_DB", json);
    }

    private void pickTop10FromDB() {
        if (myDB == null)
            myDB = new MyDB();
        temp = myDB.getRecords();
        Collections.sort(temp, new RecordComparator());
        ArrayList<Record> res = new ArrayList<>();
        int counter = 0;
        while (res.size() < 10 && temp.size() > counter) {
            res.add(temp.get(counter));
            counter++;
        }
        temp = res;
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

