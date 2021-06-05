package com.okappz.best.bull;

import android.app.WallpaperManager;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.bumptech.glide.Glide;
import com.okappz.best.bull.net.URLConst;

import java.io.IOException;

public class VideoActivity extends AppCompatActivity {
    public static final String THUMBNAIL = "THUMBNAIL";
    public static final String PREVIEW = "PREVIEW";

    private String thumbnail;
    private String preview;

    private ImageView showVideo;
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
}
