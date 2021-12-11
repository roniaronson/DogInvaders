package com.example.myapplication.fragments;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;

import com.example.myapplication.R;
import com.example.myapplication.call_backs.CallBack_List;
import com.example.myapplication.objects.Record;

import java.util.ArrayList;

public class Fragment_List extends Fragment {

    private AppCompatActivity activity;

    private CallBack_List callBackList;

    private ListView top10List;

    private ArrayList<Record> records ;

    private String arr[];

    public void setActivity(AppCompatActivity activity) {
        this.activity = activity;
    }

    public void setCallBackList(CallBack_List callBackList) {
        this.callBackList = callBackList;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_list, container, false);
        top10List = (ListView) view.findViewById(R.id.panel_list_top10);

//        records = callBackList.getRecords();
//        arr = new String[records.size()];
//        for (int i = 0; i < records.size(); i++) {
//            arr[i] = records.get(i).toString();
//        }
//
//        ArrayAdapter<String> listViewAdapter = new ArrayAdapter<String>(
//                getActivity(),
//                android.R.layout.simple_list_item_1,
//                arr);
//
//        top10List.setAdapter(listViewAdapter);
//
//        top10List.setOnItemClickListener((parent, view1, position, id) ->
//        {
//            Record r = (Record) (parent.getItemAtPosition(position));
//            callBackList.ZoomOnMap(r.getLat(), r.getLon());
//        });

        records = callBackList.getRecords();
        findViews(view);

        ArrayAdapter<Record> listViewAdapter = new ArrayAdapter<>(
                getActivity(),
                android.R.layout.simple_list_item_1,
                records);

        top10List.setAdapter(listViewAdapter);

        top10List.setOnItemClickListener((parent, view1, position, id) ->
        {
            Record r = (Record) parent.getItemAtPosition(position);
            callBackList.ZoomOnMap(r.getLat(), r.getLon());
        });

        return view;
    }



    private void findViews(View view) {
        top10List = (ListView) view.findViewById(R.id.panel_list_top10);
    }
}