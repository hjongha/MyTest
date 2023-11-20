package com.example.myapplication.retrofit2;

import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.POST;

public interface MyApi {

    @FormUrlEncoded
    @POST("createAccount.php")
    Call<createAccountModel> createAccount(@Field("user_id") String user_id, @Field("user_pw") String user_pw);
}

