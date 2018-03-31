package com.huiwan.lejiao.huiwan.control;

import android.os.Handler;
import android.os.Message;
import android.util.Log;

import com.huiwan.lejiao.huiwan.utils.Network;
public class Fama {
    String url="https://www.baidu.com/";
    Handler handler=new Handler() {
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            String result=msg.obj.toString();
            if (true){
                huqufama.huqufamasuccessful("15");
            }


        }
    };

    public Fama() {
        Log.d("55555","用户ID"+StaticValue.id);
        Network network=Network.getnetwork();
        network.connectnet("sss",url,handler);

    }

    public interface Huqufama{

        public void huqufamasuccessful(String t);
        public void signfail(String t);
    }
    private Fama.Huqufama huqufama;
    public void setfamalistener( Fama.Huqufama huqufama){
        this.huqufama=huqufama;
    }

}
