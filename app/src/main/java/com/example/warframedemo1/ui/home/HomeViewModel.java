package com.example.warframedemo1.ui.home;



import android.widget.TextView;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.example.warframedemo1.R;

import program.Cycle_details.cetusCycle_details;

public class HomeViewModel extends ViewModel {

    private MutableLiveData<String> mText;

    public HomeViewModel() {

            cetusCycle_details cetus=new cetusCycle_details();
            mText = new MutableLiveData<>();
            mText.setValue("");




    }


    public LiveData<String> getText() {
        return mText;
    }
}