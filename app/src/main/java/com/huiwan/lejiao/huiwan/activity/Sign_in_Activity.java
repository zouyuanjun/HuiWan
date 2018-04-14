package com.huiwan.lejiao.huiwan.activity;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.text.InputType;
import android.util.Log;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.TextView;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.huiwan.lejiao.huiwan.DataBean.AccountBean;
import com.huiwan.lejiao.huiwan.R;
import com.huiwan.lejiao.huiwan.control.Sign_in;
import com.huiwan.lejiao.huiwan.control.StaticValue;
import com.huiwan.lejiao.huiwan.utils.GetAlerDialog;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by zou on 2018/3/28.
 */
public class Sign_in_Activity extends AppCompatActivity {
    AutoCompleteTextView ed_username;
    EditText ed_password;
    Button bt_signin;
    TextView tv_forgetpassage;
    Gson gson;
    List<String> straccount=new ArrayList<>();
    Map<String,AccountBean> accountBeanMap =new HashMap<>();

    String accountlist;
    String username;
    String password;
    ImageButton imb_password;
    static Activity activity;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);// 隐藏标题
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);// 设置全屏
        setContentView(R.layout.activity_sign_in);
        activity=this;
        //获取保存在本地的账户列表
        accountlist=getIntent().getStringExtra("account");
        gson=new Gson();

        Type type = new TypeToken<Map<String, AccountBean>>() {}.getType();
        Map<String, AccountBean> map2 = gson.fromJson(accountlist, type);
        for (String keyString : map2.keySet()) {
            AccountBean accountBean=map2.get(keyString);
            accountBeanMap.put(keyString,accountBean);
            straccount.add(accountBean.getAccount());
        }
        initview();
        eventsViews();
    }
    private void  initview(){
        final int[] i = {1};//密码是否可见状态;1，不可见；0可见
        ed_password=findViewById(R.id.ed_signin_password);
        ed_username=findViewById(R.id.ed_signin_username);
        ed_username.setThreshold(0);
        imb_password=findViewById(R.id.bt_password_hint);
        imb_password.setOnClickListener(new View.OnClickListener() {
            int anInt=1;
            @Override
            public void onClick(View v) {
                if(anInt ==1){
                    imb_password.setBackground(activity.getDrawable(R.drawable.lg_ic_password_sel));
                    ed_password.setInputType(InputType.TYPE_TEXT_VARIATION_VISIBLE_PASSWORD);
                    anInt =0;
                    Log.d("55555","密码可见");
                }
                else if (anInt ==0){
                    imb_password.setBackground(activity.getDrawable(R.drawable.lg_ic_password_nor));
                    ed_password.setInputType(InputType.TYPE_CLASS_TEXT | InputType.TYPE_TEXT_VARIATION_PASSWORD);
                    Log.d("55555","密码不可见");
                    anInt =1;
                }
            }
        });
        bt_signin=findViewById(R.id.bt_signin);
        tv_forgetpassage=findViewById(R.id.tv_forget_password);
        bt_signin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                getdate();
            }
        });
    }
    private void getdate(){
       // username="15070078339";
        username=ed_username.getText().toString();
        StaticValue.Account=username;
        //password="147258";
        password=ed_password.getText().toString();
        Sign_in sign_in=new Sign_in(username,password,activity);
        //登陆成功则销毁登陆界面
        sign_in.setsignlistener(new Sign_in.Signresult() {
            @Override
            public void signsuccessful() {
                //将账号保存再本地
                AccountBean accountBean=new AccountBean(username);
                accountBeanMap.put(username,accountBean);
                String accountlist=gson.toJson(accountBeanMap);
                SharedPreferences pref2 = getSharedPreferences("ACCOUNT",MODE_PRIVATE);
                SharedPreferences.Editor editor=pref2.edit();
                editor.putString("account",accountlist);
                editor.commit();
                finish();
            }
            //登陆失败弹窗提示
            @Override
            public void signfail(String t) {

                AlertDialog dialog = GetAlerDialog.getdialog(activity,"登陆失败","账号或密码错误，请重新输入");
                dialog.show();
            }
        });
    }
    private void eventsViews() {
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, straccount);
        ed_username.setAdapter(adapter);
    }

}
