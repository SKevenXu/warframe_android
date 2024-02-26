package com.example.warframedemo1.ui.construction;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

public class ConstructionViewModel extends ViewModel {
    private final MutableLiveData<String> mText;

    public ConstructionViewModel() {
        mText = new MutableLiveData<>();
        mText.setValue("This is Construction fragment");
    }

    public LiveData<String> getText() {
        return mText;
    }
}
