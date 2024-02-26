package com.example.warframedemo1.ui.invasions;


import android.os.Bundle;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;

import com.example.warframedemo1.R;
import com.example.warframedemo1.databinding.FragmentInvasionsBinding;
import com.example.warframedemo1.showProgressBar;

import program.invasions.*;

public class invasionsFragment extends Fragment {
    class thread extends Thread{
        invasions inva=new invasions();


        EditText editText_invasion=getView().findViewById(R.id.text_invasion);




        @Override
        public void run() {
            editText_invasion.setFocusable(false);
            editText_invasion.setFocusableInTouchMode(false);
            editText_invasion.setText("");
            editText_invasion.setGravity(Gravity.LEFT);
            getActivity().runOnUiThread(()->{
                for(int i=0;i<inva.getnode().length;i++){
                    if(inva.getnode()[i]!=null){
                        editText_invasion.append("\t\t任务地图:\t"+inva.getnode()[i]+"\n");
                        editText_invasion.append("\t\t防守方:\t"+inva.getattackingFaction()[i]+"\n");
                        editText_invasion.append("\t\t进攻方:\t"+inva.getdefendingFaction()[i]+"\n");
                        editText_invasion.append("\t\t进攻方胜利奖励:\t"+inva.getattackerreward()[i]+"\n");
                        editText_invasion.append("\t\t防守方胜利奖励:\t"+inva.getdefenderreward()[i]+"\n");
                        editText_invasion.append("\t\t任务进度比例:\t"+inva.getcompletion()[i]+"%\n\n");

                    }
                }


            });
            showProgressBar show=new showProgressBar();
            show.hideProgressDialog();
        }
    }
    private FragmentInvasionsBinding binding;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        invasionViewModel invasionViewModel =
                new ViewModelProvider(this).get(invasionViewModel.class);

        binding = FragmentInvasionsBinding.inflate(inflater, container, false);
        View root = binding.getRoot();

        final TextView textView = binding.textInvasions;
        invasionViewModel.getText().observe(getViewLifecycleOwner(), textView::setText);
        return root;
    }
    private ConstraintLayout lay;
    public void init(String text,int id){

            EditText edit=new EditText(getActivity());
            edit.setText(text);
            edit.setId(id);
            lay.addView(edit);


    }



    @Override
    public void onStart() {
        super.onStart();
        showProgressBar show=new showProgressBar();
        show.showProgressDialog(getActivity(),"Loading...");
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
