package com.huiwan.lejiao.huiwan.fragment;

import android.app.Activity;
import android.content.Context;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.Log;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import android.widget.ImageButton;
import android.widget.TextView;


import com.huiwan.lejiao.huiwan.R;
import com.lljjcoder.Interface.OnCityItemClickListener;
import com.lljjcoder.bean.CityBean;
import com.lljjcoder.bean.DistrictBean;
import com.lljjcoder.bean.ProvinceBean;
import com.lljjcoder.citywheel.CityConfig;
import com.lljjcoder.style.citylist.Toast.ToastUtils;
import com.lljjcoder.style.citypickerview.CityPickerView;

//import org.feezu.liuli.timeselector.TimeSelector;

/**
 * Created by zou on 2018/3/28.
 */

public class Myselfpage extends Fragment {
    String citysrt;
    TextView tv_city;
    TextView tv_birday;
    ImageButton imb_citypick;
    ImageButton imb_birdaypick;
    ImageButton imb_setting;
    CityPickerView mPicker=new CityPickerView();
    Context context;
    View rootview;
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        rootview=inflater.inflate(R.layout.fragment_user_centre,container,false);
        mPicker.init(getContext());
        imb_citypick=rootview.findViewById(R.id.imb_citypick);
        imb_birdaypick=rootview.findViewById(R.id.imb_birdaypick);
        tv_city=rootview.findViewById(R.id.tv_city);
        tv_birday=rootview.findViewById(R.id.tv_birday);
        context=getContext();
        imb_birdaypick.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.d("55555","设置出生日期");
//                TimeSelector timeSelector = new TimeSelector(context, new TimeSelector.ResultHandler() {
//                    @Override
//                    public void handle(String time) {
//                        tv_birday.setText(time);
//                    }
//                }, "1960-11-22 17:34", "2018-12-1 15:20");
//                timeSelector.setMode(TimeSelector.MODE.YMD);
//                timeSelector.show();

            }
        });


        imb_citypick.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //添加默认的配置，不需要自己定义
                CityConfig cityConfig = new CityConfig.Builder().build();
                mPicker.setConfig(cityConfig);
                    //监听选择点击事件及返回结果
                mPicker.setOnCityItemClickListener(new OnCityItemClickListener() {
                    @Override
                    public void onSelected(ProvinceBean province, CityBean city, DistrictBean district) {
                        //省份
                        if (province != null) {
                            citysrt=province.getName();
                        }
                        //城市
                        if (city != null) {
                            citysrt=citysrt+"·"+city.getName();
                        }
                        //地区
                        if (district != null) {
                            citysrt=citysrt+"·"+district.getName();
                            tv_city.setText(citysrt);
                            Log.d("55555",district.getName());
                        }
                    }
                    @Override
                    public void onCancel() {
                        ToastUtils.showLongToast(getContext(), "已取消");
                    }
                });
                //显示
                mPicker.showCityPicker( );
            }
        });
        return rootview;
    }
}
