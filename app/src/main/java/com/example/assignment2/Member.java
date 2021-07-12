package com.example.assignment2;


import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity(tableName="Member_Table")
public class Member {


    @PrimaryKey(autoGenerate = true)
    private int id;

    private String name;
    private String agency;
    private String image;
    private String wikipedia;
    private String status;

    public Member( String name, String agency, String image, String wikipedia, String status) {
        this.name = name;
        this.agency = agency;
        this.image = image;
        this.wikipedia = wikipedia;
        this.status = status;
    }

   public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public String getAgency() {
        return agency;
    }

    public String getImage() {
        return image;
    }

    public String getWikipedia() {
        return wikipedia;
    }

    public String getStatus() {
        return status;
    }



}
