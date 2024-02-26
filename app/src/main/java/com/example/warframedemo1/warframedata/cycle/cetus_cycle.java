package com.example.warframedemo1.warframedata.cycle;


import com.example.warframedemo1.HttpUtils;
import com.google.gson.Gson;

import java.io.IOException;

public class cetus_cycle {
    cetus_data cetusCycle_data;
    public cetus_data.cetusCycle_data cetusCycle_detail() throws IOException {
        HttpUtils http=new HttpUtils();
        String temp=http.getData("https://api.warframestat.us/pc/cetusCycle/");
        Gson gson=new Gson();
        cetus_data.cetusCycle_data cetus=gson.fromJson(temp, cetus_data.cetusCycle_data.class);
        return cetus;
    }
    public String get_cetus_timeleft() throws IOException {
        HttpUtils http=new HttpUtils();
        String temp=http.getData("https://api.warframestat.us/pc/cetusCycle/");
        Gson gson=new Gson();
        cetus_data.cetusCycle_data cetus=gson.fromJson(temp, cetus_data.cetusCycle_data.class);
        return cetus.timeLeft;
    }
    public boolean get_cetus_isday() throws IOException {
        HttpUtils http=new HttpUtils();
        String temp=http.getData("https://api.warframestat.us/pc/cetusCycle/");
        Gson gson=new Gson();
        cetus_data.cetusCycle_data cetus=gson.fromJson(temp, cetus_data.cetusCycle_data.class);
        return cetus.isday;
    }



}
/*
 * {
 * "id": "string",
 * "activation": "2019-08-24T14:15:22Z",
 * "expiry": "2019-08-24T14:15:22Z",
 * "startString": "string",
 * "active": true,
 * "isDay": true,
 * "state": "string",
 * "timeLeft": "string",
 * "isCetus": true,
 * "shortString": "string"
 * }
 */


