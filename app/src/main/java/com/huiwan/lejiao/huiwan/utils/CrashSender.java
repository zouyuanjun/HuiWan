package com.huiwan.lejiao.huiwan.utils;

import android.content.Context;
import android.support.annotation.NonNull;
import android.util.Log;

import org.acra.ReportField;
import org.acra.collector.CrashReportData;
import org.acra.sender.ReportSender;
import org.acra.sender.ReportSenderException;

import java.io.IOException;
import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.FormBody;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

public class CrashSender implements ReportSender {
    public CrashSender() {
        Log.d("55555","日志发送");

    }

    @Override
    public void send(@NonNull Context context, @NonNull CrashReportData errorContent) throws ReportSenderException {
        // 自定义需要发送的内容到后台
        Log.i("YourOwnSender", "send: " + errorContent.toString());
        OkHttpClient client = new OkHttpClient();
        FormBody.Builder formBody = new FormBody.Builder();//创建表单请求体
        formBody.add("APP_VERSION_CODE", errorContent.getProperty(ReportField.APP_VERSION_CODE));//传递键值对参数
        formBody.add("APP_VERSION_NAME", errorContent.getProperty(ReportField.APP_VERSION_NAME));
        formBody.add("PACKAGE_NAME", errorContent.getProperty(ReportField.PACKAGE_NAME));
        formBody.add("FILE_PATH", errorContent.getProperty(ReportField.FILE_PATH));
        formBody.add("PHONE_MODEL", errorContent.getProperty(ReportField.PHONE_MODEL));
        formBody.add("PHONE_MODEL", errorContent.getProperty(ReportField.PHONE_MODEL));
        formBody.add("ANDROID_VERSION", errorContent.getProperty(ReportField.ANDROID_VERSION));
        formBody.add("BUILD", errorContent.getProperty(ReportField.BUILD));
        formBody.add("BRAND", errorContent.getProperty(ReportField.BRAND));
        formBody.add("STACK_TRACE", errorContent.getProperty(ReportField.STACK_TRACE));
        formBody.add("STACK_TRACE_HASH", errorContent.getProperty(ReportField.STACK_TRACE_HASH));
        formBody.add("USER_CRASH_DATE", errorContent.getProperty(ReportField.USER_CRASH_DATE));
        formBody.add("DUMPSYS_MEMINFO", errorContent.getProperty(ReportField.DUMPSYS_MEMINFO));
        formBody.add("DEVICE_ID", errorContent.getProperty(ReportField.DEVICE_ID));
        Request request = new Request.Builder()
                .url("http://192.168.2.111:8080/HttpControl//ACRAServlet")
                .post(formBody.build())//传递请求体
                .build();
        client.newCall(request).enqueue(new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {
            }
            @Override
            public void onResponse(Call call, Response response) throws IOException {

            }
        });


    }
}