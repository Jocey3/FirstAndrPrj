package com.example.myfirstandrprj.model;

import android.content.SharedPreferences;
import android.util.Log;

import io.reactivex.rxjava3.core.Single;

import static android.content.SharedPreferences.Editor;


public class Preference {

    private static final String SAVED_TEXT = "num";
    private final SharedPreferences settings;
    private static final String TAG = "DebagA";

    public Preference(SharedPreferences settings) {
        this.settings = settings;
    }

    public void increaseNum() {
        int num = settings.getInt(SAVED_TEXT, 0);

        Editor ed = settings.edit();
        ed.putInt(SAVED_TEXT, ++num);
        ed.apply();
    }

    public Single<Integer> getNum() {
        Log.d(TAG, Thread.currentThread().getName() + 3);
        return Single.fromCallable(() -> {
            //Server delay
            Thread.sleep(5000);
            Log.d(TAG, Thread.currentThread().getName() + 4);
            return settings.getInt(SAVED_TEXT, 0);
        });
    }
}
