package com.example.xiangmu2.Base;

import android.os.Bundle;
import android.widget.ImageView;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.example.xiangmu2.interfaces.IBasePersenter;
import com.example.xiangmu2.interfaces.IBaseView;

import butterknife.ButterKnife;
import butterknife.Unbinder;

public abstract class BaseActivity<P extends IBasePersenter> extends AppCompatActivity implements IBaseView {

    private Unbinder bind;
    protected P persenter;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //设计模式中的模板方法调用
        setContentView(getLayout());

        bind = ButterKnife.bind(this);
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
    protected void onDestroy() {
        super.onDestroy();
        if (bind!=null){
            bind.unbind();
        }
        if (persenter!=null){
            persenter.detachView();
        }
    }
}
