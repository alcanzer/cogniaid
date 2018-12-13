package com.cogniaid.cogniaid.Client;

import com.google.gson.Gson;

import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;
import retrofit2.converter.scalars.ScalarsConverterFactory;

/**
 * Created by alcanzer on 12/5/18.
 */

public class RetroClient {
    //private static final String BASE_URL = "https://cogniwebhook.mybluemix.net";

    private static Retrofit retrofit = null;
    public static Retrofit getClient(){
        if(retrofit == null){
            retrofit = new Retrofit.Builder()
                                    .addConverterFactory(GsonConverterFactory.create())
                                    .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                                    .addConverterFactory(ScalarsConverterFactory.create())
                                    .baseUrl("http://www.google.com")
                                    .build();
        }
        return retrofit;
    }
}
