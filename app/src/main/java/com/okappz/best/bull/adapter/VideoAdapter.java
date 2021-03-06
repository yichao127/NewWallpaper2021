package com.okappz.best.bull.adapter;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.okappz.best.bull.R;
import com.okappz.best.bull.VideoActivity;
import com.okappz.best.bull.entty.Video;
import com.okappz.best.bull.entty.Wall;
import com.okappz.best.bull.net.URLConst;

import java.util.ArrayList;
import java.util.List;

public class VideoAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    private List<Video> videos=new ArrayList<>(); // 数据源
    private Context context;
    private static int screenWidth;
    private static ViewGroup.LayoutParams layoutParams;

    public VideoAdapter(Context context, int screenWidth) {
        this.context = context;
        VideoAdapter.screenWidth = screenWidth;
    }

    public void addData(List<Video> videos){
       this. videos.addAll(videos);
    }


    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new MainViewHolder(LayoutInflater.from(context).inflate(R.layout.adapter_video, null, false));
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {
        MainViewHolder mholder = (MainViewHolder) holder;

        String hum = videos.get(position).thumbnail;
        String link = videos.get(position).link;
        String url = URLConst.BASE_URL + URLConst.VIDEO_PATH + hum;

        Glide.with(context).load(url).into(mholder.video_img);
        mholder.video_img.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(context,VideoActivity.class);
                intent.putExtra(VideoActivity.PREVIEW,link);
                intent.putExtra(VideoActivity.THUMBNAIL,hum);
                context.startActivity(intent);
            }
        });

    }

    @Override
    public int getItemCount() {
        return videos.size();
    }

    static class MainViewHolder extends RecyclerView.ViewHolder {
        private ImageView video_img;
        private View itemView;

        public MainViewHolder(View itemView) {
            super(itemView);
            this.itemView = itemView;
            video_img = itemView.findViewById(R.id.video_img);
            layoutParams = video_img.getLayoutParams();
            layoutParams.width = layoutParams.height = screenWidth / 2;
        }
    }

}
