package com.example.warframedemo1.warframedata.cycle;

public class vallis_data {

        String id;
        String expiry;
        boolean isWarm;
        String timeLeft;

        /*
         * {
         * "id": "string",
         * "expiry": "string",
         * "timeLeft": "string",
         * "isWarm": true
         * }
         */
        public String getId() {
            return id;
        }

        public void setId(String id) {
            this.id = id;
        }

        public String getExpiry() {
            return expiry;
        }

        public void setExpiry(String expiry) {
            this.expiry = expiry;
        }

        public boolean getIsWarm() {
            return isWarm;
        }

        public void setIsWarm(boolean iswarm) {
            this.isWarm = iswarm;
        }

        public String gettimeLeft() {
            return timeLeft;
        }

        public void settimeLeft(String timeLeft) {
            this.timeLeft = timeLeft;
        }

}
