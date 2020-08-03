package com.example.taslock;

public class Surveyposts {
    int startTime;
    String endTime;

    public Surveyposts(){

    }


    public Surveyposts(int startTime, String endTime) {
        this.startTime = startTime;
        this.endTime = endTime;

    }

    public int getStartTime() {
        return startTime;
    }

    public void setStartTime(int startTime) {
        this.startTime = startTime;
    }



}