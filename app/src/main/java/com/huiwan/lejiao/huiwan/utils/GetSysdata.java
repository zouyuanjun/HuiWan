package com.huiwan.lejiao.huiwan.utils;

import java.text.SimpleDateFormat;
import java.util.Date;

public class GetSysdata {

    static Date day=new Date();
    SimpleDateFormat df;

    private static   GetSysdata getSysdata=new GetSysdata();
     private  GetSysdata() {
        df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss：SSS");
    }
    public static GetSysdata getsysdata(){
        return getSysdata;
    }
    public String datastring(){
         String s=df.format(day);
         return s;
    }
}
