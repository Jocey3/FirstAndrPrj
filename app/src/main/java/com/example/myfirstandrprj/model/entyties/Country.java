package com.example.myfirstandrprj.model.entyties;

import androidx.annotation.Nullable;

import com.google.gson.annotations.SerializedName;

public class Country {
    @SerializedName("country_id")
    private String id;
    @SerializedName("probability")
    private Double probability; //обгортка бо сервер може вернуть нал

    @Nullable   //попередження про нал
    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    @Nullable
    public Double getProbability() {
        return probability;
    }

    public void setProbability(Double probability) {
        this.probability = probability;
    }
}
