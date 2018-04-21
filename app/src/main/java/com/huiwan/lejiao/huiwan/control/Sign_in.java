package com.huiwan.lejiao.huiwan.control;

import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Handler;
import android.os.Looper;
import android.os.Message;
import android.util.Log;

import com.google.gson.*;
import com.google.gson.Gson;
import com.google.gson.JsonElement;
import com.huiwan.lejiao.huiwan.DataBean.DbDataBasic;
import com.huiwan.lejiao.huiwan.DataBean.Signbean;
import com.huiwan.lejiao.huiwan.DataBean.UsermubiaoBean;
import com.huiwan.lejiao.huiwan.activity.MainActivity;
import com.huiwan.lejiao.huiwan.activity.Setting_user_info_Activity;
import com.huiwan.lejiao.huiwan.activity.Sign_in_Activity;

import com.huiwan.lejiao.huiwan.utils.Network;

import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;

/**
 * Created by zou on 2018/3/28.
 */

public class Sign_in {
    String username;
    String password;
    Network network;
    Activity activity;
    public Sign_in(String username, String password,Activity activity) {
        network=Network.getnetwork();
        this.activity=activity;
        this.username = username;
        this.password = password;
        sign();
    }
    public void sign(){
        Signbean signbean=new Signbean(username,password,"","999999","");
        Gson gson=new Gson();
        String requestbody=gson.toJson(signbean);  //将json对象转换为字符
        network.connectnet(requestbody,"login",StaticValue.url,handler,1);
    }
        Handler handler=new Handler(Looper.getMainLooper()) {
            @Override
            public void handleMessage(Message msg) {
                super.handleMessage(msg);
                int what=msg.what;

                    String result=msg.obj.toString();
                    Log.d("5555","SIGN返回数据"+result);
                    //对返回的数据进行判断是否登陆成功
                    isture(result);
            }
        };
    public interface Signresult{
        public void signsuccessful();
        public void signfail(String t);
    }
    private  Signresult signresult;
    public void setsignlistener( Signresult signresult1){
        this.signresult=signresult1;
    }

    public void isture(String result){
        String code="0";
        //如果是第一次登陆，跳到信息设置界面
        //if(result=="1")
        //登陆成功则跳转到主页
        if (result.length()>22){
            Gson gson=new Gson();
           try {
               DbDataBasic dbDataBasic=gson.fromJson(result,DbDataBasic.class);
               StaticValue.phone=dbDataBasic.getPhone();       //保存自己电话号码信息
               StaticValue.exphone=dbDataBasic.getExphone();   //保存上级电话号码信息
               StaticValue.kezhuangmashu=dbDataBasic.getCodenum();
               StaticValue.name=dbDataBasic.getName();
               StaticValue.weixin=dbDataBasic.getWeixin();
               StaticValue.dbDataBasic=dbDataBasic;
           }catch (JsonSyntaxException e){
               signresult.signfail("fail");
           }

            Intent intent = new Intent(activity, MainActivity.class);
            activity.startActivity(intent);
            signresult.signsuccessful();
            keepdata();
        }else {
            JsonElement je = new JsonParser().parse(result);
            try {
                code = je.getAsJsonObject().get("result").getAsString();
            }catch (IllegalStateException e){
                signresult.signfail("fail");
            }

            if (code.equals("11")) {
                Intent intent = new Intent(activity, Setting_user_info_Activity.class);
                activity.startActivity(intent);
                signresult.signsuccessful();
                keepdata();

            }  else if (code.equals("12")) {
                signresult.signfail("fail");
                Log.d("5555", "登陆失败");
            }
        }
    }

    public void keepdata(){

        SharedPreferences sp = activity.getSharedPreferences("SPuser", Activity.MODE_PRIVATE);
        SharedPreferences.Editor editor=sp.edit();
        editor.putString("name",username);
        editor.putString("password",password);
        editor.commit();

    }

}
