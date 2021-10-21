package com.example.myfirstandrprj.presenter;

import android.content.SharedPreferences;
import android.util.Log;

import com.example.myfirstandrprj.model.Preference;
import com.example.myfirstandrprj.ui.View;

import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers;
import io.reactivex.rxjava3.disposables.CompositeDisposable;
import io.reactivex.rxjava3.schedulers.Schedulers;

public class Presenter {
    private final Preference preference;
    private View view;
    private final CompositeDisposable disposable = new CompositeDisposable();
    private static final String TAG = "DebagA";

    public Presenter(SharedPreferences settings) {
        preference = new Preference(settings);

    }

    public void onPause() {
        if (!disposable.isDisposed()) disposable.dispose();
        view = null;
    }

    public void bind(View view) {
        this.view = view;
    }

    public void increaseNum() {
        preference.increaseNum();
    }

    public void getNum() {
        disposable.add(preference.getNum()
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeOn(Schedulers.io())
                .doOnSubscribe(disposable2 -> {
                    Log.d(TAG, Thread.currentThread().getName() + " " + 1);
                    view.showProgress();
                })
                .doAfterTerminate(() -> {
                    Log.d(TAG, Thread.currentThread().getName() + 2);
                    view.hideProgress();
                })
                .subscribe(view::showNumber, throwable -> {
                    Log.e(TAG, String.valueOf(throwable));
                }));


    }
}
