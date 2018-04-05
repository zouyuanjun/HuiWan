package com.huiwan.lejiao.huiwan.control;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Handler;
import android.os.Message;
import android.util.Log;

import com.google.gson.Gson;
import com.huiwan.lejiao.huiwan.DataBean.Signbean;
import com.huiwan.lejiao.huiwan.DataBean.UserdataBean;
import com.huiwan.lejiao.huiwan.R;
import com.huiwan.lejiao.huiwan.activity.MainActivity;
import com.huiwan.lejiao.huiwan.activity.Setting_user_info_Activity;
import com.huiwan.lejiao.huiwan.activity.Sign_in_Activity;
import com.huiwan.lejiao.huiwan.obj;
import com.huiwan.lejiao.huiwan.utils.GetSysdata;
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

    String encryption_password;

    public Sign_in(String username, String password) {
        this.username = username;
        this.password = password;
        sign();
    }
    public void sign(){

        Signbean signbean=new Signbean(username,password);

        //加密
        encryption_password= MD5utils.encode(password);

        Network network=Network.getnetwork();
//        String appkey="fe3697d98c691f6bd027ace47596e2b6";
//        String type="hw.user.list";
//        String page="2";
//        String pagesize="50";
//        String sign=MD5utils.encode("250"+type+appkey).toUpperCase();
//        Log.d("555","SIGN"+sign);
//        obj o=new obj(type,page,pagesize,sign);
        Gson gson=new Gson();
        String requestbody=gson.toJson(signbean);  //将json对象转换为字符串
        network.connectnet(requestbody,"login",handler,1);
    }
        Handler handler=new Handler() {
            @Override
            public void handleMessage(Message msg) {
                super.handleMessage(msg);
                String result=msg.obj.toString();
                Log.d("5555","SIGN返回数据"+result);
                //对返回的数据进行判断
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
        //如果是第一次登陆，跳到信息设置界面
        //if(result=="1")
        //登陆成功则跳转到主页
        if (result.length()>2){
            keepdata();

            //登陆成功，跳转到主页
            Intent intent=new Intent(Sign_in_Activity.getactivity(),MainActivity.class);
            Sign_in_Activity.getactivity().startActivity(intent);
            signresult.signsuccessful();
        }else if (result.equals("1")){
            keepdata();
            Intent intent=new Intent(Sign_in_Activity.getactivity(),Setting_user_info_Activity.class);
            Sign_in_Activity.getactivity().startActivity(intent);
            signresult.signsuccessful();

        }else if (result.equals("0")){
            signresult.signfail("fail");
            Log.d("5555","登陆失败");
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
        StaticValue.id=userdataBean.getId();
        StaticValue.benyuemubiao=userdataBean.getBenyuemubiao();
        StaticValue.benyuerenshu=userdataBean.getBenyuerenshu();
        StaticValue.jinrimubiao=userdataBean.getJinrimubiao();
        StaticValue.jinrirenshu=userdataBean.getJinrirenshu();
        StaticValue.name=userdataBean.getName();
        StaticValue.lv=userdataBean.getLv();
    }
}
