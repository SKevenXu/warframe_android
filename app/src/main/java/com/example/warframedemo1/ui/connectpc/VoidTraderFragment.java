package com.example.warframedemo1.ui.connectpc;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;

import com.example.warframedemo1.R;
import com.example.warframedemo1.databinding.FragmentVoidtraderBinding;
import com.example.warframedemo1.showProgressBar;
import com.example.warframedemo1.warframedata.datafromlog.USB_demo;


public class VoidTraderFragment extends Fragment{

    class thread extends Thread{
        USB_demo us=new USB_demo();
        TextView voidtrader=getActivity().findViewById(R.id.text_voidTrader);
        EditText text=getActivity().findViewById(R.id.text_voidTrader);
        showProgressBar show=new showProgressBar();

        @Override
        public void run() {

            String temp= null;
            try {
                temp = us.client("copy this!",getActivity(),R.id.text_voidTrader);

            } catch (Exception e) {
                getActivity().runOnUiThread(()->{
                    text.append("connect close!");

                });
            }


            String finalTemp = temp;
            getActivity().runOnUiThread(()->{

                text.append(finalTemp);
                Log.d("temp:", finalTemp);
                        //us.cmddemo(getActivity());
                        //us.getdatafromjcifs();
                        //us.jcifsgetdata();
                        //us.adbdemo(getContext(),getActivity());
                        // us.getUsbdevice(getContext(),getActivity());
                });




//            voidtrader.setText(us.getUsbdevice(getContext(),getActivity()));


        }
    }
    private FragmentVoidtraderBinding binding;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        VoidTraderViewModel voidTraderViewModel =
                new ViewModelProvider(this).get(VoidTraderViewModel.class);

        binding = FragmentVoidtraderBinding.inflate(inflater, container, false);
        View root = binding.getRoot();

        final TextView textView = binding.textVoidTrader;
        voidTraderViewModel.getText().observe(getViewLifecycleOwner(), textView::setText);
        return root;
    }

    @Override
    public void onResume() {
        super.onResume();
        System.out.println("thread start!");
        thread th=new thread();
        th.start();

    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }
}




