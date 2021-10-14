package com.example.myfirstandrprj.presenter;

import android.content.Context;

import com.example.myfirstandrprj.model.Preference;
import com.example.myfirstandrprj.ui.Activity;
import com.example.myfirstandrprj.ui.View;

public class Presenter {
    private final Preference preference;
    private final View view;
    private final Activity context;

    public Presenter(Activity context) {

        preference = new Preference(context.getSharedPreferences("settings", Context.MODE_PRIVATE));
        view = context;
        this.context = context;
    }


    public void increaseNum() {
        preference.increaseNum();
    }

    public void getNum() {
        view.showProgress();
        new Thread(() -> {
            int num = preference.getNum();
            context.runOnUiThread(() -> {
                view.showNumber(num);
                view.hideProgress();
            });

        }).start();
    }
}
