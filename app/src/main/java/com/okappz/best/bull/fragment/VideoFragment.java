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
import com.okappz.best.bull.adapter.VideoAdapter;
import com.okappz.best.bull.entty.Wall;

import java.util.List;

public class VideoFragment extends Fragment {

    private List<Wall> walls;
    private int screenWidth;

    public VideoFragment(int screenWidth){
        this.screenWidth = screenWidth;
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_video, container, false);


    }
    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        RecyclerView recyclerView = view.findViewById(R.id.recycler_video);
        GridLayoutManager manager = new GridLayoutManager(view.getContext(), 2);
        recyclerView.setLayoutManager(manager);
        VideoAdapter videoAdapter = new VideoAdapter(view.getContext(),walls,screenWidth);
        recyclerView.setAdapter(videoAdapter);

    }

}
