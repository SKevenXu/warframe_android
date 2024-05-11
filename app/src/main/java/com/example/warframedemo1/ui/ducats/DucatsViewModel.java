package com.example.warframedemo1.ui.ducats;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

public class DucatsViewModel extends ViewModel {

    private final MutableLiveData<String> mText;

    public DucatsViewModel() {
        mText = new MutableLiveData<>();
        mText.setValue("");
    }

    public LiveData<String> getText() {
        return mText;
    }
}