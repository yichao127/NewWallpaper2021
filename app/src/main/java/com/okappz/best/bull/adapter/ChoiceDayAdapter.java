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
import com.okappz.best.bull.base.PageHelperBean;
import com.zhengsr.viewpagerlib.callback.PageHelperListener;
import com.zhengsr.viewpagerlib.indicator.CircleIndicator;
import com.zhengsr.viewpagerlib.view.BannerViewPager;

import java.util.List;


public class ChoiceDayAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    private Context context;

    private static int screenWidth;

    private final int HORIZONTAL_VIEW = 1000;
    private final int VERTICAL_VIEW = 1001;
    private final int GRID_VIEW = 1002;

    private static List<PageHelperBean> mDatas;

    public ChoiceDayAdapter(Context context, int screenWidth,List<PageHelperBean> mDatas) {
        this.context = context;
        this.screenWidth = screenWidth;
        this.mDatas = mDatas;
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {


        if(VERTICAL_VIEW==viewType){
            return new VerticalHolder(LayoutInflater.from(context).inflate(R.layout.adapter_everyday, null, false));

        } else{
            return new HorizontalHolder(LayoutInflater.from(context).inflate(R.layout.vertical_everyday2, null, false));
        }
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {

        if (holder instanceof VerticalHolder) {
            VerticalHolder mholder = (VerticalHolder) holder;
            Glide.with(context).load("http://192.168.18.240:8080/newWallPaper/image/every/1.jpg").into(mholder.everyday_view);
        }else {
//            showBanner(bannerViewPager,indicator);
        }
//        mholder.test1.setText("aaaaa");
    }

    @Override
    public int getItemCount() {
        return 2;
    }

    @Override
    public int getItemViewType(int position) {
        if(position==0){
            return VERTICAL_VIEW;
        }
        return  HORIZONTAL_VIEW;
    }

    static class VerticalHolder extends RecyclerView.ViewHolder {
        private ImageView everyday_view;
        private View itemView;

        public VerticalHolder(View itemView) {
            super(itemView);
            this.itemView = itemView;
            everyday_view = itemView.findViewById(R.id.everyday_view);
            ViewGroup.LayoutParams layoutParams = everyday_view.getLayoutParams();
            layoutParams.width = layoutParams.height = screenWidth / 3;
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

    private static void showBanner(BannerViewPager bannerViewPager, CircleIndicator indicator) {
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
