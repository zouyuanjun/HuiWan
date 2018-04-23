package com.huiwan.lejiao.huiwan.utils;

import android.app.Application;
import android.content.Context;



import com.huiwan.lejiao.huiwan.R;

import org.acra.ACRA;

import org.acra.ReportField;
import org.acra.ReportingInteractionMode;
import org.acra.annotation.ReportsCrashes;
import org.acra.config.ACRAConfiguration;
import org.acra.config.ACRAConfigurationException;
import org.acra.config.ConfigurationBuilder;
import org.acra.ACRA;
import org.acra.sender.HttpSender;
import org.acra.sender.ReportSenderFactory;


//@ReportsCrashes(
//        formUri = "http://192.168.2.111:8080/HttpControl//ACRAServlet",
//        mode = ReportingInteractionMode.DIALOG,
//        resToastText = R.string.ACRA,
//        // 更换默认的发送器
//        customReportContent = { ReportField.APP_VERSION_CODE, ReportField.APP_VERSION_NAME, ReportField.ANDROID_VERSION, ReportField.PHONE_MODEL, ReportField.CUSTOM_DATA, ReportField.STACK_TRACE, ReportField.LOGCAT },
//        reportSenderFactoryClasses = {com.huiwan.lejiao.huiwan.utils.CrashSenderFactory.class}
//)

@ReportsCrashes(
        formUri = "http://47.98.155.149:8080/huiwanxueyuan//ACRAServlet",
       // formUri = "http://192.168.2.111:8080/HttpControl//ACRAServlet",
        mode = ReportingInteractionMode.DIALOG,
        resToastText = R.string.crash_toast_text2, // optional, displayed as soon as the crash occurs, before collecting data which can take a few seconds
        resDialogText = R.string.crash_dialog_text,
        resDialogIcon = android.R.drawable.ic_dialog_info, //optional. default is a warning sign
        resDialogTitle = R.string.crash_dialog_title, // optional. default is your application name
        resDialogCommentPrompt = R.string.crash_dialog_comment_prompt, // optional. When defined, adds a user text field input with this text resource as a label
        resDialogOkToast = R.string.crash_dialog_ok_toast,// optional. displays a Toast message when the user accepts to send a report.
        customReportContent = { ReportField.APP_VERSION_CODE, ReportField.APP_VERSION_NAME, ReportField.ANDROID_VERSION, ReportField.PHONE_MODEL, ReportField.USER_APP_START_DATE,ReportField.USER_CRASH_DATE,ReportField.USER_COMMENT, ReportField.STACK_TRACE}
       ,
  //      reportSenderFactoryClasses = {com.huiwan.lejiao.huiwan.utils.CrashSenderFactory.class}
        reportType = HttpSender.Type.JSON//配置以json形式发送
)




public class MyApplication extends Application {

    @Override
    protected void attachBaseContext(Context base) {
        super.attachBaseContext(base);
//        final Class<CrashSenderFactory> myReportSenderFactoryClasses = CrashSenderFactory.class;
//
//        // 初始化一个ConfigurationBuilder,并设置ReportSenderFactoryClasses.
//        final ACRAConfiguration config;
//        try {
//            config = new ConfigurationBuilder(this)
//                    .setReportSenderFactoryClasses(myReportSenderFactoryClasses)
//                    .build();
//            ACRA.init(this, config);
//        } catch (ACRAConfigurationException e) {
//            e.printStackTrace();
//        }

  //   ACRA.init(this);
    }

    @Override
    public void onCreate() {
        super.onCreate();
    }
}