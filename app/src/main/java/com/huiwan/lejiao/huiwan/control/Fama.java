package com.huiwan.lejiao.huiwan.control;

import android.os.Handler;
import android.os.Message;
import android.util.Log;

import com.huiwan.lejiao.huiwan.utils.Network;
public class Fama {
    String url="http://192.168.2.103:8080/HttpControl/serverControl";
    Handler handler=new Handler() {
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            String result=msg.obj.toString();
            Log.d("55555","返回数据"+result);
            if (true){
                huqufama.huqufamasuccessful("15");
            }
        }
    };
    public Fama() {
        Network network=Network.getnetwork();
      //  network.connectnet("sss",url,handler,1);
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
