package com.huiwan.lejiao.huiwan.fragment;

import android.app.ActionBar;
import android.app.Activity;
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
import android.widget.Toolbar;

import com.huiwan.lejiao.huiwan.R;
import com.huiwan.lejiao.huiwan.activity.CheakcodeActivity;
import com.huiwan.lejiao.huiwan.activity.MainActivity;
import com.huiwan.lejiao.huiwan.control.Fama;

/**
 * Created by zou on 2018/3/28.
 */

public class Providepage extends Fragment {
    Button bt_activate_code;
    Button bt_query_code;
    TextView tv_keshengcheng;
    TextView tv_yishengcheng;
    PopupWindow popupWindow;
    Context context;
    Activity activity;
    MainActivity mainActivity;
    View rootview;
    String getcode;
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        rootview=inflater.inflate(R.layout.fragment_providepage,container,false);
        context = this.getActivity();
        activity=getActivity();
        mainActivity=(MainActivity) getActivity();
        bt_query_code=rootview.findViewById(R.id.bt_query_code);
        bt_query_code.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent(activity, CheakcodeActivity.class);
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
                Button bt_quxiao=contentView.findViewById(R.id.bt_quxiao);  //取消按钮
                bt_quxiao.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        popupWindow.dismiss();
                    }
                });
                Button bt_ensure=contentView.findViewById(R.id.bt_ensure_activate_code);//确定按钮
                bt_ensure.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        successfulpopwindows();
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
        initdata();
        return rootview;
    }
    private void initdata(){
        Fama fama=new Fama();
        fama.setfamalistener(new Fama.Huqufama() {
            @Override
            public void huqufamasuccessful(String t) {
                Log.d("5555","可生成码设置成功"+t);
                tv_keshengcheng.setText(t);
            }
            @Override
            public void signfail(String t) {

            }
        });
    }
    public void successfulpopwindows(){
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
        String code="25456555";
        textView.setText(Html.fromHtml("<div>您的授权码为：<span style=\"color:#f02314\">"+code+"</span></div>"));
        Button button=contentView.findViewById(R.id.bt_getcode);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                getcode=textView.getText().toString();  //获取到激活成功的激活码后销毁
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
