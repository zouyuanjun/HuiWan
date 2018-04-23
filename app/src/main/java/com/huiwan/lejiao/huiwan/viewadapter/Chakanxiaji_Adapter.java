package com.huiwan.lejiao.huiwan.viewadapter;

import android.net.Uri;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.TextView;

import com.facebook.drawee.view.SimpleDraweeView;
import com.huiwan.lejiao.huiwan.DataBean.PersonalinfoBean;
import com.huiwan.lejiao.huiwan.R;

import java.util.ArrayList;

public class Chakanxiaji_Adapter extends BaseAdapter {
    TextView tv_name;
    TextView tv_shouji;
    TextView tv_weixin;
    TextView tv_keyongma;

    SimpleDraweeView im_photo;

    ArrayList<PersonalinfoBean> arrayList=new ArrayList<>();

    public Chakanxiaji_Adapter(ArrayList<PersonalinfoBean> arrayList) {
        this.arrayList = arrayList;
    }

    @Override
    public int getCount() {
        return arrayList.size();
    }

    @Override
    public Object getItem(int i) {
        return arrayList.get(i);
    }

    @Override
    public long getItemId(int i) {
        return i;
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        View itemview= LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.item_chakanxiaji,null);
        tv_name=itemview.findViewById(R.id.tv_xjitemname);
        tv_shouji=itemview.findViewById(R.id.tv_xjitemphonenum);
        tv_weixin=itemview.findViewById(R.id.tv_xjitemweixin);
        tv_keyongma=itemview.findViewById(R.id.tv_xjitemkeyongma);
        im_photo=itemview.findViewById(R.id.im_xjitemphoto);

        tv_name.setText("姓 名："+arrayList.get(i).getName());
        tv_shouji.setText("手机号："+arrayList.get(i).getPhonenum());
        tv_weixin.setText("微信号："+arrayList.get(i).getWeichat());
        tv_keyongma.setText("可用码数："+arrayList.get(i).getKeyongma()+"");
        if (arrayList.get(i).getPhotourl().equals("1")){
            im_photo.setImageURI((new Uri.Builder()).scheme("res").path(String.valueOf(R.drawable.men)).build());
        } else if (arrayList.get(i).getPhotourl().equals("2")){
            im_photo.setImageURI((new Uri.Builder()).scheme("res").path(String.valueOf(R.drawable.girl)).build());
        } else {
            im_photo.setImageURI((new Uri.Builder()).scheme("res").path(String.valueOf(R.drawable.studmeg_ic_portrait)).build());
        }
        return itemview;
    }
}
