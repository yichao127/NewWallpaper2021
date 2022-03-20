package com.okappz.best.bull;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.widget.Toast;

import io.flutter.embedding.android.FlutterActivity;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        new Handler().postDelayed(new Runnable() {
            public void run() {
                /**
                 * 跳转到‘/’目录执行main函数
                 */
                FlutterActivity.createDefaultIntent(MainActivity.this);


                /**
                 * 跳转到‘/my_route’目录执行main函数
                 */
                FlutterActivity
                        .withNewEngine()
                        .initialRoute("/my_route")
                        .build(MainActivity.this);


                startActivity(
                        FlutterActivity
                                .withCachedEngine("my_engine_id")
                                .build(MainActivity.this)
                );

            }
        }, 1000);
    }
}