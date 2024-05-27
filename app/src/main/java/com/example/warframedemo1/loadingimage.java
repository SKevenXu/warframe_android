package com.example.warframedemo1;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Context;
import android.view.View;
import android.widget.ImageView;

import com.squareup.picasso.Picasso;

public class loadingimage  {
    public void showimage(Activity activity){
        ImageView imageView =new ImageView(activity);
        imageView.setVisibility(View.VISIBLE);
        Picasso.get()
                .load(R.drawable.loading)
                .into(imageView);


    }
    public void hideimage(Activity activity){
        ImageView imageView=activity.findViewById(R.id.loadingimages);
        imageView.setVisibility(View.INVISIBLE);
    }

}
