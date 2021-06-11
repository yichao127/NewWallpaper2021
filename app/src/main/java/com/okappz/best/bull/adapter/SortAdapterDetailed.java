package com.okappz.best.bull.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.okappz.best.bull.R;
import com.okappz.best.bull.entty.SortDetailed;
import com.okappz.best.bull.net.URLConst;

import java.util.List;

import static com.okappz.best.bull.net.URLConst.SORT_DETAILED;

public class SortAdapterDetailed extends RecyclerView.Adapter<RecyclerView.ViewHolder> {
    private List<SortDetailed> sortDetaileds; // 数据源
    private Context context;
    private static int screenWidth;
    private static ViewGroup.LayoutParams layoutParams ;

    public SortAdapterDetailed(Context context, List<SortDetailed> sortDetaileds, int screenWidth) {
        this.sortDetaileds = sortDetaileds;
        this.context = context;
        this.screenWidth = screenWidth;
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new SortDetailHolder(LayoutInflater.from(context).inflate(R.layout.adapter_sort_detailed, null, false));
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {
        SortDetailHolder mholder =(SortDetailHolder)holder;
        String thumb=sortDetaileds.get(position).thumb;
        String imgUrl=URLConst.BASE_URL+SORT_DETAILED+thumb;
        Glide.with(context).load(imgUrl).into(mholder.sortdetailed_img);
    }

    @Override
    public int getItemCount() {
        return sortDetaileds.size();
    }

    static class SortDetailHolder extends RecyclerView.ViewHolder {
        private ImageView sortdetailed_img;
        private View itemView;

        public SortDetailHolder(View itemView) {
            super(itemView);
            this.itemView = itemView;
            sortdetailed_img = itemView.findViewById(R.id.sortdetailed_img);
            layoutParams = sortdetailed_img.getLayoutParams();
//            layoutParams.height = screenWidth / 2;
            layoutParams.width = layoutParams.height = screenWidth / 3;
        }
    }

}
