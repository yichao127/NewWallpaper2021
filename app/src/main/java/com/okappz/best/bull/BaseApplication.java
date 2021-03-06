package com.okappz.best.bull;

import android.app.Application;
import android.os.Environment;

import com.okappz.best.bull.net.HttpInterceptor;
import com.okappz.best.bull.util.UtilDownload;
import com.okhttplib.HttpInfo;
import com.okhttplib.OkHttpUtil;
import com.okhttplib.annotation.CacheType;
import com.okhttplib.annotation.Encoding;
import com.okhttplib.callback.Callback;
import com.okhttplib.cookie.PersistentCookieJar;
import com.okhttplib.cookie.cache.SetCookieCache;
import com.okhttplib.cookie.persistence.SharedPrefsCookiePersistor;

import java.io.File;
import java.io.IOException;

import io.flutter.embedding.android.FlutterFragment;
import io.flutter.embedding.engine.FlutterEngine;
import io.flutter.embedding.engine.FlutterEngineCache;
import io.flutter.embedding.engine.dart.DartExecutor;

public class BaseApplication extends Application {
    private static Application instance;
    public FlutterEngine flutterEngine;

    @Override
    public void onCreate() {
        super.onCreate();
        instance = this;
        initNet();
        initFlutterEngine();
    }

    public static Application getInstance() {
        return instance;
    }

    private void initFlutterEngine() {
        // Instantiate a FlutterEngine.
        flutterEngine = new FlutterEngine(this);

        // Configure an initial route.
//        flutterEngine.getNavigationChannel().setInitialRoute("main_route");

        // Start executing Dart code to pre-warm the FlutterEngine.
        flutterEngine.getDartExecutor().executeDartEntrypoint(
                DartExecutor.DartEntrypoint.createDefault()
        );

        // Cache the FlutterEngine to be used by FlutterActivity.
        FlutterEngineCache
                .getInstance()
                .put("my_engine_id", flutterEngine);
        //pre-warm the FlutterFragment
        FlutterFragment.withCachedEngine("my_engine_id").build();
    }

    private void initNet() {
        String downloadFileDir = UtilDownload.getPathDownload();
        String cacheDir = UtilDownload.getPathCache();
        OkHttpUtil.init(this)
                .setConnectTimeout(15)//??????????????????
                .setWriteTimeout(15)//???????????????
                .setReadTimeout(15)//???????????????
                .setMaxCacheSize(10 * 1024 * 1024)//??????????????????
                .setCacheType(CacheType.FORCE_NETWORK)//????????????
                .setHttpLogTAG("HttpLog")//????????????????????????
                .setIsGzip(false)//Gzip??????????????????????????????
                .setShowHttpLog(true)//??????????????????
                .setShowLifecycleLog(false)//??????Activity????????????
                .setRetryOnConnectionFailure(false)//????????????????????????
                .setCachedDir(new File(cacheDir))//??????????????????
                .setDownloadFileDir(downloadFileDir)//????????????????????????
                .setResponseEncoding(Encoding.UTF_8)//????????????????????????????????????
                .setRequestEncoding(Encoding.UTF_8)//?????????????????????????????????
                .setHttpsCertificate("12306.cer")//????????????Https??????
                .addResultInterceptor(HttpInterceptor.ResultInterceptor)//?????????????????????
                .addExceptionInterceptor(HttpInterceptor.ExceptionInterceptor)//???????????????????????????
                .setCookieJar(new PersistentCookieJar(new SetCookieCache(), new SharedPrefsCookiePersistor(this)))//?????????cookie
                .build();
    }

    private void async() {
        OkHttpUtil.getDefault(this).doGetAsync(
                HttpInfo.Builder().setUrl("url").build(),
                new Callback() {
                    @Override
                    public void onFailure(HttpInfo info) throws IOException {
                        String result = info.getRetDetail();
                    }

                    @Override
                    public void onSuccess(HttpInfo info) throws IOException {
                        String result = info.getRetDetail();
                    }
                });
    }
}
