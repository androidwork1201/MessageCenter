package com.example.messagecenter.login;

import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.google.firebase.messaging.FirebaseMessaging;

import java.util.Objects;

public class LoginViewModel extends ViewModel {

    private MutableLiveData<String> _driveToken = new MutableLiveData<>();

    private MutableLiveData<String> _account = new MutableLiveData<>();

    private MutableLiveData<String> _password = new MutableLiveData<>();

    public MutableLiveData<String> getDriveToken() {
        return _driveToken;
    }

    public MutableLiveData<String> getAccount() {
        return _account;
    }

    public MutableLiveData<String> getPassword() {
        return _password;
    }

    public LoginViewModel(){
        getDriveTokenInfo();
        _account.setValue("");
        _password.setValue("");
    }


    public void getDriveTokenInfo() {
        FirebaseMessaging.getInstance().getToken()
                .addOnCompleteListener(task -> {
                    if (!task.isSuccessful()) {
                        return;
                    }
                    if (null != task.getResult()) {

                        String firebaseMessagingToken = Objects.requireNonNull(task.getResult());
//                       Log.d("Tag", firebaseMessagingToken);
                        _driveToken.setValue(firebaseMessagingToken);
                    }
                });

    }

}
