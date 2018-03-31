package com.huiwan.lejiao.huiwan.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import com.huiwan.lejiao.huiwan.DataBean.PersonalinfoBean;
import com.huiwan.lejiao.huiwan.R;
import com.huiwan.lejiao.huiwan.viewadapter.Studentinfo_list_Adapter;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by zou on 2018/3/28.
 */

public class Studentpage extends Fragment {
    ImageView im_sjphoto;       //上级头像
    ImageView im_sjduanwei;     //上级段位图

    TextView tv_sjname;     //上级姓名
    TextView tv_sjshouji;   //上级手机号
    TextView tv_sjweixin;       //上级微信
    ListView listView;      //学员信息listview
    View rootview;
    ArrayList<PersonalinfoBean> arrayList=new ArrayList<>();

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        rootview=inflater.inflate(R.layout.fragment_studentpage,container,false);
        im_sjphoto=rootview.findViewById(R.id.im_sjphoto);
        im_sjduanwei=rootview.findViewById(R.id.im_sjduanwei);
        tv_sjname=rootview.findViewById(R.id.tv_xuesjname);
        tv_sjshouji=rootview.findViewById(R.id.tv_xuesjshouji);
        tv_sjweixin=rootview.findViewById(R.id.tv_xuesjweixin);
        listView=rootview.findViewById(R.id.lv_studentlist);
        setdata();
        return rootview;
    }
    public void setdata(){
        String url="http://img5q.duitang.com/uploads/item/201410/04/20141004212538_SXjWV.jpeg";
        PersonalinfoBean personalinfoBean=new PersonalinfoBean("张三","18702508050","sbjdjk5",url,"15");
        arrayList.add(personalinfoBean);
        arrayList.add(personalinfoBean);
        arrayList.add(personalinfoBean);
        arrayList.add(personalinfoBean);
        arrayList.add(personalinfoBean);
        arrayList.add(personalinfoBean);
        Studentinfo_list_Adapter adapter=new Studentinfo_list_Adapter(arrayList);
        listView.setAdapter(adapter);
    }
}
