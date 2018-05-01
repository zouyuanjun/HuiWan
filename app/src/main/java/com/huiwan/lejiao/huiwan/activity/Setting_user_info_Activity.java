package com.huiwan.lejiao.huiwan.activity;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.TextView;

import com.google.gson.Gson;
import com.huiwan.lejiao.huiwan.DataBean.DbDataBasic;
import com.huiwan.lejiao.huiwan.DataBean.Signbean;
import com.huiwan.lejiao.huiwan.R;
import com.huiwan.lejiao.huiwan.control.Setting_info;
import com.huiwan.lejiao.huiwan.control.StaticValue;
import com.huiwan.lejiao.huiwan.utils.GetAlerDialog;

public class Setting_user_info_Activity extends AppCompatActivity {

    EditText ed_username;
    EditText ed_userphonenum;
    EditText ed_idcard;
    EditText ed_wechat;
    EditText ed_taobao;
    EditText ed_shangjiphonenum;
    CheckBox cb_yes;
    CheckBox cb_no;
    Button bt_querbangding;
    TextView tv_sjname;
    TextView tv_sjwechat;

    String name;
    String phonenum;
    String idcard;
    String wechat;
    String taobao;
    String sjphonenum;
    Button bt_tijiao;

    static Activity activity;
    Setting_info  setting_info;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_setting_user_info);
        activity=this;
        initview();
    }
    private void initview(){
        setting_info=new Setting_info(activity);
        setting_info.setSetinglistener(new Setting_info.Settingresultlistern() {
            @Override
            public void querysj(String name, String weixin) {
                bt_querbangding.setBackground(activity.getDrawable(R.drawable.list_bt_search_nor));
                tv_sjname.setText(name);
                tv_sjwechat.setText(weixin);
            }

            @Override
            public void Settingsuccessful() {
                finish();
            }
            @Override
            public void Settingfail() {
                bt_querbangding.setBackground(activity.getDrawable(R.drawable.list_bt_search_nor));
                tv_sjname.setText("");
                tv_sjwechat.setText("");
                AlertDialog dialog = GetAlerDialog.getdialog(activity,"系统数据异常","请退出重新登陆或重装软件");
                dialog.show();
            }

            @Override
            public void queryfail() {

                bt_querbangding.setBackground(activity.getDrawable(R.drawable.list_bt_search_nor));
                tv_sjname.setText("");
                tv_sjwechat.setText("");
                AlertDialog dialog = GetAlerDialog.getdialog(activity,"查询失败","请确认上级手机号是否正确");
                dialog.show();
            }
        });
        ed_username=findViewById(R.id.ed_name);
        ed_userphonenum=findViewById(R.id.ed_phonenum);
        ed_idcard=findViewById(R.id.ed_idcard);
        ed_wechat=findViewById(R.id.ed_wechat);
        ed_taobao=findViewById(R.id.ed_taobao);
        ed_shangjiphonenum=findViewById(R.id.ed_sjphonenum);
        cb_yes=findViewById(R.id.cb_yes);
        cb_no=findViewById(R.id.cb_no);
        bt_querbangding=findViewById(R.id.bt_querbangding);
        tv_sjname=findViewById(R.id.tv_sjname);
        ed_taobao=findViewById(R.id.ed_taobao);
        ed_shangjiphonenum=findViewById(R.id.ed_sjphonenum);
        tv_sjwechat=findViewById(R.id.tv_sjwechat);
        bt_tijiao=findViewById(R.id.bt_tijiao);
        bt_tijiao.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                getdate();
            }
        });
        bt_querbangding.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                bt_querbangding.setBackground(activity.getDrawable(R.drawable.list_bt_search_sel));
                sjphonenum=ed_shangjiphonenum.getText().toString();
                if (sjphonenum.length()>10){
                setting_info.querysj(sjphonenum);
                    ((InputMethodManager)getSystemService(Context.INPUT_METHOD_SERVICE)).hideSoftInputFromWindow( activity.getCurrentFocus().getWindowToken(),
                            InputMethodManager.HIDE_NOT_ALWAYS);
                }
            }
        });
    }
    private void getdate(){


        name=ed_username.getText().toString();
        phonenum=ed_userphonenum.getText().toString();
        StaticValue.phone=phonenum;
        idcard=ed_idcard.getText().toString();
        wechat=ed_wechat.getText().toString();
        taobao=ed_taobao.getText().toString();
        sjphonenum=ed_shangjiphonenum.getText().toString();
        StaticValue.exphone=sjphonenum;
        StaticValue.name=name;
        StaticValue.weixin=wechat;
        DbDataBasic setting_info_bean=new DbDataBasic();
        if (name.isEmpty()||phonenum.isEmpty()||idcard.isEmpty()||wechat.isEmpty()||sjphonenum.isEmpty()){
            AlertDialog dialog = GetAlerDialog.getdialog(activity,"提交失败","请填写所有信息再提交");
            dialog.show();
        }else {
            setting_info_bean.setAccount(StaticValue.Account);
            setting_info_bean.setName(name);
            setting_info_bean.setPhonenum(phonenum);
            setting_info_bean.setIdcard(idcard);
            setting_info_bean.setWeixin(wechat);
            setting_info_bean.setTaobao(taobao);
            setting_info_bean.setExphone(sjphonenum);
            setting_info.setinfo(setting_info_bean);

        }

    }
}
