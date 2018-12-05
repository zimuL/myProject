package com.zimu.my2018.quyouui.core.adapter;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;

import com.zimu.my2018.quyouui.core.fragment.ImageDetailFragment;

import java.util.ArrayList;

/**
 * 功能：
 * 描述：
 * Created by hxl on 2018/10/26
 */
public class ImagePagerAdapter extends FragmentStatePagerAdapter {

    public ArrayList<String> fileList;
    private boolean isLocal;

    public ImagePagerAdapter(FragmentManager fm, ArrayList<String> fileList, boolean isLocal) {
        super(fm);
        this.fileList = fileList;
        this.isLocal = isLocal;
    }

    @Override
    public int getCount() {
        return fileList == null ? 0 : fileList.size();
    }

    @Override
    public Fragment getItem(int position) {
        String url = fileList.get(position);
        return ImageDetailFragment.newInstance(url, isLocal);
    }

}
