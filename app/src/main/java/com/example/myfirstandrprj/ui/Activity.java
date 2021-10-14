package com.example.myfirstandrprj.ui;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.myfirstandrprj.R;
import com.example.myfirstandrprj.presenter.Presenter;

public class Activity extends AppCompatActivity implements View.OnClickListener, com.example.myfirstandrprj.ui.View {

    private Button toast;
    private Button count;
    private TextView amount;
    private Presenter presenter;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        presenter = new Presenter(this);
        toast = findViewById(R.id.butToast);
        count = findViewById(R.id.butCount);
        amount = findViewById(R.id.textView);
        presenter.getNum();
        toast.setOnClickListener(this);
        count.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.butToast:
                Toast toast = Toast.makeText(this, R.string.toastMassage, Toast.LENGTH_LONG);
                toast.show();
                break;
            case R.id.butCount:
                makeCount();
                presenter.getNum();
                break;
        }
    }

    public void makeCount() {
        presenter.increaseNum();

    }

    @Override
    public void showNumber(int num) {
        amount.setText(String.valueOf(num));
    }
}