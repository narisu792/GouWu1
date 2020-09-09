package com.example.xiangmu2.Adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.alibaba.android.vlayout.DelegateAdapter;
import com.alibaba.android.vlayout.LayoutHelper;
import com.alibaba.android.vlayout.VirtualLayoutManager;
import com.alibaba.android.vlayout.layout.LinearLayoutHelper;
import com.example.xiangmu2.R;


public class RVAdapter extends DelegateAdapter.Adapter<RecyclerView.ViewHolder> {

    Context mContext;

    private LayoutHelper mHelper;
    private VirtualLayoutManager.LayoutParams mLayoutParams;

    public RVAdapter(Context context, LayoutHelper mHelper) {
        this(context, mHelper, new VirtualLayoutManager.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, 300));
    }

    public RVAdapter(Context context, LayoutHelper layoutHelper, @NonNull VirtualLayoutManager.LayoutParams layoutParams) {
        this.mContext = context;
        this.mHelper = layoutHelper;
        this.mLayoutParams = layoutParams;
    }


    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View inflate = LayoutInflater.from(mContext).inflate(R.layout.item_1, null);
        return new MyHolder(inflate);
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        // only vertical
        holder.itemView.setLayoutParams(
                new VirtualLayoutManager.LayoutParams(mLayoutParams));

    }

    @Override
    public int getItemCount() {
        return 1;
    }


    @Override
    public LayoutHelper onCreateLayoutHelper() {
        return mHelper;
    }

    class MyHolder extends RecyclerView.ViewHolder {
        public MyHolder(View itemView) {
            super(itemView);
        }
    }
}
