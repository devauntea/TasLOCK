package com.example.taslock;

public class taskedPosts {
    String Title; //username for the authenticated user
    int Time; //the text of the message being sent

    public taskedPosts() {

    }

    public taskedPosts(String Title, String PostMsg) {
        this.Title = Title;
        this.Time = Time;
    }

    public String getTitle() {
        return Title;
    }

    public void setTitle(String Title) {
        this.Title = Title;
    }

    public int getPostMsg() {
        return Time;
    }

    public void setPostMsg(String PostMsg) {
        this.Time = Time;
    }
}
