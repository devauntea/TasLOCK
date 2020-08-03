package com.example.taslock;

public class Surveyposts {
    String startTime;
    String endTime;


    public Surveyposts(String startTime, String endTime) {
        this.startTime = startTime;
        this.endTime = endTime;

    }

    public String getStartTime() {
        return startTime;
    }

    public void setStartTime(String startTime) {
        this.startTime = startTime;
    }


}