package com.huiwan.lejiao.huiwan.utils;

import android.content.Context;
import android.support.annotation.NonNull;

import org.acra.config.ACRAConfiguration;
import org.acra.sender.ReportSender;
import org.acra.sender.ReportSenderFactory;

public class CrashSenderFactory implements ReportSenderFactory {

    public CrashSenderFactory() {
    }

    @NonNull
    @Override
    public ReportSender create(@NonNull Context context, @NonNull ACRAConfiguration config) {

        return new CrashSender();
    }
}