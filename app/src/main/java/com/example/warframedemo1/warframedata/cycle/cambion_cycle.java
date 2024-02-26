package com.example.warframedemo1.warframedata.cycle;

import com.example.warframedemo1.HttpUtils;
import com.google.gson.Gson;

import java.io.IOException;

public class cambion_cycle {
    public cambion_data cambion_detail() throws IOException {

        HttpUtils http=new HttpUtils();
        String text=http.getData("https://api.warframestat.us/pc/cambionCycle/");

        Gson gson = new Gson();
        cambion_data cambion = gson.fromJson(text, cambion_data.class);

        return cambion;
    }
    public String get_cambion_timeleft() throws IOException {

        HttpUtils http=new HttpUtils();
        String text=http.getData("https://api.warframestat.us/pc/cambionCycle/");

        Gson gson = new Gson();
        cambion_data cambion = gson.fromJson(text, cambion_data.class);

        return cambion.timeLeft;
    }
    public String get_cambion_state() throws IOException {

        HttpUtils http=new HttpUtils();
        String text=http.getData("https://api.warframestat.us/pc/cambionCycle/");

        Gson gson = new Gson();
        cambion_data cambion = gson.fromJson(text, cambion_data.class);

        return cambion.state;
    }

}
