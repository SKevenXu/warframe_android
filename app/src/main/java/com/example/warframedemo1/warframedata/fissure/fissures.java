package com.example.warframedemo1.warframedata.fissure;

import com.example.warframedemo1.HttpUtils;
import com.google.gson.Gson;

import java.io.IOException;

public class fissures {




    public fissures_data[] fissuress() throws IOException {


        HttpUtils http=new HttpUtils();
        String text = http.getData("https://api.warframestat.us/pc/zh/fissures/");
        Gson gson = new Gson();
        fissures_data[] fiss = gson.fromJson(text, fissures_data[].class);

        return fiss;
    }


    public void getfissuredata() throws Exception{
            fissures_data[]  fiss = fissuress();
    }



}
class fissures_data {
    String activation;
    String expiry;
    String node;
    String missionType;
    String missionKey;
    String enemy;
    String tier;
    int tierNum;
    String eta;
    boolean isStorm;
    boolean isHard;

    public int getTierNum() {
        return tierNum;
    }

    public void setTierNum(int tierNum) {
        this.tierNum = tierNum;
    }

    public boolean getIsStorm() {
        return isStorm;
    }

    public void setIsStorm(boolean isStorm) {
        this.isStorm = isStorm;
    }

    public String getMissionType() {
        return missionType;
    }

    public void setMissionsType(String missionType) {
        this.missionType = missionType;
    }

    public String getMissionKey() {
        return missionKey;
    }

    public void setMissionsKey(String missionKey) {
        this.missionKey = missionKey;
    }

    public String getActivation() {
        return activation;
    }

    public void setActivation(String activation) {
        this.activation = activation;
    }

    public String getExpiry() {
        return expiry;
    }

    public void setExpiry(String expiry) {
        this.expiry = expiry;
    }

    public String getNode() {
        return node;
    }

    public void setNode(String node) {
        this.node = node;
    }

    public String getEnemy() {
        return enemy;
    }

    public void setEnemy(String enemy) {
        this.enemy = enemy;
    }

    public String getTier() {
        return tier;
    }

    public void setTier(String tier) {
        this.tier = tier;
    }

    public String getEta() {
        return eta;
    }

    public void setEta(String eta) {
        this.eta = eta;
    }

    public boolean getIsHard() {
        return isHard;
    }

    public void setIsHard(boolean isHard) {
        this.isHard = isHard;
    }

}
