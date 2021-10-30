package com.example.myfirstandrprj.presenter;

import android.content.SharedPreferences;
import android.util.Log;

import com.example.myfirstandrprj.model.Preference;
import com.example.myfirstandrprj.ui.View;

import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers;
import io.reactivex.rxjava3.disposables.CompositeDisposable;
import io.reactivex.rxjava3.schedulers.Schedulers;

public class CounterPresenter {
    private final Preference preference;
    private View view;
    private final CompositeDisposable disposable = new CompositeDisposable();
    private static final String TAG = "DebagA";

    public CounterPresenter(SharedPreferences settings) {
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

    /*public void test() {
        Observable.fromArray(0, 1, 2, 3, 4, 5)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .map(integer -> {
                    *//*Log.d("DebagTest", String.valueOf(integer));*//*
                    return integer;
                })
                .filter(integer -> integer % 2 == 0)
                .observeOn(Schedulers.io()
                .map(integer -> integer * 2)
                .observeOn(AndroidSchedulers.mainThread())
                .map(integer -> {
//                    Log.d("DebagTest", String.valueOf(integer));
                    return integer;
                })

                .subscribe(o -> {
                    Log.d("DebagTest", String.valueOf(o));

                });

    }
*/
}
