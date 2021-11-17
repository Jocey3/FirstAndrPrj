package com.example.myfirstandrprj.widgets;

import android.content.Context;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.AttributeSet;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import io.reactivex.rxjava3.core.Emitter;
import io.reactivex.rxjava3.core.Observable;
import io.reactivex.rxjava3.subjects.BehaviorSubject;

public class SearchWidget extends androidx.appcompat.widget.AppCompatEditText {
    private BehaviorSubject<String> publishSubject = BehaviorSubject.createDefault("");
    Emitter<String> emitter;
    private Observable<String> observable = Observable.create(emitter -> {
        this.emitter = emitter;
    });

    public SearchWidget(@NonNull Context context) {
        this(context, null);
    }

    public SearchWidget(@NonNull Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                publishSubject.onNext(s.toString());//лучше, бо не костиль
                emitter.onNext(s.toString());// костиль
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });

    }

    public Observable<String> getObservable() {
        return observable;
    }
}
