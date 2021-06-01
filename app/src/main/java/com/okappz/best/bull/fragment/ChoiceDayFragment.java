package com.okappz.best.bull.fragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.okappz.best.bull.R;
import com.okappz.best.bull.adapter.ChoiceDayAdapter;
import com.okappz.best.bull.base.PageHelperBean;
import com.okappz.best.bull.entty.Wall;

import java.util.ArrayList;
import java.util.List;

public class ChoiceDayFragment extends Fragment {
    List<Wall> walls;
    private int screenWidth;

    private static final int[] RESID = {
            R.mipmap.beauty1,
            R.mipmap.beauty2,
            R.mipmap.beauty3,
    };
    private static final String[] TEXT = {"图像处理", "LSB开发", "游戏开发"};
    private List<PageHelperBean> mDatas;

    public ChoiceDayFragment(int screenWidth){
        this.screenWidth = screenWidth;
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_everyday, container, false);
    }
    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        mDatas = new ArrayList<>();
        //配置数据，这里是resid和text
        for (int i = 0; i < TEXT.length; i++) {
            PageHelperBean bean = new PageHelperBean();
            bean.resId = RESID[i];
            bean.msg = TEXT[i];
            mDatas.add(bean);
        }


        RecyclerView recyclerView = view.findViewById(R.id.recycler_everyday);
        GridLayoutManager manager = new GridLayoutManager(view.getContext(), 3);
        recyclerView.setLayoutManager(manager);
        ChoiceDayAdapter everyDayAdapter = new ChoiceDayAdapter(view.getContext(),screenWidth,mDatas);
        manager.setSpanSizeLookup(new GridLayoutManager.SpanSizeLookup() {
            @Override
            public int getSpanSize(int position) {
                if (position == 0) {
                    return 3;
                }
                return 1;
            }
        });

        recyclerView.setAdapter(everyDayAdapter);
    }

}
