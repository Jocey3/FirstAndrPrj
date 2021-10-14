package com.example.myfirstandrprj.presenter;

import android.content.Context;

import com.example.myfirstandrprj.model.Preference;
import com.example.myfirstandrprj.ui.View;

public class Presenter {
    private final Preference preference;
    private final View view;

    public Presenter(Context context) {

        preference = new Preference(context.getSharedPreferences("settings", Context.MODE_PRIVATE));
        view = (View) context;
    }


    public void increaseNum() {
        preference.increaseNum();
    }

    public void getNum() {
        view.showNumber(preference.getNum());
    }
}
