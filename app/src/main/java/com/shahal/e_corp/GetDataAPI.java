package com.shahal.e_corp;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;

public interface GetDataAPI
{
    @GET("getFilesList")
    public Call<List<ECorpFile>> getAllFiles();
}
