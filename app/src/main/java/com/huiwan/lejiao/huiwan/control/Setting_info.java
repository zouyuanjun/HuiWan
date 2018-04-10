package com.huiwan.lejiao.huiwan.control;

import android.content.Intent;
import android.os.Handler;
import android.os.Message;
import android.util.Log;

import com.google.gson.Gson;
import com.google.gson.JsonElement;
import com.google.gson.JsonParser;
import com.huiwan.lejiao.huiwan.DataBean.DbDataBasic;
import com.huiwan.lejiao.huiwan.activity.MainActivity;
import com.huiwan.lejiao.huiwan.activity.Setting_user_info_Activity;
import com.huiwan.lejiao.huiwan.activity.Sign_in_Activity;
import com.huiwan.lejiao.huiwan.utils.Network;

public class Setting_info {
    String result;
    Network network;
    Handler handler=new Handler() {
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            String result= (String) msg.obj;
            Log.d("设置信息返回数据",result);
            String code="0";

                JsonElement je = new JsonParser().parse(result);
                code = je.getAsJsonObject().get("result").getAsString();
                if (code.equals("1")){
                    //登陆成功，跳转到主页
                    Intent intent=new Intent(Setting_user_info_Activity.getactivity(),MainActivity.class);
                    Sign_in_Activity.getactivity().startActivity(intent);
                    settingresult.Settingsuccessful();
                }
            else {
                settingresult.Settingfail();
            }
        }
    };

    public Setting_info(DbDataBasic setting_info_bean) {
            network=Network.getnetwork();
        Gson gson=new Gson();
        // String body="{\"address\":\"\",\"birthdate\":\"\",\"bymubiao\":\"\",\"byrenshu\":\"\",\"exname\":\"\",\"exphone\":\"18702508050\",\"exweixin\":\"\",\"idcard\":\"123456987\",\"jrmubiao\":\"\",\"jrrenshu\":\"\",\"lever\":\"\",\"name\":\"zouyuanjun\",\"phone\":\"18702508050\",\"taobao\":\"taobao\",\"userid\":\"999999\",\"weixin\":\"weinxin \"}";
        String body= gson.toJson(setting_info_bean);
        network.connectnet(body,"setdata",StaticValue.url,handler,1);
    }

    public interface Settingresultlistern {
        public void Settingsuccessful();
        public void Settingfail();
    }
    private Settingresultlistern settingresult;

    public void setSetinglistener( Settingresultlistern settingresult){
        this.settingresult=settingresult;
    }



}
