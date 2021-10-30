package com.example.myfirstandrprj.widgets;

import android.content.Context;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.AttributeSet;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import io.reactivex.rxjava3.core.Emitter;
import io.reactivex.rxjava3.core.Observable;
import io.reactivex.rxjava3.subjects.PublishSubject;

public class SearchView extends androidx.appcompat.widget.AppCompatEditText {
    private PublishSubject<String> publishSubject = PublishSubject.create();
    Emitter<String> emitter;
    private Observable<String> observable = Observable.create(emitter -> {
        this.emitter = emitter;
    });

    public SearchView(@NonNull Context context) {
        this(context, null);
    }

    public SearchView(@NonNull Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                publishSubject.onNext(s.toString());
                emitter.onNext(s.toString());
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
