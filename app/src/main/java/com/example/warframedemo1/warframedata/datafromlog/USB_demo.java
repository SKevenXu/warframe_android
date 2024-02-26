package com.example.warframedemo1.warframedata.datafromlog;

import android.Manifest;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.hardware.usb.UsbDevice;
import android.hardware.usb.UsbManager;
import android.util.Log;
import android.widget.EditText;
import android.widget.TextView;

import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

import com.example.warframedemo1.R;
import com.example.warframedemo1.showProgressBar;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.ServerSocket;
import java.net.Socket;
import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;
import java.util.HashMap;
import java.util.Scanner;

import jcifs.UniAddress;
import jcifs.smb.NtlmPasswordAuthentication;
import jcifs.smb.SmbFile;

public class USB_demo {
    private static final Intent ACTION_USB_PERMISSION = new Intent() ;
    public String msg = "Log data:";
    private UsbManager usbManager;
    private HashMap<String, UsbDevice> devicelist;

    public String getUsbdevice(Context context, Activity activity) {
        String temp=null;
        Log.d(msg,context.toString()+"\n"+activity.toString());
        usbManager = (UsbManager) activity.getSystemService(context.USB_SERVICE);
        Log.d(msg, String.valueOf(usbManager.getDeviceList().size()));
        devicelist = usbManager.getDeviceList();
        Log.d(msg, String.valueOf(devicelist.size()));
        for (int i=0;i<devicelist.size();i++) {
            UsbDevice device = usbManager.getDeviceList().get(i);
            if (device.getDeviceName()!=null) {
                try {
                    FileInputStream fis = new FileInputStream("/mnt/sdcard/" + device.getDeviceName() + "/Users/19906/AppData/Local/Warframe/EE.log");
                    Log.d(msg, device.getDeviceName().toString());
                    temp=device.getDeviceName();
                } catch (Exception e) {
                    Log.d(msg, "FILE NOT FOUND!" + e);

                }
            }else{
                Log.d(msg, "FILE NOT FOUND! from- else" );
            }
        }
        return temp;
    }
    public void adbdemo(Context context,Activity activity) throws IOException {

        if (ContextCompat.checkSelfPermission(context, Manifest.permission.WRITE_EXTERNAL_STORAGE) != PackageManager.PERMISSION_GRANTED) {
            ActivityCompat.requestPermissions(activity, new String[]{Manifest.permission.WRITE_EXTERNAL_STORAGE}, 0);
        }
        UsbManager manager = (UsbManager)activity.getSystemService(Context.USB_SERVICE);
        HashMap<String, UsbDevice> usbList = manager.getDeviceList();

        if(usbList!=null){
            Log.d(msg,"true");
            Log.d(msg, String.valueOf(usbList.isEmpty()));
            Log.d(msg, String.valueOf(usbList.size()));

            for(int i=0;i<usbList.size();i++){
               Log.d(msg,usbList.get(i).getDeviceName()) ;
            }
        }else{
            Log.d(msg,"false");
        }
       

    }
    public void jcifsgetdata(){
        System.setProperty("jcifs.smb.client.dfs.disabled","true");
        System.setProperty("jcifs.smb.client.dfs.soTimeout","8000");
        System.setProperty("jcifs.smb.client.dfs.responseTimeout","3000");
        String ip="192.168.0.1";
        String username="Administrator";
        String password="162534";
        UniAddress mDomain=null;
        try {
            mDomain=UniAddress.getByName(ip);
            NtlmPasswordAuthentication mAuthentication=new NtlmPasswordAuthentication(null,username,password);
            SmbFile smbFile=new SmbFile("smb://"+mDomain.getHostAddress(),mAuthentication);
            SmbFile[] files=smbFile.listFiles();
            System.out.println(files[0]);
        }catch (Exception e){
            Log.e(msg, String.valueOf(e));
        }
    }
    public void getdatafromjcifs() throws IOException {
        System.setProperty("jcifs.smb.client.dfs.disabled", "true");
        System.setProperty("jcifs.smb.client.dfs.shares", "true");
        System.setProperty("jcifs.smb.client.dfs.shares.known", "true");

        // 假设的共享文件夹URL
        String sharedFolderPath = "smb://192.168.1.100/Warframe";//Warframe (file://DESKTOP-KHFUN1H/Users/19906/AppData/Local/Warframe)
        // 用户名和密码，如果不需要身份验证，可以设置为null
        String username = "19906";
        String password = "162534";
        String domain="desktop-khfun1h";
        // 如果是域用户，还需要提供域名
//        String domain = "yourDomain";

        // 创建NtlmPasswordAuthentication对象
        NtlmPasswordAuthentication auth = new NtlmPasswordAuthentication(null,null, null);

        // 创建SmbFile对象
        SmbFile sharedFile = new SmbFile(sharedFolderPath+"/EE.log", auth);
        String filePath = "warframe/EE.log";

        Log.d(msg, String.valueOf(sharedFile.canRead()));
        // 获取文件的输入流
//        InputStream inputStream = sharedFile.getInputStream();
//
//        // 使用BufferedReader逐行读取文件内容
//        try (BufferedReader reader = new BufferedReader(new InputStreamReader(inputStream))) {
//            String line;
//            while ((line = reader.readLine()) != null) {
//                System.out.println(line);
//            }
//        } catch (Exception e) {
//            e.printStackTrace();
//        }
    }
    public void cmddemo(Activity activity) throws IOException, InterruptedException {
        if (ContextCompat.checkSelfPermission(activity, Manifest.permission.INTERNET)
                != PackageManager.PERMISSION_GRANTED) {
            // Permission is not granted
            ActivityCompat.requestPermissions(activity,
                    new String[]{Manifest.permission.INTERNET},
                    255);
        }
        String cmd= "ping -c 192.168.1.100";
        String[] commend=new String[]{cmd};

        Process process=Runtime.getRuntime().exec(commend);
        process.waitFor();
//        DataOutputStream dos = new DataOutputStream(process.getOutputStream());
//        dos.writeBytes(cmd + "\n"); // 2、向进程内写入shell指令，cmd为要执行的shell命令字符串
//        dos.flush();
        BufferedReader reader=new BufferedReader(new InputStreamReader(process.getInputStream()));
        String line;
        while ((line=reader.readLine())!=null){
            Log.d(msg,line);
        }


    }


    public String client(String temp,Activity activity,int id)  throws Exception{

        String TAG = "client say:";
        EditText edit=activity.findViewById(id);
        // 服务端监听 9528 端口
        ServerSocket serverSocket = null;

            serverSocket = new ServerSocket(9528);

        System.out.println("等待连接");

        Socket client = serverSocket.accept();
        activity.runOnUiThread(()->{
            edit.setText("连接成功!\n");
            System.out.println("连接成功！");

        });

        String newtemp=null;
        while (true) {
            // 获取客户端输入流
            InputStream inputStream = client.getInputStream();
            byte[] bytes = new byte[1024];
            if (bytes.length>0) {
                int read = inputStream.read(bytes);
                // 客户端发来的消息
                System.out.println("客户端：" + new String(bytes, 0, read, Charset.defaultCharset()));

                    if (new String(bytes, 0, read, Charset.defaultCharset())!=null){
                        //System.out.println("客户端：" + new String(bytes, 0, read, Charset.defaultCharset()));
                      newtemp=new String (bytes, 0, read, Charset.defaultCharset());
                      System.out.println(newtemp);
                    }


            }

            String finalNewtemp = newtemp;

            activity.runOnUiThread(()->{
                //TODO
                edit.append(finalNewtemp+"\n");
            });


            // 给客户端发端东西

            String nextLine = temp;
            if ("out".equals(nextLine)) {
                break;
            }
            client.getOutputStream().write(nextLine.getBytes(StandardCharsets.UTF_8));

        }

        return newtemp;

    }


}



