package com.huiwan.lejiao.huiwan.activity;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.TextView;

import com.huiwan.lejiao.huiwan.DataBean.dbDataBasic;
import com.huiwan.lejiao.huiwan.R;
import com.huiwan.lejiao.huiwan.control.Setting_info;

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
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_setting_user_info);
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

        dbDataBasic setting_info_bean=new dbDataBasic();
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
