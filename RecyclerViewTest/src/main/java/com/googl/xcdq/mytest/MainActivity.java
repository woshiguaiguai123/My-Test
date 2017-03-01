
package com.googl.xcdq.mytest;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.StaggeredGridLayoutManager;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.GridLayout;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;
import java.util.zip.Inflater;

public class MainActivity extends AppCompatActivity {
    private Toolbar mToolbar;
    private List<String> data;
    private RecyclerView mRecyclerView;
    private Myadapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initToolBar();
        initData();
        initRecyclerView();
    }

    public void initData() {
        data = new ArrayList<>();
        for (int i = 0; i < 100; i++) {
            data.add("Item" + i);
        }
    }

    private void initRecyclerView() {
        //1.实例化RecyclerView
        mRecyclerView = (RecyclerView) findViewById(R.id.recycierview);
        RecyclerView.LayoutManager lm = new LinearLayoutManager(this);
//      2 为RecyclerView创建布局管理器，这里使用的是LinearLayoutManager，表示里面的Item排列是线性排列

//      其中第二个参数spanCount表示表格的行数或者列数；第三个参数表示是水平滑动或者是竖直方向滑动；最后一个参数表示是否从数据的尾部开始显示。
//      mRecyclerView.setLayoutManager(new GridLayoutManager(this,6, GridLayout.VERTICAL,false));

//      构造器中，第一个参数表示列数或者行数，第二个参数表示滑动方向
//        mRecyclerView.setLayoutManager(new StaggeredGridLayoutManager(5, StaggeredGridLayoutManager.VERTICAL));
        //3 设置数据适配器
        mRecyclerView.setLayoutManager(lm);
        mRecyclerView.addItemDecoration(new DividerItemDecoration(this,DividerItemDecoration.VERTICAL_LIST));
        adapter = new Myadapter(data);
        adapter.setmOnItemClickListener(new Myadapter.OnItemClickListener() {
            @Override
            public void onItemClick(View view, int position) {
                Toast.makeText(MainActivity.this, "click" + data.get(position), Toast.LENGTH_SHORT).show();
            }
        });
        adapter.setmOnItemLongClickListener(new Myadapter.OnItemLongClickListener() {
            @Override
            public void onItemLongClick(View view, int position) {
                adapter.revomeDatas(position);
            }
        });

        mRecyclerView.setAdapter(adapter);
    }

    public void initToolBar() {
        mToolbar = (Toolbar) findViewById(R.id.toolbar);

        mToolbar.setTitle("");
        //设置导航图标、添加菜单点击事件要在setSupportActionBar方法之后
        setSupportActionBar(mToolbar);
        mToolbar.setNavigationIcon(R.mipmap.ic_launcher);
        mToolbar.setOnMenuItemClickListener(new Toolbar.OnMenuItemClickListener() {
            @Override
            public boolean onMenuItemClick(MenuItem item) {
                switch (item.getItemId()) {
                    case R.id.chat:
                        Toast.makeText(MainActivity.this, "chat", Toast.LENGTH_SHORT).show();
                        break;
                    case R.id.reminder:
                        adapter.addDatas(1);
                        Toast.makeText(MainActivity.this, "reminder", Toast.LENGTH_SHORT).show();
                        break;
                    case R.id.on:
                        adapter.chageDatas(2);
                        Toast.makeText(MainActivity.this, "on", Toast.LENGTH_SHORT).show();
                        break;
                }
                return true;
            }
        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_toolbar, menu);
        return true;
    }
}
