package com.okappz.best.bull;

import android.app.WallpaperManager;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;
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

public class ChoiceDayActivity extends AppCompatActivity {

    public static final String THUMBNAIL = "THUMBNAIL";
    public static final String PREVIEW = "PREVIEW";

    private String thumbnail;
    private String preview;

    private ImageView show_choice_img;
    private DownloadFileInfo fileInfo;

    private TextView setToPhone;
    private ProgressBar progressBar;
    private String softNamePath;
    private TextView choiceImgshowText;
    private ImageView back;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_choice_day);
        Intent intent = getIntent();
        thumbnail = intent.getStringExtra(THUMBNAIL);
        preview = intent.getStringExtra(PREVIEW);

        show_choice_img = findViewById(R.id.show_choice_img);
        choiceImgshowText = findViewById(R.id.choice_show_text);
        progressBar = findViewById(R.id.progress_bar);
        back = findViewById(R.id.back_img);


        Glide.with(this).load(URLConst.BASE_URL + thumbnail).into(show_choice_img);
        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });


        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
        setToPhone = findViewById(R.id.set_to_phone_text);
        setToPhone.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                SetWallPaper();
                setToPhone.setVisibility(View.GONE);
                Toast.makeText(ChoiceDayActivity.this, "Success", Toast.LENGTH_SHORT).show();
            }
        });
        download();
    }

    //设置桌面壁纸
    public void SetWallPaper() {
        WallpaperManager mWallManager = WallpaperManager.getInstance(this);
        try {
            Bitmap bitmap = BitmapFactory.decodeFile(softNamePath);
            mWallManager.setBitmap(bitmap);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void download() {
        String fileName = preview.substring(preview.lastIndexOf("/") + 1);
        if (null == fileInfo)
            fileInfo = new DownloadFileInfo(URLConst.BASE_URL + preview, fileName, new ProgressCallback() {
                @Override
                public void onProgressMain(int percent, long bytesWritten, long contentLength, boolean done) {
                    progressBar.setProgress(percent);
                }

                @Override
                public void onResponseMain(String filePath, HttpInfo info) {
                    if (info.isSuccessful()) {
                        softNamePath= UtilDownload.getPathDownload()+fileName;
                        setToPhone.setVisibility(View.VISIBLE);
                        choiceImgshowText.setVisibility(View.INVISIBLE);
                        progressBar.setVisibility(View.INVISIBLE);
                        back.setVisibility(View.VISIBLE);
                        Toast.makeText(ChoiceDayActivity.this, fileInfo.getDownloadStatus(), Toast.LENGTH_SHORT).show();
                    } else {
                        Toast.makeText(ChoiceDayActivity.this, info.getRetDetail(), Toast.LENGTH_SHORT).show();
                    }
                }
            });
        HttpInfo info = HttpInfo.Builder().addDownloadFile(fileInfo).build();
        OkHttpUtil.Builder().setReadTimeout(120).build(this).doDownloadFileAsync(info);
    }

}
