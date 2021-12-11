package com.example.myapplication.objects;

public class Record {

    private String time = "";
    private int score = 0;
    private double lat = 0.0;
    private double lon = 0.0;
    private String name  = "Anonymous";

    public Record() { }

    public String getTime() {
        return time;
    }

    public Record setTime(String time) {
        this.time = time;
        return this;
    }

    public int getScore() {
        return score;
    }

    public Record setScore(int score) {
        this.score = score;
        return this;
    }

    public double getLat() {
        return lat;
    }

    public Record setLat(double lat) {
        this.lat = lat;
        return this;
    }

    public double getLon() {
        return lon;
    }

    public Record setLon(double lon) {
        this.lon = lon;
        return this;
    }

    public Record setName(String name){
        this.name = name;
        return this;
    }

    public String getName(){ return name; }

    @Override
    public String toString() {
        return ("" + name + " "+ score+ " "+ time);
    }
}
