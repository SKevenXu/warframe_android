package com.example.warframedemo1.ui.updateinfo;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;



public class UpdateinfoViewModel extends ViewModel {
    private final MutableLiveData<String> mText;

    public UpdateinfoViewModel() {
        mText = new MutableLiveData<>();


        mText.setValue("");
    }

    public LiveData<String> getText() {
        return mText;
    }
}