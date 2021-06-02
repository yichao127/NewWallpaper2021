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
import com.okappz.best.bull.SortActivity;
import com.okappz.best.bull.VideoActivity;
import com.okappz.best.bull.entty.Wall;

import java.util.List;

public class SortAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    private List<Wall> walls; // 数据源
    private Context context;
    private static int screenWidth;
    private static ViewGroup.LayoutParams layoutParams ;

    public SortAdapter(Context context, List<Wall> walls,int screenWidth) {
        this.walls = walls;
        this.context = context;
        this.screenWidth = screenWidth;
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new MainViewHolder(LayoutInflater.from(context).inflate(R.layout.adapter_sort, null, false));
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {
        MainViewHolder mholder = (MainViewHolder)holder;

        Glide.with(context).load("http://192.168.18.240:8080/newWallPaper/image/every/3.jpg").into(mholder.sort_img);
        mholder.sort_img.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(context, SortActivity.class);
                //        intent.putExtra(WallActivity.IMG_URL, item);
                //        intent.putExtra(WallActivity.WALLID, walls.get(index).wallpaperId);
                context.startActivity(intent);
            }
        });


    }

    @Override
    public int getItemCount() {
        return 8;
    }

    static class MainViewHolder extends RecyclerView.ViewHolder {
        private ImageView sort_img;
        private View itemView;

        public MainViewHolder(View itemView) {
            super(itemView);
            this.itemView = itemView;
            sort_img = itemView.findViewById(R.id.sort_img);
            layoutParams = sort_img.getLayoutParams();
            layoutParams.height = screenWidth / 2;
        }
    }
}
