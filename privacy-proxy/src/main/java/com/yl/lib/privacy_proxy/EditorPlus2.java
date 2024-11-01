package com.yl.lib.privacy_proxy;

import android.content.SharedPreferences;
import android.util.Log;

import com.yl.lib.privacy_annotation.PrivacyClassReplace;

import java.util.Set;

@PrivacyClassReplace(originClass = SharedPreferences.Editor.class)
public class EditorPlus2 implements SharedPreferences.Editor {
    private final SharedPreferences.Editor editor;

    public EditorPlus2(SharedPreferences.Editor editor) {
        this.editor = editor;
    }

    @Override
    public SharedPreferences.Editor putString(String key, String value) {
        record(key, value);
        return new EditorPlus2(editor.putString(key, value));
    }

    @Override
    public SharedPreferences.Editor putStringSet(String key, Set<String> values) {
        record(key, values);
        return new EditorPlus2(editor.putStringSet(key, values));
    }

    @Override
    public SharedPreferences.Editor putInt(String key, int value) {
        record(key, value);
        return new EditorPlus2(editor.putInt(key, value));
    }

    @Override
    public SharedPreferences.Editor putLong(String key, long value) {
        record(key, value);
        return new EditorPlus2(editor.putLong(key, value));
    }

    @Override
    public SharedPreferences.Editor putFloat(String key, float value) {
        record(key, value);
        return new EditorPlus2(editor.putFloat(key, value));
    }

    @Override
    public SharedPreferences.Editor putBoolean(String key, boolean value) {
        record(key, value);
        return new EditorPlus2(editor.putBoolean(key, value));
    }

    @Override
    public SharedPreferences.Editor remove(String key) {
        return new EditorPlus2(editor.remove(key));
    }

    @Override
    public SharedPreferences.Editor clear() {
        return new EditorPlus2(editor.clear());
    }

    @Override
    public boolean commit() {
        return editor.commit();
    }

    @Override
    public void apply() {
        editor.apply();
    }

    private void record(String key, Object value) {
        String className = this.getClass().getName();
        Log.i("LiuTest", className + " EditorPlus key = " + key + ", value = " + value);
        // doFilePrinter("FileReader", "访问文件", "path is " + path, false);
    }
}
