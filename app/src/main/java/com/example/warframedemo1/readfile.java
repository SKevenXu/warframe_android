package com.example.warframedemo1;



import android.content.Context;
import android.content.SharedPreferences;
import android.util.Log;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;


public class readfile {
    public String update(Context context,String filepath) {
        File file = new File(context.getFilesDir(), filepath);
        StringBuilder fileContent = new StringBuilder();
// 检查文件是否存在
        if (file.exists()) {
            try (BufferedReader reader = new BufferedReader(new FileReader(file))) {
                String line;
                while ((line = reader.readLine()) != null) {
                    fileContent.append(line);
                    // 如果不是最后一行，添加换行符
                    if (reader.ready()) {
                        fileContent.append("\n");
                    }

                }
            } catch (Exception e) {
                Log.e("file error",e.toString());
                throw new RuntimeException(e);
            }

        }
        return fileContent.toString();
    }
    public  void write(Context context,String text,String filename) {
        File directory = context.getFilesDir();

// 创建一个指向新文件的File对象
        File file = new File(directory, filename);

// 确保文件存在
        if (!file.exists()) {
            try {
                boolean newFile = file.createNewFile();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

// 打开文件输出流
        try (FileOutputStream outputStream = new FileOutputStream(file)) {
            // 写入数据
            String data = text;
            outputStream.write(data.getBytes());
            Log.d("msgggggggg",data);
        } catch (IOException e) {
            e.printStackTrace();
        }

    }
    public String getFromAssets(String fileName, Context context){
        String Result="";
        try {
            InputStreamReader inputReader = new InputStreamReader( context.getResources().getAssets().open(fileName) );
            BufferedReader bufReader = new BufferedReader(inputReader);
            String line="";

            while((line = bufReader.readLine()) != null)
                Result += line;
        } catch (Exception e) {
            e.printStackTrace();
        }
            return Result;

    }
    public void sharepreference(Context context){



    }
    public boolean getupdate(Context context,int version){
        SharedPreferences sharedPreferences=context.getSharedPreferences("showupdate",Context.MODE_PRIVATE);
        SharedPreferences.Editor editor=sharedPreferences.edit();
        editor.putInt("numtoupdates",version);
//        editor.putBoolean("ifupdate",true);
//        editor.apply();
        Log.d("share","sharedPreferences is done");
        boolean temp=false;
        int num=context.getSharedPreferences("showupdate",Context.MODE_PRIVATE).getInt("numtoupdates",version);
        boolean ifshow=context.getSharedPreferences("showupdate",Context.MODE_PRIVATE).getBoolean("ifupdate",true);
        Log.d("share","getupdate is done\ttemp="+ifshow);
        if (num==2){
            Log.d("version","true\tnow version="+version);
            editor.putInt("numtoupdates",version+1);
            Log.d("version","true");
            if(ifshow==true){
                temp=true;
                editor.putBoolean("ifupdate",false)
                        .apply();
//            context.getSharedPreferences("showupdate",Context.MODE_PRIVATE).edit()
//                    .putBoolean("ifupdate",false)

                Log.d("share","getupdate is done\ttemp="+temp);
            }else {
                temp=false;
                Log.d("share","getupdate is false\ttemp="+temp);
            }
        }

        return temp;

    }




}


