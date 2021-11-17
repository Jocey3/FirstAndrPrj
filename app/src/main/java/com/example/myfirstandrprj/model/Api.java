package com.example.myfirstandrprj.model;

import com.example.myfirstandrprj.model.entyties.Response;

import io.reactivex.rxjava3.core.Single;
import retrofit2.http.GET;
import retrofit2.http.Query;


public interface Api {
    @GET("/")
    Single<Response> getNation(@Query("name") String name);
}
