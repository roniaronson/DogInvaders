package com.example.myapplication.objects;

import java.util.ArrayList;

public class MyDB {

    private ArrayList<Record> records = new ArrayList<>();

    public MyDB() { }

    public ArrayList<Record> getRecords() {
        return records;
    }

    public MyDB setRecords(ArrayList<Record> records) {
        this.records = records;
        return this;
    }

    public void AddRecord(Record record){
        records.add(record);
    }

    @Override
    public String toString() {
        return "MyDB{" +
                "records=" + records +
                '}';
    }
}