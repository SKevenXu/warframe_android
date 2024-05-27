package com.example.warframedemo1.ui.invasions;


import android.annotation.SuppressLint;
import android.app.AlertDialog;
import android.os.Bundle;
import android.util.Log;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;

import com.bumptech.glide.Glide;
import com.example.warframedemo1.R;
import com.example.warframedemo1.databinding.FragmentInvasionsBinding;
import com.example.warframedemo1.showProgressBar;
import com.squareup.picasso.Picasso;

import program.invasions.*;

public class invasionsFragment extends Fragment {
    AlertDialog alertDialog;
    class thread extends Thread{

        invasions inva=new invasions();


        TextView editText_invasion=getView().findViewById(R.id.text_invasions);




        @Override
        public void run() {


            LinearLayout linearLayout=getActivity().findViewById(R.id.invasions_content);

            getActivity().runOnUiThread(()->{
                try {
                    for(int i=0;i<inva.getnode().length;i++){
                        if(inva.getnode()[i]!=null){
                            LayoutInflater inflater = getLayoutInflater();
                            View customView = inflater.inflate(R.layout.invassionlayout, linearLayout, false);
                            @SuppressLint({"MissingInflatedId", "LocalSuppress"}) ImageView imageView_attacker=customView.findViewById(R.id.invassion_image_attacker);
                            @SuppressLint({"MissingInflatedId", "LocalSuppress"}) ImageView imageView_defender=customView.findViewById(R.id.invassion_image_defender);
                            TextView textView=customView.findViewById(R.id.invassion_text);
                            ProgressBar progressBar=customView.findViewById(R.id.invassion_progressbar);
                            textView.append("任务地图:\t"+inva.getnode()[i]+"\n");
                            textView.append("防守方:\t"+inva.getattackingFaction()[i]+"\n");
                            textView.append("进攻方:\t"+inva.getdefendingFaction()[i]+"\n");
                            textView.append("进攻方胜利奖励:\t"+inva.getattackerreward()[i]+"\n");
                            //editText_invasion.append("\t\t进攻方胜利奖励链接:\t"+inva.getattackerthumbnail()[i]+"\n");
                            textView.append("防守方胜利奖励:\t"+inva.getdefenderreward()[i]+"\n");
                            //editText_invasion.append("\t\t防守方胜利奖励链接:\t"+inva.getdefenderthumbnail()[i]+"\n");
                            Log.d("inva.getdefenderthumbnail()",inva.getdefenderthumbnail()[i]+"\t\t"+inva.getdefenderthumbnail()[i].hashCode());
                            Log.d("inva.getattackerthumbnail()[i]",inva.getattackerthumbnail()[i]+"\t\t"+inva.getattackerthumbnail()[i].hashCode());
                            if (inva.getdefenderthumbnail()[i].hashCode()!=0&&inva.getdefenderthumbnail()[i].hashCode()!=3392903){

                                Picasso.get()
                                        .load(inva.getdefenderthumbnail()[i])
                                        .placeholder(R.drawable.imageloading)
                                        .error(R.drawable.noimage)
                                        .into(imageView_defender);
                            }else {
                                Picasso.get()
                                        .load(R.drawable.noimage)
                                        .placeholder(R.drawable.imageloading)
                                        .error(R.drawable.noimage)
                                        .into(imageView_defender);
                            }
                            if(inva.getattackerthumbnail()[i].hashCode()!=0&&inva.getattackerthumbnail()[i].hashCode()!=3392903){
                                Picasso.get()
                                        .load(inva.getattackerthumbnail()[i])
                                        .placeholder(R.drawable.imageloading)
                                        .error(R.drawable.noimage)
                                        .into(imageView_attacker);
                            } else {
                                Picasso.get()
                                        .load(R.drawable.noimage)
                                        .placeholder(R.drawable.imageloading)
                                        .error(R.drawable.noimage)
                                        .into(imageView_attacker);
                            }

                            //progressBar.append("\t\t任务进度比例:\t"+inva.getcompletion()[i]+"%\n\n");
                            progressBar.setProgress((int)Float.parseFloat(inva.getcompletion()[i]),true);
                            linearLayout.addView(customView);
                        }
                    }
                }catch (Exception e){
                    editText_invasion.setText("DE抽风不给数据!");
                    Log.e("invassion",e.toString());
                }



            });
            showProgressBar show=new showProgressBar();
            show.hideimageDialog(alertDialog);
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
