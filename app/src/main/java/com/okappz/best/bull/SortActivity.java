package com.okappz.best.bull;

import android.content.Intent;
import android.os.Bundle;
import android.util.DisplayMetrics;
import android.widget.ImageView;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.gson.JsonObject;
import com.google.gson.reflect.TypeToken;
import com.okappz.best.bull.adapter.SortAdapter;
import com.okappz.best.bull.adapter.SortAdapterDetailed;
import com.okappz.best.bull.entty.Sort;
import com.okappz.best.bull.entty.SortDetailed;
import com.okappz.best.bull.net.GsonUtil;
import com.okhttplib.HttpInfo;
import com.okhttplib.OkHttpUtil;
import com.okhttplib.callback.Callback;

import java.io.IOException;
import java.util.List;

import static com.okappz.best.bull.net.URLConst.BASE_URL;
import static com.okappz.best.bull.net.URLConst.SORT;

public class SortActivity extends AppCompatActivity {

    private ImageView showSort;
    public static String JSON_ID = "JSON_ID";
    private List<Sort> sorts;
    private static int screenWidth;
    private static long JsonId;

    RecyclerView recyclerView;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sort);

        DisplayMetrics metric = new DisplayMetrics();
        getWindowManager().getDefaultDisplay().getMetrics(metric);
        screenWidth = metric.widthPixels;

        Intent intent = getIntent();
        JsonId = intent.getLongExtra(JSON_ID, 0);

        recyclerView = findViewById(R.id.activity_sort_recycler);
        GridLayoutManager manager = new GridLayoutManager(this, 3);
        recyclerView.setLayoutManager(manager);

        new Thread(new Runnable() {
            @Override
            public void run() {
                dowJson();
            }
        }).start();
        Toast.makeText(this,"",Toast.LENGTH_LONG).show();
        Toast.makeText(this,"",Toast.LENGTH_LONG).show();
    }

    public void dowJson() {
        OkHttpUtil.getDefault(this).doPostAsync(
                HttpInfo.Builder().addParam("id", String.valueOf(JsonId)).setUrl(BASE_URL + SORT).build(),
                new Callback() {
                    @Override
                    public void onFailure(HttpInfo info) throws IOException {
                        Toast.makeText(SortActivity.this, "出错了", Toast.LENGTH_SHORT).show();
                    }

                    @Override
                    public void onSuccess(HttpInfo info) throws IOException {
                        String sJson = info.getRetDetail();
                        JsonObject jsonObject = GsonUtil.toJsonObject(sJson);
                        String jsonArray = jsonObject.getAsJsonArray("content").toString();
                        List<SortDetailed> sortDetaileds = GsonUtil.fromJsonString(jsonArray, new TypeToken<List<SortDetailed>>() {
                        }.getType());
                        SortAdapterDetailed sortAdapter = new SortAdapterDetailed(SortActivity.this, sortDetaileds, screenWidth);
                        recyclerView.setAdapter(sortAdapter);
                    }
                });
    }

}
