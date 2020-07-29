package com.example.taslock;

public class taskedPosts {
    String Title, Time, Teacher; //username for the authenticated user
   //the text of the message being sent
    //int start;

   public taskedPosts() {

   }

    public taskedPosts(String Title, String Time, String Teacher) {
        this.Title = Title;
        this.Time = Time;
        this.Teacher = Teacher;
        //this.start = start;
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
    public void setTeacher(String Teacher) {
        this.Teacher = Teacher;
    }

    public String getTime(){ return Time; }
    public void setTime(String Time) { this.Time = Time; }

    //public int getStart() { return start;}

    //public void setStart(int start) { this.start = start; }
}
