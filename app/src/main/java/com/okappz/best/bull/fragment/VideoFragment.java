package com.okappz.best.bull.fragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.github.nukc.LoadMoreWrapper.LoadMoreAdapter;
import com.github.nukc.LoadMoreWrapper.LoadMoreWrapper;
import com.google.gson.reflect.TypeToken;
import com.okappz.best.bull.R;
import com.okappz.best.bull.adapter.VideoAdapter;
import com.okappz.best.bull.entty.Video;
import com.okappz.best.bull.net.GsonUtil;
import com.okhttplib.HttpInfo;
import com.okhttplib.OkHttpUtil;
import com.okhttplib.callback.Callback;


import java.io.IOException;
import java.util.List;

import static com.okappz.best.bull.net.URLConst.BASE_URL;
import static com.okappz.best.bull.net.URLConst.VIDEO;

public class VideoFragment extends Fragment {
    private int screenWidth;
    RecyclerView recyclerView;
    LoadMoreWrapper mWrapper;
    private int page = 0;
    private VideoAdapter videoAdapter;

    public VideoFragment(int screenWidth) {
        this.screenWidth = screenWidth;
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_video, container, false);


    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        recyclerView = view.findViewById(R.id.recycler_video);
        GridLayoutManager manager = new GridLayoutManager(view.getContext(), 2);
        recyclerView.setLayoutManager(manager);
        videoAdapter = new VideoAdapter(getContext(), screenWidth);
        recyclerView.setAdapter(videoAdapter);
        mWrapper = LoadMoreWrapper.with(videoAdapter)
                .setFooterView(R.layout.base_footer) // view or layout resource
                .setShowNoMoreEnabled(true) // enable show NoMoreView，default false
                .setListener(new LoadMoreAdapter.OnLoadMoreListener() {
                    @Override
                    public void onLoadMore(LoadMoreAdapter.Enabled enabled) {
                        page++;
                        dowJson(page);
                    }
                });
        mWrapper.into(recyclerView);
    }


    public void dowJson(int page) {
        OkHttpUtil.getDefault(this).doGetAsync(
                HttpInfo.Builder().addParam("page", String.valueOf(page)).setUrl(BASE_URL + VIDEO).build(),
                new Callback() {
                    @Override
                    public void onFailure(HttpInfo info) throws IOException {
                        Toast.makeText(getContext(), info.getRetDetail(), Toast.LENGTH_SHORT).show();
                    }

                    @Override
                    public void onSuccess(HttpInfo info) throws IOException {
                        String sJson = info.getRetDetail();
                        List<Video> videos = GsonUtil.fromJsonString(sJson, new TypeToken<List<Video>>() {
                        }.getType());
                        videoAdapter.addData(videos);
                        videoAdapter.notifyDataSetChanged();
                    }
                });
    }

}
