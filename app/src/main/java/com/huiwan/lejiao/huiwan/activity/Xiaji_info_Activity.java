package com.huiwan.lejiao.huiwan.activity;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.TextView;

import com.huiwan.lejiao.huiwan.R;

public class Xiaji_info_Activity extends AppCompatActivity {
    Toolbar toolbar;
    TextView tv_toolbar_xiaji;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_xiaji_info);
        toolbar=findViewById(R.id.toolbar_xiaji);
        tv_toolbar_xiaji=findViewById(R.id.tv_toolbar_xiaji);
        toolbar.setTitle("查看下级");
        tv_toolbar_xiaji.setText("下级信息");
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }
}
