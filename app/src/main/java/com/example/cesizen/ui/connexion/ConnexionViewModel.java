package com.example.cesizen.ui.connexion;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

public class ConnexionViewModel extends ViewModel {

    private final MutableLiveData<String> mText;

    public ConnexionViewModel() {
        mText = new MutableLiveData<>();
        mText.setValue("This is connexion fragment");
    }

    public LiveData<String> getText() {
        return mText;
    }
}