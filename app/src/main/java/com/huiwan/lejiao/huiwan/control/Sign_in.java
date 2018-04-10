package com.huiwan.lejiao.huiwan.control;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Handler;
import android.os.Message;
import android.util.Log;

import com.google.gson.*;
import com.google.gson.Gson;
import com.google.gson.JsonElement;
import com.huiwan.lejiao.huiwan.DataBean.DbDataBasic;
import com.huiwan.lejiao.huiwan.DataBean.Signbean;
import com.huiwan.lejiao.huiwan.DataBean.SignchengduBean;
import com.huiwan.lejiao.huiwan.DataBean.UserdataBean;
import com.huiwan.lejiao.huiwan.activity.MainActivity;
import com.huiwan.lejiao.huiwan.activity.Setting_user_info_Activity;
import com.huiwan.lejiao.huiwan.activity.Sign_in_Activity;

import com.huiwan.lejiao.huiwan.utils.MD5utils;
import com.huiwan.lejiao.huiwan.utils.Network;

/**
 * Created by zou on 2018/3/28.
 */

public class Sign_in {
    //String url= "http://localhost:8080//ServletLifeCycleDemo/servlet/TestServlet1";
   String url="http://192.168.2.102:8080/HttpControl/serverControl";
    String username;
    String password;
    Network network;


    String encryption_password;

    public Sign_in(String username, String password) {
        network=Network.getnetwork();
        this.username = username;
        this.password = password;
        sign();
    }
    public  void signchengdu(){
        String url="https://huiwan.hz.taeapp.com/api/index.php?m=home&c=tapi&a=index";
        String appkey="fe3697d98c691f6bd027ace47596e2b6";
        String type="hw.user.login";
        String sign=password+type+username+appkey;
        Log.d("5555","sign字符串"+sign);
        String md5sign=MD5utils.encode(sign).toUpperCase();
        SignchengduBean signchengduBean=new SignchengduBean(username,password,type,md5sign);
        Gson gson=new Gson();
        String requestbody=gson.toJson(signchengduBean);  //将json对象转换为字符
        network.connectnet(requestbody,"login",url,handler,2);
    }
    public void sign(){
        Log.d("5555","自己服务器登陆");
        Signbean signbean=new Signbean(username,password,"","999999","");

        //加密
        encryption_password= MD5utils.encode(password);


        Gson gson=new Gson();
        String requestbody=gson.toJson(signbean);  //将json对象转换为字符
        network.connectnet(requestbody,"login",StaticValue.url,handler,1);
    }
        Handler handler=new Handler() {
            @Override
            public void handleMessage(Message msg) {
                super.handleMessage(msg);
                int what=msg.what;
                if (what==2){  //成都返回的数据
                    String result=msg.obj.toString();
                    JsonElement je = new JsonParser().parse(result);
                    String iserr = je.getAsJsonObject().get("iserr").getAsString();
                    if (iserr.equals("false")){
                        sign();
                    }else {
                        signresult.signfail("fail");
                        Log.d("5555","登陆失败");
                    }
                    Log.d("5555","成都SIGN返回数据"+result);
                }else {
                    String result=msg.obj.toString();
                    Log.d("5555","SIGN返回数据"+result);
                    //对返回的数据进行判断是否登陆成功
                    isture(result);
                }

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
            DbDataBasic dbDataBasic=gson.fromJson(result,DbDataBasic.class);
            StaticValue.phone=dbDataBasic.getPhone();
            Intent intent = new Intent(Sign_in_Activity.getactivity(), MainActivity.class);
            Sign_in_Activity.getactivity().startActivity(intent);
            signresult.signsuccessful();
        }else {
            JsonElement je = new JsonParser().parse(result);
            code = je.getAsJsonObject().get("result").getAsString();
            if (code.equals("2")) {
               // keepdata();
                //登陆成功，跳转到主页

            } else if (code.equals("1")) {
              //  keepdata();
                Intent intent = new Intent(Sign_in_Activity.getactivity(), Setting_user_info_Activity.class);
                Sign_in_Activity.getactivity().startActivity(intent);
                signresult.signsuccessful();

            } else if (code.equals("0")) {
                signresult.signfail("fail");
                Log.d("5555", "登陆失败");
            }
        }
    }
    //将返回回来的结果进行保存
    public void keepdata(){
        UserdataBean userdataBean=new UserdataBean("uid_5456","王麻子","1","15","6","12","65");
        Gson gson=new Gson();
        String user=gson.toJson(userdataBean);  //将json对象转换为字符串
        SharedPreferences sp = Sign_in_Activity.getactivity().getSharedPreferences("SPuser", Activity.MODE_PRIVATE);
        SharedPreferences.Editor editor=sp.edit();
        editor.putString("SIGN",user);
        editor.commit();
        StaticValue.uid=userdataBean.getId();
        StaticValue.benyuemubiao=userdataBean.getBenyuemubiao();
        StaticValue.benyuerenshu=userdataBean.getBenyuerenshu();
        StaticValue.jinrimubiao=userdataBean.getJinrimubiao();
        StaticValue.jinrirenshu=userdataBean.getJinrirenshu();
        StaticValue.name=userdataBean.getName();
        StaticValue.lv=userdataBean.getLv();
    }
}
