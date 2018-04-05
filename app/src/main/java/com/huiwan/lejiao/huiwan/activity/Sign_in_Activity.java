package com.huiwan.lejiao.huiwan.activity;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.MultiAutoCompleteTextView;
import android.widget.TextView;
import android.widget.Toast;

import com.huiwan.lejiao.huiwan.R;
import com.huiwan.lejiao.huiwan.control.Sign_in;
import com.huiwan.lejiao.huiwan.utils.GetSysdata;

/**
 * Created by zou on 2018/3/28.
 */
public class Sign_in_Activity extends AppCompatActivity {
    AutoCompleteTextView ed_username;
    EditText ed_password;
    Button bt_signin;
    TextView tv_forgetpassage;
    private String[] res = {"beijing1", "beijing2", "beijing3", "shanghai1", "shanghai2", "guangzhou1", "shenzhen"};
    String username;
    String password;
    static Activity activity;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);// 隐藏标题
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
         WindowManager.LayoutParams.FLAG_FULLSCREEN);// 设置全屏
        setContentView(R.layout.activity_sign_in);
        activity=this;
        initview();
        eventsViews();
    }
    private void  initview(){
        ed_password=findViewById(R.id.ed_signin_password);
        ed_username=findViewById(R.id.ed_signin_username);
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
        username=ed_username.getText().toString();
        password=ed_password.getText().toString();
        Sign_in sign_in=new Sign_in(username,password);
        //登陆成功则销毁登陆界面
        sign_in.setsignlistener(new Sign_in.Signresult() {
            @Override
            public void signsuccessful() {
                finish();
            }
            //登陆失败弹窗提示
            @Override
            public void signfail(String t) {
                AlertDialog.Builder builder = new AlertDialog.Builder(Sign_in_Activity.getactivity());
                builder.setTitle("登陆失败");
                builder.setMessage("请重新输入");
                //点击对话框以外的区域是否让对话框消失
                builder.setCancelable(true);
                //设置正面按钮
                builder.setPositiveButton("确定", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.dismiss();
                    }
                });
                AlertDialog dialog = builder.create();
                dialog.show();
            }
        });
    }
    private void eventsViews() {
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, res);
        ed_username.setAdapter(adapter);
    }

    public static Activity getactivity(){
          return activity;
    }
}
