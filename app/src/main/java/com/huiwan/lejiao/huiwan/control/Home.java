package com.huiwan.lejiao.huiwan.control;


import android.os.Handler;
import android.os.Message;
import android.util.Log;

import com.google.gson.Gson;
import com.google.gson.JsonSyntaxException;
import com.huiwan.lejiao.huiwan.DataBean.DbDataBasic;
import com.huiwan.lejiao.huiwan.DataBean.LowerMgr;
import com.huiwan.lejiao.huiwan.DataBean.Signbean;
import com.huiwan.lejiao.huiwan.DataBean.UsermubiaoBean;
import com.lejiaokeji.fentuan.util.Network;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

//homepage页面控制类
public class Home {
    Gson gson;
    com.lejiaokeji.fentuan.util.Network network;
    Handler handler=new Handler() {
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            int what=msg.what;
            if (what==1){
                String result= (String) msg.obj;
                Log.d("5555HomePage","返回数据"+result);
                try {
                    DbDataBasic dbDataBasic=gson.fromJson(result,DbDataBasic.class);
                    StaticValue.kezhuangmashu=dbDataBasic.getCodenum();
                    StaticValue.phone=dbDataBasic.getPhone();
                    StaticValue.dbDataBasic=dbDataBasic;
                    homeresult.signsuccessful(dbDataBasic);
                }catch (JsonSyntaxException e){
                    homeresult.gethomefail();
                }

            }
            if (what==2){
                String result= (String) msg.obj;
                Log.d("5555HomePage","返回目标数据"+result);
                UsermubiaoBean usermubiaoBean=gson.fromJson(result,UsermubiaoBean.class);
                homeresult.getmubiao(usermubiaoBean);
                result=result.substring(2,result.length()-1);
                String pattern = "\\{.*?\\}";             //正则匹配
                Pattern r = Pattern.compile(pattern);
                Matcher m = r.matcher(result);
                while(m.find()) {
                    try {
                        LowerMgr lowerMgr = gson.fromJson(m.group(), LowerMgr.class);
                        homeresult.getlever(lowerMgr);
                    }catch (JsonSyntaxException e){
                    }
                }
            }
        }
    };

    public Home() {
        gson=new Gson();
        network=Network.getnetwork();
        //发送uid向服务器请求数据
        Signbean signbean=new Signbean("","","","999999",StaticValue.phone);
        Gson gson=new Gson();
        String body=gson.toJson(signbean);
        network.connectnet(body,"getbasic",StaticValue.url,handler,1);  //请求个人信息
        network.connectnet(body,"getmubiao",StaticValue.url,handler,2);  //请求目标信息
    }
    public interface Homeresult
    {   public void gethomefail();
        public void signsuccessful( DbDataBasic dbDataBasic);
        public void getmubiao(UsermubiaoBean usermubiaoBean);
        public void getlever(LowerMgr lowerMgr);
    }

    private  Homeresult homeresult;
    public void Sethomelistener( Homeresult homeresult){
        this.homeresult=homeresult;
    }
    public void getmubiao(String PHONE){
        Signbean signbean=new Signbean("","","","999999",PHONE);
        Gson gson=new Gson();
        String body=gson.toJson(signbean);
        network.connectnet(body,"getmubiao",StaticValue.url,handler,2);  //请求目标信息
    }
}
