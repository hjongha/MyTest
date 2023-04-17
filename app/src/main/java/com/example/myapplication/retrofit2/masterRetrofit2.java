package com.example.myapplication.retrofit2;

import retrofit2.Call;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class masterRetrofit2 {

    Retrofit retrofit = new Retrofit.Builder()
            .baseUrl("https://wshwan15.cafe24.com/wisethan/test01/")
            .addConverterFactory(GsonConverterFactory.create())
            .build();

    MyApi myApi = retrofit.create(MyApi.class);



    public Call<createAccountModel> createAccount(String user_id, String user_pw){
        Call<createAccountModel> model = myApi.createAccount(user_id, user_pw);
        return model;
    }


}
