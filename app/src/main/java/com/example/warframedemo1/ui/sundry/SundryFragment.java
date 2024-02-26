package com.example.warframedemo1.ui.sundry;
import android.os.Bundle;
import android.text.method.ScrollingMovementMethod;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;

import com.example.warframedemo1.R;
import com.example.warframedemo1.databinding.FragmentSundryBinding;
import com.example.warframedemo1.showProgressBar;

import program.Arbitration.arbitration;
import program.sortie.*;
import program.steelPath.*;
import program.ArchonHuntReward.*;
import program.fissures.fissures_translate;


public class SundryFragment extends Fragment{

    private FragmentSundryBinding binding;
    class thread extends Thread{
        ArchonHuntReward arch=new ArchonHuntReward();
        steelPath steel=new steelPath();
        sortie sortie=new sortie();
        arbitration arbit=new arbitration();
        fissures_translate tra=new fissures_translate();


        EditText textView_arbit=getView().findViewById(R.id.attribute_text);
        EditText textView_sortie=getView().findViewById(R.id.sortie_text);
        EditText textView_steel=getView().findViewById(R.id.steelpath_text);
        EditText textView_arch=getView().findViewById(R.id.ArchonHuntReward_text);

        showProgressBar show=new showProgressBar();
        @Override
        public void run() {
            textView_sortie.setMovementMethod(ScrollingMovementMethod.getInstance());
            getActivity().runOnUiThread(()->{
                textView_arbit.setText("");
                textView_sortie.setText("");
                textView_arch.setText("");
                textView_steel.setText("");
                textView_arbit.append("仲裁:\t\t\t\n"+"\t\t\t仲裁任务:\t"+tra.fissure_translate(arbit.arbittype())+"\n\t\t\t任务地点:\t"+arbit.arbitnode()
                +"\n\t\t\t派系:\t"+arbit.arbitenemy()+"\n\t\t\t剩余时间:\t\t"+arbit.arbittime()+"分钟");
                textView_sortie.append("突击:\t\t\t\n");
                for(int i=0;i<sortie.getmissions().length;i++){
                    textView_sortie.append("\t\t任务"+(i+1)+":\t"+tra.fissure_translate(sortie.getmissions()[i])+"\n\t\t\t任务地点:\t"+sortie.getnodes()[i]
                    +"\n\t\t\t任务限制:\t"+sortie.getmodifiers()[i]+"\n\t\t\t限制描述:\t"+sortie.getmodifierDescriptions()[i]+"\n");
                }
                textView_arch.append("执行官:\n"+"\t\t本周执行官:\t"+arch.ArchonHunt_boss()
                +"\n\t\t还有\t"+arch.ArchonHunt_eta()+"\t轮换\n");
                for(int i=0;i<arch.ArchonHunt_node().length;i++){
                    textView_arch.append("\t\t任务"+(i+1)+":\t"+tra.fissure_translate(arch.ArchonHunt_type()[i])+"\n\t\t\t任务地点:\t"+arch.ArchonHunt_node()[i]+"\n");
                }
                textView_steel.append("钢铁之路奖励:\n"+"\t\t\t物品:\t"+steel.curr_name()+"\n"+"\t\t\t需要的钢精:\t"+steel.curr_cost()+"\n\t\t\t还有\t"+steel.steel_remaining()+"\t轮换\n");

            });

            show.hideProgressDialog();
        }
    }

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        SundryViewModel sundryViewModel =
                new ViewModelProvider(this).get(SundryViewModel.class);

        binding = FragmentSundryBinding.inflate(inflater, container, false);
        View root = binding.getRoot();

        final TextView textView = binding.ArchonHuntRewardText;
        sundryViewModel.getText().observe(getViewLifecycleOwner(), textView::setText);
        return root;
    }

    @Override
    public void onStart() {
        super.onStart();
        showProgressBar show=new showProgressBar();
        show.showProgressDialog(getActivity(),"loading...");

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
