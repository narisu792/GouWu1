package com.example.xiangmu2.ui.home;

import android.widget.LinearLayout;

import androidx.recyclerview.widget.RecyclerView;

import com.alibaba.android.vlayout.DelegateAdapter;
import com.alibaba.android.vlayout.VirtualLayoutManager;
import com.alibaba.android.vlayout.layout.GridLayoutHelper;
import com.alibaba.android.vlayout.layout.LinearLayoutHelper;
import com.bumptech.glide.Glide;
import com.bumptech.glide.load.resource.bitmap.RoundedCorners;
import com.bumptech.glide.request.RequestOptions;
import com.example.xiangmu2.Adapter.GridHelperAdapter;
import com.example.xiangmu2.Adapter.LinearAdapter;
import com.example.xiangmu2.Adapter.RVAdapter;
import com.example.xiangmu2.Base.BaseFragment;
import com.example.xiangmu2.Persenter.home.HomePersenter;
import com.example.xiangmu2.R;
import com.example.xiangmu2.bean.Banner_Bean;
import com.example.xiangmu2.interfaces.home.IHome;
import com.youth.banner.Banner;
import com.youth.banner.adapter.BannerImageAdapter;
import com.youth.banner.holder.BannerImageHolder;
import com.youth.banner.indicator.CircleIndicator;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;


public class HomeFragment extends BaseFragment<IHome.BannerPersenter> implements IHome.BannerView {


    @BindView(R.id.banner)
    Banner banner;
    @BindView(R.id.home_rlv)
    RecyclerView homeRlv;
    private List<Integer> integers;

    @Override
    protected void initData() {
        persenter.getBanner();
    }

    @Override
    protected IHome.BannerPersenter initPersenter() {
        return new HomePersenter();
    }

    @Override
    protected void initView() {
        VirtualLayoutManager linearLayoutManager = new VirtualLayoutManager(getActivity());
//开始
        DelegateAdapter delegateAdapter = new DelegateAdapter(linearLayoutManager, false);
        VirtualLayoutManager virtualLayoutManager = new VirtualLayoutManager(getContext());
        homeRlv.setLayoutManager(virtualLayoutManager);
        homeRlv.setAdapter(delegateAdapter);
        integers = new ArrayList<>();
        integers.add(1);


//item_1
        GridLayoutHelper gridHelper = new GridLayoutHelper(5);
        gridHelper.setMarginTop(30);
// gridHelper.setWeights(new float[]{20.0f,20.0f,20.0f,20.0f,20.0f});
//设置垂直方向条目的间隔
        gridHelper.setVGap(5);
//设置水平方向条目的间隔
        gridHelper.setHGap(5);
        gridHelper.setMarginLeft(30);
        gridHelper.setMarginBottom(30);
//自动填充满布局，在设置完权重，若没有占满，自动填充满布局
        gridHelper.setAutoExpand(true);
        delegateAdapter.addAdapter(new GridHelperAdapter(integers, gridHelper));
//item_1
//Linear 布局
        LinearLayoutHelper linearHelper1 = new LinearLayoutHelper(5);
        delegateAdapter.addAdapter(new LinearAdapter(getActivity(), linearHelper1));

        LinearLayoutHelper linearHelper = new LinearLayoutHelper(5);
        delegateAdapter.addAdapter(new RVAdapter(getActivity(), linearHelper));

    }

    @Override
    protected int getLayout() {
        return R.layout.fragment_home;
    }

    @Override
    public void getBannerReturn(Banner_Bean banner_bean) {
        if (banner_bean.getCode() == 200) {
            //舒適化banner
            List<String> bannerUrls = new ArrayList<>();
            if (banner_bean.getData().getAdvertiseList().size() > 0) {
                for (int i = 0; i < banner_bean.getData().getAdvertiseList().size(); i++) {
                    bannerUrls.add(banner_bean.getData().getAdvertiseList().get(i).getPic());
                }
            }
            banner.setAdapter(new BannerImageAdapter<String>(bannerUrls) {
                @Override
                public void onBindView(BannerImageHolder holder, String data, int position, int size) {
                    Glide.with(holder.imageView)
                            .load(data)
                            .apply(RequestOptions.bitmapTransform(new RoundedCorners(30)))
                            .into(holder.imageView);
                }
            })
                    .addBannerLifecycleObserver(this)
                    .setIndicator(new CircleIndicator(context));
        }
    }

    @Override
    public void showTips(String s) {

    }
}