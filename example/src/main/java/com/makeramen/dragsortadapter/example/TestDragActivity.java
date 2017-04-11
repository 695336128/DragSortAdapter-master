package com.makeramen.dragsortadapter.example;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.StaggeredGridLayoutManager;
import android.view.Menu;
import android.view.MenuItem;

import java.util.ArrayList;
import java.util.List;

import static android.R.id.list;

public class TestDragActivity extends AppCompatActivity {

    private RecyclerView mRecyclerView;

    private TestDragAdapter mAdapter;

    List<Integer> data = new ArrayList<>(100);

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_test_drag);
        initView();
    }

    private void initView() {
        mRecyclerView = (RecyclerView) findViewById(list);

        for (int i = 1; i < 100 + 1; i++) {
            data.add(i);
        }

        mRecyclerView.setLayoutManager(new LinearLayoutManager(this));
        mRecyclerView.setItemAnimator(new DefaultItemAnimator());
        mAdapter = new TestDragAdapter(mRecyclerView,data);
        mRecyclerView.setAdapter(mAdapter);

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        super.onCreateOptionsMenu(menu);
        getMenuInflater().inflate(R.menu.example,menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()){
            case R.id.action_layout_linear:
                item.setChecked(true);
                mRecyclerView.setLayoutManager(new LinearLayoutManager(this));
                break;
            case R.id.action_layout_grid:
                item.setChecked(true);
                mRecyclerView.setLayoutManager(new GridLayoutManager(this,3));
                break;
            case R.id.action_layout_staggered:
                item.setChecked(true);
                mRecyclerView.setLayoutManager(new StaggeredGridLayoutManager(3,StaggeredGridLayoutManager.VERTICAL));
                break;
        }
        return super.onOptionsItemSelected(item);
    }
}
