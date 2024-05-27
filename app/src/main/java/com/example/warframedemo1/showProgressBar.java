package com.example.warframedemo1;
import android.app.AlertDialog;
import android.app.ProgressDialog;
import android.content.Context;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.squareup.picasso.Picasso;

public class showProgressBar {



        private static ProgressDialog progressDialog;//ProgressDialog这个对象你从上面的导入也可以看到，这是Android库自带的

        public  void showProgressDialog(Context context, String message) {
            progressDialog = new ProgressDialog(context);
            progressDialog.setMessage(message);
            progressDialog.setCancelable(true);

            progressDialog.show();
        }

        public  void hideProgressDialog() {
            if (progressDialog != null && progressDialog.isShowing()) {
                progressDialog.dismiss();
            }
        }

    public  AlertDialog showimageDialog(Context context) {
            AlertDialog.Builder builder=new AlertDialog.Builder(context);
            LayoutInflater inflater=builder.create().getLayoutInflater();
            View view=inflater.inflate(R.layout.loadingimage,null);

            ImageView imageView=view.findViewById(R.id.loadingimages);

//            Picasso.get()
//                    .load(R.drawable.loading)
//                    .into(imageView);
        Glide.with(context)
                .asGif()
                .load(R.drawable.loading)
                .into(imageView);
        builder.setCancelable(true);
        builder.setView(view);
        AlertDialog alertDialog=builder.create();
        alertDialog.create();
        alertDialog.show();
        Window window=alertDialog.getWindow();
        if (window!=null){
            window.setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
            window.setGravity(Gravity.CENTER);
            WindowManager.LayoutParams lp=new WindowManager.LayoutParams();
            lp.copyFrom(window.getAttributes());
            lp.gravity= Gravity.BOTTOM|Gravity.CENTER_HORIZONTAL;
            lp.width=WindowManager.LayoutParams.MATCH_PARENT;
            lp.height=WindowManager.LayoutParams.WRAP_CONTENT;
            window.setAttributes(lp);
        }
        return alertDialog;
    }
    public void hideimageDialog(AlertDialog alertDialog){

            alertDialog.dismiss();



    }

}
