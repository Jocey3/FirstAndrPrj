package com.example.myfirstandrprj.ui;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.myfirstandrprj.R;
import com.example.myfirstandrprj.presenter.Presenter;

public class Activity extends AppCompatActivity implements com.example.myfirstandrprj.ui.View {

    private Button count;
    private TextView amount;
    private Presenter presenter;
    private ProgressBar progressBar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        presenter = new Presenter(this);
        progressBar = findViewById(R.id.progress_circular);
        count = findViewById(R.id.butCount);
        amount = findViewById(R.id.textView);
        presenter.getNum();
        findViewById(R.id.butToast).setOnClickListener(v -> {
            Toast toast = Toast.makeText(this, R.string.toastMassage, Toast.LENGTH_LONG);
            toast.show();
        });
        count.setOnClickListener(v -> {
            makeCount();
            presenter.getNum();
        });
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
}