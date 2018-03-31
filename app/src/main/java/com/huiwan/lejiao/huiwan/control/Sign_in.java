package com.huiwan.lejiao.huiwan.control;

import android.content.Intent;
import android.os.Handler;
import android.os.Message;
import android.util.Log;

import com.google.gson.Gson;
import com.huiwan.lejiao.huiwan.activity.MainActivity;
import com.huiwan.lejiao.huiwan.activity.Sign_in_Activity;
import com.huiwan.lejiao.huiwan.obj;
import com.huiwan.lejiao.huiwan.utils.GetSysdata;
import com.huiwan.lejiao.huiwan.utils.MD5utils;
import com.huiwan.lejiao.huiwan.utils.Network;

/**
 * Created by zou on 2018/3/28.
 */

public class Sign_in {
    String url="https://huiwan.hz.taeapp.com/api/index.php?m=home&c=tapi&a=index";
    String username;
    String password;

    String encryption_password;

    public Sign_in(String username, String password) {
        this.username = username;
        this.password = password;
        sign();
    }
    public void sign(){
        //加密
        encryption_password= MD5utils.encode(password);

        Network network=Network.getnetwork();
        String appkey="fe3697d98c691f6bd027ace47596e2b6";
        String type="hw.user.list";
        String page="2";
        String pagesize="50";
        String sign=MD5utils.encode("250"+type+appkey).toUpperCase();
        Log.d("555","SIGN"+sign);
        obj o=new obj(type,page,pagesize,sign);
        Gson gson=new Gson();
        password=gson.toJson(o);  //将json对象转换为字符串
        network.connectnet(password,handler);
    }
        Handler handler=new Handler() {
            @Override
            public void handleMessage(Message msg) {
                super.handleMessage(msg);
                String result=msg.obj.toString();
                isture(result);

            }
        };
    public interface Signresult{

        public void signsuccessful(String t);
        public void signfail(String t);
    }
    private  Signresult signresult;

    public void setsignlistener( Signresult signresult1){
        this.signresult=signresult1;
    }

    public void isture(String result){
        if (true){
            Intent intent=new Intent(Sign_in_Activity.getactivity(),MainActivity.class);
            Sign_in_Activity.getactivity().startActivity(intent);
            signresult.signsuccessful(result);

        }else {

            signresult.signfail("fail");
            Log.d("5555","登陆失败");
        }
    }
}
