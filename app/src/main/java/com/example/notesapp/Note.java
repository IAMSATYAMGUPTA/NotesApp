package com.example.notesapp;

import io.realm.RealmObject;

public class Note extends RealmObject {

    String title;
    String description;
    Long Createdtime;

//    public Note(){}
//    public Note(String title, String description, Long createdtime) {
//        this.title = title;
//        this.description = description;
//        Createdtime = createdtime;
//    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Long getCreatedtime() {
        return Createdtime;
    }

    public void setCreatedtime(Long createdtime) {
        Createdtime = createdtime;
    }
}
