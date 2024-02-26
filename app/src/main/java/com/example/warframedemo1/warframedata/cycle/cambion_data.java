package com.example.warframedemo1.warframedata.cycle;

public class cambion_data {

        String id;
        String expiry;
        String activation;
        String state;
        String active;
        String timeLeft;

        /*
         * {
         * "id": "string",
         * "expiry": "string",
         * "activation": "string",
         * "state": "vome",
         * "active": "vome",
         * "timeLeft": "string"
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

        public String getActivation() {
            return activation;
        }

        public void setActivation(String activtion) {
            this.activation = activtion;
        }

        public String getState() {
            return state;
        }

        public void setState(String state) {
            this.state = state;
        }

        public String getActive() {
            return active;
        }

        public void setActive(String active) {
            this.active = active;
        }

        public String gettimeLeft() {
            return timeLeft;
        }

        public void settimeLeft(String timeLeft) {
            this.timeLeft = timeLeft;
        }

}
