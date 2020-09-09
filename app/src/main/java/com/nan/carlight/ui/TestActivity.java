package com.nan.carlight.ui;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.alibaba.fastjson.JSON;
import com.nan.carlight.R;
import com.nan.carlight.constant.Answer;
import com.nan.carlight.constant.AnswerConstant;
import com.nan.carlight.constant.IntentArg;
import com.nan.carlight.model.TestBean;
import com.nan.carlight.util.Logger;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Locale;
import java.util.concurrent.TimeUnit;

import io.reactivex.Observable;
import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;

public class TestActivity extends AppCompatActivity implements View.OnClickListener {

    private static final String TAG = "TestActivity";
    private TextView mTvTitle;
    private TextView mTvState;
    private Button mBtnAnswer1;
    private Button mBtnAnswer2;
    private Button mBtnAnswer3;
    private Button mBtnAnswer4;
    private Button mBtnAnswer5;
    private List<TestBean> mTestList = new ArrayList<>();
    private Disposable mDisposable;
    @Answer
    private int mCurrentAnswer = Answer.UNKNOWN;
    private int mCurrentIndex = 0;
    private Handler mHandler = new Handler(Looper.getMainLooper());

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_test);

        mTvTitle = findViewById(R.id.tv_title);
        mTvState = findViewById(R.id.tv_state);
        mBtnAnswer1 = findViewById(R.id.btn_answer1);
        mBtnAnswer2 = findViewById(R.id.btn_answer2);
        mBtnAnswer3 = findViewById(R.id.btn_answer3);
        mBtnAnswer4 = findViewById(R.id.btn_answer4);
        mBtnAnswer5 = findViewById(R.id.btn_answer5);
        mBtnAnswer1.setOnClickListener(this);
        mBtnAnswer2.setOnClickListener(this);
        mBtnAnswer3.setOnClickListener(this);
        mBtnAnswer4.setOnClickListener(this);
        mBtnAnswer5.setOnClickListener(this);
        mBtnAnswer1.setText(AnswerConstant.sAnswerStringMap.get(Answer.DIPPED_HEADLIGHT));
        mBtnAnswer2.setText(AnswerConstant.sAnswerStringMap.get(Answer.HIGH_BEAM));
        mBtnAnswer3.setText(AnswerConstant.sAnswerStringMap.get(Answer.DIPPED_HEADLIGHT_AND_HIGH_BEAM));
        mBtnAnswer4.setText(AnswerConstant.sAnswerStringMap.get(Answer.CLEARANCE_LAMP));
        mBtnAnswer5.setText(AnswerConstant.sAnswerStringMap.get(Answer.CLOSE_ALL));

        loadTest();
        startNext();
    }

    @Override
    protected void onDestroy() {
        if (null != mDisposable && !mDisposable.isDisposed()) {
            mDisposable.dispose();
        }
        mHandler.removeCallbacksAndMessages(null);
        super.onDestroy();
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btn_answer1:
                mCurrentAnswer = Answer.DIPPED_HEADLIGHT;
                break;
            case R.id.btn_answer2:
                mCurrentAnswer = Answer.HIGH_BEAM;
                break;
            case R.id.btn_answer3:
                mCurrentAnswer = Answer.DIPPED_HEADLIGHT_AND_HIGH_BEAM;
                break;
            case R.id.btn_answer4:
                mCurrentAnswer = Answer.CLEARANCE_LAMP;
                break;
            case R.id.btn_answer5:
                mCurrentAnswer = Answer.CLOSE_ALL;
                break;
            default:
                break;
        }
        stopAndCheck();
    }

    private void loadTest() {
        mTestList.clear();
        mTestList.add(new TestBean("将要进行夜间行驶，请打开前照灯", Answer.DIPPED_HEADLIGHT));

        List<TestBean> temp = new ArrayList<>();
        temp.add(new TestBean("夜间通过急弯", Answer.DIPPED_HEADLIGHT_AND_HIGH_BEAM));
        temp.add(new TestBean("夜间通过坡路", Answer.DIPPED_HEADLIGHT_AND_HIGH_BEAM));
        temp.add(new TestBean("夜间通过拱桥", Answer.DIPPED_HEADLIGHT_AND_HIGH_BEAM));
        temp.add(new TestBean("夜间通过人行横道", Answer.DIPPED_HEADLIGHT_AND_HIGH_BEAM));
        temp.add(new TestBean("进入照明良好道路", Answer.DIPPED_HEADLIGHT));
        temp.add(new TestBean("进入照明不良道路", Answer.HIGH_BEAM));
        temp.add(new TestBean("夜间在路边临时停车", Answer.CLEARANCE_LAMP));
        temp.add(new TestBean("夜间与机动车会车", Answer.DIPPED_HEADLIGHT));
        temp.add(new TestBean("夜间超越前方车辆", Answer.DIPPED_HEADLIGHT_AND_HIGH_BEAM));
        temp.add(new TestBean("夜间通过路口", Answer.DIPPED_HEADLIGHT));
        temp.add(new TestBean("同方向近距离跟车行驶", Answer.DIPPED_HEADLIGHT));
        temp.add(new TestBean("夜间经过有信号灯路口", Answer.DIPPED_HEADLIGHT));
        temp.add(new TestBean("夜间经过无信号灯路口", Answer.DIPPED_HEADLIGHT_AND_HIGH_BEAM));

        Collections.shuffle(temp);
        mTestList.addAll(temp);
        mTestList.add(new TestBean("模拟夜间考试完成，请关闭所有灯光", Answer.CLOSE_ALL));
    }

    private void startNext() {
        TestBean nextTest = mTestList.get(mCurrentIndex);
        mTvTitle.setText(nextTest.getTitle());
        mCurrentAnswer = Answer.UNKNOWN;

        mTvState.setText(String.format(Locale.getDefault(), "倒计时%d", 5));
        Observable.interval(1, 1, TimeUnit.SECONDS)
                .take(5)
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<Long>() {
                    @Override
                    public void onSubscribe(Disposable d) {
                        mDisposable = d;
                    }

                    @Override
                    public void onNext(Long aLong) {
                        long time = 4 - aLong;
                        mTvState.setText(String.format(Locale.getDefault(), "倒计时%d", time));
                        Logger.d(TAG, "onNext aLong=%s", aLong);
                    }

                    @Override
                    public void onError(Throwable e) {
                        Logger.e(TAG, "onError e=%s", e.getMessage());
                    }

                    @Override
                    public void onComplete() {
                        Logger.d(TAG, "onComplete");
                        stopAndCheck();
                    }
                });
    }

    private void stopAndCheck() {
        setAnswerEnable(false);
        if (null != mDisposable && !mDisposable.isDisposed()) {
            mDisposable.dispose();
        }

        TestBean testBean = mTestList.get(mCurrentIndex);
        testBean.setUserAnswer(mCurrentAnswer);
        if (mCurrentAnswer == testBean.getAnswer()) {
            mTvState.setText("回答正确");
        } else {
            mTvState.setText("回答错误");
        }

        if (++mCurrentIndex < mTestList.size()) {
            mHandler.postDelayed(new Runnable() {
                @Override
                public void run() {
                    setAnswerEnable(true);
                    startNext();
                }
            }, 500L);
        } else {
            mHandler.postDelayed(new Runnable() {
                @Override
                public void run() {
                    gotoTestCompleteActivity();
                }
            }, 500L);
        }
    }

    private void setAnswerEnable(boolean enable) {
        mBtnAnswer1.setEnabled(enable);
        mBtnAnswer2.setEnabled(enable);
        mBtnAnswer3.setEnabled(enable);
        mBtnAnswer4.setEnabled(enable);
        mBtnAnswer5.setEnabled(enable);
    }

    private void gotoTestCompleteActivity() {
        Intent intent = new Intent(this, CompleteActivity.class);
        intent.putExtra(IntentArg.TEST_LIST, JSON.toJSONString(mTestList));
        startActivity(intent);
        finish();
    }

}