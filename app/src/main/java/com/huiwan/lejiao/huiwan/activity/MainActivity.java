package com.huiwan.lejiao.huiwan.activity;

import android.content.Context;
import android.media.Image;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.ImageView;
import android.widget.Toast;

import com.huiwan.lejiao.huiwan.R;
import com.huiwan.lejiao.huiwan.control.Sign_in;
import com.huiwan.lejiao.huiwan.fragment.Homepage;
import com.huiwan.lejiao.huiwan.fragment.Livelypage;
import com.huiwan.lejiao.huiwan.fragment.Myselfpage;
import com.huiwan.lejiao.huiwan.fragment.Providepage;
import com.huiwan.lejiao.huiwan.fragment.Studentpage;
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


    private ViewPager viewPager;

    private ViewPagerAdapter viewPagerAdapter;
    private List<Fragment> fragmentlist=new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //requestWindowFeature(Window.FEATURE_NO_TITLE);      // 隐藏标题
        setContentView(R.layout.activity_main);
        context=this;
        Toolbar toolbar =  findViewById(R.id.toolbar);
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
                Toast.makeText(context,"第一个",Toast.LENGTH_LONG).show();
                break;
            case R.id.im_provide:
                viewPager.setCurrentItem(1);
                Toast.makeText(context,"第二个",Toast.LENGTH_LONG).show();
                break;
            case R.layout.fragment_livelypage:
                viewPager.setCurrentItem(2);
                break;
            case R.layout.fragment_studentpage:
                viewPager.setCurrentItem(3);
                break;
            case R.layout.fragment_myselfpage:
                viewPager.setCurrentItem(4);
                break;

        }
    }
}
