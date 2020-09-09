package com.nan.carlight.ui;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.alibaba.fastjson.JSON;
import com.nan.carlight.R;
import com.nan.carlight.adapter.TestAdapter;
import com.nan.carlight.constant.IntentArg;
import com.nan.carlight.model.TestBean;

import java.util.List;

public class CompleteActivity extends AppCompatActivity {

    private RecyclerView mRvList;
    private List<TestBean> mTestList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_complete);
        mRvList = findViewById(R.id.rv_list);
        mTestList = JSON.parseArray(getIntent().getStringExtra(IntentArg.TEST_LIST), TestBean.class);
        mRvList.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false));
        mRvList.setAdapter(new TestAdapter(this, mTestList, false));
    }
}