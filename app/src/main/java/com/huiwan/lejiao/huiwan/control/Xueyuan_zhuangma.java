package com.huiwan.lejiao.huiwan.control;

import android.os.Handler;
import android.os.Message;
import android.util.Log;

import com.google.gson.Gson;
import com.google.gson.JsonElement;
import com.google.gson.JsonParser;
import com.google.gson.JsonSyntaxException;
import com.huiwan.lejiao.huiwan.DataBean.ChangemaBean;
import com.huiwan.lejiao.huiwan.DataBean.DbDataBasic;
import com.huiwan.lejiao.huiwan.DataBean.Signbean;
import com.huiwan.lejiao.huiwan.utils.Network;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Xueyuan_zhuangma {

    Boolean isfamacg=false;

    Network network;
    Gson gson=new Gson();
    Handler handler=new Handler(){
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            if (msg.what==1){   //获取下级列表返回结果
               String result= (String) msg.obj;
                if (result.length()>30){      //字符串长度大于30才是正确结果
                result=result.substring(2,result.length()-1);
                String pattern = "\\{.*?\\}";             //正则匹配
                Pattern r = Pattern.compile(pattern);
                List<DbDataBasic> list=new ArrayList<>();
                Matcher m = r.matcher(result);
                while(m.find()) {
                    try {
                        DbDataBasic dataBasic = gson.fromJson(m.group(), DbDataBasic.class);
                        list.add(dataBasic);
                    }catch (JsonSyntaxException e){
                    }
                }
                Log.d("5555","获取下级信息成功");
                xueyuan_zhuangma.getxiajilist(list);
                }
            }
            if (msg.what==2){   //获取上级列表返回结果
                String result= (String) msg.obj;
                DbDataBasic dataBasic = gson.fromJson(result, DbDataBasic.class);
                xueyuan_zhuangma.getshangjiinfo(dataBasic);
            }
            if (msg.what==3){   //转码成功结果
                String result= (String) msg.obj;
                JsonElement je = new JsonParser().parse(result);
                String code = je.getAsJsonObject().get("result").getAsString();
                if (code.equals("51")){
                    StaticValue.kezhuangmashu= StaticValue.kezhuangmashu-zhuanmashu;
                    getxiajiinfo(StaticValue.phone);
                    xueyuan_zhuangma.tongzhizhuanmacg();

                }else  if(code.equals("53")){
                    xueyuan_zhuangma.tongzhizhuanmafall();
                }
            }
        }
    };
    public Xueyuan_zhuangma() {
        network=Network.getnetwork();
    }

    public void getxiajiinfo(String phone){
        Signbean signbean=new Signbean("","","","999999",phone);
        Gson gson=new Gson();
        String requestbody=gson.toJson(signbean);  //将json对象转换为字符
        network.connectnet(requestbody,"getLowerlist",StaticValue.url,handler,1);  //请求下级信息
    }

    public void getshangjiinfo(){
        Signbean signbean1=new Signbean("","","","999999",StaticValue.exphone);
        Gson gson1=new Gson();
        String body1=gson1.toJson(signbean1);
        network.connectnet(body1,"getbasic",StaticValue.url,handler,2);  //请求上级信息
    }
    int zhuanmashu=1;
    public int zhuanmajia(){
        if (zhuanmashu<StaticValue.kezhuangmashu){
            zhuanmashu++;
        }
        return zhuanmashu;
    }
    public int zhuanmajian(){
        if (zhuanmashu>1){
            zhuanmashu--;
        }
        return zhuanmashu;
    }
    public void ensurezhuangchu(String phone, String tophone){
        Gson gson=new Gson();
        ChangemaBean changemaBean=new ChangemaBean(phone,tophone,zhuanmashu);
        String strbody=gson.toJson(changemaBean);

       network.connectnet(strbody,"changema",StaticValue.url,handler,3);
    }
    public interface interface_Xueyuan_zhuanma {
        public void getxiajilist(List<DbDataBasic> list);
        public  void getshangjiinfo(DbDataBasic dataBasic);
        public void tongzhizhuanmacg();
        public void tongzhizhuanmafall();
    }
    private interface_Xueyuan_zhuanma xueyuan_zhuangma;

    public void setXueyuanlistener( interface_Xueyuan_zhuanma result){
        this.xueyuan_zhuangma =result;
    }


}
