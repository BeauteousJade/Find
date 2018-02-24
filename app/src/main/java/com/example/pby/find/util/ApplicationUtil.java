package com.example.pby.find.util;

import android.content.Context;
import android.content.SharedPreferences;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.os.Environment;
import android.util.Log;

import com.bumptech.glide.Glide;
import com.example.pby.find.constant.ConstVariable;

import java.io.File;

import io.reactivex.Observable;
import io.reactivex.ObservableEmitter;
import io.reactivex.ObservableOnSubscribe;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.functions.Consumer;
import io.reactivex.schedulers.Schedulers;

/**
 * Created by pby on 2018/2/22.
 */

public class ApplicationUtil {
    public static String getAppVersionName(Context context) {
        String versionName = "";
        int versioncode = 0;
        try {
            // ---get the package info---
            PackageManager pm = context.getPackageManager();
            PackageInfo pi = pm.getPackageInfo(context.getPackageName(), 0);
            versionName = pi.versionName;
            versioncode = pi.versionCode;
            if (versionName == null || versionName.length() <= 0) {
                return "";
            }
        } catch (Exception e) {
            Log.e("VersionInfo", "Exception", e);
        }
        return versionName;
    }

    public static void clearCache(Context context) {

        Observable.create((ObservableOnSubscribe<String>) emitter -> {
            Glide.get(context).clearDiskCache();
            deleteDir(context.getCacheDir());
            if (Environment.getExternalStorageState().equals(
                    Environment.MEDIA_MOUNTED)) {
                deleteDir(context.getExternalCacheDir());
            }
        }).subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread()).doOnNext(s -> Glide.get(context).clearMemory());
    }

    private static boolean deleteDir(File dir) {
        if (dir != null && dir.isDirectory()) {
            String[] children = dir.list();
            int size = 0;
            if (children != null) {
                size = children.length;
                for (int i = 0; i < size; i++) {
                    boolean success = deleteDir(new File(dir, children[i]));
                    if (!success) {
                        return false;
                    }
                }
            }

        }
        if (dir == null) {
            return true;
        } else {

            return dir.delete();
        }
    }


    public static String getTextFromSharedPreferences(Context context, String key) {
        SharedPreferences sharedPreferences = context.getSharedPreferences(ConstVariable.NAME_SHAREDPREFERENCE, Context.MODE_PRIVATE);
        if (sharedPreferences != null) {
            return sharedPreferences.getString(key, null);
        }
        return null;
    }

    public static boolean getBooleanFromSharedPreferences(Context context, String key) {
        SharedPreferences sharedPreferences = context.getSharedPreferences(ConstVariable.NAME_SHAREDPREFERENCE, Context.MODE_PRIVATE);
        if (sharedPreferences != null) {
            return sharedPreferences.getBoolean(key, false);
        }
        return false;
    }

    public static void addStringToSharedPreferences(Context context, String key, String value) {
        SharedPreferences.Editor editor = context.getSharedPreferences(ConstVariable.NAME_SHAREDPREFERENCE, Context.MODE_PRIVATE).edit();
        editor.putString(key, value);
        editor.apply();
    }

    public static void addBooleanToSharedPreferences(Context context, String key, Boolean value) {
        SharedPreferences.Editor editor = context.getSharedPreferences(ConstVariable.NAME_SHAREDPREFERENCE, Context.MODE_PRIVATE).edit();
        editor.putBoolean(key, value);
        editor.apply();
    }

    public static void clearSharedPreferences(Context context) {
        SharedPreferences.Editor editor = context.getSharedPreferences(ConstVariable.NAME_SHAREDPREFERENCE, Context.MODE_PRIVATE).edit();
        editor.clear();
        editor.apply();
    }
}
