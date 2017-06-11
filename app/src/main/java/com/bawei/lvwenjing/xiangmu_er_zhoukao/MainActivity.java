package com.bawei.lvwenjing.xiangmu_er_zhoukao;

import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.app.FragmentActivity;
import android.support.v4.view.ViewPager;

public class MainActivity extends FragmentActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        TabLayout tabLayout = (TabLayout) findViewById(R.id.tablout);
        ViewPager viewPager = (ViewPager) findViewById(R.id.mactivity_v_pager);
        Adapters adapters = new Adapters(getSupportFragmentManager());
        viewPager.setAdapter(adapters);
        //绑定
        tabLayout.setupWithViewPager(viewPager);
        //字体颜色
        tabLayout.setTabTextColors(getResources().getColor(R.color.hui), getResources().getColor(R.color.black));
        //指示器颜色
        tabLayout.setSelectedTabIndicatorColor(getResources().getColor(R.color.yellow));
        //模式
        tabLayout.setTabMode(TabLayout.MODE_SCROLLABLE);


    }
}