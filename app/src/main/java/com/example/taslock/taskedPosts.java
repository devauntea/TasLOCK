package com.example.taslock;

public class taskedPosts {
    String Title;
    String Time;
    String Teacher;
    String Subject;

   public taskedPosts() {

   }

    public taskedPosts(String Title, String Time, String Teacher, String Subject) {
        this.Title = Title;
        this.Time = Time;
        this.Teacher = Teacher;
        this.Subject = Subject;
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

    public String getSubject() { return Subject;}
    public void setSubject(String Subject) { this.Subject = Subject; }
}
