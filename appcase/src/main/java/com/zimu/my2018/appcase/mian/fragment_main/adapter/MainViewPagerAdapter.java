package com.zimu.my2018.appcase.mian.fragment_main.adapter;

import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.app.FragmentStatePagerAdapter;
import android.view.ViewGroup;

import java.util.List;

/**
 * 功能：
 * 描述：
 * Created by hxl on 2018/10/9
 */
public class MainViewPagerAdapter extends FragmentStatePagerAdapter {
    private List<Fragment> fragments;
    private List<String> data;
    public MainViewPagerAdapter(FragmentManager fm, List<Fragment> fragments, List<String> data) {
        super(fm);
        this.fragments= fragments;
        this.data = data;
    }

    @Override
    public Fragment getItem(int position) {
        return fragments.get(position);
    }

    @Override
    public int getCount() {
        return fragments.size();
    }

    @Nullable
    @Override
    public CharSequence getPageTitle(int position) {
        return data.get(position);
    }

    @Override
    public void destroyItem(ViewGroup container, int position, Object object) {
        // 重写FragmentPagerAdapter的destroyItem方法注释掉super.destroyItem(container, position, object);
        // viewpager+fragment来回滑动fragment重新加载的解决办法
        // FragmentPagerAdapter默认会保存的三个item即当前的一个，前一个和后一个。
        // 滑动过程中适配器默认会把前一个之前的item destroy掉，所以当滑动回来时就依然会重新加载。
        // 还会执行一次onCreateView的方法。
        // 分析其原因就是适配器销毁了之前的item，自然解决办法就是不让他销毁。

        // super.destroyItem(container, position, object);
    }
}
