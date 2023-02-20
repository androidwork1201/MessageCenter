package com.example.messagecenter.network.loginpage;

import com.example.messagecenter.value.ValuePage;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class LoginClient {

    private static Retrofit retrofit;

    public static Retrofit getLoginClientData() {
        if (retrofit == null) {
            retrofit = new Retrofit.Builder()
                    .baseUrl(ValuePage.LOGIN_URL)
                    .addConverterFactory(GsonConverterFactory.create())
                    .build();
        }
        return retrofit;
    }
}
