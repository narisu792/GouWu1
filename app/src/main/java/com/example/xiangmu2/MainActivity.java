package com.example.xiangmu2;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.widget.LinearLayout;


import com.example.xiangmu2.Base.BaseActivity;
import com.example.xiangmu2.interfaces.IBasePersenter;
import com.example.xiangmu2.ui.classify.ClassifyFragment;
import com.example.xiangmu2.ui.home.HomeFragment;
import com.example.xiangmu2.ui.own.OwnFragment;
import com.example.xiangmu2.ui.sperial.SperialFragment;
import com.google.android.material.tabs.TabLayout;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;

public class MainActivity extends BaseActivity {
    @BindView(R.id.tab_main)
    TabLayout tabMain;
    @BindView(R.id.ll_main)
    LinearLayout ll_main;
    private FragmentManager fragmentManager;
    private HomeFragment homeFragment;
    private SperialFragment sperialFragment;
    private OwnFragment ownFragment;
    private ClassifyFragment classifyFragment;


    @Override
    protected void initData() {
        fragmentManager.beginTransaction()
                .add(R.id.ll_main,homeFragment)
                .show(homeFragment)
                .commit();
    }

    @Override
    protected IBasePersenter initPersenter() {
        return null;
    }

    @Override
    protected void initView() {
        fragmentManager = getSupportFragmentManager();
        homeFragment = new HomeFragment();
        classifyFragment = new ClassifyFragment();
        sperialFragment = new SperialFragment();
        ownFragment = new OwnFragment();
        tabMain.addTab(tabMain.newTab().setText("首页").setIcon(R.mipmap.home_fragment));
        tabMain.addTab(tabMain.newTab().setText("分类").setIcon(R.mipmap.elassify_fragment));
        tabMain.addTab(tabMain.newTab().setText("专题").setIcon(R.mipmap.sperial_fragment));
        tabMain.addTab(tabMain.newTab().setText("我的").setIcon(R.mipmap.own_fragment));


        initData();
        tabMain.addOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {
                initFragment(tab.getPosition());
            }

            @Override
            public void onTabUnselected(TabLayout.Tab tab) {

            }

            @Override
            public void onTabReselected(TabLayout.Tab tab) {

            }
        });

    }

    @Override
    protected int getLayout() {
        return R.layout.activity_main;
    }

    private void initFragment(int position) {
        FragmentTransaction beginTransaction = fragmentManager.beginTransaction();
        switch (position) {
            case 0:
                if (!homeFragment.isAdded()){
                    beginTransaction.add(R.id.ll_main,homeFragment).hide(classifyFragment).hide(sperialFragment).hide(ownFragment);
                }
                beginTransaction.show(homeFragment).hide(classifyFragment).hide(sperialFragment).hide(ownFragment).commit();
                break;
            case 1:
                if (!classifyFragment.isAdded()){
                    beginTransaction.add(R.id.ll_main,classifyFragment).hide(homeFragment).hide(sperialFragment).hide(ownFragment);
                }
                beginTransaction.show(classifyFragment).hide(homeFragment).hide(sperialFragment).hide(ownFragment).commit();
                break;
            case 2:
                if (!sperialFragment.isAdded()){
                    beginTransaction.add(R.id.ll_main,sperialFragment).hide(classifyFragment).hide(homeFragment).hide(ownFragment);
                }
                beginTransaction.show(sperialFragment).hide(classifyFragment).hide(homeFragment).hide(ownFragment).commit();
                break;
            case 3:
                if (!ownFragment.isAdded()){
                    beginTransaction.add(R.id.ll_main,ownFragment).hide(classifyFragment).hide(sperialFragment).hide(homeFragment);
                }
                beginTransaction.show(ownFragment).hide(classifyFragment).hide(sperialFragment).hide(homeFragment).commit();
                break;
        }
    }

    @Override
    public void showTips(String s) {

    }
}