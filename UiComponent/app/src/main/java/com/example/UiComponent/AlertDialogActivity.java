package com.example.UiComponent;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class AlertDialogActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_alert_dialog);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        if (getSupportActionBar() != null) {
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        }

        Button showAlertDialogBtn = findViewById(R.id.alert_dialog_btn);
        showAlertDialogBtn.setOnClickListener(v -> showCustomDialog());
    }

    private void showCustomDialog() {
        // 加载自定义布局
        View dialogView = LayoutInflater.from(this).inflate(R.layout.dialog_login, null);

        // 创建AlertDialog构建器
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setView(dialogView); // 设置自定义布局

        // 获取对话框中的按钮
        Button cancelButton = dialogView.findViewById(R.id.cancelButton);
        Button signInButton = dialogView.findViewById(R.id.signInButton);

        // 创建对话框
        AlertDialog dialog = builder.create();

        // 设置取消按钮点击事件
        cancelButton.setOnClickListener(v -> dialog.dismiss());

        // 设置登录按钮点击事件
        signInButton.setOnClickListener(v -> {
            Toast.makeText(AlertDialogActivity.this, "登录按钮被点击", Toast.LENGTH_SHORT).show();
            dialog.dismiss();
        });

        // 显示对话框
        dialog.show();
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == android.R.id.home) {
            finish(); // 关闭当前Activity，返回上一级
            return true;
        }
        return super.onOptionsItemSelected(item);
    }
}