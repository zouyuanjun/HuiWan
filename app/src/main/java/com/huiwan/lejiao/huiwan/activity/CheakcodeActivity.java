package com.huiwan.lejiao.huiwan.activity;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.view.WindowManager;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;


import com.huiwan.lejiao.huiwan.DataBean.CodeBean;
import com.huiwan.lejiao.huiwan.R;
import com.huiwan.lejiao.huiwan.viewadapter.Codeinfo_Adapter;

import java.util.ArrayList;

public class CheakcodeActivity extends AppCompatActivity {
    EditText ed_search_code;
    ListView listView;
    ArrayList<CodeBean> arrayList=new ArrayList<>();
    Codeinfo_Adapter adapter;
    Toolbar toolbar;
    TextView tv_toolbar;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_ALWAYS_HIDDEN);
        setContentView(R.layout.activity_cheak_codeinfo);
        toolbar =  findViewById(R.id.toolbar_cheakcode);

        ed_search_code=findViewById(R.id.ed_search_code);
        listView=findViewById(R.id.lv_code);
        tv_toolbar=findViewById(R.id.tv_cheakcode_toolbar_title);
        tv_toolbar.setText("查看信息");
        toolbar.setTitle("发码");
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

        setdata();
    }
    public void setdata(){
        CodeBean codeBean1=new CodeBean("256541","发射点发生","180506 8:42","170923 15:21","1");
        CodeBean codeBean2=new CodeBean("4854651455","弹道式导弹","185506 8:42","179923 15:21","0");
        arrayList.add(codeBean1);
        arrayList.add(codeBean2);
        arrayList.add(codeBean1);
        arrayList.add(codeBean2);
        arrayList.add(codeBean1);
        arrayList.add(codeBean2);
        arrayList.add(codeBean1);
        adapter=new Codeinfo_Adapter(arrayList);
        listView.setAdapter(adapter);
    }
}
