package com.example.warframedemo1.ui.ducats;

public class Ducats {
    String name;
    int volume;
    float wa_price;
    float median;
    int ducats;
    float ducats_per_platinum_wa;
    String thumb;

    public float getDucats_per_platinum_wa() {
        return ducats_per_platinum_wa;
    }

    public float getMedian() {
        return median;
    }

    public float getWa_price() {
        return wa_price;
    }

    public int getDucats() {
        return ducats;
    }

    public int getVolume() {
        return volume;
    }

    public String getName() {
        return name;
    }

    public String getThumb() {
        return thumb;
    }

    public Ducats(String name,int volume,float wa_price, float median,int ducats,float ducats_per_platinum_wa,String thumb){
        this.ducats_per_platinum_wa=ducats_per_platinum_wa;
        this.ducats=ducats;
        this.median=median;
        this.name=name;
        this.volume=volume;
        this.wa_price=wa_price;
        this.thumb=thumb;
    }


}
