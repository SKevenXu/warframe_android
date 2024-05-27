package com.example.warframedemo1.ui.ducats;

import android.annotation.SuppressLint;
import android.app.AlertDialog;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.ScrollView;
import android.widget.Spinner;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;


import com.example.warframedemo1.R;
import com.example.warframedemo1.databinding.FragmentDucatsBinding;

import com.example.warframedemo1.showProgressBar;
import com.squareup.picasso.Picasso;

import java.sql.Array;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import program.getItemUrlName.*;
import program.ducatssearch.*;

public class DucatsFragment extends Fragment {
    AlertDialog alertDialog;
    public String msg="ducats: ";
    public  long strToDate(String dateStr) throws Exception {
        Date dates = new Date();
        // this object contains the current date value
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSSXXX");
        Date da = sdf.parse(sdf.format(dates));
        // System.out.println(da.getHours() + "\t" + da.getTime());

        Date date = sdf.parse(dateStr);
        long diff = da.getTime() - date.getTime();
        long days = diff / (1000 * 60 * 60 * 24);
        // System.out.println(date.getHours() + "\t" + date.getTime());
        long hours = (diff - days * (1000 * 60 * 60 * 24)) / (1000 * 60 * 60);
        long minutes = (diff - days * (1000 * 60 * 60 * 24) - hours * (1000 * 60 * 60)) / (1000 * 60);

        System.out.println("结果:\t" + (60 - minutes) + "分");
        long atleasttime=(60-minutes);
        return atleasttime;
    }

    class thread extends Thread{



   //     ListView textView=getView().findViewById(R.id.ducats_view);
        //ScrollView scrollView=getView().findViewById(R.id.ducats_scroll);
        //TextView textView=getView().findViewById(R.id.ducats_view);
        //ImageView imageView=getView().findViewById(R.id.ducats_images);
//        TextView textView=getView().findViewById(R.id.ducats_text);
//        TextView time=getView().findViewById(R.id.ducats_time);
//        Spinner spinner=getActivity().findViewById(R.id.ducats_spinner);
        ducatsget du = new ducatsget();
        itemurlget it = new itemurlget();

        @SuppressLint("ResourceType")
        @Override
        public void run() {

            getActivity().runOnUiThread(()->{

                    LinearLayout linear=getActivity().findViewById(R.id.ducats_linear);

                //textView.setText("");
//                time.setText("");
//                try {
//                    time.append("还有\t"+strToDate(du.getdatetime()[0])+"\t分钟刷新");
//                } catch (Exception e) {
//                    throw new RuntimeException(e);
//                }
                ArrayList<String> name = du.sethash(du.getitem(), it.getid(), it.getitemname());
                ArrayList<String> thumb = du.sethashthumb(du.getitem(), it.getid(), it.getthumb());

                int[] num = du.getthemaxnum(du.getducats_per_platinum_wa(), 5);
//                List<Ducats> ducatslist =new ArrayList<>();
//                Ducats ducats;

                for (int i = 0; i < num.length; i++) {

                    TextView textView=  new TextView(getActivity());
                    ImageView imageView =new ImageView(getActivity());
                    textView.setId(i);
                    textView.setTextColor(Color.BLACK);
                    textView.setBackgroundResource(R.drawable.shapedrawable);
                    imageView.setId(255+i);





//                    ducatslist.add(new Ducats(name.get(num[i]),du.getvolume()[num[i]],du.getwa_price()[num[i]],du.getducats()[num[i]],du.getmedian()[num[i]],
//                            du.getducats_per_platinum_wa()[num[i]],thumb.get(num[i])));


                    textView.append("物品id:\t" + du.getid()[num[i]]+"\n");

                    textView.append("物品名字:\t" + name.get(num[i])+"\n");
                    textView.append("物品剩余:\t" + du.getvolume()[num[i]]+"\n");
                    textView.append("物品平均售价:\t" + du.getwa_price()[num[i]]+"\n");
                    textView.append("物品最低售价:\t" + du.getmedian()[num[i]]+"\n");

                    // System.out.println("物品白金:\t" + du.getplat_worth()[i]);
                    textView.append("物品杜卡德:\t" + du.getducats()[num[i]]+"\n");
                    textView.append("物品平均白金(杜卡德/1白金):\t" + du.getducats_per_platinum_wa()[num[i]]+"\n");
                    //textView.append("thumb:\t" + thumb.get(num[i])+"\n");
                    Picasso.get()
                            .load("https://warframe.market/static/assets/"+thumb.get(num[i]))
                            .placeholder(R.drawable.ic_menu_gallery)
                            .error(R.drawable.xx)
                            .into(imageView);
                    Log.d(msg,thumb.get(num[i])+"\n");
                    //textView.append("物品刷新时间:\t" + du.getdatetime()[num[i]]+"\n");
                    textView.append("\n"+"\n");

                    linear.addView(imageView);
                    linear.addView(textView);
                }
 //               ArrayAdapter<Ducats> adapter=new ArrayAdapter<>(getActivity(),R.layout.fragment_ducats,ducatslist);
  //              textView.setAdapter(adapter);
                String[] temp= {"5","10","15","20","50"};
//                ArrayAdapter arrayAdapter=new ArrayAdapter(getActivity(),R.layout.spinner,temp);
//                spinner.setAdapter(arrayAdapter);
//
//                spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
//                    @Override
//                    public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
//                            Log.d(msg,i+"\t"+l);
//                    }
//
//                    @Override
//                    public void onNothingSelected(AdapterView<?> adapterView) {
//
//                    }
//                });
            });

            showProgressBar show=new showProgressBar();
            show.hideimageDialog(alertDialog);
        }
    }

    private FragmentDucatsBinding binding;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {




        DucatsViewModel ducatsViewModel =
                new ViewModelProvider(this).get(DucatsViewModel.class);

        binding = FragmentDucatsBinding.inflate(inflater, container, false);
        View root = binding.getRoot();

        final TextView textView = binding.ducatsTittle;
        ducatsViewModel.getText().observe(getViewLifecycleOwner(), textView::setText);
        return root;
    }

    @Override
    public void onStart() {
        super.onStart();

        showProgressBar show=new showProgressBar();
        alertDialog=show.showimageDialog(getActivity());
    }

    @Override
    public void onResume() {
        super.onResume();
        new Thread(()->{
            thread th=new thread();
            th.start();
        }).start();
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }
}