package com.huiwan.lejiao.huiwan.fragment;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.Intent;
import android.graphics.drawable.ColorDrawable;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.content.ContextCompat;
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

import com.huiwan.lejiao.huiwan.DataBean.DbDataBasic;
import com.huiwan.lejiao.huiwan.DataBean.PersonalinfoBean;
import com.huiwan.lejiao.huiwan.R;
import com.huiwan.lejiao.huiwan.activity.Xueyuan_info_Activity;
import com.huiwan.lejiao.huiwan.control.StaticValue;
import com.huiwan.lejiao.huiwan.control.Xueyuan_zhuangma;
import com.huiwan.lejiao.huiwan.utils.GetAlerDialog;
import com.huiwan.lejiao.huiwan.viewadapter.Studentinfo_list_Adapter;

import java.util.ArrayList;
import java.util.List;

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
    Activity activity;
    Xueyuan_zhuangma xueyuan_zhuangma;
    PopupWindow window;
    TextView tv_kzms;                       //可转码数
    TextView tv_qbzc;               //全部转出
    Studentinfo_list_Adapter adapter;

    TextView tv_noxueyuan;

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
        tv_noxueyuan=rootview.findViewById(R.id.tv_meiyouxueyuan);
        activity=getActivity();
        adapter=new Studentinfo_list_Adapter(activity,arrayList);
        listView.setAdapter(adapter);
        listView.setOnItemClickListener(this);
        adapter.setsignlistener(new Studentinfo_list_Adapter.Zhuanma() {
            @Override
            public void tongzhizhuanma(int t) {
                String tophone=arrayList.get(t).getPhonenum();
                zhuanmapopwindows(tophone,arrayList.get(t).getName());
            }
        });
        return rootview;
    }
    @Override
    public void setUserVisibleHint(boolean isVisibleToUser) {
        super.setUserVisibleHint(isVisibleToUser);
        if (isVisibleToUser){
            setdata();
        }
    }
    public void setdata(){
        xueyuan_zhuangma=new Xueyuan_zhuangma();
        xueyuan_zhuangma.getshangjiinfo();
        xueyuan_zhuangma.getxiajiinfo(StaticValue.phone);

        final String url="http://img4.duitang.com/uploads/item/201602/07/20160207104805_nmcdP.thumb.700_0.jpeg";

        xueyuan_zhuangma.setXueyuanlistener(new Xueyuan_zhuangma.interface_Xueyuan_zhuanma() {
            @Override
            public void getxiajilist(List<DbDataBasic> list) {
                tv_noxueyuan.setVisibility(View.GONE);
                arrayList.clear();
                    for (DbDataBasic dbDataBasic:list){
                        PersonalinfoBean personalinfoBean=new PersonalinfoBean(dbDataBasic.getName(),dbDataBasic.getPhone(),dbDataBasic.getWeixin(),String.valueOf(dbDataBasic.getSex()),dbDataBasic.getCodenum());
                        arrayList.add(personalinfoBean);
                    }
                adapter.notifyDataSetChanged();
            }
            @Override
            public void getshangjiinfo(DbDataBasic dbDataBasic) {
                if (dbDataBasic.getLever()==1){
                    im_sjduanwei.setBackground( ContextCompat.getDrawable(activity,R.drawable.stud_ic_lv1));
                }else if (dbDataBasic.getLever()==2){
                    im_sjduanwei.setBackground( ContextCompat.getDrawable(activity,R.drawable.stud_ic_lv2));
                }else if (dbDataBasic.getLever()==3){
                    im_sjduanwei.setBackground( ContextCompat.getDrawable(activity,R.drawable.stud_ic_lv3));
                }else if (dbDataBasic.getLever()==4){
                    im_sjduanwei.setBackground( ContextCompat.getDrawable(activity,R.drawable.stud_ic_lv4));
                }else if (dbDataBasic.getLever()==5){
                    im_sjduanwei.setBackground( ContextCompat.getDrawable(activity,R.drawable.stud_ic_lv5));
                }else if (dbDataBasic.getLever()==0){

                }
              //  im_sjphoto.setBackground(getActivity().getDrawable(R.drawable.pfile_ic_portrait));
                tv_sjname.setText("姓名:"+dbDataBasic.getName());
                tv_sjshouji.setText("手机:"+dbDataBasic.getPhone());
                tv_sjweixin.setText("微信:"+dbDataBasic.getWeixin());
                if (dbDataBasic.getPhone().equals("88888888888")){
                    im_sjphoto.setImageURI((new Uri.Builder()).scheme("res").path(String.valueOf(R.drawable.logo)).build());
                }else {
                    if (dbDataBasic.getSex()==1){
                        im_sjphoto.setImageURI((new Uri.Builder()).scheme("res").path(String.valueOf(R.drawable.men)).build());
                    } else if (dbDataBasic.getSex()==2){
                        im_sjphoto.setImageURI((new Uri.Builder()).scheme("res").path(String.valueOf(R.drawable.girl)).build());
                    } else {
                        im_sjphoto.setImageURI((new Uri.Builder()).scheme("res").path(String.valueOf(R.drawable.studmeg_ic_portrait)).build());
                    }
                }




            }

            @Override
            public void tongzhizhuanmacg() {
                tv_kzms.setText("可转码数："+StaticValue.kezhuangmashu);
                zcsuceessfulpopwindows();
            }

            @Override
            public void tongzhizhuanmafall() {
                AlertDialog dialog = GetAlerDialog.getdialog(activity,"转码失败","您好像没有足够的码可以转哦。");
                dialog.show();
            }
        });
    }

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {   //跳到学员界面
        String xiajiphone=arrayList.get(position).getPhonenum();
        Intent intent=new Intent(getActivity(), Xueyuan_info_Activity.class);
        intent.putExtra("phone",xiajiphone);
        intent.putExtra("name",arrayList.get(position).getName());
        intent.putExtra("weixin",arrayList.get(position).getWeichat());
        startActivity(intent);
    }
    public void zhuanmapopwindows(final String tophone,String name){
        final int[] shezhizhuangma = new int[1];  //设置的转码数量
        Button bt_ensure_zhuanma;
        Button bt_jia;                            //加
        Button bt_jian;                          //减
        final TextView tv_shezhizhuangma;             //设置的转码数


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
        TextView toname=contentView.findViewById(R.id.tv_zhuanmaname);
        toname.setText(name);
        final int codenum=StaticValue.kezhuangmashu;
        tv_kzms.setText("可转码数："+codenum);
        tv_qbzc.setOnClickListener(new View.OnClickListener() {  //全部转出按钮
            @Override
            public void onClick(View v) {
                tv_shezhizhuangma.setText(String.valueOf(codenum));
            }
        });
        //加按钮监听
        bt_jia.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
               shezhizhuangma[0] = xueyuan_zhuangma.zhuanmajia();
               //+1
               tv_shezhizhuangma.setText(String.valueOf(shezhizhuangma[0]));
            }
        });
        //减按钮监听
        bt_jian.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //-1
                shezhizhuangma[0] =xueyuan_zhuangma.zhuanmajian();
                tv_shezhizhuangma.setText(String.valueOf(shezhizhuangma[0]));
            }
        });

        //确认转码
        bt_ensure_zhuanma.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
              xueyuan_zhuangma.ensurezhuangchu(StaticValue.phone,tophone);
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
