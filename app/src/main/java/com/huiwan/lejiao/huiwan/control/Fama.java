package com.huiwan.lejiao.huiwan.control;

import android.os.Handler;
import android.os.Looper;
import android.os.Message;
import android.util.Log;

import com.google.gson.Gson;
import com.google.gson.JsonElement;
import com.google.gson.JsonParser;
import com.google.gson.JsonSyntaxException;
import com.huiwan.lejiao.huiwan.DataBean.CodeinfoBean;
import com.huiwan.lejiao.huiwan.DataBean.Signbean;
import com.huiwan.lejiao.huiwan.utils.Network;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Fama {
    Gson gson;
    Network network;
    String url="http://192.168.2.103:8080/HttpControl/serverControl";
    Handler handler=new Handler(Looper.getMainLooper()) {
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            int i=msg.what;
            if (i==1){
                String result=msg.obj.toString();
                JsonElement je = new JsonParser().parse(result);
                String code = je.getAsJsonObject().get("result").getAsString();
                if (code.equals("62")){
                    //发码失败
                    huqufama.fail("0");
                } else {
                    huqufama.huqufamasuccessful(code);
                }
            }
            if (i==2){
                String result= (String) msg.obj;
                Log.d("555","获取已生成码信息"+result);
                result=result.substring(1,result.length()-1);
                String code="0";
                try {
                    JsonElement je = new JsonParser().parse(result);
                    Log.d("555",je.toString());
                    code  = je.getAsJsonObject().get("result").getAsString();
                }catch (JsonSyntaxException e){
                }catch (NullPointerException exception){
                    Log.d("555","发码空指针错误" );
                }
                if (code.equals("72")){
                    //发码失败
                    huqufama.getcodeinfofail();
                }else
                    {
                String pattern = "\\{.*?\\}";             //正则匹配
                Pattern r = Pattern.compile(pattern);
                ArrayList<CodeinfoBean> list=new ArrayList<>();
                Matcher m = r.matcher(result);
                while(m.find()) {
                    try {
                        CodeinfoBean cheakcode = gson.fromJson(m.group(), CodeinfoBean.class);
                        list.add(cheakcode);
                    }catch (JsonSyntaxException e){
                    }
                }
                huqufama.getcodeinfosuccessful(list);
                }
            }
        }
    };
    public void creatcode(Signbean signbean){    //生成激活码
        String reqbody=gson.toJson(signbean);
        network.connectnet(reqbody,"getma",StaticValue.url,handler,1);
    }
    public void  getcodeinfo(){
        Signbean signbean=new Signbean("","","","",StaticValue.phone);
        Gson gson=new Gson();
        String requestbody=gson.toJson(signbean);  //将json对象转换为字符
        network.connectnet(requestbody,"findmalist",StaticValue.url,handler,2);
    }
    public Fama() {
        gson=new Gson();
        network=Network.getnetwork();
    }
    public interface Huqufama{
        public void getcodeinfofail();
        public void getcodeinfosuccessful( ArrayList<CodeinfoBean> list);
        public void huqufamasuccessful(String t);
        public void fail(String str);
    }
    private Huqufama huqufama;
    public void setfamalistener( Huqufama huqufama){
        this.huqufama=huqufama;
    }

}
