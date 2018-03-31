package com.huiwan.lejiao.huiwan.viewadapter;

import android.net.Uri;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.facebook.drawee.view.SimpleDraweeView;
import com.huiwan.lejiao.huiwan.DataBean.PersonalinfoBean;
import com.huiwan.lejiao.huiwan.R;

import java.util.ArrayList;

public class Studentinfo_list_Adapter extends BaseAdapter{

    TextView tv_name;
    TextView tv_shouji;
    TextView tv_weixin;
    TextView tv_keyongma;
    Button bt_zhuangma;
    SimpleDraweeView im_photo;

    ArrayList<PersonalinfoBean> arrayList=new ArrayList<>();

    public Studentinfo_list_Adapter(ArrayList<PersonalinfoBean> arrayList) {
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
        View itemview= LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.item_student_info,null);
        tv_name=itemview.findViewById(R.id.tv_itemname);
        tv_shouji=itemview.findViewById(R.id.tv_itemphonenum);
        tv_weixin=itemview.findViewById(R.id.tv_itemweixin);
        tv_keyongma=itemview.findViewById(R.id.tv_itemkeyongma);
        bt_zhuangma=itemview.findViewById(R.id.bt_itemzhuangma);
        im_photo=itemview.findViewById(R.id.im_itemphoto);

        tv_name.setText(arrayList.get(i).getName());
        tv_shouji.setText(arrayList.get(i).getPhonenum());
        tv_weixin.setText(arrayList.get(i).getWeichat());
        tv_keyongma.setText(arrayList.get(i).getKeyongma());
        Uri uri = Uri.parse(arrayList.get(i).getPhotourl());
        im_photo.setImageURI(uri);

        return itemview;
    }
}
