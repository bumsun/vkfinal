package com.example.myapplication.activity;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import com.example.myapplication.R;
import com.example.myapplication.utils.MockData;
import com.example.myapplication.utils.PostsAdapter;
import com.example.myapplication.utils.VkClusterItem;

import java.util.ArrayList;

public class FeedActivity extends BaseActivity {

    private ListView postsLV;
    private String category;
    private ArrayList<VkClusterItem> localItems;
    private TextView titleTV;
    private String categoryRus;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_feed);

        category = getIntent().getExtras().getString("category");
        categoryRus = getIntent().getExtras().getString("categoryRus");
        Log.d("myLogs","category = " + category);
        Log.d("myLogs","MockData.getItems().size = " + MockData.getItems().size());
        postsLV = findViewById(R.id.postsLV);
        titleTV = findViewById(R.id.titleTV);

        categoryRus = categoryRus.substring(0, 1).toUpperCase() + categoryRus.substring(1).toLowerCase();
        titleTV.setText(categoryRus);
        if(categoryRus.equals("It")){
            titleTV.setText("Информационные технологии");
        }
        localItems = new ArrayList<VkClusterItem>();
        for(VkClusterItem item: MockData.getItems()){
            Log.d("myLogs","item.getPhotoUrl() = " + item.getPhotoUrl());
            if(item.getPhotoUrl().contains("/"+category)){
                Log.d("myLogs","contains = " + category);
                localItems.add(item);
            }
        }
        PostsAdapter postsAdapter = new PostsAdapter(getActivity(),localItems);
        postsLV.setAdapter(postsAdapter);
    }
}