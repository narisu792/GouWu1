package com.example.xiangmu2.Base;

import android.content.Context;
import android.text.Layout;
import android.util.SparseArray;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.xiangmu2.R;

import java.util.List;

public abstract class BaseAdapter<T> extends RecyclerView.Adapter {
    protected Context context;
    protected List<T> data;

    protected IClick iClick;
    public void setClick(IClick cb){
        iClick = cb;
    }

    public void addData(List<T> data){
        this.data.clear();
        this.data.addAll(data);
        notifyDataSetChanged();
    }

    public BaseAdapter(Context context, List<T> data) {
        this.context = context;
        this.data = data;
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View inflate = LayoutInflater.from(context).inflate(getLayout(), parent, false);
        final BaseViewHolder baseViewHolder = new BaseViewHolder(inflate);
        baseViewHolder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (iClick!=null){
                    iClick.click(baseViewHolder.getLayoutPosition());
                }
            }
        });
        return baseViewHolder;
    }

    protected abstract int getLayout();

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {
        BaseViewHolder viewHolder = (BaseViewHolder) holder;
        T t = data.get(position);
        bindData(viewHolder,t,position);
    }

    //绑定数据
    protected abstract void bindData(BaseViewHolder viewHolder,T data,int position);
    @Override
    public int getItemCount() {
        return data.size();
    }
    public class BaseViewHolder extends RecyclerView.ViewHolder{

        SparseArray views = new SparseArray();

        public BaseViewHolder(@NonNull View itemView) {
            super(itemView);

        }
        //通过id适配器的组件
        public View getViewById(int id){
            View view = (View) views.get(id);
            if (view!=null){
                view = itemView.findViewById(id);
                views.append(id,view);
            }
            return view;
        }
    }

    public interface IClick {
        void click(int position);
    }
}
