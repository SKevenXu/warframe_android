package com.example.warframedemo1.ui.voidtrader;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.example.warframedemo1.warframedata.datafromlog.USB_demo;

public class VoidTraderViewModel extends ViewModel{
    private final MutableLiveData<String> mText;

    public VoidTraderViewModel() {
        mText = new MutableLiveData<>();
        USB_demo us=new USB_demo();

        mText.setValue("");
    }

    public LiveData<String> getText() {
        return mText;
    }
}
