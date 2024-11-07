package com.yl.lib.privacy_replace;

import android.util.Log;

import com.yl.lib.privacy_annotation.PrivacyClassReplace;
import com.yl.lib.sentry.hook.util.PrivacyProxyUtil;

import java.io.File;
import java.io.FileDescriptor;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;

/**
 * @author yulun
 * @since 2022-11-18 15:01
 */
@PrivacyClassReplace(originClass = FileOutputStream.class)
public class PrivacyFileOutputStream extends FileOutputStream {
    public PrivacyFileOutputStream(String name) throws FileNotFoundException {
        super(name);
        record(name);
    }

    public PrivacyFileOutputStream(File file) throws FileNotFoundException {
        super(file);
        record(file.getAbsolutePath());
    }

    public PrivacyFileOutputStream(FileDescriptor fdObj) {
        super(fdObj);
        record(fdObj.toString());
    }

    private void record(String path) {
        String className = this.getClass().getName();
        Log.d("LiuTest", className + "访问文件 FileInputStream path is " + path);
        PrivacyProxyUtil.Util.INSTANCE.doFilePrinter("FileInputStream", "访问文件", "path is " + path,  false);
    }

    @Override
    public void write(int b) throws IOException {
        super.write(b);
    }
}
