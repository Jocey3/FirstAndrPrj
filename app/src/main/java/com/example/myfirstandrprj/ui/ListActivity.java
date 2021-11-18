package com.example.myfirstandrprj.ui;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.myfirstandrprj.R;
import com.example.myfirstandrprj.utils.Consumer;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class ListActivity extends AppCompatActivity {
    RecyclerView recycler;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list);

        recycler = findViewById(R.id.recycler);
        recycler.setLayoutManager(new LinearLayoutManager(this, RecyclerView.VERTICAL, true)); //відображення вью в ресайклері.. реверс знизу вверх і навпаки
        Adapter adapter = new Adapter();
        recycler.setAdapter(adapter);
        List<Integer> data = new ArrayList<>();
        Random random = new Random();
        for (int i = 0; i < 200; i++) {
            data.add(random.nextInt());
        }
        adapter.setAll(data);
        adapter.setOnClickListener(integer -> Toast.makeText(this, String.valueOf(integer), Toast.LENGTH_LONG).show());//показ актівіті

    }


    static class Adapter extends RecyclerView.Adapter<VH> {
        private final List<Integer> data = new ArrayList<>();
        private Consumer<Integer> clickListener;

        @NonNull
        @Override
        public VH onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
            Log.d("myLog", "onCreate");
            LayoutInflater inflater = LayoutInflater.from(parent.getContext());
            return new VH(parent, inflater);// інфлатер нада шоб перевести хмл в джаву
        }

        public void setOnClickListener(Consumer<Integer> consumer) {
            clickListener = consumer;
        }

        @Override
        public void onBindViewHolder(@NonNull VH holder, int position) {
            final Integer integer = data.get(position);
            Log.d("myLog", "onBind " + integer + " pos: " + position);
            holder.bind(integer);
            holder.itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {

                    clickListener.apply(integer);
                }
            });

        }

        @Override
        public int getItemCount() {
            return data.size();
        }

        public void setAll(List<Integer> list) {
            data.clear();
            data.addAll(list);
            notifyDataSetChanged();
        }
    }

    static class VH extends RecyclerView.ViewHolder {
        private Integer data;

        public VH(@NonNull ViewGroup group, LayoutInflater inflater) {
            super(inflater.inflate(R.layout.item, group, false));
        }

        public void bind(Integer data) {
            Log.d("myLog", "Bind " + data + " old data " + this.data);
            this.data = data;

            ((TextView) itemView.findViewById(R.id.tv_card)).setText(String.valueOf(data));
        }
    }
}
