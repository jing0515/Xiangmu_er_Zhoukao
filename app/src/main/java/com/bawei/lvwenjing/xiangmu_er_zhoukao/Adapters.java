package com.bawei.lvwenjing.xiangmu_er_zhoukao;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

public class Adapters extends FragmentPagerAdapter {
    String[] PATH = {"新闻标题", "新闻详情", "社内新闻", "本社介绍", "履行职责", "自身建设", "新闻", "娱乐", "搞笑", "视屏"};

    public Adapters(FragmentManager fm) {
        super(fm);
    }

    @Override
    public Fragment getItem(int position) {
        if (position == 0) {
            Fragment01 fragment01 = new Fragment01();
            return fragment01;
        }else if (position==1){
            Fragment02 fragment02 = new Fragment02();
            return fragment02;
        }
        Fragment01 fragment = new Fragment01();
        Bundle bundle = new Bundle();
        bundle.putString("Tile", PATH[position]);
        fragment.setArguments(bundle);
        return fragment;

    }

    @Override
    public int getCount() {
        return PATH.length;
    }

    @Override
    public CharSequence getPageTitle(int position) {
        return PATH[position];
    }
}