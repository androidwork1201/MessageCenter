package com.example.messagecenter.network.contentpage;

import com.example.messagecenter.value.ValuePage;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class ContentListClient {

    private static Retrofit retrofit;

    public static Retrofit getContentListClientData() {
        if (retrofit == null) {
            retrofit = new Retrofit.Builder()
                    .baseUrl(ValuePage.CONTENT_LIST_URL)
                    .addConverterFactory(GsonConverterFactory.create())
                    .build();
        }
        return retrofit;
    }
}
