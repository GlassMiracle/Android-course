package com.example.UiComponent;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;


public class MainActivity extends AppCompatActivity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);


        Button button = findViewById(R.id.simple_adapter_btn);
        button.setOnClickListener(v -> {
            Intent intent = new Intent(MainActivity.this, SimpleAdapterActivity.class);
            startActivity(intent);
        });
        Button button2 = findViewById(R.id.custom_dialog);
        button2.setOnClickListener(v -> {
            Intent intent = new Intent(MainActivity.this, AlertDialogActivity.class);
            startActivity(intent);
        });

        Button xmlMenuBtn = findViewById(R.id.xml_menu_btn);
        xmlMenuBtn.setOnClickListener(v -> {
            Intent intent = new Intent(MainActivity.this, XmlMenuActivity.class);
            startActivity(intent);
        });

        Button actionContextMenuBtn = findViewById(R.id.action_context_btn);
        actionContextMenuBtn.setOnClickListener(v -> {
            Intent intent = new Intent(MainActivity.this, ActionContextMenuActivity.class);
            startActivity(intent);
        });

    }

}