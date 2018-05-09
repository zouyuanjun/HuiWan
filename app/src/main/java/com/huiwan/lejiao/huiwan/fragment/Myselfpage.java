package com.huiwan.lejiao.huiwan.fragment;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.ClipboardManager;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.res.Resources;
import android.graphics.drawable.ColorDrawable;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.text.Html;
import android.util.Log;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.PopupWindow;
import android.widget.TextView;
import android.widget.Toast;

import com.facebook.drawee.view.SimpleDraweeView;
import com.huiwan.lejiao.huiwan.R;
import com.huiwan.lejiao.huiwan.control.Setting_info;
import com.huiwan.lejiao.huiwan.control.StaticValue;
import com.huiwan.lejiao.huiwan.utils.GetAlerDialog;
import com.lljjcoder.Interface.OnCityItemClickListener;
import com.lljjcoder.bean.CityBean;
import com.lljjcoder.bean.DistrictBean;
import com.lljjcoder.bean.ProvinceBean;
import com.lljjcoder.citywheel.CityConfig;
import com.lljjcoder.style.citylist.Toast.ToastUtils;
import com.lljjcoder.style.citypickerview.CityPickerView;

import org.feezu.liuli.timeselector.TimeSelector;

//import org.feezu.liuli.timeselector.TimeSelector;

/**
 * Created by zou on 2018/3/28.
 */

public class Myselfpage extends Fragment {
    TextView tv_user_centen_name;
    TextView tv_user_centen_phone;
    TextView tv_user_centen_weixin;
    TextView tv_setting;
    TextView tv_xiaoshengyi;
    TextView tv_huiwan;
    TextView tv_lijimai;
    TextView tv_appmgr;
    TextView tv_accountcentent;
    String citysrt;
    TextView tv_city;
    TextView tv_birday;
    ImageView im_msg;
    ImageView im_shouyi;
    ImageView im_shiyongjiaocheng;
    SimpleDraweeView im_photo;
    ImageButton imb_citypick;
    ImageButton imb_birdaypick;
    ImageButton imb_setting;
    CityPickerView mPicker=new CityPickerView();
    Context context;
    View rootview;
    Activity activity;
    Setting_info setting_info;
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        rootview=inflater.inflate(R.layout.fragment_user_centre,container,false);
        mPicker.init(getContext());
        activity=getActivity();
        imb_citypick=rootview.findViewById(R.id.imb_citypick);
        imb_birdaypick=rootview.findViewById(R.id.imb_birdaypick);
        im_msg=rootview.findViewById(R.id.im_meg_centen);
        im_shouyi=rootview.findViewById(R.id.im_shouyi);
        im_shiyongjiaocheng=rootview.findViewById(R.id.im_shiyongjiaochen);
        im_photo=rootview.findViewById(R.id.im_centen_photo);
        try {
            if (StaticValue.dbDataBasic.getSex()==1){
                im_photo.setImageURI((new Uri.Builder()).scheme("res").path(String.valueOf(R.drawable.men)).build());
            } else if (StaticValue.dbDataBasic.getSex()==2){
                im_photo.setImageURI((new Uri.Builder()).scheme("res").path(String.valueOf(R.drawable.girl)).build());
            } else {
                im_photo.setImageURI((new Uri.Builder()).scheme("res").path(String.valueOf(R.drawable.studmeg_ic_portrait)).build());
            }
        }catch (NullPointerException e){
            im_photo.setImageURI((new Uri.Builder()).scheme("res").path(String.valueOf(R.drawable.studmeg_ic_portrait)).build());
        }
        im_photo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                    selectphotopopwindows();
            }
        });
        setting_info=new Setting_info(activity);
        tv_city=rootview.findViewById(R.id.tv_city);
        tv_birday=rootview.findViewById(R.id.tv_birday);
        tv_user_centen_name=rootview.findViewById(R.id.tv_user_centen_name);
        tv_user_centen_phone=rootview.findViewById(R.id.tv_user_centen_phone);
        tv_user_centen_weixin=rootview.findViewById(R.id.tv_user_centen_weixin);
        tv_setting=rootview.findViewById(R.id.tv_setting);
        tv_setting.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                SharedPreferences sp = activity.getSharedPreferences("SPuser", Activity.MODE_PRIVATE);
                SharedPreferences.Editor editor=sp.edit();
                editor.putString("name","1");
                editor.putString("password","1");
                editor.commit();
                getActivity().finish();
            }
        });
        tv_user_centen_name.setText("姓 名："+StaticValue.name);
        tv_user_centen_phone.setText("手机号："+StaticValue.phone);
        tv_user_centen_weixin.setText("微信号："+StaticValue.weixin);
        if (!StaticValue.dbDataBasic.getAddress().isEmpty()){
            tv_city.setText(StaticValue.dbDataBasic.getAddress());
        }
        if (!StaticValue.dbDataBasic.getBirthdate().isEmpty()){
            tv_birday.setText(StaticValue.dbDataBasic.getBirthdate());
        }
        context=getContext();
        imb_birdaypick.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.d("55555","设置出生日期");
                TimeSelector timeSelector = new TimeSelector(context, new TimeSelector.ResultHandler() {
                    @Override
                    public void handle(String time) {
                        tv_birday.setText(time.substring(0,time.length()-5));
                        StaticValue.dbDataBasic.setBirthdate(time.substring(0,time.length()-5));
                        setting_info.updatainfo(StaticValue.dbDataBasic);
                    }
                }, "1960-11-22 17:34", "2018-12-1 15:20");
                timeSelector.setMode(TimeSelector.MODE.YMD);
                timeSelector.show();

            }
        });


        imb_citypick.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //添加默认的配置，不需要自己定义
                CityConfig cityConfig = new CityConfig.Builder().build();
                mPicker.setConfig(cityConfig);
                    //监听选择点击事件及返回结果
                mPicker.setOnCityItemClickListener(new OnCityItemClickListener() {
                    @Override
                    public void onSelected(ProvinceBean province, CityBean city, DistrictBean district) {
                        //省份
                        if (province != null) {
                            citysrt=province.getName();
                        }
                        //城市
                        if (city != null) {
                            citysrt=citysrt+"·"+city.getName();
                        }
                        //地区
                        if (district != null) {
                            citysrt=citysrt+"·"+district.getName();
                            citysrt=citysrt.replace("省","");
                            citysrt=citysrt.replace("市","");
                            tv_city.setText(citysrt);
                            StaticValue.dbDataBasic.setAddress(citysrt);
                            setting_info.updatainfo(StaticValue.dbDataBasic);
                            Log.d("55555",district.getName());
                        }
                    }
                    @Override
                    public void onCancel() {
                        ToastUtils.showLongToast(getContext(), "已取消");
                    }
                });
                //显示
                mPicker.showCityPicker( );
            }
        });

        im_msg.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AlertDialog dialog = GetAlerDialog.getdialog(activity,"敬请期待","程序猿小哥哥正在加班开发···");
                dialog.show();
            }
        });
        im_shouyi.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AlertDialog dialog = GetAlerDialog.getdialog(activity,"敬请期待","程序猿小哥哥正在加班开发···");
                dialog.show();
            }
        });
        im_shiyongjiaocheng.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Uri uri = Uri.parse("http://47.98.155.149:8080/WebDemo");
                Intent intent = new Intent(Intent.ACTION_VIEW, uri);
                startActivity(intent);
            }
        });
        tv_accountcentent=rootview.findViewById(R.id.tv_accountcentent);
        tv_accountcentent.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Accountpopwindows();
            }
        });
        tv_appmgr=rootview.findViewById(R.id.tv_APPMgr);
        tv_xiaoshengyi=rootview.findViewById(R.id.tv_xiaoshengyi);
        tv_lijimai=rootview.findViewById(R.id.tv_lijimai);
        tv_huiwan=rootview.findViewById(R.id.tv_huiwan);
        tv_huiwan.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Uri uri = Uri.parse("http://www.fir.im/zjhuiwan");
                Intent intent = new Intent(Intent.ACTION_VIEW, uri);
                startActivity(intent);
            }
        });
        tv_appmgr.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Uri uri = Uri.parse("http://app.yuncaifx.com/shareapp/#/login/2oeGyzY6");
                Intent intent = new Intent(Intent.ACTION_VIEW, uri);
                startActivity(intent);
            }
        });
        tv_xiaoshengyi.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Uri uri = Uri.parse("https://cc.yuncai12.top/mobile/index.php?m=wap&c=Invite&a=qrt&sign=49237e4884fb8eb235379505e27143fc");
                Intent intent = new Intent(Intent.ACTION_VIEW, uri);
                startActivity(intent);
            }
        });
        tv_lijimai.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Uri uri = Uri.parse("http://a.app.qq.com/o/simple.jsp?pkgname=com.hwlijimai");
                Intent intent = new Intent(Intent.ACTION_VIEW, uri);
                startActivity(intent);
            }
        });
        return rootview;
    }
    public void selectphotopopwindows(){
        WindowManager.LayoutParams lp = activity.getWindow().getAttributes();
        lp.alpha = 0.4f;
        activity.getWindow().setAttributes(lp);
        final PopupWindow window ;
        View contentView = LayoutInflater.from(context).inflate(R.layout.pop_selectphoto, null, false);
        SimpleDraweeView im_men=contentView.findViewById(R.id.img_select_girl);

        SimpleDraweeView im_girl=contentView.findViewById(R.id.img_select_men);

        window = new PopupWindow(contentView,  ViewGroup.LayoutParams.WRAP_CONTENT,  ViewGroup.LayoutParams.WRAP_CONTENT, true);
        // 设置PopupWindow的背景
        im_men.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                StaticValue.dbDataBasic.setSex(1);
                setting_info.updatainfo(StaticValue.dbDataBasic);
                im_photo.setImageURI((new Uri.Builder()).scheme("res").path(String.valueOf(R.drawable.men)).build());
                window.dismiss();
            }
        });
        im_girl.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                StaticValue.dbDataBasic.setSex(2);
                setting_info.updatainfo(StaticValue.dbDataBasic);
                im_photo.setImageURI((new Uri.Builder()).scheme("res").path(String.valueOf(R.drawable.girl)).build());
                window.dismiss();
            }
        });
        window.setBackgroundDrawable(new ColorDrawable(0xff2581ff));
        // 设置PopupWindow是否能响应外部点击事件
        window.setOutsideTouchable(true);
        // 设置PopupWindow是否能响应点击事件
        window.setTouchable(true);
        window.setOnDismissListener(new PopupWindow.OnDismissListener() {
            //在dismiss中恢复透明度
            public void onDismiss() {
                WindowManager.LayoutParams lp = activity.getWindow().getAttributes();
                lp.alpha = 1f;
                activity.getWindow().setAttributes(lp);
            }
        });
        window.showAtLocation(activity.getWindow().getDecorView(), Gravity.CENTER, 0, 0);
    }
    public void Accountpopwindows(){
        final PopupWindow window;
        WindowManager.LayoutParams lp = activity.getWindow().getAttributes();
        lp.alpha = 0.4f;
        activity.getWindow().setAttributes(lp);
        View contentView = LayoutInflater.from(activity).inflate(R.layout.popwindows_accountcentent, null, false);
        window = new PopupWindow(contentView,  ViewGroup.LayoutParams.WRAP_CONTENT,  ViewGroup.LayoutParams.WRAP_CONTENT, true);
        // 设置PopupWindow的背景
        window.setBackgroundDrawable(new ColorDrawable(0xff2581ff));
        // 设置PopupWindow是否能响应外部点击事件
        window.setOutsideTouchable(false);
        // 设置PopupWindow是否能响应点击事件
        window.setTouchable(true);
        window.setFocusable(false); // 获取焦点
        Button button=contentView.findViewById(R.id.bt_getcode);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                window.dismiss();
                WindowManager.LayoutParams lp = activity.getWindow().getAttributes();
                lp.alpha = 1f;
                activity.getWindow().setAttributes(lp);
            }
        });
        TextView tv_account=contentView.findViewById(R.id.tv_popAccount);
        tv_account.setText("账号"+StaticValue.Account);
        TextView tv_password=contentView.findViewById(R.id.tv_popPASSWORD);
        tv_password.setText("密码："+StaticValue.PASSWORD);
        window.showAtLocation(activity.getWindow().getDecorView(), Gravity.CENTER, 0, 0);
    }
}
