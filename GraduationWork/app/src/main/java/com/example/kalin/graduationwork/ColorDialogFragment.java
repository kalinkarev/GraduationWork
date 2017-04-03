package com.example.kalin.graduationwork;

import android.app.DialogFragment;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.kalin.graduationwork.adapter.ColorsAdapter;
import com.example.kalin.graduationwork.model.ColorData;

import java.util.ArrayList;

/**
 * Created by Kalin on 3.4.2017 г..
 */

public class ColorDialogFragment extends DialogFragment {

    private ColorsAdapter adapter;
    private RecyclerView list;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        View rootView = inflater.inflate(R.layout.content_colors_dialogfragment, container);

        list = (RecyclerView) rootView.findViewById(R.id.colorsRecycleView);
        adapter = new ColorsAdapter();
        list.setAdapter(adapter);
        list.setLayoutManager(new LinearLayoutManager(getActivity()));


        ArrayList<ColorData> colors = new ArrayList<>();
        ColorData event1 = new ColorData();
        event1.setName("Blue");

        colors.add(event1);
        adapter.addItems(colors);

        return rootView;
    }
}