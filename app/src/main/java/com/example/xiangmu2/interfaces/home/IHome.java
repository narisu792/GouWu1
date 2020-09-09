package com.example.xiangmu2.interfaces.home;

import android.view.View;


import com.example.xiangmu2.bean.Banner_Bean;
import com.example.xiangmu2.interfaces.IBasePersenter;
import com.example.xiangmu2.interfaces.IBaseView;

public interface IHome {

    interface BannerView extends IBaseView {
        void getBannerReturn(Banner_Bean banner_bean);
    }

    interface BannerPersenter extends IBasePersenter<BannerView> {
        void getBanner();
    }
}
