package com.example.warframedemo1.ui.sundry;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;
public class SundryViewModel extends ViewModel {
    private final MutableLiveData<String> mText;

    public SundryViewModel() {
        mText = new MutableLiveData<>();

    }

    public LiveData<String> getText() {
        return mText;
    }
}
