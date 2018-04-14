package com.huiwan.lejiao.huiwan.activity;

import android.graphics.Color;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.view.WindowManager;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;

import com.huiwan.lejiao.huiwan.DataBean.CodeinfoBean;
import com.huiwan.lejiao.huiwan.R;
import com.huiwan.lejiao.huiwan.control.Cheakcode;
import com.huiwan.lejiao.huiwan.viewadapter.Codeinfo_Adapter;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

public class CheakcodeActivity extends AppCompatActivity {
    EditText ed_search_code;
    ListView listView;
    ArrayList<CodeinfoBean> arrayList=new ArrayList<>();
    Codeinfo_Adapter adapter;
    Toolbar toolbar;
    TextView tv_toolbar;
    TextView tv_creattime;
    TextView tv_usetime;
    int b=1;
    int a=1;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_ALWAYS_HIDDEN);
        setContentView(R.layout.activity_cheak_codeinfo);
        toolbar =  findViewById(R.id.toolbar_cheakcode);
        arrayList = (ArrayList<CodeinfoBean>) getIntent().getSerializableExtra("codeinfo");
        ed_search_code=findViewById(R.id.ed_search_code);
        tv_creattime=findViewById(R.id.tv_order_creattime);
        tv_usetime=findViewById(R.id.tv_order_usetime);
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
        adapter=new Codeinfo_Adapter(arrayList);
        listView.setAdapter(adapter);
        tv_creattime.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                order(1);
                tv_creattime.setTextColor(Color.parseColor("#3ca0f5"));
                tv_usetime.setTextColor(Color.parseColor("#666666"));
            }
        });
        tv_usetime.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                tv_usetime.setTextColor(Color.parseColor("#3ca0f5"));
                tv_creattime.setTextColor(Color.parseColor("#666666"));
                order(2);
            }
        });
        setdata();
    }
    public void setdata(){
//        Cheakcode cheakcode=new Cheakcode();
//        cheakcode.getcodeinfo();
//        cheakcode.setcodelistener(new Cheakcode.Coderesult() {
//            @Override
//            public void getcodeinfosuccessful(List<CodeinfoBean> list) {
//                for (CodeinfoBean codeinfoBean:list){
//                    arrayList.add(codeinfoBean);
//                    adapter.notifyDataSetChanged();
//                }
//            }
//            @Override
//            public void fail(String t) {
//
//            }
//        });


    }


    public void order(int i) {

        switch (i) {
            case 1: {
                if (a==1){
                    Collections.sort(arrayList, new Comparator<CodeinfoBean>() {
                    @Override
                    public int compare(CodeinfoBean o1, CodeinfoBean o2) {
                        return o1.getGenerate().compareTo(o2.getGenerate());

                    }
                    });
                    adapter.notifyDataSetChanged();
                    a=2;
                }else if (a==2){
                    Collections.sort(arrayList, new Comparator<CodeinfoBean>() {
                        @Override
                        public int compare(CodeinfoBean o1, CodeinfoBean o2) {
                            return o2.getGenerate().compareTo(o1.getGenerate());
                        }
                    });
                    adapter.notifyDataSetChanged();
                    a=1;
                }

            }
            case 2:{
                if (b==1){
                    Collections.sort(arrayList, new Comparator<CodeinfoBean>() {
                        @Override
                        public int compare(CodeinfoBean o1, CodeinfoBean o2) {
                            return o1.getUsedate().compareTo(o2.getUsedate());

                        }
                    });
                    adapter.notifyDataSetChanged();
                    b=2;
                }else if (b==2){
                    Collections.sort(arrayList, new Comparator<CodeinfoBean>() {
                        @Override
                        public int compare(CodeinfoBean o1, CodeinfoBean o2) {
                            return o2.getUsedate().compareTo(o1.getUsedate());
                        }
                    });
                    adapter.notifyDataSetChanged();
                    b=1;
                }

            }


        }




    }
}
