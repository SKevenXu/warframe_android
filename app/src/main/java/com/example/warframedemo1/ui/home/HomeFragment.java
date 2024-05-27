package com.example.warframedemo1.ui.home;

import android.app.AlertDialog;
import android.graphics.Color;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;


import com.example.warframedemo1.MainActivity;
import com.example.warframedemo1.R;
import com.example.warframedemo1.databinding.FragmentHomeBinding;

import com.example.warframedemo1.loadingimage;
import com.example.warframedemo1.readfile;
import com.example.warframedemo1.showProgressBar;
import com.example.warframedemo1.showmessage;
import com.example.warframedemo1.warframedata.cycle.*;

import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.SimpleTimeZone;
import java.util.Timer;
import java.util.TimerTask;


public class HomeFragment extends Fragment {
    private AlertDialog alertDialog;
    private FragmentHomeBinding binding;

    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        HomeViewModel homeViewModel =
                new ViewModelProvider(this).get(HomeViewModel.class);

        binding = FragmentHomeBinding.inflate(inflater, container, false);
        View root = binding.getRoot();

        final TextView textView = binding.textHome;
        homeViewModel.getText().observe(getViewLifecycleOwner(), textView::setText);
        return root;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }
    public String cetus_time=null;
    public boolean cetus_isday;
    public String cambion_time=null;
    public String cambion_state=null;
    public String vallis_time=null;
    public boolean vallis_iswarm;
    String msg="Android warframe :";

    class thread extends Thread{
//

        public String getcetustime() throws IOException {
            cetus_cycle cetus=new cetus_cycle();
            return cetus.get_cetus_timeleft();
        }
        public boolean getcetusisday() throws IOException {
            cetus_cycle cetus=new cetus_cycle();
            return  cetus.get_cetus_isday();
        }
        public String getcambiontimeleft() throws IOException {
            cambion_cycle cambion=new cambion_cycle();
            return cambion.get_cambion_timeleft();
        }
        public String getcambionstate() throws IOException {
            cambion_cycle cambion=new cambion_cycle();
            return cambion.get_cambion_state();
        }
        public String getvallistimeleft() throws IOException {
            vallis_cycle vallis=new vallis_cycle();
            return vallis.get_vallis_timeleft();
        }
        public boolean getvallisiswarm() throws  IOException{
            vallis_cycle vallis= new vallis_cycle();
            return vallis.get_vallis_iswarm();
        }
        TextView textView_cetus = getView().findViewById(R.id.text_cetus);
        TextView textView_cambion = getView().findViewById(R.id.text_cambion);
        TextView textView_vallis = getView().findViewById(R.id.text_vail);




        @Override
        public void run() {
            try{

                Log.d(msg,"getcetustime()"+getcetustime()+"\n"+"getcetusisday()"+getcetusisday()+"\ngetcambionstate()"+getcambionstate()+"\ngetcambiontimeleft()"+getcambiontimeleft());
                Log.d(msg,"getvallisiswarm()"+getvallisiswarm()+"\ngetvallistimeleft()"+getvallistimeleft());
                cetus_time=getcetustime();
                cetus_isday=getcetusisday();
                cambion_state=getcambionstate();
                cambion_time=getcambiontimeleft();
                vallis_iswarm=getvallisiswarm();
                vallis_time=getvallistimeleft();
//                      textView.append(getcetustime());

                    getActivity().runOnUiThread(() -> {
                        textView_cetus.setText("");
                        textView_cambion.setText("");
                        textView_vallis.setText("");

                        if (cetus_time != null && cambion_time != null && vallis_time != null) {
                            textView_cetus.append("cetus timeleft:\t" + cetus_time + "\n");
                            textView_cambion.append("cambion timeleft:\t" + cambion_time + "\n");

                            textView_cambion.append("cambion is " + cambion_state);

                            textView_vallis.append("vallis timeleft:\t" + vallis_time + "\n");

                            if (cetus_isday != true) {
                                textView_cetus.setBackgroundColor(Color.WHITE);
                                textView_cetus.setTextColor(Color.BLACK);
                                textView_cetus.append("cetus is day" + "\n");
                            } else {
                                textView_cetus.setBackgroundColor(Color.BLACK);
                                textView_cetus.setTextColor(Color.WHITE);
                                textView_cetus.append("cetus is night\n");
                            }
                            if (vallis_iswarm == true) {
                                textView_vallis.append("vallis is warm");
                            } else {
                                textView_vallis.append("vallis is cold");
                            }


                        }



                    });




            }catch (Exception e){
                Log.d(msg,e.toString());
            }

            showProgressBar show=new showProgressBar();
            show.hideimageDialog(alertDialog);

        }
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        TextView textView_cetus=getView().findViewById(R.id.text_cetus);
        TextView textView_cambion=getView().findViewById(R.id.text_cambion);
        TextView textView_vallis=getView().findViewById(R.id.text_vail);
        showmessage show=new showmessage();


        Button button=getView().findViewById(R.id.nicebutton);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                show.Dialogtestmessage(getActivity(),"看看实力");
            }
        });

    }

    @Override
    public void onStart() {

        super.onStart();
        showProgressBar show=new showProgressBar();
        //show.showProgressDialog(getActivity(),"loading...");
        alertDialog=show.showimageDialog(getActivity());

    }

    @Override
    public void onResume() {
        super.onResume();
        thread th=new thread();
        th.start();
        showmessage s=new showmessage();
        readfile read=new readfile();
        //read.write(getContext(),"0","update_if.txt");
        //TODO 发布时记得改
        if (read.update(getContext(),"update_if.txt").hashCode()=="0".hashCode()){
            s.showupdatemessage(getActivity(),"update");
            read.write(getContext(),"1","update_if.txt");
            //TODO 发布时记得改
        }



//        read.sharepreference(getContext());

//        if (read.getupdate(getContext(),version)){
//            s.showupdatemessage(getActivity(),"text");
//            Log.d("msg",read.getFromAssets("update.txt",getContext()));
//        }



    }

    @Override
    public void onStop() {
        super.onStop();

    }

}