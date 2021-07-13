package com.example.helloworld;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.graphics.Color;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import com.example.helloworld.recycleview.LinearItemDecoration;
import com.example.helloworld.recycleview.MyAdapter;
import com.example.helloworld.recycleview.WebsiteData;

import java.util.ArrayList;
import java.util.List;

public class WebsiteList extends AppCompatActivity implements MyAdapter.IOnItemClickListener {
    private RecyclerView recyclerView;
    private MyAdapter mAdapter;
    private RecyclerView.LayoutManager layoutManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_website_list);

        View view = findViewById(R.id.weblistLayout);
        view.getBackground().setAlpha(64);

        findViewById(R.id.backFromWeb).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onBackPressed();
            }
        });

        findViewById(R.id.addBtn).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mAdapter.addData(new WebsiteData("百度", "https://www.baidu.com"));
            }
        });

        initView();

        Log.i("life cycle", "WebsiteListActivity onCreate");
    }

    private void initView() {
        //获取实例
        recyclerView = findViewById(R.id.recycler);
        //更改数据时不会变更宽高
        recyclerView.setHasFixedSize(true);
        //创建线性布局管理器
        layoutManager = new LinearLayoutManager(this);
        //设置布局管理器
        recyclerView.setLayoutManager(layoutManager);
        //创建Adapter
        mAdapter = new MyAdapter(getSampleData());
        //设置Adapter每个item的点击事件
        mAdapter.setOnItemClickListener(this);
        //设置Adapter
        recyclerView.setAdapter(mAdapter);
        //分割线
        LinearItemDecoration itemDecoration = new LinearItemDecoration(Color.BLUE);
//        recyclerView.addItemDecoration(itemDecoration);
        recyclerView.addItemDecoration(new DividerItemDecoration(this, LinearLayoutManager.VERTICAL));
        //动画
        DefaultItemAnimator animator = new DefaultItemAnimator();
        animator.setAddDuration(3000);
        recyclerView.setItemAnimator(animator);
    }

    public static List<WebsiteData> getSampleData() {
        List<WebsiteData> sampleData = new ArrayList();
        sampleData.add(new WebsiteData("校网", "https://net2.zju.edu.cn/"));
        sampleData.add(new WebsiteData("教务网", "http://jwbinfosys.zju.edu.cn/"));
        sampleData.add(new WebsiteData("浙大邮箱", "https://mail.zju.edu.cn/"));
        sampleData.add(new WebsiteData("查老师", "https://chalaoshi.24dt.cn/"));
        sampleData.add(new WebsiteData("院网", "http://cspo.zju.edu.cn/"));
        return sampleData;
    }

    @Override
    protected void onStart() {
        super.onStart();
        Log.i("life cycle", "WebsiteListActivity onStart");
    }

    @Override
    protected void onResume() {
        super.onResume();
        Log.i("life cycle", "WebsiteListActivity onResume");
    }

    @Override
    protected void onRestart() {
        super.onRestart();
        Log.i("life cycle", "WebsiteListActivity onRestart");
    }

    @Override
    protected void onPause() {
        super.onPause();
        Log.i("life cycle", "WebsiteListActivity onPause");
    }

    @Override
    protected void onStop() {
        super.onStop();
        Log.i("life cycle", "WebsiteListActivity onStop");
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        Log.i("life cycle", "WebsiteListActivity onDestroy");
    }

    @Override
    public void onItemCLick(int position, WebsiteData data) {
        Intent intent = new Intent(Intent.ACTION_VIEW);
        Uri uri = Uri.parse(data.getUrl());
        intent.setData(uri);
        startActivity(intent);
    }
}