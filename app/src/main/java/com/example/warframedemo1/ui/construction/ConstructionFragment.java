package com.example.warframedemo1.ui.construction;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ProgressBar;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;

import com.example.warframedemo1.R;
import com.example.warframedemo1.databinding.FragmentContructionBinding;
import com.example.warframedemo1.showProgressBar;

import program.ConstructionProgress.*;


public class ConstructionFragment extends Fragment {
    class thread extends Thread{
        construction con=new construction();
        TextView text_fomorian=getView().findViewById(R.id.formorian_text);
        TextView texi_razorback=getView().findViewById(R.id.Razorback_text);
        ProgressBar progressBar_fomorian=getView().findViewById(R.id.formorian_progressbar);
        ProgressBar progressBar_razorabck=getView().findViewById(R.id.Razorback_progressbar);
        @Override
        public void run() {
                text_fomorian.setText("");
                texi_razorback.setText("");
            getActivity().runOnUiThread(()->{
                text_fomorian.append("豺狼舰队建造进度:\t"+con.getFomorian());
                texi_razorback.append("巨人舰队建造进度:\t"+con.getRazorback());
                progressBar_fomorian.setProgress((int)con.getFomorian(),true);
                progressBar_razorabck.setProgress((int)con.getRazorback(),true);

            });
            showProgressBar show=new showProgressBar();
            show.hideProgressDialog();
        }
    }
    private FragmentContructionBinding binding;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        ConstructionViewModel constructionViewModel =
                new ViewModelProvider(this).get(ConstructionViewModel.class);

        binding = FragmentContructionBinding.inflate(inflater, container, false);
        View root = binding.getRoot();

        final TextView textView = binding.textConstruction;
        constructionViewModel.getText().observe(getViewLifecycleOwner(), textView::setText);
        return root;
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
