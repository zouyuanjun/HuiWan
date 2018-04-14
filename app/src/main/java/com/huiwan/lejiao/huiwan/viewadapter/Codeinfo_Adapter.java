package com.huiwan.lejiao.huiwan.viewadapter;

import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.huiwan.lejiao.huiwan.DataBean.CodeinfoBean;
import com.huiwan.lejiao.huiwan.R;

import java.util.ArrayList;

public class Codeinfo_Adapter extends BaseAdapter{
    ArrayList<CodeinfoBean> arrayList=new ArrayList<>();

    public Codeinfo_Adapter(ArrayList<CodeinfoBean> arrayList) {
        this.arrayList = arrayList;
    }

    @Override
    public int getCount() {
        return arrayList.size();
    }

    @Override
    public Object getItem(int position) {
        return arrayList.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View itemview= LayoutInflater.from(parent.getContext()).inflate(R.layout.item_codeinfo,null);
        TextView code=itemview.findViewById(R.id.tv_code1);
        TextView beizhu=itemview.findViewById(R.id.tv_beizhu);
        TextView creattime=itemview.findViewById(R.id.tv_creattime);
        TextView usetime=itemview.findViewById(R.id.tv_usetime);
        TextView isuse =itemview.findViewById(R.id.tv_isuse);
        code.setText(arrayList.get(position).getCode()+"");
        creattime.setText(arrayList.get(position).getGenerate());
        usetime.setText(arrayList.get(position).getUsedate());
        if (arrayList.get(position).getUsedate().equals("1")){
            isuse.setText("未使用");
            isuse.setTextColor(Color.parseColor("#f0780a"));
        }else isuse.setText("已使用");
        return itemview;
    }
}
