package com.example.xiangmu2.interfaces;

public interface IBasePersenter<V extends IBaseView> {

    //v层接口的关联
    void attachView(V view);

    //v层接口的取消关联
    void detachView();
}
