package com.example.layoutexperiment;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;

public class ConstraintLayoutActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        // 根据传入的参数决定加载哪个布局
        Bundle extras = getIntent().getExtras();
        if (extras != null && extras.getInt("layout_type") == 2) {
            setContentView(R.layout.activity_constraint_layout2);
        } else {
            setContentView(R.layout.activity_constraint_layout1);
        }
    }
}