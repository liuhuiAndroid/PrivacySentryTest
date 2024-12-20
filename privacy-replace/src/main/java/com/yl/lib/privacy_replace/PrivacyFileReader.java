package com.yl.lib.privacy_replace;

import android.util.Log;

import com.yl.lib.privacy_annotation.PrivacyClassReplace;
import com.yl.lib.sentry.hook.PrivacySentry;
import com.yl.lib.sentry.hook.util.PrivacyProxyUtil;

import java.io.File;
import java.io.FileDescriptor;
import java.io.FileNotFoundException;
import java.io.FileReader;

/**
 * @author yulun
 * @since 2022-11-18 15:02
 */
@PrivacyClassReplace(originClass = FileReader.class)
public class PrivacyFileReader extends FileReader {
    public PrivacyFileReader(String fileName) throws FileNotFoundException {
        super(fileName);
        record(fileName);
    }

    public PrivacyFileReader(File file) throws FileNotFoundException {
        super(file);
        record(file.getAbsolutePath());
    }

    public PrivacyFileReader(FileDescriptor fd) {
        super(fd);
        record(fd.toString());
    }

    private void record(String path) {
        String className = this.getClass().getName();
        Log.d("LiuTest", className + "访问文件 FileReader path is " + path);
        PrivacyProxyUtil.Util.INSTANCE.doFilePrinter("FileReader", "访问文件", "path is " + path, false);
    }
}
