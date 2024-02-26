package com.example.warframedemo1.ui.gallery;

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
import com.example.warframedemo1.databinding.FragmentGalleryBinding;
import com.example.warframedemo1.showProgressBar;
import com.example.warframedemo1.warframedata.Syndicates.Syndicate;
import com.example.warframedemo1.warframedata.Syndicates.Syndicates_translate;


public class GalleryFragment extends Fragment {
    public String msg="Syndicate: ";
    class thread extends Thread{
//        CetusSyndicate syndicate=new CetusSyndicate();

        Syndicates_translate tra=new Syndicates_translate();
        Syndicate synd=new Syndicate();
        TextView textView_cetus=getView().findViewById(R.id.Syndicate_cetus_title);
        TextView textView_vaills=getView().findViewById(R.id.Syndicate_vaills_title);
        TextView textView_cambion=getView().findViewById(R.id.Syndicate_cambion_title);

        EditText editText_cetus=getView().findViewById(R.id.Syndicate_cetus);
        EditText editText_vaills=getView().findViewById(R.id.Syndicate_vaills);
        EditText editText_cambion=getView().findViewById(R.id.Syndicate_cambion);
        @Override
        public void run() {
            getActivity().runOnUiThread(()->{
                editText_cetus.setText("");
                editText_cambion.setText("");
                editText_vaills.setText("");
                textView_cetus.append("\t\t"+"剩余时间:\t"+synd.atleasttime(0)+"\t分钟");
                textView_cambion.append("\t\t"+"剩余时间:\t"+synd.atleasttime(0)+"\t分钟");
                textView_vaills.append("\t\t"+"剩余时间:\t"+synd.atleasttime(0)+"\t分钟");
                for(int i=0;i<synd.Syndicate_list_cetus().length;i++){
                    editText_cetus.append(tra.Cetustran(synd.Syndicate_list_cetus()[i])+"\n");
                    Log.d(msg,"cetus:\t"+synd.Syndicate_list_cetus()[i]+"\n");
                }
                for(int i=0;i<synd.Syndicate_list_vaills().length;i++){
                    editText_vaills.append(tra.vallistran(synd.Syndicate_list_vaills()[i])+"\n");
                    Log.d(msg,"vaills:\t"+synd.Syndicate_list_vaills()[i]+"\n");
                }
                for(int i=0;i<synd.Syndicate_list_cambion().length;i++){
                    editText_cambion.append(tra.Cambiontran(synd.Syndicate_list_cambion()[i]) +"\n");
                    Log.d(msg,"cambion:\t"+synd.Syndicate_list_cambion()[i]+"\n");
                }
            });
            showProgressBar show=new showProgressBar();
            show.hideProgressDialog();
        }
    }

    private FragmentGalleryBinding binding;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        GalleryViewModel galleryViewModel =
                new ViewModelProvider(this).get(GalleryViewModel.class);

        binding = FragmentGalleryBinding.inflate(inflater, container, false);
        View root = binding.getRoot();

        final TextView textView = binding.SyndicateCetusTitle;
        galleryViewModel.getText().observe(getViewLifecycleOwner(), textView::setText);
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