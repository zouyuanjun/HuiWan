package com.huiwan.lejiao.huiwan.fragment;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.ClipboardManager;
import android.content.Context;
import android.content.Intent;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.text.Html;
import android.util.Log;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.PopupWindow;
import android.widget.TextView;
import android.widget.Toast;

import com.huiwan.lejiao.huiwan.DataBean.CodeinfoBean;
import com.huiwan.lejiao.huiwan.DataBean.Signbean;
import com.huiwan.lejiao.huiwan.R;
import com.huiwan.lejiao.huiwan.activity.CheakcodeActivity;
import com.huiwan.lejiao.huiwan.activity.MainActivity;
import com.huiwan.lejiao.huiwan.control.Fama;
import com.huiwan.lejiao.huiwan.control.StaticValue;
import com.huiwan.lejiao.huiwan.utils.GetAlerDialog;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;

/**
 * Created by zou on 2018/3/28.
 */

public class Famapage extends Fragment {
    Button bt_activate_code;
    Button bt_query_code;
    TextView tv_keshengcheng;
    TextView tv_yishengcheng;
    PopupWindow popupWindow;
    Context context;
    Activity activity;
    MainActivity mainActivity;
    View rootview;
    Fama fama;
    ArrayList<CodeinfoBean> arraylist =new ArrayList<>();

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        rootview=inflater.inflate(R.layout.fragment_providepage,container,false);
        context = this.getActivity();
        activity=getActivity();
        fama=new Fama();
        mainActivity=(MainActivity) getActivity();
        bt_query_code=rootview.findViewById(R.id.bt_query_code);
        bt_query_code.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent(activity, CheakcodeActivity.class);
                Bundle bundle = new Bundle();
                bundle.putSerializable("codeinfo", arraylist);
                intent.putExtras(bundle);
                startActivity(intent);
            }
        });
        bt_activate_code=rootview.findViewById(R.id.bt_activate_code);
        bt_activate_code.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                WindowManager.LayoutParams lp = activity.getWindow().getAttributes();
                lp.alpha = 0.4f;
                activity.getWindow().setAttributes(lp);
                View contentView = LayoutInflater.from(context).inflate(R.layout.popwindows_activate_code, null, false);
                TextView textView=contentView.findViewById(R.id.tv_activate_successful_code);
                textView.setText("当前可生成授权码数："+String.valueOf(StaticValue.kezhuangmashu));
                Button bt_quxiao=contentView.findViewById(R.id.bt_quxiao);  //取消按钮
                bt_quxiao.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        popupWindow.dismiss();
                    }
                });
                Button bt_ensure=contentView.findViewById(R.id.bt_ensure_activate_code);//确定激活按钮
                bt_ensure.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        Signbean signbean=new Signbean("","","","",StaticValue.phone);
                        if (StaticValue.kezhuangmashu>=1){
                            fama.creatcode(signbean);
                        }else {
                            AlertDialog dialog = GetAlerDialog.getdialog(activity,"激活失败","请确认是否有码可以激活");
                            dialog.show();
                        }
                    }
                });
                popupWindow = new PopupWindow(contentView, ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT, true);
                // 设置PopupWindow的背景
                popupWindow.setBackgroundDrawable(new ColorDrawable(0xff2581ff));
                // 设置PopupWindow是否能响应外部点击事件
                popupWindow.setOutsideTouchable(true);

                popupWindow.setAnimationStyle(R.style.Widget_AppCompat_PopupWindow);
                // 设置PopupWindow是否能响应点击事件
                popupWindow.setTouchable(true);
                popupWindow.setOnDismissListener(new PopupWindow.OnDismissListener() {
                    //在dismiss中恢复透明度
                    public void onDismiss() {
                        WindowManager.LayoutParams lp = activity.getWindow().getAttributes();
                        lp.alpha = 1f;
                        activity.getWindow().setAttributes(lp);
                    }
                });
                popupWindow.showAtLocation(activity.getWindow().getDecorView(), Gravity.CENTER, 0, 0);
            }
        });
        tv_keshengcheng=rootview.findViewById(R.id.tv_keshencheng);

        tv_yishengcheng=rootview.findViewById(R.id.tv_yishengcheng);
        tv_keshengcheng.setText(String.valueOf(StaticValue.kezhuangmashu));
        initdata();
        fama.getcodeinfo();
        Log.d("555","fama噜噜onCreateView");
        return rootview;
    }
    @Override
    public void setUserVisibleHint(boolean isVisibleToUser) {
        super.setUserVisibleHint(isVisibleToUser);
        if (isVisibleToUser){


            Log.d("famapage","发码可见了");

        }
    }


    private void initdata(){
        fama.setfamalistener(new Fama.Huqufama() {
            @Override
            public void getcodeinfofail() {
                tv_yishengcheng.setText("0");
            }

            @Override
            public void getcodeinfosuccessful(ArrayList<CodeinfoBean> list1) {
                arraylist =list1;
                tv_yishengcheng.setText(String.valueOf(arraylist.size()));
            }
            @Override
            public void huqufamasuccessful(String str) {
                Log.d("famapage","可生成码设置成功1"+str);
                StaticValue.kezhuangmashu--;
                //激活成功则可用码数减1
                tv_keshengcheng.setText(String.valueOf(StaticValue.kezhuangmashu));
                fama.getcodeinfo();
                successfulpopwindows(str);
            }
            @Override
            public void fail(String str) {

            }
        });

    }
    public void successfulpopwindows(final String str){
        final PopupWindow window;
        View contentView = LayoutInflater.from(context).inflate(R.layout.popwindows_activate_successful, null, false);
        window = new PopupWindow(contentView,  ViewGroup.LayoutParams.WRAP_CONTENT,  ViewGroup.LayoutParams.WRAP_CONTENT, true);
        // 设置PopupWindow的背景
        window.setBackgroundDrawable(new ColorDrawable(0xff2581ff));
        // 设置PopupWindow是否能响应外部点击事件
        window.setOutsideTouchable(true);
        // 设置PopupWindow是否能响应点击事件
        window.setTouchable(true);
        final TextView textView=contentView.findViewById(R.id.tv_activate_successful_code);
        textView.setText(Html.fromHtml("<div>您的授权码为：<span style=\"color:#f02314\">"+str+"</span></div>"));
        Button button=contentView.findViewById(R.id.bt_getcode);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ClipboardManager cm = (ClipboardManager) getActivity().getSystemService(Context.CLIPBOARD_SERVICE);
                Toast.makeText(getActivity(),"复制成功",Toast.LENGTH_SHORT).show();
                cm.setText(str);
                window.dismiss();
            }
        });
        window.setOnDismissListener(new PopupWindow.OnDismissListener() {
            @Override
            public void onDismiss() {
                popupWindow.dismiss();
            }
        });
        window.showAtLocation(activity.getWindow().getDecorView(), Gravity.CENTER, 0, 0);
    }


}
