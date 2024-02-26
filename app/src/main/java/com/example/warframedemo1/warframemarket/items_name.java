package com.example.warframedemo1.warframemarket;



import android.content.Context;

import com.example.warframedemo1.HttpUtils;
import com.google.gson.Gson;
import com.example.warframedemo1.MainActivity;


import java.io.IOException;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import program.Random.Main;

public class items_name {
    HttpUtils http = new HttpUtils();

    public static String unicodeDecode(String string) {
        Pattern pattern = Pattern.compile("(\\\\u(\\p{XDigit}{4}))");
        Matcher matcher = pattern.matcher(string);
        char ch;
        while (matcher.find()) {
            ch = (char) Integer.parseInt(matcher.group(2), 16);
            string = string.replace(matcher.group(1), ch + "");
        }
        return string;
    }


    public payload items_detail() {


        String text = null;
        try {
            //text= http.getFromAssets("weapon_url_name.json");

            //text = http.getData_zh("https://api.warframe.market/v1/riven/items");
            System.out.println(text);
        } catch (Exception e) {
            System.err.println(e);
        }
        Gson gson = new Gson();


        payload pay=gson.fromJson(text,payload.class);

        return pay;
    }

    payload pay = items_detail();

    public String[] getitemurl() {

        String[] geturlname = new String[pay.items.size()];
        for (int i = 0; i < geturlname.length; i++) {
            geturlname[i] = pay.items.get(i).url_name;
        }
        return geturlname;
    }

    public ArrayList getitemname() {
        ArrayList allitemName=new ArrayList();

        for(int i=0;i<pay.items.size();i++){
            allitemName.add(pay.items.get(i).item_name);
        }

        return allitemName;
    }

//    public String getoneurlname(String name) {
//
//        String url = null;
//        for (int i = 0; i < getitemname().length; i++) {
//            if (getitemname()[i].hashCode() == name.hashCode()) {
//                url = getitemurl()[i];
//            }
//        }
//        return url;
//    }

//    public String getonename(String url) {
//        String name = null;
//        for (int i = 0; i < getitemurl().length; i++) {
//            if (getitemurl()[i].hashCode() == url.hashCode()) {
//                name = getitemname()[i];
//            }
//        }
//        return name;
//    }
//    class weaponitems{
//        JsonObject payload;
//
//        public JsonObject getPayload() {
//            return payload;
//        }
//
//        public void setPayload(JsonObject payload) {
//            this.payload = payload;
//        }
//    }


    public class payload {
        List<items> items;

        public List<items> getItems() {
            return items;
        }

        public void setItems(List<items> items) {
            this.items = items;
        }
    }

    public class items {
        String item_name;
        String url_name;

        public String getItem_name() {
            return item_name;
        }

        public void setItem_name(String item_name) {
            this.item_name = item_name;
        }

        public String getUrl_name() {
            return url_name;
        }

        public void setUrl_name(String url_name) {
            this.url_name = url_name;
        }
    }
}
