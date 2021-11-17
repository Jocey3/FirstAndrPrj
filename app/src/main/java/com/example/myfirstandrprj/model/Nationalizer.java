package com.example.myfirstandrprj.model;

import com.example.myfirstandrprj.model.entyties.Country;
import com.example.myfirstandrprj.model.entyties.Response;

import java.util.List;

import io.reactivex.rxjava3.core.Single;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava3.RxJava3CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

public class Nationalizer {
    private Api api;

    public Nationalizer() {
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("https://api.nationalize.io")
                .addCallAdapterFactory(RxJava3CallAdapterFactory.create())
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        api = retrofit.create(Api.class);

    }

    public Single<List<Country>> getNation(String name) {
        return api.getNation(name).map(Response::getCountries);
    }
}
