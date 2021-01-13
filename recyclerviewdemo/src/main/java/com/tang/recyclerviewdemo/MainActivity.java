package com.tang.recyclerviewdemo;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;

import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import com.google.android.material.snackbar.Snackbar;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    List<String> mData = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        initData();

        RecyclerView rv = findViewById(R.id.rv);
        final MyAdapter adapter = new MyAdapter(mData);
        adapter.setOnItemClickListener(new OnItemClickListener() {
            @Override
            public void onClick(View view, int position) {
                Toast.makeText(MainActivity.this, mData.get(position), Toast.LENGTH_SHORT).show();
            }
        });
        adapter.setOnLongClickListener(new OnItemLongClickListener() {
            @Override
            public void onLongClick(View view, final int position) {
                Snackbar snackbar = Snackbar.make(view, "确定要删除吗？", Snackbar.LENGTH_LONG);
                snackbar.setAction("确定", new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        mData.remove(position);
                        adapter.notifyDataSetChanged();
                    }
                });
                snackbar.show();
            }
        });
        LinearDecoration divider = new LinearDecoration(this, RecyclerView.VERTICAL);
        divider.setDivider(getDrawable(R.drawable.shape_divider));
        divider.setWeight(20);
        rv.addItemDecoration(divider);
        rv.setAdapter(adapter);
    }

    private void initData() {
        for (int i = 0; i < 100; i++) {
            mData.add(i + "");
        }
    }
}
