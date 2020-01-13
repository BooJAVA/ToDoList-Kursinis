package com.example.bnd.ds;

import java.io.Serializable;

public class Project implements Serializable {

    private int id;
    private String title;

    public Project(String title) {
        this.title = title;
    }

    public int getId() {
        return id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public void setId(int id) {
        this.id = id;
    }

    @Override
    public String toString() {
        return "Project{" +
                "Id=" + id +
                ", Title='" + title + '\'' +
                '}';
    }

}
