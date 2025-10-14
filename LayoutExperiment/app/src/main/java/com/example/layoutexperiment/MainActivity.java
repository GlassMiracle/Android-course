package com.example.layoutexperiment;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    private Button btnLinearLayout, btnTableLayout, btnConstraintLayout1, btnConstraintLayout2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        initViews();
        setListeners();
    }

    private void initViews() {
        btnLinearLayout = findViewById(R.id.btn_linear_layout);
        btnTableLayout = findViewById(R.id.btn_table_layout);
        btnConstraintLayout1 = findViewById(R.id.btn_constraint_layout1);
        btnConstraintLayout2 = findViewById(R.id.btn_constraint_layout2);
    }

    private void setListeners() {
        btnLinearLayout.setOnClickListener(this);
        btnTableLayout.setOnClickListener(this);
        btnConstraintLayout1.setOnClickListener(this);
        btnConstraintLayout2.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        Intent intent;

        if (v.getId() == R.id.btn_linear_layout) {
            intent = new Intent(MainActivity.this, LinearLayoutActivity.class);
        } else if (v.getId() == R.id.btn_table_layout) {
            intent = new Intent(this, TableLayoutActivity.class);
        } else if (v.getId() == R.id.btn_constraint_layout1) {
            intent = new Intent(this, ConstraintLayoutActivity.class);
            intent.putExtra("layout_type", 1);
        } else if (v.getId() == R.id.btn_constraint_layout2) {
            intent = new Intent(this, ConstraintLayoutActivity.class);
            intent.putExtra("layout_type", 2);
        } else {
            return;
        }

        startActivity(intent);
    }
}