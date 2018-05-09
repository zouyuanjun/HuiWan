package com.huiwan.lejiao.huiwan.control;

import android.app.Activity;
import android.content.Intent;
import android.os.Handler;
import android.os.Message;
import android.util.Log;
import com.google.gson.Gson;
import com.google.gson.JsonElement;
import com.google.gson.JsonParser;
import com.google.gson.JsonSyntaxException;
import com.huiwan.lejiao.huiwan.DataBean.DbDataBasic;
import com.huiwan.lejiao.huiwan.DataBean.Signbean;
import com.huiwan.lejiao.huiwan.activity.MainActivity;
import com.huiwan.lejiao.huiwan.activity.Setting_user_info_Activity;
import com.huiwan.lejiao.huiwan.activity.Sign_in_Activity;


public class Setting_info {
    Activity activity;
    String result;
    com.lejiaokeji.fentuan.util.Network network;
    Gson gson;
    Handler handler=new Handler() {
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            int what=msg.what;
            if (what==1){
            String result= (String) msg.obj;
            Log.d("设置信息返回数据",result);
            String code="0";
                try {
                    JsonElement je = new JsonParser().parse(result);
                    code = je.getAsJsonObject().get("result").getAsString();
                }catch (JsonSyntaxException e){
                    settingresult.Settingfail();
                }

                if (code.equals("21")){
                    //登陆成功，跳转到主页
                    Intent intent=new Intent(activity,MainActivity.class);
                    activity.startActivity(intent);
                    settingresult.Settingsuccessful();
                }else if (code.equals("22")){
                    settingresult.queryfail();
                }
            else {
                settingresult.Settingfail();
            }
            }else if (what==2){
                String result= (String) msg.obj;
                if (result.length()>22) {
                    JsonElement je = new JsonParser().parse(result);
                    String sjname = je.getAsJsonObject().get("name").getAsString();
                    String sjweixin = je.getAsJsonObject().get("weixin").getAsString();
                    settingresult.querysj(sjname, sjweixin);
                }else {
                    settingresult.queryfail();
                }
            }
        }
    };

    public Setting_info(Activity activity) {
        this.activity=activity;
            network= com.lejiaokeji.fentuan.util.Network.getnetwork();
            gson=new Gson();
        // String body="{\"address\":\"\",\"birthdate\":\"\",\"bymubiao\":\"\",\"byrenshu\":\"\",\"exname\":\"\",\"exphone\":\"18702508050\",\"exweixin\":\"\",\"idcard\":\"123456987\",\"jrmubiao\":\"\",\"jrrenshu\":\"\",\"lever\":\"\",\"name\":\"zouyuanjun\",\"phone\":\"18702508050\",\"taobao\":\"taobao\",\"userid\":\"999999\",\"weixin\":\"weinxin \"}";

    }
    public void querysj(String sjphonenum){
        Signbean signbean=new Signbean("","","","",sjphonenum);
        String body= gson.toJson(signbean);
        network.connectnet(body,"getbasic",StaticValue.url,handler,2);
    }
    public void setinfo(DbDataBasic setting_info_bean){
        String body= gson.toJson(setting_info_bean);
        network.connectnet(body,"setdata",StaticValue.url,handler,1);
    }
    public void updatainfo(DbDataBasic setting_info_bean){
        String body= gson.toJson(setting_info_bean);
        network.connectnet(body,"setdata",StaticValue.url,handler,3);
    }

    public interface Settingresultlistern {
        public  void querysj(String phone,String weixin);
        public void Settingsuccessful();
        public void Settingfail();
        public void  queryfail();
    }
    private Settingresultlistern settingresult;

    public void setSetinglistener( Settingresultlistern settingresult){
        this.settingresult=settingresult;
    }



}
