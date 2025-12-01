package com.example.UiComponent;

import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.TextView;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class XmlMenuActivity extends AppCompatActivity {
    private TextView testText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_xml_menu);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        if (getSupportActionBar() != null) {
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        }

        testText = findViewById(R.id.test_text);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_xml_menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        if (id == android.R.id.home) {
            finish();
            return true;
        }
        if (id == R.id.menu_font_small) {
            testText.setTextSize(10);
            return true;
        } else if (id == R.id.menu_font_medium) {
            testText.setTextSize(16);
            return true;
        } else if (id == R.id.menu_font_large) {
            testText.setTextSize(20);
            return true;
        } else if (id == R.id.menu_normal) {
            Toast.makeText(this, "普通菜单项被点击", Toast.LENGTH_SHORT).show();
            return true;
        } else if (id == R.id.menu_color_red) {
            testText.setTextColor(ContextCompat.getColor(this, android.R.color.holo_red_dark));
            return true;
        } else if (id == R.id.menu_color_black) {
            testText.setTextColor(ContextCompat.getColor(this, android.R.color.black));
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

}