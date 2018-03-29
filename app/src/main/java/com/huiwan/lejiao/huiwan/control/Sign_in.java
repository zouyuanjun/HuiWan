package com.huiwan.lejiao.huiwan.control;

import android.content.Intent;
import android.util.Log;

import com.google.gson.Gson;
import com.huiwan.lejiao.huiwan.activity.MainActivity;
import com.huiwan.lejiao.huiwan.activity.Sign_in_Activity;
import com.huiwan.lejiao.huiwan.obj;
import com.huiwan.lejiao.huiwan.utils.MD5utils;
import com.huiwan.lejiao.huiwan.utils.Network;

import net.sf.json.JSONObject;


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
        this.password = "C97A81FE48CE5BDA4585904661C4021B";
        sign();
    }


    public void sign(){
        encryption_password= MD5utils.encode(password);
        Log.d("5555",encryption_password);
        Network network=Network.getnetwork();
        String a="'{\"username\":hello,b:word}'";
        obj o=new obj("hw.user.list","1","20","C97A81FE48CE5BDA4585904661C4021B");
        Gson gson=new Gson();
        password=gson.toJson(o);
        JSONObject json = new JSONObject();//将java对象转换为json对象
        json.put("type","hw.user.list");
        json.put("page","1");
        json.put("pagesize","20");
        json.put("sign","C97A81FE48CE5BDA4585904661C4021B");
        String str = json.toString();//将json对象转换为字符串

        network.connectnet("{\"type\":\"hw.user.list\",\"page\":\"1\",\"pagesize\":\"20\",\"sign\":\"C97A81FE48CE5BDA4585904661C4021B\"}");
        Intent intent=new Intent(Sign_in_Activity.getactivity(),MainActivity.class);
        Sign_in_Activity.getactivity().startActivity(intent);
    }
}
