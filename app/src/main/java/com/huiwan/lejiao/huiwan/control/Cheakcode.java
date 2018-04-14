package com.huiwan.lejiao.huiwan.control;

import android.os.Handler;
import android.os.Message;
import android.util.Log;

import com.google.gson.Gson;
import com.huiwan.lejiao.huiwan.DataBean.CodeinfoBean;
import com.huiwan.lejiao.huiwan.DataBean.DbDataBasic;
import com.huiwan.lejiao.huiwan.DataBean.Signbean;
import com.huiwan.lejiao.huiwan.utils.Network;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;


public class Cheakcode {
    Network network;
    Gson gson;

    public Cheakcode() {
        network=Network.getnetwork();
        gson=new Gson();
    }
    Handler handler=new Handler(){
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            String result= (String) msg.obj;
            String pattern = "\\{.*?\\}";             //正则匹配
            Pattern r = Pattern.compile(pattern);
            List<CodeinfoBean> list=new ArrayList<>();
            Matcher m = r.matcher(result);
            while(m.find()) {
                CodeinfoBean cheakcode = gson.fromJson(m.group(), CodeinfoBean.class);
                list.add(cheakcode);
            }
            coderesult.getcodeinfosuccessful(list);
            Log.d("555",result);
        }
    };

    public void  getcodeinfo(){
        Signbean signbean=new Signbean("","","","",StaticValue.phone);
        Gson gson=new Gson();
        String requestbody=gson.toJson(signbean);  //将json对象转换为字符
        network.connectnet(requestbody,"findmalist",StaticValue.url,handler,1);
    }

    public interface Coderesult{
        public void getcodeinfosuccessful( List<CodeinfoBean> list);
        public void fail(String t);
    }
    private  Coderesult coderesult;
    public void setcodelistener( Coderesult coderesult){
        this.coderesult=coderesult;
    }


}
