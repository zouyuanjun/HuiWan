package com.huiwan.lejiao.huiwan.utils;

import android.app.Notification;
import android.os.Message;
import android.preference.PreferenceActivity;
import android.util.Log;

import java.io.IOException;
import okhttp3.Call;
import okhttp3.Callback;

import okhttp3.MediaType;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;


/**
 * Created by zou on 2018/3/28.
 */

public class Network {
    private static Network instance=new Network();
    public static Network getnetwork(){
        return instance;
    }
    private Network() {

    }
    public void connectnet(String date , final android.os.Handler handler){
        Log.d("55555","network中的发起请求的"+Thread.currentThread().getId());
        OkHttpClient client = new OkHttpClient();//创建OkHttpClient对象。
        MediaType JSON = MediaType.parse("application/json; charset=utf-8");//数据类型为json格式，
        String jsonStr = date;//json数据.

        RequestBody body = RequestBody.create(JSON, jsonStr);

        Request request = new Request.Builder()
                .url("https://huiwan.hz.taeapp.com/api/index.php?m=home&c=tapi&a=index")
                .post(body)
                .build();
        client.newCall(request).enqueue(new Callback() {

            @Override
            public void onFailure(Call call, IOException e) {

            }
            @Override
            public void onResponse(Call call, Response response) throws IOException {
                Log.d("55555","networ中返回成功的"+Thread.currentThread().getId());
               Message message=new Message();
               String s=response.body().string();
               message.obj=s;
                handler.sendMessage(message);
               Log.d("5555","返回数据"+s);
            }
        });
    }

}
