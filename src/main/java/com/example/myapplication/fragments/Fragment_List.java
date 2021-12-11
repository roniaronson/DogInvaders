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

        records = callBackList.getRecords();
        String arr[] = new String[records.size()];
        for (int i = 0; i < records.size(); i++) {
            arr[i] = records.get(i).toString();
            Log.d("jjjjj", "onCreateView: "+arr[i].toString());
        }

        ArrayAdapter<String> listViewAdapter = new ArrayAdapter<String>(
                getActivity(),
                android.R.layout.simple_list_item_1,
                arr);

//        ListView simpleList = view.findViewById(R.id.panel_list_top10);
//        String countryList[] = {"India", "China", "australia", "Portugle", "America", "NewZealand"};
//
//
//        ArrayAdapter<String> arrayAdapter = new ArrayAdapter<String>(getActivity(),  android.R.layout.simple_list_item_1, countryList);
//        simpleList.setAdapter(arrayAdapter);




        top10List.setAdapter(listViewAdapter);
//        top10List.setAdapter(arrayAdapter);
//        top10List.setOnItemClickListener((parent, view1, position, id) ->
//        {
//            Record r = (Record) parent.getItemAtPosition(position);
//            //callBackList.ZoomOnMap(r.getLat(), r.getLon());
//            Log.d("psss", "onCreateView: "+"hi");
//        });

        return view;
    }

    private void findViews(View view) {
        top10List = (ListView) view.findViewById(R.id.panel_list_top10);
    }
}