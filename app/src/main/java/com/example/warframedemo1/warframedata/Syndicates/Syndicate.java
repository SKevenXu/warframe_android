package com.example.warframedemo1.warframedata.Syndicates;
import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.URL;
import java.net.URLConnection;
import java.util.List;

import com.google.gson.Gson;

import program.Arbitration.getTime;
public class Syndicate {
        public Syndicate_data[] Syndicates() {
            String text = null;
            try {
                // like https://api.warframestat.us/pc/zh/vallisCycle/
                // cambionCycle是火卫二时间
                // cetusCycle是希图斯时间
                // vallisCycle是金星平原时间
                URL url = new URL("https://api.warframestat.us/pc/syndicateMissions/");
                // 2、连接服务器:打开服务器连接,得到对象conn
                URLConnection conn = url.openConnection();
                conn.setRequestProperty("User-Agent", "Mozilla/4.0 (compatible; MSIE 5.0; Windows NT; DigExt)");
                // 3、加载数据:获取加载数据的字节输入流
                InputStream is = conn.getInputStream();
                // 4、将is装饰为能一次读取一行的字符输入流br
                // BufferedReader br = new BufferedReader(new InputStreamReader(is));
                BufferedReader br = new BufferedReader(new InputStreamReader(is, "UTF-8"));
                // 5、加载一行数据ca
                text = br.readLine();

            } catch (Exception e) {
                System.out.println("false");
            }
            Gson gson = new Gson();

            Syndicate_data[] synd = gson.fromJson(text, Syndicate_data[].class);
            // cetus[0-1]={地球,火卫二,金星}
            // jobs[]
            // 赏金6是钢铁之路
            // 赏金7是和一众 记得改

            return synd;
        }
        public Syndicate_data[] syndicate_data=Syndicates();
        public String[] Syndicate_list_cetus(){
            String[] Syndicate_cetus=new String[syndicate_data[0].jobs.size()];
            for(int i=0;i<syndicate_data[0].jobs.size();i++){
                Syndicate_cetus[i]=syndicate_data[0].jobs.get(i).type;
            }
            return  Syndicate_cetus;
        }
        public String[] Syndicate_list_vaills(){
            String[] Syndicate_vallis=new String[syndicate_data[3].jobs.size()];
            for(int i=0;i<syndicate_data[3].jobs.size();i++){
                Syndicate_vallis[i]=syndicate_data[3].jobs.get(i).type;
            }
            return  Syndicate_vallis;
        }
        public String[] Syndicate_list_cambion(){
            String[] Syndicate_cambion=new String[syndicate_data[2].jobs.size()];
            for(int i=0;i<syndicate_data[2].jobs.size();i++){
                Syndicate_cambion[i]=syndicate_data[2].jobs.get(i).type;
            }
            return  Syndicate_cambion;
        }
        public long atleasttime(int num) {
            getTime gt = new getTime();
            return gt.gettime(syndicate_data[num].expiry, syndicate_data[num].activation);
        }

        public String activationTime(int num) {
            return syndicate_data[num].activation;
        }

        public String expiryTime(int num) {
            return syndicate_data[num].expiry;
        }

        public String StartTime(int num) {
            return syndicate_data[num].startString;
        }




    }

    class Syndicate_data {
        // List<nodes> nodes;
        String eta;
        List<jobs> jobs;
        String syndicate;
        String id;
        String expiry;
        String activation;
        String startString;

        public String getStatrString() {
            return startString;
        }

        public void setStartString(String startString) {
            this.startString = startString;
        }

        public List<jobs> getjobs() {
            return jobs;
        }

        public void setjobs(List<jobs> jobs) {
            this.jobs = jobs;
        }

        /*
         * public List<nodes> getNodes() {
         * return nodes;
         * }
         *
         * public void setNodes(List<nodes> nodes) {
         * this.nodes = nodes;
         * }
         */

        public String getEta() {
            return eta;
        }

        public void setEta(String eta) {
            this.eta = eta;
        }

        public String getSyndicate() {
            return syndicate;
        }

        public void setSyndicate(String syndicate) {
            this.syndicate = syndicate;
        }

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

        public void setActivation(String activation) {
            this.activation = activation;
        }
    }

    class jobs {
        String activation;
        String expiry;
        String type;

        int minMR;

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

        public String getType() {
            return type;
        }

        public void setType(String type) {
            this.type = type;
        }

        public int getMinMR() {
            return minMR;
        }

        public void setMinMR(int minMR) {
            this.minMR = minMR;
        }

    }

    class standingStages {
        int temp;

        public int getTemp() {
            return temp;
        }

        public void setTemp(int temp) {
            this.temp = temp;
        }
    }

    class enemyLevels {
        int number;

        public int getNumber() {
            return number;
        }

        public void setNumber(int number) {
            this.number = number;
        }
    }

    class nodes {
        String nodes;

        public String getNodes() {
            return nodes;
        }

        public void setNodes(String nodes) {
            this.nodes = nodes;
        }
    }

/**
 * translate
 */


