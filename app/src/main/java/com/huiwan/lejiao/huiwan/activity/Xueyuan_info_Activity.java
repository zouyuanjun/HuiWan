package com.huiwan.lejiao.huiwan.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.huiwan.lejiao.huiwan.R;
import com.huiwan.lejiao.huiwan.viewadapter.Chakanxiaji_Adapter;

public class Xueyuan_info_Activity extends AppCompatActivity {
    Toolbar toolbar;
    Button bt_zhuangma;
    Button bt_chakanxiaji;
    TextView tv_jinrixinzen;
    TextView tv_jinrimubiao;
    TextView tv_toolbar;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_xueyuaninfo);
        toolbar =  findViewById(R.id.toolbar_xunyuan);
        tv_toolbar=findViewById(R.id.tv_toolbar_xueyuan);
        tv_toolbar.setText("学员信息");
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
                Intent intent=new Intent(Xueyuan_info_Activity.this,Chakanxiaji_Activity.class);
                startActivity(intent);
            }
        });
        bt_zhuangma=findViewById(R.id.bt_xueyuan_zhuangma);
        bt_zhuangma.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });
    }
}
