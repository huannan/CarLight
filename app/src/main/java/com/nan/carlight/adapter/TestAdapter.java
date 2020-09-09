package com.nan.carlight.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.nan.carlight.R;
import com.nan.carlight.constant.AnswerConstant;
import com.nan.carlight.model.TestBean;

import java.util.List;

public class TestAdapter extends RecyclerView.Adapter<TestAdapter.TestViewHolder> {
    private Context mContext;
    private List<TestBean> mTestList;
    private boolean mIsReview;

    public TestAdapter(Context context, List<TestBean> testList, boolean isReview) {
        mContext = context;
        mTestList = testList;
        mIsReview = isReview;
    }

    @NonNull
    @Override
    public TestViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(mContext).inflate(R.layout.item_result, parent, false);
        return new TestViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull TestViewHolder holder, int position) {
        TestBean testBean = mTestList.get(position);
        holder.tvTitle.setText(testBean.getTitle());
        boolean isCorrect = testBean.getAnswer() == testBean.getUserAnswer();
        if (!mIsReview) {
            holder.tvState.setText(isCorrect ? "回答正确，正确答案：" + AnswerConstant.sAnswerStringMap.get(testBean.getAnswer()) :
                    "回答错误，您的答案：" + AnswerConstant.sAnswerStringMap.get(testBean.getUserAnswer()) + "\n正确答案：" + AnswerConstant.sAnswerStringMap.get(testBean.getAnswer()));
            holder.itemView.setBackgroundDrawable(mContext.getResources().getDrawable(isCorrect ? android.R.color.holo_green_light : android.R.color.holo_red_light));
        } else {
            holder.tvState.setText("正确答案：" + AnswerConstant.sAnswerStringMap.get(testBean.getAnswer()));
        }
    }


    @Override
    public int getItemCount() {
        return mTestList.size();
    }

    protected static class TestViewHolder extends RecyclerView.ViewHolder {

        TextView tvTitle;
        TextView tvState;

        public TestViewHolder(@NonNull View itemView) {
            super(itemView);
            tvTitle = itemView.findViewById(R.id.tv_title);
            tvState = itemView.findViewById(R.id.tv_state);
        }

    }
}
