package com.example.taslock;

public class taskedPosts {
    String Title; //username for the authenticated user
    String Teacher;
    int Time; //the text of the message being sent
    int start;

    public taskedPosts(String title, String time, String post) {

    }

    public taskedPosts(String Title, String PostMsg) {
        this.Title = Title;
        this.Time = Time;
        this.Teacher = Teacher;
        this.start = start;
    }

    public String getTitle() {
        return Title;
    }

    public void setTitle(String Title) {
        this.Title = Title;
    }


    public String getTeacher() {
        return Teacher;
    }

    public void setTeacher(String Title) {
        this.Title = Teacher;
    }

    public int getPostMsg() {
        return Time;
    }

    public void setPostMsg(int PostMsg) {
        this.Time = Time;
    }

    public int getStart() { return start;}

    public void setStart(int start) { this.start = start; }
}
