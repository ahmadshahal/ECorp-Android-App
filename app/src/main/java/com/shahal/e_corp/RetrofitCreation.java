package com.shahal.e_corp;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class RetrofitCreation
{
    private static final String BASE_URL = "http://51.195.90.233/api/";
    private static Retrofit retrofit;
    public static Retrofit getRetrofitInstance()
    {
        if(retrofit == null)
        {
            retrofit = new retrofit2.Retrofit.Builder()
                    .baseUrl(BASE_URL)
                    .addConverterFactory(GsonConverterFactory.create())
                    .build();
        }
        return retrofit;
    }
}
