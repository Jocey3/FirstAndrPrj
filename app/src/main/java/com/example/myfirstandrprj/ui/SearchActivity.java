package com.example.myfirstandrprj.ui;

import android.app.Activity;
import android.os.Bundle;
import android.widget.TextView;

import androidx.annotation.Nullable;

import com.example.myfirstandrprj.R;
import com.example.myfirstandrprj.presenter.SearchPresenter;
import com.example.myfirstandrprj.widgets.SearchView;

public class SearchActivity extends Activity implements com.example.myfirstandrprj.ui.SearchView {
    private final SearchPresenter presenter = new SearchPresenter();
    private SearchView searchView;
    private TextView textView;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search);
        searchView = findViewById(R.id.search_view);
        textView = findViewById(R.id.some_text);

    }

    @Override
    protected void onResume() {
        super.onResume();
        presenter.bind(this);
        presenter.bindObservable(searchView.getObservable());

    }

    @Override
    protected void onPause() {
        super.onPause();
        presenter.onPause();
    }

    @Override
    public void showJson(String data) {
        textView.setText(data);
    }
}
