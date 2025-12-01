package com.example.UiComponent;

import android.content.Context;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AbsListView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.view.ActionMode;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Set;

public class ActionContextMenuActivity extends AppCompatActivity {
    private ListView listView;
    private ActionMode actionMode;
    private List<String> dataList = new ArrayList<>();
    private SelectionAdapter adapter;  // 替换原ArrayAdapter为自定义多选适配器

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_action_context_menu);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        if (getSupportActionBar() != null) {
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        }

        initData();

        // 获取ListView并设置适配器
        listView = findViewById(R.id.action_context_list);
        // 使用自定义SelectionAdapter替换原ArrayAdapter
        adapter = new SelectionAdapter(this, R.layout.row_list_item, R.id.textView, dataList);
        listView.setAdapter(adapter);

        // 设置多选模式
        listView.setChoiceMode(ListView.CHOICE_MODE_MULTIPLE_MODAL);
        // 设置多选上下文监听器
        listView.setMultiChoiceModeListener(new AbsListView.MultiChoiceModeListener() {
            private int selectedCount = 0;

            @Override
            public void onItemCheckedStateChanged(android.view.ActionMode mode, int position, long id, boolean checked) {
                if (checked) {
                    selectedCount++;
                    adapter.setNewSelection(position, true);
                } else {
                    selectedCount--;
                    adapter.removeSelection(position);
                }
                mode.setTitle(selectedCount + " selected");  // 显示选中数量
            }

            @Override
            public boolean onCreateActionMode(android.view.ActionMode mode, Menu menu) {
                mode.getMenuInflater().inflate(R.menu.context_menu, menu);  // 加载上下文菜单
                selectedCount = 0;
                return true;
            }

            @Override
            public boolean onPrepareActionMode(android.view.ActionMode mode, Menu menu) {
                return false;
            }

            @Override
            public boolean onActionItemClicked(android.view.ActionMode mode, MenuItem item) {
                int id = item.getItemId();
                if (id == R.id.action_delete) {
                    // 批量删除选中项（按倒序删除避免索引错乱）
                    Set<Integer> selectedPositions = adapter.getCurrentCheckedPosition();
                    List<Integer> positions = new ArrayList<>(selectedPositions);
                    Collections.sort(positions, Collections.reverseOrder());
                    for (int pos : positions) {
                        dataList.remove(pos);
                    }
                    adapter.notifyDataSetChanged();
                    adapter.clearSelection();
                    mode.finish();
                    return true;
                } else if (id == R.id.action_edit) {
                    // 编辑选中项（示例取第一个选中项）
                    Set<Integer> selectedPositions = adapter.getCurrentCheckedPosition();
                    if (!selectedPositions.isEmpty()) {
                        int firstPos = new ArrayList<>(selectedPositions).get(0);
                        Toast.makeText(ActionContextMenuActivity.this,
                                "编辑: " + dataList.get(firstPos),
                                Toast.LENGTH_SHORT).show();
                    }
                    mode.finish();
                    return true;
                }
                return false;
            }

            @Override
            public void onDestroyActionMode(android.view.ActionMode mode) {
                adapter.clearSelection();  // 清除所有选中状态
            }
        });

        // 长按列表项切换选中状态
        listView.setOnItemLongClickListener((parent, view, position, id) -> {
            listView.setItemChecked(position, !adapter.isPositionChecked(position));
            return false;
        });

        // 添加普通点击事件处理
        listView.setOnItemClickListener((parent, view, position, id) -> {
            // 如果当前处于多选模式，则切换选中状态
            if (listView.getChoiceMode() == ListView.CHOICE_MODE_MULTIPLE_MODAL &&
                    listView.getCheckedItemCount() > 0) {
                listView.setItemChecked(position, !adapter.isPositionChecked(position));
            } else {
                // 否则处理普通点击事件
                Toast.makeText(ActionContextMenuActivity.this,
                        "点击了: " + dataList.get(position),
                        Toast.LENGTH_SHORT).show();
            }
        });
    }

    // 自定义适配器，管理多选状态
    private class SelectionAdapter extends ArrayAdapter<String> {
        private HashMap<Integer, Boolean> mSelection = new HashMap<>();

        public SelectionAdapter(Context context, int resource, int textViewResourceId, List<String> objects) {
            super(context, resource, textViewResourceId, objects);
        }

        // 设置选中状态
        public void setNewSelection(int position, boolean value) {
            mSelection.put(position, value);
            notifyDataSetChanged();
        }

        // 检查位置是否选中
        public boolean isPositionChecked(int position) {
            Boolean result = mSelection.get(position);
            return result != null && result;
        }

        // 获取所有选中位置
        public Set<Integer> getCurrentCheckedPosition() {
            return mSelection.keySet();
        }

        // 移除单个选中位置
        public void removeSelection(int position) {
            mSelection.remove(position);
            notifyDataSetChanged();
        }

        // 清除所有选中状态
        public void clearSelection() {
            mSelection.clear();
            notifyDataSetChanged();
        }

        // 自定义列表项视图，高亮选中项
        @Override
        public View getView(int position, View convertView, ViewGroup parent) {
            View v = super.getView(position, convertView, parent);
            // 默认背景色
            v.setBackgroundColor(getResources().getColor(android.R.color.background_light));
            // 选中项高亮
            if (isPositionChecked(position)) {
                v.setBackgroundColor(getResources().getColor(android.R.color.holo_blue_light));
            }
            return v;
        }
    }

    private void initData() {
        dataList.add("121052023057");
        dataList.add("列表项 1");
        dataList.add("列表项 2");
        dataList.add("列表项 3");
        dataList.add("列表项 4");
        dataList.add("列表项 5");
        dataList.add("列表项 6");
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
