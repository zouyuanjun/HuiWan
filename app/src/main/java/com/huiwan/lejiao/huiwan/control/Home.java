package com.huiwan.lejiao.huiwan.control;


import android.os.Handler;
import android.os.Message;
import android.util.Log;

import com.google.gson.Gson;
import com.huiwan.lejiao.huiwan.DataBean.DbDataBasic;
import com.huiwan.lejiao.huiwan.DataBean.Signbean;
import com.huiwan.lejiao.huiwan.utils.Network;

//homepage页面控制类
public class Home {

    Network network;
    Handler handler=new Handler() {
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            String result= (String) msg.obj;
            Log.d("5555HomePage","返回数据"+result);
            Gson gson=new Gson();
            DbDataBasic dbDataBasic=gson.fromJson(result,DbDataBasic.class);
            homeresult.signsuccessful(dbDataBasic);
        }
    };
    public Home() {
        network=Network.getnetwork();
        //发送uid向服务器请求数据
        Signbean signbean=new Signbean("","","","999999","");
        Gson gson=new Gson();
        String body=gson.toJson(signbean);
        network.connectnet(body,"getbasic",handler,1);
    }

    public interface Homeresult
    {
        public void signsuccessful( DbDataBasic dbDataBasic);
        public void signfail(String t);
    }
    private  Homeresult homeresult;
    public void Sethomelistener( Homeresult homeresult){
        this.homeresult=homeresult;
    }
}
