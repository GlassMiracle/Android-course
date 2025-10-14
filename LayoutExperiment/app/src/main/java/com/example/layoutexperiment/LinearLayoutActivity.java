package com.example.layoutexperiment;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;

public class LinearLayoutActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_linear_layout);

        // 初始化视图
        initViews();
    }

    private void initViews() {
    }

}