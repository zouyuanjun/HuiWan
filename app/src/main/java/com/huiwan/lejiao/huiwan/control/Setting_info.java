package com.huiwan.lejiao.huiwan.control;

import android.os.Handler;
import android.os.Message;
import android.util.Log;

import com.google.gson.Gson;
import com.huiwan.lejiao.huiwan.DataBean.dbDataBasic;
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
            if (result.length()>10){

            }else {
                settingresult.Settingfail();
            }
        }
    };

    public Setting_info(dbDataBasic setting_info_bean) {
            network=Network.getnetwork();
        Gson gson=new Gson();
        String body=gson.toJson(setting_info_bean);
        network.connectnet(body,"setdata",handler,1);
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
