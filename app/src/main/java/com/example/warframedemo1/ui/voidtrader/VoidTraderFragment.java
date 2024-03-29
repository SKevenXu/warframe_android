package com.example.warframedemo1.ui.voidtrader;

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

import java.io.IOException;
import program.voidTrader.*;

public class VoidTraderFragment extends Fragment{

    class thread extends Thread{
        voidTrader vo=new voidTrader();

        EditText editText=getView().findViewById(R.id.voidtraderedittext);
        @Override
        public void run() {
            getActivity().runOnUiThread(()->{

                if (vo.voidtrader_active()==true){
                    editText.append("奸商位置:"+vo.voidLocation()+"\n");
                    editText.append("还有 "+vo.voidEndString()+"结束\n");
                    for (int i=0;i<vo.item().length;i++){
                        editText.append("物品: "+vo.item()[i]+"\n");
                        editText.append("\t杜卡德金币: "+vo.item_ducats()[i]+"\n");
                        editText.append("\t现金: "+vo.item_credits()[i]+"\n");
                    }
                }else {

                    editText.setText("奸商还没来呢,别急\n\t还需要 "+vo.getstartString());
                    editText.append("\n\n奸商位置:"+vo.voidLocation()+"记得提前去\n");
                }

            });
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




