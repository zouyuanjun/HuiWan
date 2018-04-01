package com.huiwan.lejiao.huiwan.control;

import android.os.Handler;
import android.os.Message;

import com.huiwan.lejiao.huiwan.utils.Network;

public class Xueyuan_zhuangma {

    Boolean isfamacg=false;

    Network network;
    Handler handler=new Handler(){
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            if (msg.what==1){
                isfamacg=true;
            }
        }
    };

    public Xueyuan_zhuangma() {
        network=Network.getnetwork();
    }

    int zhuanmashu=1;
    public int zhuanmajia(){
        if (zhuanmashu<StaticValue.kezhuangmashu){
            zhuanmashu++;
        }
        return zhuanmashu;
    }
    public int zhuanmajian(){
        if (zhuanmashu>0){
            zhuanmashu--;
        }
        return zhuanmashu;
    }
    public void ensurezhuangchu(){
        String url="https://www.baidu.com/";
       network.connectnet("",url,handler,1);
    }
    public interface interface_Xueyuan_zhuanma {

        public void tongzhizhuanmacg(String t);
    }

    private interface_Xueyuan_zhuanma zhuanma;

    public void setXueyuanlistener( interface_Xueyuan_zhuanma signresult1){
        this.zhuanma=signresult1;
    }


}
