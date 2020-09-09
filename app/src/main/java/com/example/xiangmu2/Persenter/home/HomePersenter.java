package com.example.xiangmu2.Persenter.home;

import com.example.xiangmu2.Base.BasePersenter;
import com.example.xiangmu2.Model.HttpManager;
import com.example.xiangmu2.bean.Banner_Bean;
import com.example.xiangmu2.common.CommonSubscriber;
import com.example.xiangmu2.interfaces.home.IHome;
import com.example.xiangmu2.utils.RxUtils;

public class HomePersenter extends BasePersenter<IHome.BannerView> implements IHome.BannerPersenter  {
    @Override
    public void getBanner() {
        addSubscribe(HttpManager.getInstance().getdSapi().getBannerBean()
                .compose(RxUtils.<Banner_Bean>rxScheduler())
                .subscribeWith(new CommonSubscriber<Banner_Bean>(mView) {
                    @Override
                    public void onNext(Banner_Bean banner_bean) {
                        mView.getBannerReturn(banner_bean);
                    }
                }));
    }
}
