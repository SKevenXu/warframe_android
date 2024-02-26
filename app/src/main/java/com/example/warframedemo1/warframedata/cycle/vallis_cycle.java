package com.example.warframedemo1.warframedata.cycle;

import com.example.warframedemo1.HttpUtils;
import com.google.gson.Gson;

import java.io.IOException;

public class vallis_cycle {
    public vallis_data vallis_detail() throws IOException {
        HttpUtils http=new HttpUtils();
        String text=http.getData("https://api.warframestat.us/pc/vallisCycle/");
        Gson gson = new Gson();

        vallis_data vallis = gson.fromJson(text, vallis_data.class);
        return vallis;
    }
    public String get_vallis_timeleft() throws IOException {
        HttpUtils http=new HttpUtils();
        String text=http.getData("https://api.warframestat.us/pc/vallisCycle/");
        Gson gson = new Gson();

        vallis_data vallis = gson.fromJson(text, vallis_data.class);
        return vallis.timeLeft;
    }
    public boolean get_vallis_iswarm() throws IOException {
        HttpUtils http=new HttpUtils();
        String text=http.getData("https://api.warframestat.us/pc/vallisCycle/");
        Gson gson = new Gson();

        vallis_data vallis = gson.fromJson(text, vallis_data.class);
        return vallis.isWarm;
    }

}
