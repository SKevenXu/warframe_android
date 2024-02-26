package com.example.warframedemo1;

import android.content.Context;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.URL;
import java.net.URLConnection;
import com.example.warframedemo1.MainActivity;

public class HttpUtils {
    public String getData(String URL) throws IOException {
        URL url = new URL(URL);
        // 2、连接服务器:打开服务器连接,得到对象conn
        URLConnection conn = url.openConnection();
        conn.setRequestProperty("User-Agent", "Mozilla/4.0 (compatible; MSIE 5.0; Windows NT; DigExt)");
        // 3、加载数据:获取加载数据的字节输入流
        InputStream is = conn.getInputStream();
        // 4、将is装饰为能一次读取一行的字符输入流br
        // BufferedReader br = new BufferedReader(new InputStreamReader(is));
        BufferedReader br = new BufferedReader(new InputStreamReader(is, "UTF-8"));
        // 5、加载一行数据ca
        String text = br.readLine();
        return text;
    }
    public String getData_zh(String URL) throws IOException {
        URL url = new URL(URL);
        // 2、连接服务器:打开服务器连接,得到对象conn
        URLConnection conn = url.openConnection();
        conn.setRequestProperty("Language", "zh-hans");
        conn.setRequestProperty("User-Agent", "Mozilla/4.0 (compatible; MSIE 5.0; Windows NT; DigExt)");
        conn.connect();
        // 3、加载数据:获取加载数据的字节输入流
        InputStream is = conn.getInputStream();
        // 4、将is装饰为能一次读取一行的字符输入流br
        // BufferedReader br = new BufferedReader(new InputStreamReader(is));
        BufferedReader br = new BufferedReader(new InputStreamReader(is, "UTF-8"));
        // 5、加载一行数据ca
        String text = br.readLine();
        return text;
    }
    public String getFromAssets(String fileName, Context context) throws Exception {


        InputStreamReader inputReader = new InputStreamReader(context.getAssets().open(fileName));
        BufferedReader bufReader = new BufferedReader(inputReader);
        String line = "";
        String Result = "";
        while ((line = bufReader.readLine()) != null)
            Result += line;
        return Result;
    }

}

