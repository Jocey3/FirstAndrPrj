package com.example.myfirstandrprj.ui;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.example.myfirstandrprj.R;
import com.example.myfirstandrprj.presenter.CounterPresenter;

public class CounterActivity extends AppCompatActivity implements com.example.myfirstandrprj.ui.View {

    private Button count;
    private TextView amount;
    private TextView textView;
    private CounterPresenter presenter;
    private ProgressBar progressBar;
    private Button toast;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        presenter = new CounterPresenter(getSharedPreferences("settings", Context.MODE_PRIVATE));
        presenter.bind(this);
        progressBar = findViewById(R.id.progress_circular);
        count = findViewById(R.id.butCount);
        toast = findViewById(R.id.butToast);
        amount = findViewById(R.id.textView);
        textView = findViewById(R.id.tv_first_act);
        presenter.getNum();
        findViewById(R.id.butToast).setOnClickListener(v -> {
            startActivity(new Intent("com.example.myfirstandrprj.somename"));
        });
        count.setOnClickListener(v -> {
            makeCount();
            presenter.getNum();
        });

    }

    @Override
    protected void onPause() {
        super.onPause();
        presenter.onPause();
    }

    @Override
    protected void onResume() {
        super.onResume();
        presenter.bind(this);
    }

    public void makeCount() {
        presenter.increaseNum();
    }

    @Override
    public void showNumber(int num) {
        amount.setText(String.valueOf(num));
    }

    @Override
    public void hideProgress() {
        count.setEnabled(true);
        progressBar.setVisibility(View.GONE);
    }

    @Override
    public void showProgress() {
        count.setEnabled(false);
        progressBar.setVisibility(View.VISIBLE);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (data == null) return;
        textView.setText(data.getStringExtra("name"));
    }
}