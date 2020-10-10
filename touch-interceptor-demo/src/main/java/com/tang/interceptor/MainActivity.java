package com.tang.interceptor;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.DiffUtil;
import androidx.recyclerview.widget.ListAdapter;
import androidx.recyclerview.widget.RecyclerView;
import androidx.viewpager.widget.ViewPager;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    DiffUtil.ItemCallback callback = new DiffUtil.ItemCallback() {
        @Override
        public boolean areItemsTheSame(@NonNull Object oldItem, @NonNull Object newItem) {
            return false;
        }

        @Override
        public boolean areContentsTheSame(@NonNull Object oldItem, @NonNull Object newItem) {
            return false;
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
//        setContentView(R.layout.activity_main2);
//        setContentView(R.layout.activity_main);
//
//        RecyclerView rvHorizontal = findViewById(R.id.rv_horizontal);
//        ListAdapter horizontalAdapter = new ListAdapter<Integer, RecyclerView.ViewHolder>(callback) {
//            @NonNull
//            @Override
//            public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
//                View itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.view_horizontal_item, parent, false);
//                return new RecyclerView.ViewHolder(itemView) {
//                };
//            }
//
//            @Override
//            public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {
//                RecyclerView rvVertical = holder.itemView.findViewById(R.id.rv_vertical);
//                ListAdapter verticalAdapter = new ListAdapter<String, RecyclerView.ViewHolder>(callback) {
//                    @NonNull
//                    @Override
//                    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
//                        View itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.view_vertical_item, parent, false);
//                        return new RecyclerView.ViewHolder(itemView) {
//                        };
//                    }
//
//                    @Override
//                    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {
//                        TextView textView = holder.itemView.findViewById(R.id.textView);
//                        textView.setText("Hello World");
//                    }
//                };
//                rvVertical.setAdapter(verticalAdapter);
//                List<String> verticalList = new ArrayList<>();
//                for (int i = 0; i < 40; i++) {
//                    verticalList.add("Hello World" + i);
//                }
//                verticalAdapter.submitList(verticalList);
//            }
//        };
//        rvHorizontal.setAdapter(horizontalAdapter);
//        List<Integer> horizontalList = new ArrayList<>();
//        for (int i = 0; i < 10; i++) {
//            horizontalList.add(i);
//        }
//        horizontalAdapter.submitList(horizontalList);


        setContentView(R.layout.activity_main3);

        RecyclerView rv = findViewById(R.id.rv);
        ListAdapter verticalAdapter = new ListAdapter<String, RecyclerView.ViewHolder>(callback) {
            @NonNull
            @Override
            public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
                View itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.view_vertical_item, parent, false);
                return new RecyclerView.ViewHolder(itemView) {
                };
            }

            @Override
            public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {
                TextView textView = holder.itemView.findViewById(R.id.textView);
                textView.setText("Hello World");
            }
        };
        rv.setAdapter(verticalAdapter);
        List<String> verticalList = new ArrayList<>();
        for (int i = 0; i < 40; i++) {
            verticalList.add("Hello World" + i);
        }
        verticalAdapter.submitList(verticalList);
    }

    /*
    public static final int ACTION_DOWN             = 0;
    public static final int ACTION_UP               = 1;
    public static final int ACTION_MOVE             = 2;
    public static final int ACTION_CANCEL           = 3;
     */

//    @Override
//    public boolean dispatchTouchEvent(MotionEvent ev) {
//        Log.d("tang", "MainActivity dispatchTouchEvent: " + ev.getAction());
//        return super.dispatchTouchEvent(ev);
//    }
//
//    @Override
//    public boolean onTouchEvent(MotionEvent event) {
//        Log.d("tang", "MainActivity onTouchEvent: " + event.getAction());
//        return super.onTouchEvent(event);
//    }


}