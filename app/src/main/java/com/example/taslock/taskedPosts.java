package com.example.taslock;

import android.widget.LinearLayout;

import javax.xml.datatype.Duration;

public class taskedPosts {
    String Title;
    String Time;
    String EndTime;
    String Subject;

   public taskedPosts() {

   }

    public taskedPosts(String Title, String Time, String EndTime, String Subject) {
        this.Title = Title;
        this.Time = Time;
        this.EndTime = EndTime;
        this.Subject = Subject;

    }

    public String getTitle() {
        return Title;
    }
    public void setTitle(String Title) {
        this.Title = Title;
    }


    public String getEndTime() {
        return EndTime;
    }
    public void setEndTime(String EndTime) {
        this.EndTime = EndTime;
    }

    public String getTime(){ return Time; }
    public void setTime(String Time) { this.Time = Time; }

    public String getSubject() { return Subject;}
    public void setSubject(String Subject) { this.Subject = Subject; }


}
