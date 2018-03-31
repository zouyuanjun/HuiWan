package com.huiwan.lejiao.huiwan.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.TextView;

import com.huiwan.lejiao.huiwan.DataBean.PersonalinfoBean;
import com.huiwan.lejiao.huiwan.R;
import com.huiwan.lejiao.huiwan.viewadapter.Chakanxiaji_Adapter;
import com.huiwan.lejiao.huiwan.viewadapter.Studentinfo_list_Adapter;

import java.util.ArrayList;

public class Chakanxiaji_Activity extends AppCompatActivity{
    ListView listView;
    Toolbar toolbar;
    TextView tv_toolbae;
    ArrayList<PersonalinfoBean> arrayList=new ArrayList<>();
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.avtivity_chakanxiaji);
        toolbar=findViewById(R.id.toolbar_chakanxiaji);
        toolbar.setTitle("学员信息");
        tv_toolbae=findViewById(R.id.tv_toolbar_chakanxiaji);
        tv_toolbae.setText("查看下级");
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
        listView=findViewById(R.id.lv_chakanxiaji);
        setdata();
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
        Chakanxiaji_Adapter adapter=new Chakanxiaji_Adapter(arrayList);
        listView.setAdapter(adapter);
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Intent intent=new Intent(Chakanxiaji_Activity.this,Xiaji_info_Activity.class);
                startActivity(intent);
            }
        });

    }
}
