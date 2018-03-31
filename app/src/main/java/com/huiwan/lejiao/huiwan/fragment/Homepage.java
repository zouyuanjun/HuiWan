package com.huiwan.lejiao.huiwan.fragment;

import android.app.Activity;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.google.gson.Gson;
import com.huiwan.lejiao.huiwan.DataBean.UserdataBean;
import com.huiwan.lejiao.huiwan.R;

/**
 * Created by zou on 2018/3/28.
 */

public class Homepage extends Fragment {
    ImageView imageView;
    TextView tv_dangqianduanwei;
    TextView tv_xiaduan;
    TextView tv_jinrirenshu;
    TextView tv_jinrimubiao;
    TextView tv_dangyuerenshu;
    TextView tv_dangyuemubiao;
    View rootview;
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        rootview=inflater.inflate(R.layout.fragment_homepage,container,false);
        imageView=rootview.findViewById(R.id.im_hp_dengji);
        tv_dangqianduanwei=rootview.findViewById(R.id.tv_hp_dangqiandengji);
        tv_xiaduan=rootview.findViewById(R.id.tv_hp_xiagedengji);
        tv_jinrirenshu=rootview.findViewById(R.id.tv_homepage_jinrirenshu);
        tv_jinrimubiao=rootview.findViewById(R.id.tv_homepage_jirimubiao);
        tv_dangyuerenshu=rootview.findViewById(R.id.tv_hp_benyuerenshu);
        tv_dangyuemubiao=rootview.findViewById(R.id.tv_hp_benyuemubiao);
        getdata();
        return rootview;

    }
    //获取保存在SharedPreferences中的信息
    public void getdata(){
        SharedPreferences sp = getActivity().getSharedPreferences("SPuser", Activity.MODE_PRIVATE);
        String info=sp.getString("SIGN","");
        if (info!=null){
            Gson gson=new Gson();
            UserdataBean userdataBean=gson.fromJson(info,UserdataBean.class);
            tv_jinrirenshu.setText(userdataBean.getJinrirenshu());
            tv_jinrimubiao.setText(userdataBean.getJinrimubiao());
            tv_dangyuerenshu.setText(userdataBean.getBenyuerenshu());
            tv_dangyuemubiao.setText(userdataBean.getBenyuemubiao());
        }
    }

}
