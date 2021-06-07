package com.okappz.best.bull.util;

import android.os.Environment;

import com.okappz.best.bull.BaseApplication;

public class UtilDownload {
    public static final String PATH_DOWNLOAD="/okHttp_download/";
    public static final String PATH_CACHE="/okHttp_cache/";

    public static String getPathDownload(){
        return BaseApplication.getInstance().getFilesDir().getAbsolutePath() + UtilDownload.PATH_DOWNLOAD;
    }

    public static String getPathCache(){
        return BaseApplication.getInstance().getCacheDir().getAbsolutePath() + UtilDownload.PATH_CACHE;
    }
}
