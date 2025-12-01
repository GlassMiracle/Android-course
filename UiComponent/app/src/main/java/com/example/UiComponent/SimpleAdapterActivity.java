package com.example.UiComponent;

import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ListView;
import android.widget.SimpleAdapter;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class SimpleAdapterActivity extends AppCompatActivity {
    ListView simpleListView;

    // 动物名称数组
    String[] animalName = {"Lion", "Tiger", "Monkey", "Dog", "Cat", "Elephant"};
    // 动物图像数组
    int[] animalImages = {R.drawable.lion, R.drawable.tiger, R.drawable.monkey, R.drawable.dog, R.drawable.cat, R.drawable.elephant};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // 启用边到边显示模式
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_simple_adapter);

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        if (getSupportActionBar() != null) {
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        }

        simpleListView = findViewById(R.id.simpleListView);
        if (simpleListView == null) {
            throw new IllegalStateException("布局中未找到 ListView");
        }

        List<Map<String, String>> dataList = new ArrayList<>();
        for (int i = 0; i < animalName.length; i++) {
            Map<String, String> dataMap = new HashMap<>();
            dataMap.put("name", animalName[i]);
            dataMap.put("image", String.valueOf(animalImages[i]));
            dataList.add(dataMap);
        }
        String[] from = {"name", "image"};
        int[] to = {R.id.textView, R.id.imageView};

        SimpleAdapter simpleAdapter = new SimpleAdapter(this, dataList,
                R.layout.list_view_items, from, to);
        simpleListView.setAdapter(simpleAdapter);

        simpleListView.setOnItemClickListener((parent, view, position, id) ->
                Toast.makeText(getApplicationContext(), animalName[position],
                        Toast.LENGTH_SHORT).show()
        );
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();

        // 处理返回按钮点击
        if (id == android.R.id.home) {
            finish(); // 关闭当前 Activity，返回上一级
            return true;
        }

        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}