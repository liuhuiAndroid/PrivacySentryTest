package com.yl.lib.privacy_replace;

import android.util.Log;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.yl.lib.privacy_annotation.PrivacyClassReplace;
import com.yl.lib.sentry.hook.PrivacySentry;
import com.yl.lib.sentry.hook.util.PrivacyLog;
import com.yl.lib.sentry.hook.util.PrivacyProxyUtil;

import java.io.File;
import java.net.URI;

/**
 * @author yulun
 * @since 2022-11-18 15:01
 * 代理File的构造方法，如果是自定义的file类，需要业务方单独配置自行处理
 */
@PrivacyClassReplace(originClass = File.class)
public class PrivacyFile extends File {

    public PrivacyFile(@NonNull String pathname) {
        super(pathname);
        record(pathname);
    }

    public PrivacyFile(@Nullable String parent, @NonNull String child) {
        super(parent, child);
        record(parent + child);
    }

    public PrivacyFile(@Nullable File parent, @NonNull String child) {
        super(parent, child);
        record(parent.getPath() + child);
    }

    public PrivacyFile(@NonNull URI uri) {
        super(uri);
        record(uri.toString());
    }

    private void record(String path) {
        String className = this.getClass().getName();
        Log.d("LiuTest", className + "访问文件 PrivacyFile path is " + path);
        PrivacyProxyUtil.Util.INSTANCE.doFilePrinter("PrivacyFile", "访问文件", "path is " + path, false);
    }
}
