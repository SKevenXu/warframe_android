package com.example.warframedemo1.warframedata.cycle;


public class cetus_data {
    class cetusCycle_data {


        String id;
        String activation;
        String expiry;
        String startString;
        boolean active;
        boolean isday;
        String state;
        String timeLeft;
        boolean isCetus;
        String shortString;

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
        public String getId() {
            return id;
        }

        public void setId(String id) {
            this.id = id;
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

        public String getstartString() {
            return startString;
        }

        public void setstartString(String startString) {
            this.startString = startString;
        }

        public boolean getactive() {
            return active;
        }

        public void setactive(boolean active) {
            this.active = active;
        }

        public boolean getIsday() {
            return isday;
        }

        public void setIsday(boolean isday) {
            this.isday = isday;
        }

        public String getState() {
            return state;
        }

        public void setState(String state) {
            this.state = state;
        }

        public boolean getIscetus() {
            return isCetus;
        }

        public void setIscetus(boolean isCetus) {
            this.isCetus = isCetus;
        }

        public String gettimeLeft() {
            return timeLeft;
        }

        public void settimeLeft(String timeLeft) {
            this.timeLeft = timeLeft;
        }

        public String getShortString() {
            return shortString;
        }

        public void setShortString(String shortString) {
            this.shortString = shortString;
        }
    }
}
