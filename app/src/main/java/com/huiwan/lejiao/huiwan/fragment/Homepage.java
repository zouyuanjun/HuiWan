package com.huiwan.lejiao.huiwan.fragment;

import android.app.Activity;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.google.gson.Gson;
import com.huiwan.lejiao.huiwan.DataBean.DbDataBasic;
import com.huiwan.lejiao.huiwan.DataBean.LowerMgr;
import com.huiwan.lejiao.huiwan.DataBean.UsermubiaoBean;
import com.huiwan.lejiao.huiwan.R;
import com.huiwan.lejiao.huiwan.control.Home;
import com.huiwan.lejiao.huiwan.control.StaticValue;
/**
 * Created by zou on 2018/3/28.
 */
public class Homepage extends Fragment {
    Activity activity;

    ImageView hp_dengji;
    TextView tv_dangqianduanwei;
    TextView tv_xiaduan;
    TextView tv_jinrirenshu;
    TextView tv_jinrimubiao;
    TextView tv_dangyuerenshu;
    TextView tv_dangyuemubiao;
    View rootview;
    int nEqual=0;

    int lever=1;
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        rootview=inflater.inflate(R.layout.fragment_homepage,container,false);
        activity=getActivity();
        hp_dengji =rootview.findViewById(R.id.im_hp_dengji);
        tv_dangqianduanwei=rootview.findViewById(R.id.tv_hp_dangqiandengji);
        tv_xiaduan=rootview.findViewById(R.id.tv_hp_xiagedengji);
        tv_jinrirenshu=rootview.findViewById(R.id.tv_homepage_jinrirenshu);
        tv_jinrimubiao=rootview.findViewById(R.id.tv_homepage_jirimubiao);
        tv_dangyuerenshu=rootview.findViewById(R.id.tv_hp_benyuerenshu);
        tv_dangyuemubiao=rootview.findViewById(R.id.tv_hp_benyuemubiao);
        getdata();
        return rootview;
    }

    //获取保存在SharedPreferences中的信息
    public void getdata(){
        Home homePage=new Home();
        homePage.Sethomelistener(new Home.Homeresult() {
            @Override
            public void signsuccessful(DbDataBasic dbDataBasic) {
                lever=dbDataBasic.getLever();
                int chaju=5-nEqual;
                int chaju2=3-nEqual;
                if (dbDataBasic.getLever()==1){
                    tv_dangqianduanwei.setText("您当前等级为：达人");
                    tv_xiaduan.setText("您再培养 "+chaju+" 个 达人 就能升级成导师");
                    hp_dengji.setBackground(getActivity().getDrawable(R.drawable.home_ic_lv1));
                }else if (dbDataBasic.getLever()==2){
                    tv_xiaduan.setText("您再培养 "+chaju2+" 个 导师 就能升级成高级导师");
                    tv_dangqianduanwei.setText("您当前等级为：导师");
                    hp_dengji.setBackground(getActivity().getDrawable(R.drawable.home_ic_lv2));
                }else if (dbDataBasic.getLever()==3){
                    tv_xiaduan.setText("您再培养 "+chaju2+" 个 高导 就能升级成联创");
                    tv_dangqianduanwei.setText("您当前等级为：高级导师");
                    hp_dengji.setBackground(getActivity().getDrawable(R.drawable.home_ic_lv3));
                }else if (dbDataBasic.getLever()==4){
                    tv_xiaduan.setText("您再培养 "+chaju2+" 个 联创 就能升级成总裁");
                    tv_dangqianduanwei.setText("您当前等级为：联创");
                    hp_dengji.setBackground(getActivity().getDrawable(R.drawable.home_ic_lv4));
                }else if (dbDataBasic.getLever()==5){
                    tv_xiaduan.setText("恭喜您已经到最高等级了");
                    tv_dangqianduanwei.setText("您当前等级为：总裁");
                    hp_dengji.setBackground(getActivity().getDrawable(R.drawable.home_ic_lv5));
                }else {
                    tv_xiaduan.setText("等级信息有误，请联系管理员");
                }
            }
            @Override
            public void getmubiao(UsermubiaoBean usermubiaoBean) {
                StaticValue.jinrirenshu=usermubiaoBean.getJrrenshu();
                tv_jinrirenshu.setText(String.valueOf(usermubiaoBean.getJrrenshu()));
                tv_jinrimubiao.setText(String.valueOf(usermubiaoBean.getJrmubiao()));
                tv_dangyuerenshu.setText(String.valueOf(usermubiaoBean.getByrenshu()));
                tv_dangyuemubiao.setText(String.valueOf(usermubiaoBean.getBymubiao()));
            }
            @Override
            public void getlever(LowerMgr lowerMgr) {
                nEqual=lowerMgr.getnEqual();
                StaticValue.teamnum=lowerMgr.getnTeam()+lowerMgr.getnLower();

                int chaju2=3-nEqual;
                if (lever==1){
                    int chaju=5-nEqual;
                    tv_xiaduan.setText("您再培养 "+chaju+" 个 达人 就能升级成导师");
                }else if (lever==2){
                    tv_xiaduan.setText("您再培养 "+chaju2+" 个 导师 就能升级成高级导师");
                }else if (lever==3){
                    tv_xiaduan.setText("您再培养 "+chaju2+" 个 高导 就能升级成联创");
                }else if (lever==4){
                    tv_xiaduan.setText("您再培养 "+chaju2+" 个 联创 就能升级成总裁");
                }else if (lever==5){
                    tv_xiaduan.setText("恭喜您已经到最高等级了");
                }
            }
        });
    }

}
