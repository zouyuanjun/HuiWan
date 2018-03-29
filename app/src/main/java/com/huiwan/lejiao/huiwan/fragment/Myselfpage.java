package com.huiwan.lejiao.huiwan.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.huiwan.lejiao.huiwan.R;

/**
 * Created by zou on 2018/3/28.
 */

public class Myselfpage extends Fragment {
    View rootview;
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        rootview=inflater.inflate(R.layout.fragment_providepage,container,false);
        return rootview;
    }
}
