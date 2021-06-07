package com.okappz.best.bull;

import android.app.WallpaperManager;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.telephony.mbms.FileInfo;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.bumptech.glide.Glide;
import com.okappz.best.bull.net.URLConst;
import com.okappz.best.bull.util.UtilDownload;
import com.okhttplib.HttpInfo;
import com.okhttplib.OkHttpUtil;
import com.okhttplib.bean.DownloadFileInfo;
import com.okhttplib.callback.ProgressCallback;

import java.io.IOException;

public class VideoActivity extends AppCompatActivity {
    public static final String THUMBNAIL = "THUMBNAIL";
    public static final String PREVIEW = "PREVIEW";

    private String thumbnail;
    private String preview;

    private ImageView showVideo;
    private DownloadFileInfo fileInfo;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_video);

        showVideo = findViewById(R.id.show_video);
        Button button = findViewById(R.id.set_view);
        Intent intent = getIntent();
        thumbnail = intent.getStringExtra(THUMBNAIL);
        preview = intent.getStringExtra(PREVIEW);

        Glide.with(this).load(URLConst.BASE_URL+thumbnail).into(showVideo);


//        dowTOPhone();
        findViewById(R.id.back_view).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                SetTable();
            }
        });

download();

    }

    public void SetTable() {


        WallpaperManager mWallManager = WallpaperManager.getInstance(this);
        try {
            Bitmap bitmap = BitmapFactory.decodeFile(URLConst.BASE_URL+preview);
            mWallManager.setBitmap(bitmap);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void download(){
        String fileName=  preview.substring(preview.lastIndexOf("/")+1);
        if(null == fileInfo)
            fileInfo = new DownloadFileInfo(URLConst.BASE_URL+preview, fileName,new ProgressCallback(){
                @Override
                public void onProgressMain(int percent, long bytesWritten, long contentLength, boolean done) {
                    Log.d("PATH_DOWNLOAD","preview"+percent);
                }
                @Override
                public void onResponseMain(String filePath, HttpInfo info) {
                    if(info.isSuccessful()){
                        Toast.makeText(VideoActivity.this,fileInfo.getDownloadStatus(),Toast.LENGTH_SHORT).show();
                    }else{
                        Toast.makeText(VideoActivity.this,info.getRetDetail(),Toast.LENGTH_SHORT).show();
                    }
                }
            });
        HttpInfo info = HttpInfo.Builder().addDownloadFile(fileInfo).build();
        OkHttpUtil.Builder().setReadTimeout(120).build(this).doDownloadFileAsync(info);
    }
}
