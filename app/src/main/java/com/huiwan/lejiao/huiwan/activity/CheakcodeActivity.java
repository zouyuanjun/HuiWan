package com.huiwan.lejiao.huiwan.activity;

import android.app.Activity;
import android.content.ClipboardManager;
import android.content.Context;
import android.graphics.Color;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.KeyEvent;
import android.view.View;
import android.view.WindowManager;
import android.view.inputmethod.EditorInfo;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.huiwan.lejiao.huiwan.DataBean.CodeinfoBean;
import com.huiwan.lejiao.huiwan.R;
import com.huiwan.lejiao.huiwan.control.Cheakcode;
import com.huiwan.lejiao.huiwan.viewadapter.Codeinfo_Adapter;
import com.mysql.jdbc.log.Log;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
/*
* 查看激活码信息类
* */
public class CheakcodeActivity extends AppCompatActivity  {
    AutoCompleteTextView ed_search_code;    //激活码搜索
    ListView listView;
    ArrayList<CodeinfoBean> arrayList=new ArrayList<>();
    Codeinfo_Adapter adapter;
    Toolbar toolbar;
    TextView tv_toolbar;
    TextView tv_creattime;
    TextView tv_usetime;
    Activity activity;
    ImageView im_usesort;
    ImageView im_creat_sort;
    List<String> strcode=new ArrayList<>();
    Map<String,CodeinfoBean> map=new HashMap<>();
    ImageButton imb_code_search;
    int b=1;
    int a=1;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_ALWAYS_HIDDEN);
        setContentView(R.layout.activity_cheak_codeinfo);
        activity=this;
        toolbar =  findViewById(R.id.toolbar_cheakcode);
        arrayList = (ArrayList<CodeinfoBean>) getIntent().getSerializableExtra("codeinfo");
        strcode.clear();
        map.clear();
        for (CodeinfoBean codeinfoBean:arrayList){
            strcode.add(codeinfoBean.getCode());
            map.put(codeinfoBean.getCode(),codeinfoBean);
        }
        ed_search_code=findViewById(R.id.ed_search_code);
        ed_search_code.setOnEditorActionListener(new TextView.OnEditorActionListener() {
            @Override
            public boolean onEditorAction(TextView v, int actionId, KeyEvent event) {
                if (actionId== EditorInfo.IME_ACTION_SEARCH){
                    if(ed_search_code.getText().toString().isEmpty()){
                        Toast.makeText(activity,"请输入要搜索的激活码",Toast.LENGTH_SHORT);
                    }else {
                        arrayList.clear();
                        String strcode=ed_search_code.getText().toString();
                        arrayList.add(map.get(strcode));
                        adapter.notifyDataSetChanged();
                    }
                }
                return false;
            }
        });
        imb_code_search=findViewById(R.id.imb_code_search);
        imb_code_search.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(ed_search_code.getText().toString().isEmpty()){
                    Toast.makeText(activity,"请输入要搜索的激活码",Toast.LENGTH_SHORT);
                }else {
                    arrayList.clear();
                    String strcode=ed_search_code.getText().toString();
                    arrayList.add(map.get(strcode));
                    adapter.notifyDataSetChanged();
                }

            }
        });
        im_creat_sort=findViewById(R.id.im_codeucreatesort);
        im_usesort=findViewById(R.id.im_codeusesort);
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
        order(1);
        listView.setAdapter(adapter);
        listView.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> parent, View view, int position, long id) {
                String  strcode=arrayList.get(position).getCode();
                ClipboardManager cm = (ClipboardManager) activity.getSystemService(Context.CLIPBOARD_SERVICE);
                Toast.makeText(activity,"复制 "+strcode+" 成功",Toast.LENGTH_SHORT).show();
                cm.setText(strcode);
                return true;
            }
        });
        tv_creattime.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                order(1);
//
                im_creat_sort.setVisibility(View.VISIBLE);
                im_usesort.setVisibility(View.GONE);
                tv_creattime.setTextColor(Color.parseColor("#3ca0f5"));
                tv_usetime.setTextColor(Color.parseColor("#666666"));
            }
        });
        tv_usetime.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                im_usesort.setVisibility(View.VISIBLE);
                im_creat_sort.setVisibility(View.GONE);
                tv_usetime.setTextColor(Color.parseColor("#3ca0f5"));
                tv_creattime.setTextColor(Color.parseColor("#666666"));
                order(2);
            }
        });
        eventsViews();
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
    private void eventsViews() {
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, strcode);
        ed_search_code.setAdapter(adapter);
    }


    public void order(int i) {

        switch (i) {
            case 1: {
                if (a==2){
                    Collections.sort(arrayList, new Comparator<CodeinfoBean>() {
                    @Override
                    public int compare(CodeinfoBean o1, CodeinfoBean o2) {
                        return o1.getGenerate().compareTo(o2.getGenerate());

                    }
                    });
                    adapter.notifyDataSetChanged();
                    a=1;
                }else if (a==1){
                    Collections.sort(arrayList, new Comparator<CodeinfoBean>() {
                        @Override
                        public int compare(CodeinfoBean o1, CodeinfoBean o2) {
                            return o2.getGenerate().compareTo(o1.getGenerate());
                        }
                    });
                    adapter.notifyDataSetChanged();
                    a=2;
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
