package com.okappz.best.bull.adapter;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.okappz.best.bull.ChoiceDayActivity;
import com.okappz.best.bull.R;
import com.okappz.best.bull.VideoActivity;
import com.okappz.best.bull.entty.Wall;
import com.okappz.best.bull.net.URLConst;
import com.zhengsr.viewpagerlib.callback.PageHelperListener;
import com.zhengsr.viewpagerlib.view.BannerViewPager;

import java.util.ArrayList;
import java.util.List;


public class ChoiceDayAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {
    private List<Wall> verticalDatas = new ArrayList<>();; // 数据源
    private static List<Wall> horizontalDatas;
    private Context context;
    private static int screenWidth;
    private final int HORIZONTAL_VIEW = 1000;
    private final int VERTICAL_VIEW = 1001;


    public ChoiceDayAdapter(Context context, int screenWidth) {
        this.context = context;
        verticalDatas.add(new Wall());
        ChoiceDayAdapter.screenWidth = screenWidth;
    }

    public void addHorizontalDatas(List<Wall> horizontalDatas) {
        this.horizontalDatas=horizontalDatas;
        notifyItemChanged(0);
    }

    public void addVerticalDatas(List<Wall> VerticalDatas) {
        this.verticalDatas.addAll(VerticalDatas);
        notifyDataSetChanged();
    }


    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        if (HORIZONTAL_VIEW == viewType) {
            return new HorizontalViewHolder(LayoutInflater.from(context).inflate(R.layout.adapter_choice_day_horizontal, null, false));
        } else {
            return new VerticalViewHolder(LayoutInflater.from(context).inflate(R.layout.adapter_choice_day_vertical, null, false));
        }
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {
        if (holder instanceof HorizontalViewHolder) {
            HorizontalViewHolder horizontalViewHolder = (HorizontalViewHolder) holder;
            horizontalViewHolder.bannerViewPager.setCurrentPosition(0);
            horizontalViewHolder.bannerViewPager.setPageListener(R.layout.loop_layout, horizontalDatas, new PageHelperListener<Wall>() {
                @Override
                public void bindView(View view, final Wall data, int position) {
                    ImageView imageView = view.findViewById(R.id.loop_icon);
                    TextView loop_text = view.findViewById(R.id.loop_text);
                    Glide.with(view)
                            .load(URLConst.BASE_URL+data.thumbnail)
                            .into(imageView);
                    loop_text.setText(data.title);
                }

                @Override
                public void onItemClick(View view, Wall data, int position) {
                    super.onItemClick(view, data, position);
                }
            });
        } else {
            VerticalViewHolder mholder = (VerticalViewHolder) holder;
            Glide.with(context).load(URLConst.BASE_URL + verticalDatas.get(position).thumbnail).into(mholder.choice_day_vertical_view);
            mholder.choice_day_vertical_view.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent intent = new Intent(context, ChoiceDayActivity.class);
                    intent.putExtra(VideoActivity.THUMBNAIL, verticalDatas.get(position).getThumbnail());
                    intent.putExtra(VideoActivity.PREVIEW, verticalDatas.get(position).getPreview());
                    context.startActivity(intent);
                }
            });

        }
    }

    @Override
    public int getItemCount() {
        return verticalDatas.size();
    }

    @Override
    public int getItemViewType(int position) {
        if (position == 0) {
            return HORIZONTAL_VIEW;
        }
        return VERTICAL_VIEW;
    }

    static class VerticalViewHolder extends RecyclerView.ViewHolder {
        public ImageView choice_day_vertical_view;
        private View itemView;

        public VerticalViewHolder(View itemView) {
            super(itemView);
            this.itemView = itemView;
            choice_day_vertical_view = itemView.findViewById(R.id.choice_day_vertical_view);

            if (choice_day_vertical_view != null) {
                ViewGroup.LayoutParams layoutParams = choice_day_vertical_view.getLayoutParams();
                layoutParams.width = layoutParams.height = screenWidth / 3;
                choice_day_vertical_view.setLayoutParams(layoutParams);
            }

        }
    }

    static class HorizontalViewHolder extends RecyclerView.ViewHolder {
        private BannerViewPager bannerViewPager;
        public HorizontalViewHolder(@NonNull View itemView) {
            super(itemView);
            bannerViewPager = itemView.findViewById(R.id.normal_banner);
        }

    }


}
