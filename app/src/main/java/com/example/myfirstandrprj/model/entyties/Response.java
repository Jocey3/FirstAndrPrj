package com.example.myfirstandrprj.model.entyties;

import com.google.gson.annotations.SerializedName;

import java.util.List;

public class Response {
    @SerializedName("name")
    private String name;
    @SerializedName("country")
    private List<Country> countries;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<Country> getCountries() {
        return countries;
    }

    public void setCountries(List<Country> countries) {
        this.countries = countries;
    }
}
