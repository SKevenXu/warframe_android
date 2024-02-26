package com.example.warframedemo1.ui.RivenSearch;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

public class RivenSearchViewModel extends ViewModel {
    private final MutableLiveData<String> mText;

    public RivenSearchViewModel() {
        mText = new MutableLiveData<>();
        mText.setValue("Search...");
    }

    public LiveData<String> getText() {
        return mText;
    }
}
