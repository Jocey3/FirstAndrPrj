package com.example.myfirstandrprj.model;

import io.reactivex.rxjava3.core.Single;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava3.RxJava3CallAdapterFactory;

public class Nationalizer {
    private Api api;

    public Nationalizer() {
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("https://api.nationalize.io/")
                .addCallAdapterFactory(RxJava3CallAdapterFactory.create())
                .build();
//                .addConverterFactory(GsonConverterFactory.create())

        api = retrofit.create(Api.class);

    }

    public Single<String> getNation(String name) {
        return api.getNation(name).map(responseBody -> responseBody.string());
    }
}
