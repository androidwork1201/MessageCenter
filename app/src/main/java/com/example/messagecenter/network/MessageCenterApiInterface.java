package com.example.messagecenter.network;

import com.example.messagecenter.network.contentpage.ContentListData;
import com.example.messagecenter.network.loginpage.LoginData;
import com.example.messagecenter.value.ValuePage;

import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.Header;
import retrofit2.http.POST;

public interface MessageCenterApiInterface {

    @FormUrlEncoded
    @POST("login.php")
    Call<LoginData> getLoginData(
            @Header("push-token") String pushToken,
            @Field("acc") String account,
            @Field("pwd") String password);


    @POST("get_list.php")
    Call<ContentListData> getContentListData(
            @Header("session-token") String sessionToken);

}

