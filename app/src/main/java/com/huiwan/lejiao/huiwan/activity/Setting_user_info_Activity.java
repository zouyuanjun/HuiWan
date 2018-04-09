package com.huiwan.lejiao.huiwan.activity;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.TextView;

import com.huiwan.lejiao.huiwan.DataBean.DbDataBasic;
import com.huiwan.lejiao.huiwan.R;
import com.huiwan.lejiao.huiwan.control.Setting_info;
import com.huiwan.lejiao.huiwan.control.StaticValue;

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
    String sjname;
    String sjwechat;
    Button bt_tijiao;

    static Activity activity;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_setting_user_info);
        activity=this;
        initview();
    }
    private void initview(){
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
    }
    private void getdate(){


        name=ed_username.getText().toString();
        phonenum=ed_userphonenum.getText().toString();
        idcard=ed_idcard.getText().toString();
        wechat=ed_wechat.getText().toString();
        taobao=ed_taobao.getText().toString();
        sjphonenum=ed_shangjiphonenum.getText().toString();
        DbDataBasic setting_info_bean=new DbDataBasic();
        if (name==null||phonenum==null||idcard==null||wechat==null||taobao==null||sjphonenum==null){
            AlertDialog.Builder builder = new AlertDialog.Builder(Sign_in_Activity.getactivity());
            builder.setTitle("提交失败");
            builder.setMessage("请填写所有信息再提交");
            //点击对话框以外的区域是否让对话框消失
            builder.setCancelable(true);
            //设置正面按钮
            builder.setPositiveButton("确定", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int which) {
                    dialog.dismiss();
                }
            });
            AlertDialog dialog = builder.create();
            dialog.show();
        }else {
            setting_info_bean.setAccount(StaticValue.Account);
            setting_info_bean.setName(name);
            setting_info_bean.setPhonenum(phonenum);
            setting_info_bean.setIdcard(idcard);
            setting_info_bean.setWeixin(wechat);
            setting_info_bean.setTaobao(taobao);
            setting_info_bean.setExphone(sjphonenum);
            Setting_info setting_info=new Setting_info(setting_info_bean);
            setting_info.setSetinglistener(new Setting_info.Settingresultlistern() {
                @Override
                public void Settingsuccessful() {
                    finish();
                }
                @Override
                public void Settingfail() {

                }
            });
        }
    }
    public static Activity getactivity(){
        return activity;
    }
}
