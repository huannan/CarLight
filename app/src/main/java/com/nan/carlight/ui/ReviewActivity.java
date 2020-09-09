package com.nan.carlight.ui;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.nan.carlight.R;
import com.nan.carlight.adapter.TestAdapter;
import com.nan.carlight.constant.Answer;
import com.nan.carlight.model.TestBean;

import java.util.ArrayList;
import java.util.List;

public class ReviewActivity extends AppCompatActivity {

    private RecyclerView mRvList;
    private List<TestBean> mTestList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_review);
        mRvList = findViewById(R.id.rv_list);
        loadTest();
        mRvList.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false));
        mRvList.setAdapter(new TestAdapter(this, mTestList, true));
    }

    private void loadTest() {
        mTestList = new ArrayList<>();
        mTestList.add(new TestBean("将要进行夜间行驶，请打开前照灯", Answer.DIPPED_HEADLIGHT));
        mTestList.add(new TestBean("夜间通过急弯", Answer.DIPPED_HEADLIGHT_AND_HIGH_BEAM));
        mTestList.add(new TestBean("夜间通过坡路", Answer.DIPPED_HEADLIGHT_AND_HIGH_BEAM));
        mTestList.add(new TestBean("夜间通过拱桥", Answer.DIPPED_HEADLIGHT_AND_HIGH_BEAM));
        mTestList.add(new TestBean("夜间通过人行横道", Answer.DIPPED_HEADLIGHT_AND_HIGH_BEAM));
        mTestList.add(new TestBean("进入照明良好道路", Answer.DIPPED_HEADLIGHT));
        mTestList.add(new TestBean("进入照明不良道路", Answer.HIGH_BEAM));
        mTestList.add(new TestBean("夜间在路边临时停车", Answer.CLEARANCE_LAMP));
        mTestList.add(new TestBean("夜间与机动车会车", Answer.DIPPED_HEADLIGHT));
        mTestList.add(new TestBean("夜间超越前方车辆", Answer.DIPPED_HEADLIGHT_AND_HIGH_BEAM));
        mTestList.add(new TestBean("夜间通过路口", Answer.DIPPED_HEADLIGHT));
        mTestList.add(new TestBean("同方向近距离跟车行驶", Answer.DIPPED_HEADLIGHT));
        mTestList.add(new TestBean("夜间经过有信号灯路口", Answer.DIPPED_HEADLIGHT));
        mTestList.add(new TestBean("夜间经过无信号灯路口", Answer.DIPPED_HEADLIGHT_AND_HIGH_BEAM));
        mTestList.add(new TestBean("模拟夜间考试完成，请关闭所有灯光", Answer.CLOSE_ALL));
    }
}