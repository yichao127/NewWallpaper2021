package com.okappz.best.bull.adapter;

import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;
import android.view.ViewGroup;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.okappz.best.bull.ChoiceDayActivity;
import com.okappz.best.bull.R;
import com.okappz.best.bull.VideoActivity;
import com.okappz.best.bull.base.PageHelperBean;
import com.okappz.best.bull.entty.Wall;
import com.okappz.best.bull.net.URLConst;

import java.util.List;


public class ChoiceDayAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {


    private List<Wall> walls; // 数据源
    private Context context;
    private static int screenWidth;

    private final int HORIZONTAL_VIEW = 1000;
    private final int VERTICAL_VIEW = 1001;
    private final int GRID_VIEW = 1002;

    private  List<PageHelperBean> mDatas;





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
            return new HorizontalViewHolder(LayoutInflater.from(context).inflate(R.layout.adapter_choice_day_horizontal, null, false));


        } else{
            return new VerticalViewHolder(LayoutInflater.from(context).inflate(R.layout.adapter_choice_day_vertical, null, false));
        }
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {

        if (holder instanceof HorizontalViewHolder) {

            ((HorizontalViewHolder) holder).hor_recyclerview.setLayoutManager(new LinearLayoutManager(context, LinearLayoutManager.HORIZONTAL, false));
            ((HorizontalViewHolder) holder).hor_recyclerview.setAdapter(new HorizontalViewHolder.HorizontalAdapter(context,walls,screenWidth));

        }else {

            VerticalViewHolder mholder = (VerticalViewHolder) holder;
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

    static class VerticalViewHolder extends RecyclerView.ViewHolder {
        public ImageView choice_day_vertical_view;
        private View itemView;

        public VerticalViewHolder(View itemView) {
            super(itemView);
            this.itemView = itemView;
            choice_day_vertical_view = itemView.findViewById(R.id.choice_day_vertical_view);

            if(choice_day_vertical_view!=null){
                ViewGroup.LayoutParams layoutParams = choice_day_vertical_view.getLayoutParams();
                layoutParams.width = layoutParams.height = screenWidth / 3;
            }

        }
    }



    static class HorizontalViewHolder extends  RecyclerView.ViewHolder{
        private RecyclerView hor_recyclerview;

        private List<Integer> data;

        private int scrollX;//纪录X移动的距离

        private static int HORIZONTAL_VIEW_X = 0;//水平RecyclerView滑动的距离

        private boolean isLoadLastState = false;//是否加载了之前的状态


        public HorizontalViewHolder(@NonNull View itemView) {
            super(itemView);
            hor_recyclerview = (RecyclerView) itemView.findViewById(R.id.choice_recycler);
            //为了保存移动距离，所以添加滑动监听
            hor_recyclerview.addOnScrollListener(new RecyclerView.OnScrollListener() {
                @Override
                public void onScrolled(RecyclerView recyclerView, int dx, int dy) {
                    //每次条目重新加载时，都会滑动到上次的距离
                    if (!isLoadLastState) {
                        isLoadLastState = true;
                        hor_recyclerview.scrollBy(HORIZONTAL_VIEW_X, 0);
                    }
                    //dx为每一次移动的距离，所以我们需要做累加操作
                    scrollX += dx;
                }
            });
        }


        /**
         * 在条目回收时调用，保存X轴滑动的距离
         */
        public void saveStateWhenDestory() {
            HORIZONTAL_VIEW_X = scrollX;
            isLoadLastState = false;
            scrollX = 0;
        }


        private static class HorizontalAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {


            private Context context;
//            private DataInfor data;
            private List<Wall> walls;
            private static int screenWidth;


            public HorizontalAdapter(Context context,List<Wall> walls,int screenWidth){
                this.context = context;
                this.walls = walls;
                this.screenWidth = screenWidth;
            }

            @Override
            public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
//                return new ItemViewHolder(R.layout.item_x2_imageview, parent, viewType);
                return new HorizontalViewHolderAnd(LayoutInflater.from(context).inflate(R.layout.adapter_choice_horizontal, null, false));
            }

            @Override
            public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {
                HorizontalViewHolderAnd mholder = (HorizontalViewHolderAnd) holder;
//                Glide.with(context).load(URLConst.BASE_URL+walls.get(position).thumbnail).into(mholder.choice_day_vertical_view);
                Bitmap bitmap = BitmapFactory.decodeResource(context.getResources(), R.drawable.aaa);
                mholder.choice_day_horizontal_view.setImageBitmap(bitmap);
            }

            @Override
            public int getItemCount() {
                return 8;
            }

            static class HorizontalViewHolderAnd extends RecyclerView.ViewHolder {
                public ImageView choice_day_horizontal_view;
                private View itemView;

                public HorizontalViewHolderAnd(View itemView) {
                    super(itemView);
                    this.itemView = itemView;
                    choice_day_horizontal_view = itemView.findViewById(R.id.choice_day_horizontal_view);

                    if(choice_day_horizontal_view!=null){
                        ViewGroup.LayoutParams layoutParams = choice_day_horizontal_view.getLayoutParams();
                        layoutParams.width = layoutParams.height = screenWidth / 3;
                    }

                }
            }

        }
    }














//    static class HorizontalHolder extends RecyclerView.ViewHolder {
//        private View itemView;
//        private BannerViewPager bannerViewPager;
//        private CircleIndicator indicator;
//        private ImageView choice_day_vertical_view;
//
//        public HorizontalHolder(View itemView) {
//            super(itemView);
//            this.itemView = itemView;
//
////            BannerViewPager bannerViewPager = itemView.findViewById(R.id.normal_banner);
////            CircleIndicator indicator = itemView.findViewById(R.id.normal_indicator);
//            choice_day_vertical_view = itemView.findViewById(R.id.choice_day_vertical_view);
//            bannerViewPager.addIndicator(indicator);
//            bannerViewPager.setCurrentPosition(1);
//        }
//    }





}
