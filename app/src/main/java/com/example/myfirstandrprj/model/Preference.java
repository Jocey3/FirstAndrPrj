package com.example.myfirstandrprj.model;

import android.content.SharedPreferences;

import static android.content.SharedPreferences.Editor;


public class Preference {

    private static final String SAVED_TEXT = "num";
    private final SharedPreferences settings;

    public Preference(SharedPreferences settings) {
        this.settings = settings;
    }

    public void increaseNum() {
        int num = settings.getInt(SAVED_TEXT, 0);

        Editor ed = settings.edit();
        ed.putInt(SAVED_TEXT, ++num);
        ed.apply();
    }

    public int getNum() {
        return settings.getInt(SAVED_TEXT, 0);
    }

}
