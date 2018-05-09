package com.huiwan.lejiao.huiwan.activity;

import android.app.Activity;
import android.content.Context;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.os.Handler;
import android.support.v4.app.Fragment;
import android.support.v4.content.ContextCompat;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.facebook.drawee.backends.pipeline.Fresco;
import com.huiwan.lejiao.huiwan.R;
import com.huiwan.lejiao.huiwan.fragment.Homepage;
import com.huiwan.lejiao.huiwan.fragment.Livelypage;
import com.huiwan.lejiao.huiwan.fragment.Myselfpage;
import com.huiwan.lejiao.huiwan.fragment.Famapage;
import com.huiwan.lejiao.huiwan.fragment.Studentpage;
import com.huiwan.lejiao.huiwan.viewadapter.ViewPagerAdapter;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.List;
public class MainActivity extends AppCompatActivity implements View.OnClickListener{
    ImageView im_homepage;
    ImageView im_provide;
    ImageView im_lively;
    ImageView im_student;
    ImageView im_my;
    Context context;
    RecyclerView recyclerView;
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
        Log.d("555", "Ma"+ com.lejiaokeji.fentuan.util.GetSystemdate.getsysdata().datastring());
        setContentView(R.layout.activity_main);
        context=this;
        toolbar =findViewById(R.id.toolbar);
        toolbar.setTitle("");
        setSupportActionBar(toolbar);
        initview();
    }
    private void  initview(){
        fragmentlist.add(new Homepage());
        fragmentlist.add(new Famapage());
        fragmentlist.add(new Studentpage());
        fragmentlist.add(new Livelypage());
        fragmentlist.add(new Myselfpage());
        viewPager=findViewById(R.id.viewpager);
        im_homepage=findViewById(R.id.im_homepage);
        im_provide=findViewById(R.id.im_provide);
        im_lively=findViewById(R.id.im_lively);
        im_student =findViewById(R.id.im_student);
        im_my=findViewById(R.id.im_my);
        im_homepage.setOnClickListener(this);
        im_provide.setOnClickListener(this);
        im_lively.setOnClickListener(this);
        im_student.setOnClickListener(this);
        im_my.setOnClickListener(this);
        tv_toobar_text=findViewById(R.id.tv_toolbar_title);
        tv_toobar_text.setText("首页");
        viewPagerAdapter=new ViewPagerAdapter(getSupportFragmentManager(),fragmentlist);
        viewPager.setAdapter(viewPagerAdapter);
        viewPager.setCurrentItem(0);
        viewPager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {
                switch (position){
                    case 0:
                        im_homepage.setBackground( ContextCompat.getDrawable(context,R.drawable.tool_ic_home_sel));
                        im_provide.setBackground( ContextCompat.getDrawable(context,R.drawable.tool_ic_code_nor));
                        im_student.setBackground(ContextCompat.getDrawable(context,R.drawable.tool_ic_stud_nor));
                        im_my.setBackground(ContextCompat.getDrawable(context,R.drawable.tool_ic_user_nor));
                        im_lively.setBackground( ContextCompat.getDrawable(context,R.drawable.tool_ic_activ_nor));
                        tv_toobar_text.setText("首页");
                        break;
                    case 1:
                        tv_toobar_text.setText("发码");
                        im_homepage.setBackground( ContextCompat.getDrawable(context,R.drawable.tool_ic_home_nor));
                        im_provide.setBackground( ContextCompat.getDrawable(context,R.drawable.tool_ic_code_sel));
                        im_student.setBackground(ContextCompat.getDrawable(context,R.drawable.tool_ic_stud_nor));
                        im_my.setBackground(ContextCompat.getDrawable(context,R.drawable.tool_ic_user_nor));
                        im_lively.setBackground( ContextCompat.getDrawable(context,R.drawable.tool_ic_activ_nor));
                        break;
                    case 2:
                        tv_toobar_text.setText("学员中心");
                        im_homepage.setBackground( ContextCompat.getDrawable(context,R.drawable.tool_ic_home_nor));
                        im_provide.setBackground( ContextCompat.getDrawable(context,R.drawable.tool_ic_code_nor));
                        im_student.setBackground(ContextCompat.getDrawable(context,R.drawable.tool_ic_stud_sel));
                        im_my.setBackground(ContextCompat.getDrawable(context,R.drawable.tool_ic_user_nor));
                        im_lively.setBackground( ContextCompat.getDrawable(context,R.drawable.tool_ic_activ_nor));
                        break;
                    case 3:
                        tv_toobar_text.setText("活跃度");
                        im_homepage.setBackground( ContextCompat.getDrawable(context,R.drawable.tool_ic_home_nor));
                        im_provide.setBackground( ContextCompat.getDrawable(context,R.drawable.tool_ic_code_nor));
                        im_student.setBackground(ContextCompat.getDrawable(context,R.drawable.tool_ic_stud_nor));
                        im_my.setBackground(ContextCompat.getDrawable(context,R.drawable.tool_ic_user_nor));
                        im_lively.setBackground( ContextCompat.getDrawable(context,R.drawable.tool_ic_activ_sel));
                        break;
                    case 4:
                        tv_toobar_text.setText("个人中心");
                        im_homepage.setBackground( ContextCompat.getDrawable(context,R.drawable.tool_ic_home_nor));
                        im_provide.setBackground( ContextCompat.getDrawable(context,R.drawable.tool_ic_code_nor));
                        im_student.setBackground(ContextCompat.getDrawable(context,R.drawable.tool_ic_stud_nor));
                        im_my.setBackground(ContextCompat.getDrawable(context,R.drawable.tool_ic_user_sel));
                        im_lively.setBackground( ContextCompat.getDrawable(context,R.drawable.tool_ic_activ_nor));
                        break;
                }
            }
            @Override
            public void onPageSelected(int position) {
            }
            @Override
            public void onPageScrollStateChanged(int state) {
            }
        });
        viewPagerAdapter.setpagelistener(new ViewPagerAdapter.Viewpageposition() {
            @Override
            public void sendposition(int position) {
            }
        });
    }
    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.im_homepage:
                viewPager.setCurrentItem(0,false);
                im_homepage.setBackground( ContextCompat.getDrawable(context,R.drawable.tool_ic_home_sel));
                im_provide.setBackground( ContextCompat.getDrawable(context,R.drawable.tool_ic_code_nor));
                im_student.setBackground(ContextCompat.getDrawable(context,R.drawable.tool_ic_stud_nor));
                im_my.setBackground(ContextCompat.getDrawable(context,R.drawable.tool_ic_user_nor));
                im_lively.setBackground( ContextCompat.getDrawable(context,R.drawable.tool_ic_activ_nor));
             //   tv_toobar_text.setText("首页");

                break;
            case R.id.im_provide:
              //  tv_toobar_text.setText("发码");
                im_homepage.setBackground( ContextCompat.getDrawable(context,R.drawable.tool_ic_home_nor));
                im_provide.setBackground( ContextCompat.getDrawable(context,R.drawable.tool_ic_code_sel));
                im_student.setBackground(ContextCompat.getDrawable(context,R.drawable.tool_ic_stud_nor));
                im_my.setBackground(ContextCompat.getDrawable(context,R.drawable.tool_ic_user_nor));
                im_lively.setBackground( ContextCompat.getDrawable(context,R.drawable.tool_ic_activ_nor));
                viewPager.setCurrentItem(1,false);

                break;
            case R.id.im_student:
            //    tv_toobar_text.setText("学员中心");
                im_homepage.setBackground( ContextCompat.getDrawable(context,R.drawable.tool_ic_home_nor));
                im_provide.setBackground( ContextCompat.getDrawable(context,R.drawable.tool_ic_code_nor));
                im_student.setBackground(ContextCompat.getDrawable(context,R.drawable.tool_ic_stud_sel));
                im_my.setBackground(ContextCompat.getDrawable(context,R.drawable.tool_ic_user_nor));
                im_lively.setBackground( ContextCompat.getDrawable(context,R.drawable.tool_ic_activ_nor));
                viewPager.setCurrentItem(2,false);
                break;
            case R.id.im_lively:
            //    tv_toobar_text.setText("活跃度");

                im_homepage.setBackground( ContextCompat.getDrawable(context,R.drawable.tool_ic_home_nor));
                im_provide.setBackground( ContextCompat.getDrawable(context,R.drawable.tool_ic_code_nor));
                im_student.setBackground(ContextCompat.getDrawable(context,R.drawable.tool_ic_stud_nor));
                im_my.setBackground(ContextCompat.getDrawable(context,R.drawable.tool_ic_user_nor));
                im_lively.setBackground( ContextCompat.getDrawable(context,R.drawable.tool_ic_activ_sel));
                viewPager.setCurrentItem(3,false);
                viewPager.setCurrentItem(3);
                break;
            case R.id.im_my:
             //   tv_toobar_text.setText("个人中心");
//                try {
//                    Field field = viewPager.getClass().getDeclaredField("mCurItem");//参数mCurItem是系统自带的
//                    field.setAccessible(true);
//                    field.setInt(viewPager,4);
//                }catch (Exception e){
//                    e.printStackTrace();
//                }

//然后调用下面的函数刷新数据
                viewPagerAdapter.notifyDataSetChanged();
//再调用setCurrentItem()函数设置一次
              //  viewPager.setCurrentItem(4,false);
                viewPager.setCurrentItem(4);
                im_homepage.setBackground( ContextCompat.getDrawable(context,R.drawable.tool_ic_home_nor));
                im_provide.setBackground( ContextCompat.getDrawable(context,R.drawable.tool_ic_code_nor));
                im_student.setBackground(ContextCompat.getDrawable(context,R.drawable.tool_ic_stud_nor));
                im_my.setBackground(ContextCompat.getDrawable(context,R.drawable.tool_ic_user_sel));
                im_lively.setBackground( ContextCompat.getDrawable(context,R.drawable.tool_ic_activ_nor));
         //       viewPager.setCurrentItem(4);
                break;

        }
    }

}
