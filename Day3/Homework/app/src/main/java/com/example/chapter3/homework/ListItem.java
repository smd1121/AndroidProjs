package com.example.chapter3.homework;

public class ListItem {
    private String name;
    private int srcID;

    public ListItem(String name, int srcID) {
        this.name = name;
        this.srcID = srcID;
    }

    public String getName() {
        return name;
    }

    public int getSrcID() {
        return srcID;
    }
}
