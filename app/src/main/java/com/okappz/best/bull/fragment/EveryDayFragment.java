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
import com.okappz.best.bull.adapter.EveryDayAdapter;
import com.okappz.best.bull.entty.Wall;

import java.util.List;

public class EveryDayFragment extends Fragment {
    List<Wall> walls;
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_everyday, container, false);
    }
    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        RecyclerView recyclerView = view.findViewById(R.id.recycler_everyday);
        GridLayoutManager manager = new GridLayoutManager(view.getContext(), 3);
        recyclerView.setLayoutManager(manager);
        EveryDayAdapter everyDayAdapter = new EveryDayAdapter(view.getContext());
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
