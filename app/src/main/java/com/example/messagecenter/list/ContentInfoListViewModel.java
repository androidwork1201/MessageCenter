package com.example.messagecenter.list;

import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

public class ContentInfoListViewModel extends ViewModel {


    private MutableLiveData<String> _sessionToken = new MutableLiveData<>();

    public MutableLiveData<String> getSessionToken() {
        return _sessionToken;
    }

    public void setSessionToken(MutableLiveData<String> _sessionToken) {
        this._sessionToken = _sessionToken;
    }

}
