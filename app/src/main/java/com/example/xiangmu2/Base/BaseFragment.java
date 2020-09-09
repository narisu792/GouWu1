package com.example.xiangmu2.Base;

import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.example.xiangmu2.interfaces.IBasePersenter;
import com.example.xiangmu2.interfaces.IBaseView;

import butterknife.ButterKnife;
import butterknife.Unbinder;

public abstract class BaseFragment<P extends IBasePersenter> extends Fragment implements IBaseView {
    Unbinder unbinder;

    protected P persenter;
    protected Context context;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View inflate = inflater.inflate(getLayout(), null);
        return inflate;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        unbinder = ButterKnife.bind(this, view);
        context =  getContext();
        initView();
        persenter = initPersenter();
        if (persenter!=null){
            persenter.attachView(this);
            initData();
        }
    }

    protected abstract void initData();

    protected abstract P initPersenter();

    protected abstract void initView();

    protected abstract int getLayout();

    @Override
    public void onDestroy() {
        super.onDestroy();
        if (unbinder!=null){
            unbinder.unbind();
        }
        if (persenter!=null){
            persenter.detachView();
        }
    }
}
