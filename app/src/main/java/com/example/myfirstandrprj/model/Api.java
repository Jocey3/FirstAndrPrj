package com.example.myfirstandrprj.model;

import io.reactivex.rxjava3.core.Single;
import okhttp3.ResponseBody;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface Api {
    @GET("/")
    Single<ResponseBody> getNation(@Query("name") String name);
}
