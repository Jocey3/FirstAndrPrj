package com.example.myfirstandrprj.presenter;

import com.example.myfirstandrprj.model.Nationalizer;
import com.example.myfirstandrprj.ui.SearchView;

import java.util.concurrent.TimeUnit;

import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers;
import io.reactivex.rxjava3.core.Observable;
import io.reactivex.rxjava3.disposables.CompositeDisposable;
import io.reactivex.rxjava3.schedulers.Schedulers;

public class SearchPresenter {
    private SearchView view;
    private final CompositeDisposable disposable = new CompositeDisposable();
    private final Nationalizer nationalizer = new Nationalizer();


    public void onPause() {
        if (!disposable.isDisposed()) disposable.dispose();
        view = null;
    }

    public void bind(SearchView view) {
        this.view = view;
    }

    public void bindObservable(Observable<String> observable) {
        disposable.add(observable
                .debounce(500, TimeUnit.MILLISECONDS)
                .observeOn(Schedulers.io())
                .flatMapSingle(nationalizer::getNation)
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(view::showJson)


        );
    }
}
