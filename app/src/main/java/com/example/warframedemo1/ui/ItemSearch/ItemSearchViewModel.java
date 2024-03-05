package com.example.warframedemo1.ui.ItemSearch;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

public class ItemSearchViewModel extends ViewModel {
    private final MutableLiveData<String> mText;

    public ItemSearchViewModel() {
        mText = new MutableLiveData<>();
        mText.setValue("Search...");
    }

    public LiveData<String> getText() {
        return mText;
    }
}
