package com.okappz.best.bull.adapter;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.okappz.best.bull.R;

import java.io.File;


public class EveryDayAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    private Context context;

    public EveryDayAdapter(Context context) {
        this.context = context;
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new MainViewHolder(LayoutInflater.from(context).inflate(R.layout.adapter_everyday, null, false));
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {
        MainViewHolder mholder = (MainViewHolder)holder;
//        Glide.with(context).load("http://192.168.18.240:8080/newWallPaper/image/every/1.jpg").into(mholder.everyday_view);
        mholder.test1.setText("aaaaa");
    }

    @Override
    public int getItemCount() {
        return 10;
    }

    static class MainViewHolder extends RecyclerView.ViewHolder {
        private ImageView everyday_view;
        private TextView test1;
        private View itemView;

        public MainViewHolder(View itemView) {
            super(itemView);
            this.itemView = itemView;
            everyday_view = itemView.findViewById(R.id.everyday_view);
            test1 = itemView.findViewById(R.id.test1);

        }
    }

}
