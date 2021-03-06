package com.okappz.best.bull.fragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.github.nukc.LoadMoreWrapper.LoadMoreAdapter;
import com.github.nukc.LoadMoreWrapper.LoadMoreWrapper;
import com.google.gson.reflect.TypeToken;
import com.okappz.best.bull.R;
import com.okappz.best.bull.adapter.ChoiceDayAdapter;
import com.okappz.best.bull.base.PageHelperBean;
import com.okappz.best.bull.entty.Wall;
import com.okappz.best.bull.net.GsonUtil;
import com.okappz.best.bull.net.URLConst;
import com.okhttplib.HttpInfo;
import com.okhttplib.OkHttpUtil;
import com.okhttplib.callback.Callback;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import static com.okappz.best.bull.net.URLConst.BASE_URL;
import static com.okappz.best.bull.net.URLConst.CHOICEDAY;

public class ChoiceDayFragment extends Fragment {

    private int screenWidth;
    private RecyclerView recyclerView;
    private ChoiceDayAdapter choiceDayAdapter;
    private int page = 0;
    LoadMoreWrapper mWrapper;


    private static final int[] RESID = {
            R.mipmap.beauty1,
            R.mipmap.beauty2,
            R.mipmap.beauty3,
    };
    private static final String[] TEXT = {"图像处理", "LSB开发", "游戏开发"};
    private List<PageHelperBean> mDatas;

    public ChoiceDayFragment(int screenWidth) {
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

        recyclerView = view.findViewById(R.id.recycler_everyday);
        GridLayoutManager manager = new GridLayoutManager(view.getContext(), 3);
        recyclerView.setLayoutManager(manager);
        choiceDayAdapter = new ChoiceDayAdapter(getContext(), screenWidth);
        mWrapper = LoadMoreWrapper.with(choiceDayAdapter)
                .setFooterView(R.layout.base_footer) // view or layout resource
                .setShowNoMoreEnabled(true) // enable show NoMoreView，default false
                .setListener(new LoadMoreAdapter.OnLoadMoreListener() {
                    @Override
                    public void onLoadMore(LoadMoreAdapter.Enabled enabled) {
                        page++;
                        dowJsonVertical(page);
                    }
                });
        mWrapper.into(recyclerView);
        manager.setSpanSizeLookup(new GridLayoutManager.SpanSizeLookup() {
            @Override
            public int getSpanSize(int position) {
                if (position == 0) {
                    return 3;
                }
                return 1;
            }
        });
        dowJsonHorizontal();
    }

    public void dowJsonHorizontal() {
        OkHttpUtil.getDefault(this).doGetAsync(
                HttpInfo.Builder().setUrl(BASE_URL + URLConst.EVERYDAY).build(),
                new Callback() {
                    @Override
                    public void onFailure(HttpInfo info) throws IOException {
                    }

                    @Override
                    public void onSuccess(HttpInfo info) throws IOException {
                        String sJson = info.getRetDetail();
                        List<Wall> walls = GsonUtil.fromJsonString(sJson, new TypeToken<List<Wall>>() {
                        }.getType());
                        choiceDayAdapter.addHorizontalDatas(walls);
                    }
                });
    }


    public void dowJsonVertical(int page) {
        OkHttpUtil.getDefault(this).doGetAsync(
                HttpInfo.Builder().addParam("page", String.valueOf(page)).setUrl(BASE_URL + CHOICEDAY).build(),
                new Callback() {
                    @Override
                    public void onFailure(HttpInfo info) throws IOException {
                    }

                    @Override
                    public void onSuccess(HttpInfo info) throws IOException {
                        String sJson = info.getRetDetail();
                        List<Wall> walls = GsonUtil.fromJsonString(sJson, new TypeToken<List<Wall>>() {
                        }.getType());
                        choiceDayAdapter.addVerticalDatas(walls);
                    }
                });
    }


}
