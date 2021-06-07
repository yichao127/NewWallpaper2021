package com.okappz.best.bull.adapter;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.okappz.best.bull.R;
import com.okappz.best.bull.SortActivity;
import com.okappz.best.bull.entty.Sort;
import com.okappz.best.bull.net.URLConst;

import java.util.List;

import static com.okappz.best.bull.net.URLConst.SORTAND;

public class SortAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    private List<Sort> sorts; // 数据源
    private Context context;
    private static int screenWidth;
    private static ViewGroup.LayoutParams layoutParams ;

    public SortAdapter(Context context, List<Sort> sorts, int screenWidth) {
        this.sorts = sorts;
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
        mholder.sort_Text.setText(sorts.get(position).title);
        Glide.with(context).load(URLConst.BASE_URL+SORTAND+sorts.get(position).thumbnail).into(mholder.sort_img);
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
        return sorts.size();
    }

    static class MainViewHolder extends RecyclerView.ViewHolder {
        private ImageView sort_img;
        private TextView sort_Text;
        private View itemView;

        public MainViewHolder(View itemView) {
            super(itemView);
            this.itemView = itemView;
            sort_img = itemView.findViewById(R.id.sort_img);
            sort_Text = itemView.findViewById(R.id.sort_Text);
            layoutParams = sort_img.getLayoutParams();
            layoutParams.height = screenWidth / 2;
        }
    }
}
