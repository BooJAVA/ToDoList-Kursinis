package com.example.bnd.ds;

import java.io.Serializable;

public class Task implements Serializable {

    private int id;
    private String title;
    private String done;

    public Task(String title) {
        this.title = title;
    }

    public int getId() {
        return id;
    }

    public String getTitle() {
        return title;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getDone() {
        return done;
    }

    public void setDone(String completed) {
        this.done = completed;
    }

    @Override
    public String toString() {
        return "Task{" +
                "Id=" + id +
                ", Title=" + title +
                ", Completed='" + done + '\'' +
                '}';
    }

}