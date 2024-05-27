package com.example.warframedemo1.ui.slideshow;

import android.app.AlertDialog;
import android.os.Bundle;
import android.util.Log;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;

import com.example.warframedemo1.R;
import com.example.warframedemo1.databinding.FragmentSlideshowBinding;
import com.example.warframedemo1.showProgressBar;
import com.example.warframedemo1.warframedata.fissure.fissures_tools;

import program.fissures.*;
public class SlideshowFragment extends Fragment {
    AlertDialog alertDialog;
    public String msg="fissure :";
    private FragmentSlideshowBinding binding;
    class theard extends Thread{
        fissures fiss=new fissures();
        fissures_translate fiss_tra=new fissures_translate();
        fissures_tools fissures_tools=new fissures_tools();

        @Override
        public void run() {
            getActivity().runOnUiThread(()->{

                TextView edit=getView().findViewById(R.id.fissure_text_area);
                TextView edit_hard=getView().findViewById(R.id.fissure_text_area_hard);
                TextView edit_storm=getView().findViewById(R.id.fissure_text_area_storm);
                edit_storm.setFocusable(false);
                edit_storm.setFocusableInTouchMode(false);
                edit.setFocusable(false);
                edit.setFocusableInTouchMode(false);
                edit_hard.setFocusable(false);
                edit_hard.setFocusableInTouchMode(false);

                edit.setGravity(Gravity.LEFT);
                edit_storm.setGravity(Gravity.LEFT);
                edit_hard.setGravity(Gravity.LEFT);
                try {
                    //Thread.sleep(3000);
//                    for(int i=0;i<5;i++){
//                        for(int j=0;j<fiss.fissure_Hardmission(i).length;j++){
//                            edit.append(fiss.fissure_Hardmission(j)[i]+fiss.fissure_Hardtier(j)[i]+fiss.fissure_Hardeta(j)[i]+fiss.fissure_Hardnode(j)[i]
//                                    +fiss.fissure_Hardenemy(j)[i]+"\n");
//                            Log.d(msg,fiss.fissure_Hardmission(j)[i]+fiss.fissure_Hardtier(j)[i]+fiss.fissure_Hardeta(j)[i]+fiss.fissure_Hardnode(j)[i]
//                                    +fiss.fissure_Hardenemy(j)[i]);
//                        }
//                    }
                    for(int i=0;i<fiss.fissure_alltype().length;i++){
                        if(fiss.fissure_allishard()[i]==false&&fiss.fissure_allisStorm()[i]==false){
                            edit.append(fiss_tra.fissure_translate(fiss.fissure_alltype()[i])+"\t\t"+fiss.fissure_alleta()[i]+"\t" +
                                    "\t"+fissures_tools.fissures_tire(fiss.fissure_alltierNum()[i])+"\t\t"+fiss.fissure_allnode()[i]+"\n");
                        }
                        if(fiss.fissure_allishard()[i]==true&&fiss.fissure_allisStorm()[i]==false){
                            edit_hard.append(fiss_tra.fissure_translate(fiss.fissure_alltype()[i])+"\t\t"+fiss.fissure_alleta()[i]+"\t" +
                                    "\t"+fissures_tools.fissures_tire(fiss.fissure_alltierNum()[i])+"\t\t"+fiss.fissure_allnode()[i]+"\n");
                        }
                        if(fiss.fissure_allishard()[i]==false&&fiss.fissure_allisStorm()[i]==true){
                            edit_storm.append(fiss_tra.fissure_translate(fiss.fissure_alltype()[i])+"\t\t"+fiss.fissure_alleta()[i]+"\t" +
                                    "\t"+fissures_tools.fissures_tire(fiss.fissure_alltierNum()[i])+"\t\t"+fiss.fissure_allnode()[i]+"\n");
                        }


                        System.out.println(fiss.fissure_alltype()[i]);
                        System.out.println(fiss.fissure_alleta()[i]);
                        System.out.println(fiss.fissure_alltierNum()[i]);

                    }
                }catch (Exception e){
                    edit.setText(e.toString());
                    Log.d(msg,e.toString());
                }

            });
            showProgressBar show=new showProgressBar();
            show.hideimageDialog(alertDialog);
        }
    }

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        SlideshowViewModel slideshowViewModel =
                new ViewModelProvider(this).get(SlideshowViewModel.class);

        binding = FragmentSlideshowBinding.inflate(inflater, container, false);
        View root = binding.getRoot();

        final TextView textView = binding.textSlideshow;
        slideshowViewModel.getText().observe(getViewLifecycleOwner(), textView::setText);
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
            theard th=new theard();
            th.start();
        }).start();

    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }
}