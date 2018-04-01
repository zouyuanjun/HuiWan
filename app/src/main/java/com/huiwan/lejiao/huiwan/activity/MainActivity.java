package com.huiwan.lejiao.huiwan.activity;

import android.app.Activity;
import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.Handler;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.Window;
import android.widget.ImageView;
import android.widget.TextView;

import com.facebook.drawee.backends.pipeline.Fresco;
import com.google.gson.Gson;
import com.huiwan.lejiao.huiwan.DataBean.UserdataBean;
import com.huiwan.lejiao.huiwan.R;
import com.huiwan.lejiao.huiwan.fragment.Homepage;
import com.huiwan.lejiao.huiwan.fragment.Livelypage;
import com.huiwan.lejiao.huiwan.fragment.Myselfpage;
import com.huiwan.lejiao.huiwan.fragment.Providepage;
import com.huiwan.lejiao.huiwan.fragment.Studentpage;
import com.huiwan.lejiao.huiwan.utils.GetSysdata;
import com.huiwan.lejiao.huiwan.viewadapter.ViewPagerAdapter;

import java.util.ArrayList;
import java.util.List;
public class MainActivity extends AppCompatActivity implements View.OnClickListener{
    ImageView im_homepage;
    ImageView im_provide;
    ImageView im_lively;
    ImageView im_studeng;
    ImageView im_my;
    Context context;

    TextView tv_toobar_text;
    private ViewPager viewPager;
    Toolbar toolbar;
    private ViewPagerAdapter viewPagerAdapter;
    private List<Fragment> fragmentlist=new ArrayList<>();

    Handler handler=new Handler(){

    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        Fresco.initialize(this.getApplicationContext());
        //requestWindowFeature(Window.FEATURE_NO_TITLE);      // 隐藏标题
        Log.d("555", "Ma"+GetSysdata.getsysdata().datastring());
        setContentView(R.layout.activity_main);
        context=this;
        toolbar =findViewById(R.id.toolbar);
        toolbar.setTitle("");
        setSupportActionBar(toolbar);
        initview();

    }

    private void  initview(){
        fragmentlist.add(new Homepage());
        fragmentlist.add(new Providepage());
        fragmentlist.add(new Studentpage());
        fragmentlist.add(new Livelypage());
        fragmentlist.add(new Myselfpage());

        viewPager=findViewById(R.id.viewpager);
        im_homepage=findViewById(R.id.im_homepage);
        im_provide=findViewById(R.id.im_provide);
        im_lively=findViewById(R.id.im_lively);
        im_studeng=findViewById(R.id.im_student);
        im_my=findViewById(R.id.im_my);
        im_homepage.setOnClickListener(this);
        im_provide.setOnClickListener(this);
        im_lively.setOnClickListener(this);
        im_studeng.setOnClickListener(this);
        im_my.setOnClickListener(this);
        tv_toobar_text=findViewById(R.id.tv_toolbar_title);
        tv_toobar_text.setText("首页");
        viewPagerAdapter=new ViewPagerAdapter(getSupportFragmentManager(),fragmentlist);
        viewPager.setAdapter(viewPagerAdapter);
        viewPager.setCurrentItem(0);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.im_homepage:
                viewPager.setCurrentItem(0);
                tv_toobar_text.setText("首页");

                break;
            case R.id.im_provide:
                tv_toobar_text.setText("发码");
                viewPager.setCurrentItem(1);

                break;
            case R.id.im_student:
                tv_toobar_text.setText("学员中心");
                viewPager.setCurrentItem(2);
                break;
            case R.id.im_lively:
                tv_toobar_text.setText("活跃度");
                viewPager.setCurrentItem(3);
                break;
            case R.id.im_my:
                tv_toobar_text.setText("个人中心");
                viewPager.setCurrentItem(4);
                break;

        }
    }

}
