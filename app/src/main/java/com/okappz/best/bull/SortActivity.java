package com.okappz.best.bull;

import android.app.WallpaperManager;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.bumptech.glide.Glide;
import com.liulishuo.filedownloader.BaseDownloadTask;
import com.liulishuo.filedownloader.FileDownloadListener;
import com.liulishuo.filedownloader.FileDownloader;

import java.io.File;
import java.io.IOException;

public class SortActivity extends AppCompatActivity {

    private ImageView showSort;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sort);
        showSort = findViewById(R.id.show_sort);
        Button button = findViewById(R.id.set_view);
        Glide.with(this).load("http://192.168.18.240:8080/newWallPaper/image/every/3.jpg").into(showSort);
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
            Bitmap bitmap = BitmapFactory.decodeFile("http://192.168.18.240:8080/newWallPaper/image/every/3.jpg");
            mWallManager.setBitmap(bitmap);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}
