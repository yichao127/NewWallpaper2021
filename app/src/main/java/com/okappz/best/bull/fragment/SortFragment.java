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

import com.google.gson.reflect.TypeToken;
import com.okappz.best.bull.R;
import com.okappz.best.bull.adapter.SortAdapter;
import com.okappz.best.bull.entty.Sort;
import com.okappz.best.bull.net.GsonUtil;
import com.okhttplib.HttpInfo;
import com.okhttplib.OkHttpUtil;
import com.okhttplib.callback.Callback;

import java.io.IOException;
import java.util.List;

import static com.okappz.best.bull.net.URLConst.BASE_URL;
import static com.okappz.best.bull.net.URLConst.SORT;

public class SortFragment extends Fragment {

    private int screenWidth;
    RecyclerView recyclerView;

    public SortFragment(int screenWidth){
        this.screenWidth = screenWidth;
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_sort, container, false);


    }
    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        recyclerView = view.findViewById(R.id.recycler_sort);
        GridLayoutManager manager = new GridLayoutManager(view.getContext(), 1);
        recyclerView.setLayoutManager(manager);
        new Thread(new Runnable() {
            @Override
            public void run() {
                dowJson();
            }
        }).start();

    }

    public void dowJson() {
        OkHttpUtil.getDefault(this).doGetAsync(
                HttpInfo.Builder().setUrl(BASE_URL+SORT).build(),
                new Callback() {
                    @Override
                    public void onFailure(HttpInfo info) throws IOException {
                        Toast.makeText(getContext(),"出错了",Toast.LENGTH_SHORT).show();
                    }

                    @Override
                    public void onSuccess(HttpInfo info) throws IOException {
                        String sJson = info.getRetDetail();
                        List<Sort> sorts = GsonUtil.fromJsonString(sJson, new TypeToken<List<Sort>>() {
                        }.getType());
                        SortAdapter sortAdapter = new SortAdapter(getContext(),sorts,screenWidth);
                        recyclerView.setAdapter(sortAdapter);
                    }
                });
    }

}
