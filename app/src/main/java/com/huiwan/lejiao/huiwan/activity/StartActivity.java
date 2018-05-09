package com.huiwan.lejiao.huiwan.activity;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.pm.PackageManager;
import android.graphics.drawable.ColorDrawable;
import android.net.Uri;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.PopupWindow;
import com.lejiaokeji.fentuan.util.Network;
import com.google.gson.Gson;
import com.huiwan.lejiao.huiwan.DataBean.UpAPPBean;
import com.huiwan.lejiao.huiwan.R;
import com.huiwan.lejiao.huiwan.control.Sign_in;
import com.huiwan.lejiao.huiwan.control.StaticValue;

import java.util.Timer;
import java.util.TimerTask;

public class StartActivity extends AppCompatActivity{
    Activity activity;
    Timer timer;
    TimerTask task;
    PopupWindow window;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);// 隐藏标题
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);// 设置全屏
        setContentView(R.layout.activity_startactivity);
        Log.d("ddd","开始页面启动成功");
        activity=this;
     //  "1".substring(5);
        //获取保存的登陆密码
        SharedPreferences pref = getSharedPreferences("SPuser",MODE_PRIVATE);
        //获取保存的已登陆历史账号记录
        SharedPreferences pref2 = getSharedPreferences("ACCOUNT",MODE_PRIVATE);
        final String accountlist=pref2.getString("account","{\"ee\":{\"account\":\"ee\"}}");
        final String name=pref.getString("name","1");
        StaticValue.Account=name;
        final String password=pref.getString("password","1");
        timer=new Timer();
        task=new TimerTask(){
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

                        @Override
                        public void fistlogin() {
                            finish();
                        }

                        @Override
                        public void severerr() {

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
        handler.sendEmptyMessageDelayed(0, 500);


    }
    Handler handler=new Handler(){
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            switch (msg.what) {
                case 0:
                    com.lejiaokeji.fentuan.util.Network network=Network.getnetwork();
                    network.connectnet("{\"type\":\"Android\"}","getversion",StaticValue.url,handler,1);
                    break;
                case 1:
                    String result= (String) msg.obj.toString();
                    Log.d("555",result);
                    Gson gson=new Gson();
                    final UpAPPBean upAPPBean=gson.fromJson(result,UpAPPBean.class);
                    Log.d("5555","服务器版本"+upAPPBean.getAndroidappversion());
                    if (upAPPBean.getAndroidappversion().equals(String.valueOf(getVersionCode(activity)))){
                        timer.schedule(task, 1000);
                    }else {
                        WindowManager.LayoutParams lp = activity.getWindow().getAttributes();
                        lp.alpha = 0.4f;
                        activity.getWindow().setAttributes(lp);
                        View contentView = LayoutInflater.from(activity).inflate(R.layout.popwindows_upapp, null, false);
                        window = new PopupWindow(contentView,  ViewGroup.LayoutParams.WRAP_CONTENT,  ViewGroup.LayoutParams.WRAP_CONTENT, true);
                        // 设置PopupWindow的背景
                        window.setBackgroundDrawable(new ColorDrawable(0xff2581ff));
                        // 设置PopupWindow是否能响应外部点击事件
                        window.setOutsideTouchable(false);
                        // 设置PopupWindow是否能响应点击事件
                        window.setTouchable(true);
                        window.setFocusable(false); // 获取焦点
                        Button button=contentView.findViewById(R.id.bt_getcode);
                        button.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View view) {
                                Uri uri = Uri.parse(upAPPBean.getAndroidURL());
                                Intent intent = new Intent(Intent.ACTION_VIEW, uri);
                                startActivity(intent);
                            }
                        });
                        window.showAtLocation(activity.getWindow().getDecorView(), Gravity.CENTER, 0, 0);
                    }
                    break;
            }




        }
    };
    @Override
    public boolean dispatchTouchEvent(MotionEvent event){
        if(window!=null&&window.isShowing()){
            return false;
        }
        return super.dispatchTouchEvent(event);
    }
    public static int getVersionCode(Context mContext) {
        int versionCode = 0;
        try {
            //获取软件版本号，对应AndroidManifest.xml下android:versionCode
            versionCode = mContext.getPackageManager().
                    getPackageInfo(mContext.getPackageName(), 0).versionCode;
        } catch (PackageManager.NameNotFoundException e) {
            e.printStackTrace();
        }
        Log.d("5555","本地版本"+versionCode);
        return versionCode;
    }
}
