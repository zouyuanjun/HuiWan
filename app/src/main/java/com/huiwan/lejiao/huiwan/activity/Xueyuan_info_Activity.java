package com.huiwan.lejiao.huiwan.activity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.huiwan.lejiao.huiwan.DataBean.DbDataBasic;
import com.huiwan.lejiao.huiwan.DataBean.PersonalinfoBean;
import com.huiwan.lejiao.huiwan.DataBean.UsermubiaoBean;
import com.huiwan.lejiao.huiwan.R;
import com.huiwan.lejiao.huiwan.control.Xueyuan_zhuangma;
import com.huiwan.lejiao.huiwan.viewadapter.Chakanxiaji_Adapter;

import java.util.ArrayList;
import java.util.List;

public class Xueyuan_info_Activity extends AppCompatActivity {
    Toolbar toolbar;
    Button bt_chakanxiaji;
    TextView tv_jinrixinzen;
    TextView tv_jinrimubiao;
    TextView tv_toolbar_xiaji;;
    Activity activity;

    TextView tv_name;
    TextView tv_phone;
    TextView tv_weixin;
    ArrayList<PersonalinfoBean> arraylist=new ArrayList();
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_xueyuaninfo);
        activity=this;
        toolbar=findViewById(R.id.toolbar_xunyuan);
        tv_toolbar_xiaji=findViewById(R.id.tv_toolbar_xueyuan);

        tv_name=findViewById(R.id.tv_xueyuan_name);
        tv_phone=findViewById(R.id.tv_xueyuan_phone);
        tv_weixin=findViewById(R.id.tv_xueyuan_weixin);

        tv_name.setText(getIntent().getStringExtra("name"));
        tv_phone.setText(getIntent().getStringExtra("phone"));
        tv_weixin.setText(getIntent().getStringExtra("weixin"));

        tv_toolbar_xiaji.setText("学员信息");
        toolbar.setTitle("学员中心");
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

        tv_jinrimubiao=findViewById(R.id.tv_xueyuan_jinrimubiao);
        tv_jinrixinzen=findViewById(R.id.tv_xueyuan_jinrixinzen);
        bt_chakanxiaji=findViewById(R.id.bt_xuyuan_chakanxiaji);
        bt_chakanxiaji.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (arraylist.size()==0){
                    Toast.makeText(activity,"您还没有下级",Toast.LENGTH_SHORT).show();
                }else {
                    Intent intent=new Intent(Xueyuan_info_Activity.this,Chakanxiaji_Activity.class);
                    Bundle bundle = new Bundle();
                    bundle.putSerializable("xiajiliebiao", arraylist);
                    intent.putExtras(bundle);
                    startActivity(intent);
                }

            }
        });
        getxiaji();
    }
    public  void getxiaji(){
        Xueyuan_zhuangma xueyuan_zhuangma=new Xueyuan_zhuangma();
        xueyuan_zhuangma.getxiajiinfo(getIntent().getStringExtra("phone"));
        xueyuan_zhuangma.setXueyuanlistener(new Xueyuan_zhuangma.interface_Xueyuan_zhuanma() {
            @Override
            public void getxiajilist(List<DbDataBasic> list) {
                for (DbDataBasic dbDataBasic:list){
                  PersonalinfoBean personalinfoBean=new PersonalinfoBean(dbDataBasic.getName(),dbDataBasic.getPhone(),dbDataBasic.getWeixin(),"",dbDataBasic.getCodenum());
                    arraylist.add(personalinfoBean);
                }

            }

            @Override
            public void getshangjiinfo(DbDataBasic dataBasic) {

            }

            @Override
            public void tongzhizhuanmacg() {

            }

            @Override
            public void tongzhizhuanmafall() {

            }
        });
    }
}
