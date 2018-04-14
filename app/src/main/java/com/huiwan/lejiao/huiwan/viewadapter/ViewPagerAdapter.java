package com.huiwan.lejiao.huiwan.viewadapter;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import com.huiwan.lejiao.huiwan.control.Sign_in;

import java.util.List;

/**
 * Created by zou on 2018/3/28.
 */

public class ViewPagerAdapter extends FragmentPagerAdapter {

    private List<Fragment> fragments;

    public ViewPagerAdapter(FragmentManager fm, List<Fragment> fragments) {
        super(fm);
        this.fragments = fragments;
    }

    @Override
    public int getCount() {
        return fragments.size();
    }

    @Override
    public Fragment getItem(int position) {
        pagelistener.sendposition(position);
        return fragments.get(position);
    }
    public interface Viewpageposition{
        public void sendposition(int position);
    }
    private Viewpageposition pagelistener;
    public void setpagelistener( Viewpageposition pagelistener){
        this.pagelistener=pagelistener;
    }


}
