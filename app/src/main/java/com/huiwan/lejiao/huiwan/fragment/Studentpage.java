package com.huiwan.lejiao.huiwan.fragment;

import android.app.Activity;
import android.content.Intent;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.PopupWindow;
import android.widget.TextView;

import com.huiwan.lejiao.huiwan.DataBean.PersonalinfoBean;
import com.huiwan.lejiao.huiwan.R;
import com.huiwan.lejiao.huiwan.activity.Xueyuan_info_Activity;
import com.huiwan.lejiao.huiwan.control.Xueyuan_zhuangma;
import com.huiwan.lejiao.huiwan.viewadapter.Studentinfo_list_Adapter;

import java.util.ArrayList;

/**
 * Created by zou on 2018/3/28.
 */

public class Studentpage extends Fragment implements AdapterView.OnItemClickListener{

    ImageView im_sjphoto;       //上级头像
    ImageView im_sjduanwei;     //上级段位图
    TextView tv_sjname;         //上级姓名
    TextView tv_sjshouji;       //上级手机号
    TextView tv_sjweixin;       //上级微信
    ListView listView;          //学员信息listview
    View rootview;
    ArrayList<PersonalinfoBean> arrayList=new ArrayList<>();
    Button button;
    Activity activity;
    Xueyuan_zhuangma xueyuan_zhuangma;
    PopupWindow window;

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
        activity=getActivity();
        setdata();
        return rootview;
    }
    public void setdata(){
        xueyuan_zhuangma=new Xueyuan_zhuangma();
        String url="http://img5q.duitang.com/uploads/item/201410/04/20141004212538_SXjWV.jpeg";
        PersonalinfoBean personalinfoBean=new PersonalinfoBean("张三","18702508050","sbjdjk5",url,"15");
        arrayList.clear();
        arrayList.add(personalinfoBean);
        arrayList.add(personalinfoBean);
        arrayList.add(personalinfoBean);
        arrayList.add(personalinfoBean);
        arrayList.add(personalinfoBean);
        arrayList.add(personalinfoBean);
        arrayList.add(personalinfoBean);
        arrayList.add(personalinfoBean);
        arrayList.add(personalinfoBean);
        arrayList.add(personalinfoBean);
        arrayList.add(personalinfoBean);
        arrayList.add(personalinfoBean);
        Studentinfo_list_Adapter adapter=new Studentinfo_list_Adapter(activity,arrayList);
        adapter.setsignlistener(new Studentinfo_list_Adapter.Zhuanma() {
            @Override
            public void tongzhizhuanma(String t) {
                Log.d("5555","转码弹窗"+t);
                zhuanmapopwindows();
            }
        });
        listView.setAdapter(adapter);
        listView.setOnItemClickListener(this);
    }
    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        Intent intent=new Intent(getActivity(), Xueyuan_info_Activity.class);
        startActivity(intent);
    }
    public void zhuanmapopwindows(){
        final int[] shezhizhuangma = new int[1];  //设置的转码数量
        Button bt_ensure_zhuanma;
        Button bt_jia;                            //加
        Button bt_jian;                          //减
        final TextView tv_shezhizhuangma;             //设置的转码数
        TextView tv_kzms;                       //可转码数
        TextView tv_qbzc;               //全部转出

        //设置背景变暗
        WindowManager.LayoutParams lp = activity.getWindow().getAttributes();
        lp.alpha = 0.4f;
        activity.getWindow().setAttributes(lp);
        View contentView = LayoutInflater.from(getContext()).inflate(R.layout.popwindows_zhuanma, null, false);
        bt_ensure_zhuanma=contentView.findViewById(R.id.bt_ensure_zhuanchu);
        bt_jia=contentView.findViewById(R.id.bt_zhuanma_jia);
        bt_jian=contentView.findViewById(R.id.bt_jian);
        tv_shezhizhuangma=contentView.findViewById(R.id.tv_shezhizhuanma);
        tv_qbzc=contentView.findViewById(R.id.tv_zhuanma_qbzc);
        tv_kzms=contentView.findViewById(R.id.tv_zhuanma_kzms);
        //加按钮监听
        bt_jia.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
               shezhizhuangma[0] = xueyuan_zhuangma.zhuanmajia();
               //+1
               tv_shezhizhuangma.setText(shezhizhuangma[0]+"");
            }
        });
        //减按钮监听
        bt_jian.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //-1
                shezhizhuangma[0] =xueyuan_zhuangma.zhuanmajian();
                tv_shezhizhuangma.setText(shezhizhuangma[0]+"");
            }
        });
        bt_ensure_zhuanma.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
              xueyuan_zhuangma.ensurezhuangchu();
            }
        });
        xueyuan_zhuangma.setXueyuanlistener(new Xueyuan_zhuangma.interface_Xueyuan_zhuanma() {
            @Override
            public void tongzhizhuanmacg(String t) {
                zcsuceessfulpopwindows();
            }
        });
        window = new PopupWindow(contentView,  ViewGroup.LayoutParams.WRAP_CONTENT,  ViewGroup.LayoutParams.WRAP_CONTENT, true);
        // 设置PopupWindow的背景
        window.setBackgroundDrawable(new ColorDrawable(0xff2581ff));
        // 设置PopupWindow是否能响应外部点击事件
        window.setOutsideTouchable(true);
        // 设置PopupWindow是否能响应点击事件
        window.setTouchable(true);
        window.setOnDismissListener(new PopupWindow.OnDismissListener() {
            //在dismiss中恢复透明度
            public void onDismiss() {
                WindowManager.LayoutParams lp = activity.getWindow().getAttributes();
                lp.alpha = 1f;
                activity.getWindow().setAttributes(lp);
            }
        });
        window.showAtLocation(activity.getWindow().getDecorView(), Gravity.CENTER, 0, 0);
    }
    private void zcsuceessfulpopwindows(){
        PopupWindow popupWindow;
        View contentView = LayoutInflater.from(getContext()).inflate(R.layout.popwindows_zhuanmasuccessful, null, false);
        popupWindow= new PopupWindow(contentView,  ViewGroup.LayoutParams.WRAP_CONTENT,  ViewGroup.LayoutParams.WRAP_CONTENT, true);
        // 设置PopupWindow的背景
        popupWindow.setBackgroundDrawable(new ColorDrawable(0xff2581ff));
        // 设置PopupWindow是否能响应外部点击事件
        popupWindow.setOutsideTouchable(true);
        // 设置PopupWindow是否能响应点击事件
        popupWindow.setTouchable(true);
        popupWindow.setOnDismissListener(new PopupWindow.OnDismissListener() {
            //在dismiss中恢复透明度
            public void onDismiss() {
                window.dismiss();
            }
        });
        popupWindow.showAtLocation(activity.getWindow().getDecorView(), Gravity.CENTER, 0, 0);
    }

}
