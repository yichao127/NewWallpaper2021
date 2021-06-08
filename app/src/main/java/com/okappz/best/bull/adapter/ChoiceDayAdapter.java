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
import com.okappz.best.bull.ChoiceDayActivity;
import com.okappz.best.bull.R;
import com.okappz.best.bull.VideoActivity;
import com.okappz.best.bull.base.PageHelperBean;
import com.okappz.best.bull.entty.Wall;
import com.okappz.best.bull.net.URLConst;
import com.zhengsr.viewpagerlib.callback.PageHelperListener;
import com.zhengsr.viewpagerlib.indicator.CircleIndicator;
import com.zhengsr.viewpagerlib.view.BannerViewPager;

import java.util.List;


public class ChoiceDayAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {


    private List<Wall> walls; // 数据源
    private Context context;
    private  int screenWidth;

    private final int HORIZONTAL_VIEW = 1000;
    private final int VERTICAL_VIEW = 1001;
    private final int GRID_VIEW = 1002;

    private  List<PageHelperBean> mDatas;

    private  ViewGroup.LayoutParams layoutParams ;



    public ChoiceDayAdapter(Context context, int screenWidth,List<PageHelperBean> mDatas, List<Wall> walls) {
        this.context = context;
        this.screenWidth = screenWidth;
        this.mDatas = mDatas;
        this.walls = walls;
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {


        if(HORIZONTAL_VIEW==viewType){
            return new HorizontalHolder(LayoutInflater.from(context).inflate(R.layout.vertical_everyday2, null, false));


        } else{
            return new VerticalHolder(LayoutInflater.from(context).inflate(R.layout.adapter_choice_day_vertical, null, false));
        }
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {

        if (holder instanceof HorizontalHolder) {



        }else {

            VerticalHolder mholder = (VerticalHolder) holder;
            Glide.with(context).load(URLConst.BASE_URL+walls.get(position).thumbnail).into(mholder.choice_day_vertical_view);
            mholder.choice_day_vertical_view.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent intent = new Intent(context, ChoiceDayActivity.class);
                    intent.putExtra(VideoActivity.THUMBNAIL,walls.get(position).getPreview());
                    intent.putExtra(VideoActivity.PREVIEW,walls.get(position).getPreview());
                    context.startActivity(intent);
                }
            });

        }
//        mholder.test1.setText("aaaaa");
    }

    @Override
    public int getItemCount() {
        return walls.size();
    }

    @Override
    public int getItemViewType(int position) {
        if(position==0){
            return HORIZONTAL_VIEW;
        }
        return  VERTICAL_VIEW;
    }

    static class VerticalHolder extends RecyclerView.ViewHolder {
        private ImageView choice_day_vertical_view;
        private View itemView;

        public VerticalHolder(View itemView) {
            super(itemView);
            this.itemView = itemView;
            choice_day_vertical_view = itemView.findViewById(R.id.choice_day_vertical_view);
            ViewGroup.LayoutParams layoutParams = choice_day_vertical_view.getLayoutParams();
//            layoutParams.width = layoutParams.height = screenWidth / 3;
        }
    }

    static class HorizontalHolder extends RecyclerView.ViewHolder {
        private View itemView;
        private BannerViewPager bannerViewPager;
        private CircleIndicator indicator;

        public HorizontalHolder(View itemView) {
            super(itemView);
            this.itemView = itemView;

            BannerViewPager bannerViewPager = itemView.findViewById(R.id.normal_banner);
            CircleIndicator indicator = itemView.findViewById(R.id.normal_indicator);

            bannerViewPager.addIndicator(indicator);
            bannerViewPager.setCurrentPosition(1);



        }
    }

    private  void showBanner(BannerViewPager bannerViewPager, CircleIndicator indicator) {
        bannerViewPager.addIndicator(indicator);

        bannerViewPager.setPageListener(R.layout.loop_layout, mDatas, new PageHelperListener<PageHelperBean>() {
            @Override
            public void bindView(View view, final PageHelperBean data, int position) {
                setText(view, R.id.loop_text, data.msg);
                ImageView imageView = view.findViewById(R.id.loop_icon);
                Glide.with(view)
                        .load(data.resId)
                        .into(imageView);
            }

            @Override
            public void onItemClick(View view, PageHelperBean data, int position) {
                super.onItemClick(view, data, position);

            }

        });
    }



}
