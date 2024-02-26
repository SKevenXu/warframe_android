package com.example.warframedemo1.warframedata.fissure;

public class fissures_tools {
    public String fissures_tire(int tirenum){
        String tire=null;
        switch (tirenum){
            case 1:tire="古纪";
                break;
            case 2:tire="前纪";
                break;
            case 3:tire="中纪";
                break;
            case 4:tire="后纪";
                break;
            case 5:tire="安魂";
                break;
        }
        return tire;
    }

}
