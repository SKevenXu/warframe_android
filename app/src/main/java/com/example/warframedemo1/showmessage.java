package com.example.warframedemo1;



import static android.content.Context.NOTIFICATION_SERVICE;

import static com.google.android.material.internal.ContextUtils.getActivity;

import android.app.AlertDialog;
import android.app.Dialog;
import android.app.Notification;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.content.Context;
import android.content.DialogInterface;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.drawable.Icon;
import android.util.Log;
import android.view.Window;
import android.view.WindowManager;
import android.widget.EditText;
import android.widget.ImageView;

import androidx.core.app.NotificationCompat;

import com.example.warframedemo1.tests.testset;

import java.util.Random;


public class showmessage {
    public void showupdatemessage(Context context,String texttittle){
        readfile read=new readfile();
        AlertDialog.Builder builder=new AlertDialog.Builder(context);
        builder.setTitle(texttittle);
        builder.setMessage(read.getFromAssets("update.txt",context));
        builder.setPositiveButton("朕已阅", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                Log.d("msg","onclick");
                dialogInterface.dismiss();

            }
        });
        AlertDialog dialog=builder.create();
        dialog.show();
    }


    public void showmessage(Context context,String messagetitle,String messagetext){
        NotificationManager notificationManager = (NotificationManager)context.getSystemService(NOTIFICATION_SERVICE);
//        Notification.BigPictureStyle bigPictureStyle = new Notification.BigPictureStyle()
//                .setBigContentTitle(messagetitle)
//
//                .setSummaryText(messagetext);


        String channelId = "warframedemo";
        Notification notification = new Notification.Builder(context,channelId)
                .setContentTitle(messagetitle)
                .setContentText(messagetext)
                .setWhen(System.currentTimeMillis())
                .setSmallIcon(R.mipmap.ic_launcher)
                .setDefaults(Notification.DEFAULT_SOUND|Notification.DEFAULT_VIBRATE)
                .setStyle(new Notification.BigTextStyle().bigText(messagetext))
                .setVibrate(new long[]{0, 1000, 1000, 1000})
                .build();





        NotificationChannel channel = new NotificationChannel(channelId,"测试渠道名称", NotificationManager.IMPORTANCE_DEFAULT);
        notificationManager.createNotificationChannel(channel);

        notificationManager.notify(1123, notification);


    }
    public void Dialogtestmessage(Context context,String messagetittle ){
        testset set=new testset();
        Random random = new Random();
        int index = random.nextInt(3);

        final EditText editText=new EditText(context.getApplicationContext());

        AlertDialog.Builder builder= new AlertDialog.Builder(context);//MainActivity.this为当前环境

        builder.setIcon(R.mipmap.ic_launcher);//提示图标

        builder.setTitle(messagetittle);//提示框标题
        builder.setView(editText);

        builder.setMessage(set.getTestset(index));//提示内容

        builder.setPositiveButton("确认", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                int temp=Integer.parseInt(String.valueOf(editText.getText()));
                if(temp==set.getanswer(index)){//这里要改
                    //TODO
                    showDialog(context);
                }else{
//                    showimage(context,"666","image");
                    unpass(context);
                }
            }
        });//确定按钮

        builder.create().show();


    }
    /*
     * 弹出图片
     */
    private void showDialog(Context context){
        int[] idimage={
                R.drawable.p1,
                R.drawable.p2,
                R.drawable.p3,
                R.drawable.p4,
                R.drawable.p6
        };
        Random random = new Random();
        int index = random.nextInt(idimage.length);
        Dialog dia = new Dialog(context);
        dia.setContentView(R.layout.dialog);

        ImageView imageView = (ImageView) dia.findViewById(R.id.ivdialog);
        //可以set任何格式图片
        imageView.setImageBitmap(BitmapFactory.decodeResource(context.getResources(),idimage[index]));
        dia.show();
        //选择true的话点击其他地方可以使dialog消失，为false的话不会消失
        dia.setCanceledOnTouchOutside(true); // Sets whether this dialog is
        Window w = dia.getWindow();
        WindowManager.LayoutParams lp = w.getAttributes();
        lp.x = 0;
        lp.y = 40;
        dia.onWindowAttributesChanged(lp);
    }
    public void unpass(Context context){

        testset set=new testset();
        AlertDialog.Builder builder= new AlertDialog.Builder(context);//MainActivity.this为当前环境

        builder.setIcon(R.mipmap.ic_launcher);//提示图标

        builder.setTitle("错了!");//提示框标题
        builder.setCancelable(true);

        builder.setMessage("菜就多练!");//提示内容



        builder.create().show();


    }


}