package com.example.todolist;



public class Task {
    private String name;
    private String time;
    private String date;
    private String priority;

    Task(){

    }
    public Task(String name, String time, String date, String priority) {
        this.name = name;
        this.time = time;
        this.date = date;

        this.priority = priority;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public String getPriority() {
        return priority;
    }

    public void setPriority(String priority) {
        this.priority = priority;
    }
}
