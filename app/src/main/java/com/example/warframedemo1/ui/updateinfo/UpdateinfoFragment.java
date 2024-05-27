package com.example.warframedemo1.ui.updateinfo;


import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import android.widget.LinearLayout;

import android.widget.RelativeLayout;
import android.widget.ScrollView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;


import com.example.warframedemo1.R;
import com.example.warframedemo1.databinding.FragmentUpdateinfoBinding;
import com.example.warframedemo1.readfile;

import program.version.getversion;


public class UpdateinfoFragment extends Fragment{

    class thread extends Thread{





        @Override
        public void run() {
            LinearLayout linearLayout=getActivity().findViewById(R.id.updateinfo_layout);
            getActivity().runOnUiThread(()->{
                getversion ver=new getversion();
                ver.getJson(new readfile().getFromAssets("version.json",getActivity()));
                int temp=ver.getversion_num().length;
                for(int i=0;i<temp;i++){
                    LayoutInflater inflater = getLayoutInflater();
                    View customView = inflater.inflate(R.layout.updateinfo_view, linearLayout, false);

                    TextView textView_tittle=customView.findViewById(R.id.update_tittle);
                    TextView textView_text=customView.findViewById(R.id.update_text);
                    TextView textview_time=customView.findViewById(R.id.update_time);
//                    RelativeLayout.LayoutParams params=new RelativeLayout.LayoutParams(RelativeLayout.LayoutParams.WRAP_CONTENT,
//                            RelativeLayout.LayoutParams.WRAP_CONTENT);
//                    params.addRule(RelativeLayout.ALIGN_PARENT_TOP);
//                    params.addRule(RelativeLayout.ALIGN_PARENT_LEFT);
//                    textview_time.setLayoutParams(params);
//                    textView_text.setLayoutParams(params);
//                    textView_tittle.setLayoutParams(params);


                    textView_tittle.append(ver.getversion_num()[i]);
                    textview_time.append(ver.getversion_time()[i]);

                    for(int j=0;j<ver.returnversionsize(i);j++){
                        textView_text.append("\t\t"+ver.getversion_detail(i)[j]+"\n");
                    }


                    Log.d("msg",textView_tittle.getText().toString());

                    //progressBar.append("\t\t任务进度比例:\t"+inva.getcompletion()[i]+"%\n\n");

                    linearLayout.addView(customView);
                }



            });
        }
    }
    private FragmentUpdateinfoBinding binding;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
       UpdateinfoViewModel updateinfoViewModel =
                new ViewModelProvider(this).get(UpdateinfoViewModel.class);

        binding = FragmentUpdateinfoBinding.inflate(inflater, container, false);
        View root = binding.getRoot();

        final TextView textView = binding.textUpdateinfo;
        updateinfoViewModel.getText().observe(getViewLifecycleOwner(), textView::setText);
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




