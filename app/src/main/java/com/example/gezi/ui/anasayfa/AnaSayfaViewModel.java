package com.example.gezi.ui.anasayfa;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

public class AnaSayfaViewModel extends ViewModel {

    private MutableLiveData<String> mText;

    public AnaSayfaViewModel() {
        mText = new MutableLiveData<>();
    }

    public LiveData<String> getText() {
        return mText;
    }
}