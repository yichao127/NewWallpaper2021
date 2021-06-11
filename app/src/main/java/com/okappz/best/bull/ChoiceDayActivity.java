package com.okappz.best.bull;

import android.app.WallpaperManager;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.media.Image;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.bumptech.glide.Glide;
import com.liulishuo.filedownloader.BaseDownloadTask;
import com.liulishuo.filedownloader.FileDownloadListener;
import com.liulishuo.filedownloader.FileDownloader;
import com.okappz.best.bull.net.URLConst;
import com.okhttplib.bean.DownloadFileInfo;

import java.io.File;
import java.io.IOException;

public class ChoiceDayActivity extends AppCompatActivity {

    public static final String THUMBNAIL = "THUMBNAIL";
    public static final String PREVIEW = "PREVIEW";

    private String thumbnail;
    private String preview;

    private ImageView show_choice_img;
    private DownloadFileInfo fileInfo;

    private String serverImagePath;
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
        back = findViewById(R.id.back_img);

        serverImagePath = URLConst.BASE_URL+preview;

        Glide.with(this).load(URLConst.BASE_URL+preview).into(show_choice_img);
        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
        FileDownloader.setup(getApplicationContext());
        dowTOPhone();


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
                Toast.makeText(ChoiceDayActivity.this,"Success", Toast.LENGTH_SHORT).show();
            }
        });


    }


    public void dowTOPhone() {

//        WallActivity.this.getFilesDir().getAbsolutePath();    可以获取到 SDCard/Android/data/你的应用的包名/files/
//        /data/user/0/com.magic.emoji.pho/files
        String filesDirPath = getFilesDir().getAbsolutePath();
//        /data/user/0/com.magic.emoji.pho/files/emojidow
        String appPath = filesDirPath + "/newWallpaper2012";
//        String appPath = "SDCard/Android/data/newWallpaper2012";
        File file = new File(appPath);
        //文件夹不存在，则创建它
        if (!file.exists()) {
            file.mkdir();
        }
        String imgName = serverImagePath.substring(serverImagePath.lastIndexOf("/")+1);
        progressBar = findViewById(R.id.progress_bar);
        softNamePath = appPath + "/" + System.currentTimeMillis() + imgName;
        FileDownloader.getImpl().create(serverImagePath).setPath(softNamePath).setListener(new FileDownloadListener() {
            @Override
            protected void pending(BaseDownloadTask task, int soFarBytes, int totalBytes) {

            }

            @Override
            protected void progress(BaseDownloadTask task, int soFarBytes, int totalBytes) {
                int percent = (int) (soFarBytes * 100 / totalBytes);
                progressBar.setProgress(percent);
            }

            @Override
            protected void completed(BaseDownloadTask task) {
                setToPhone.setVisibility(View.VISIBLE);
                choiceImgshowText.setVisibility(View.INVISIBLE);
                progressBar.setVisibility(View.INVISIBLE);
                back.setVisibility(View.VISIBLE);
            }

            @Override
            protected void paused(BaseDownloadTask task, int soFarBytes, int totalBytes) {

            }

            @Override
            protected void error(BaseDownloadTask task, Throwable e) {

            }

            @Override
            protected void warn(BaseDownloadTask task) {

            }
        }).start();
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













//    private void download(){
//        String fileName=  preview.substring(preview.lastIndexOf("/")+1);
//        if(null == fileInfo)
//            fileInfo = new DownloadFileInfo(URLConst.BASE_URL+preview, fileName,new ProgressCallback(){
//                @Override
//                public void onProgressMain(int percent, long bytesWritten, long contentLength, boolean done) {
//                    Log.d("PATH_DOWNLOAD","preview"+percent);
//                }
//                @Override
//                public void onResponseMain(String filePath, HttpInfo info) {
//                    if(info.isSuccessful()){
//                        Toast.makeText(ChoiceDayActivity.this,fileInfo.getDownloadStatus(),Toast.LENGTH_SHORT).show();
//                    }else{
//                        Toast.makeText(ChoiceDayActivity.this,info.getRetDetail(),Toast.LENGTH_SHORT).show();
//                    }
//                }
//            });
//        HttpInfo info = HttpInfo.Builder().addDownloadFile(fileInfo).build();
//        OkHttpUtil.Builder().setReadTimeout(120).build(this).doDownloadFileAsync(info);
//    }

}
