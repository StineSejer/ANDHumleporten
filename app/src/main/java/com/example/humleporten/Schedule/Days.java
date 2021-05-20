package com.example.humleporten.Schedule;

public class Days {

    private String title;
    private String humleporten;

    public Days(String title, String humleporten){
        this.title =title;
        this.humleporten = humleporten;
    }
    public Days(){

    }

    public String getTitle(){return title;}
    public void setTitle(String title){this.title = title;}
    public String getHumleporten(){return humleporten;}
    public void setHumleporten(String humleporten){this.humleporten = humleporten;}
}
