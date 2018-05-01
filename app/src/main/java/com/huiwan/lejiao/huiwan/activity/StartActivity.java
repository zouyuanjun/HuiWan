package com.huiwan.lejiao.huiwan.activity;

import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.Window;
import android.view.WindowManager;
import android.widget.AutoCompleteTextView;
import android.widget.ImageView;

import com.huiwan.lejiao.huiwan.R;
import com.huiwan.lejiao.huiwan.control.Sign_in;
import com.huiwan.lejiao.huiwan.control.StaticValue;

import java.util.Timer;
import java.util.TimerTask;

public class StartActivity extends AppCompatActivity{
    Activity activity;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);// 隐藏标题
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);// 设置全屏
        setContentView(R.layout.activity_startactivity);
        Log.d("ddd","开始页面启动成功");
        activity=this;
     //  "1".substring(5);
        SharedPreferences pref = getSharedPreferences("SPuser",MODE_PRIVATE);
        SharedPreferences pref2 = getSharedPreferences("ACCOUNT",MODE_PRIVATE);
        final String accountlist=pref2.getString("account","{\"ee\":{\"account\":\"ee\"}}");
        final String name=pref.getString("name","1");
        StaticValue.Account=name;
        final String password=pref.getString("password","1");
        Timer timer=new Timer();
        TimerTask task=new TimerTask(){
            public void run(){
                if (!name.equals("1")){
                    Log.d("ddd","开始页面启动成功");
                    Sign_in sign_in=new Sign_in(name,password,activity);
                    //登陆成功则销毁登陆界面
                    sign_in.setsignlistener(new Sign_in.Signresult() {
                        @Override
                        public void signsuccessful() {

                            Intent intent = new Intent(activity, MainActivity.class);
                            activity.startActivity(intent);
                            finish();
                        }
                        //登陆失败弹窗提示
                        @Override
                        public void signfail(String t) {
                            Intent intent = new Intent(activity, Sign_in_Activity.class);
                            intent.putExtra("account",accountlist);
                            startActivity(intent);
                            finish();
                        }
                    });
                    finish();
                }else {
                    Log.d("ddd","开始页面启动失败"+name);
                    Intent intent = new Intent(activity, Sign_in_Activity.class);
                    intent.putExtra("account",accountlist);
                    startActivity(intent);
                    finish();
                }
            }
        };
        timer.schedule(task, 1000);

    }
}
