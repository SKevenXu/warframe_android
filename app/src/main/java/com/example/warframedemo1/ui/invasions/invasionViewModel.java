package com.example.warframedemo1.ui.invasions;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

public class invasionViewModel extends ViewModel {
    private final MutableLiveData<String> mText;

    public invasionViewModel() {
        mText = new MutableLiveData<>();
        mText.setValue("入侵:");
    }

    public LiveData<String> getText() {
        return mText;
    }
}
