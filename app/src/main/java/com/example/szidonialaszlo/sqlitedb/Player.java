package com.example.szidonialaszlo.sqlitedb;

/**
 * Created by szidonia.laszlo on 2017. 11. 16..
 */

public class Player {

    private int id;
    private String name;
    private String position;
    private int height;

    public Player() {
    }


    public Player(int id, String name, String position, int height) {
        this.id = id;
        this.name = name;
        this.height = height;
        this.position = position;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getId() {
        return id;
    }

    public void setName(String name) {
        this.name=name;
        }

    public String getName(){
        return name;
    }

    public void setPosition(String position){
        this.position=position;
    }

    public String getPosition(){
        return position;
    }

    public void setHeight(int height){
        this.height=height;
    }

    public int getHeight(){
        return height;
    }


    public String toString(){
        return name+" - "+ position+" - "+height+" cm";
    }
}
