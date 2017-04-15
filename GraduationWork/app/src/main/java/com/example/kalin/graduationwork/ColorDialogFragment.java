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
import com.example.kalin.graduationwork.interfaces.ColorSelectedListener;
import com.example.kalin.graduationwork.model.ColorData;

import java.util.ArrayList;

/**
 * Created by Kalin on 3.4.2017 г..
 */

public class ColorDialogFragment extends DialogFragment {

    private ColorsAdapter adapter;
    private RecyclerView list;
    ColorSelectedListener listener;

    public static ColorDialogFragment newInstance(ColorSelectedListener listener) {
        Bundle args = new Bundle();

        ColorDialogFragment fragment = new ColorDialogFragment();
        fragment.listener = listener;
        fragment.setArguments(args);
        return fragment;
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        View rootView = inflater.inflate(R.layout.content_colors_dialogfragment, container);

        list = (RecyclerView) rootView.findViewById(R.id.colorsRecycleView);
        adapter = new ColorsAdapter(listener);
        list.setAdapter(adapter);
        list.setLayoutManager(new LinearLayoutManager(getActivity()));


        ArrayList<ColorData> colors = new ArrayList<>();
        ColorData color1 = new ColorData();
        color1.setName("Dark Blue");
        colors.add(color1);

        ColorData color2 = new ColorData();
        color2.setName("Blue");
        colors.add(color2);

        ColorData color3 = new ColorData();
        color3.setName("Light Blue");
        colors.add(color3);

        ColorData color4 = new ColorData();
        color4.setName("Dark Pink");
        colors.add(color4);

        ColorData color5 = new ColorData();
        color5.setName("Pink");
        colors.add(color5);

        ColorData color6 = new ColorData();
        color6.setName("Light Pink");
        colors.add(color6);

        ColorData color7 = new ColorData();
        color7.setName("Red");
        colors.add(color7);

        ColorData color8 = new ColorData();
        color8.setName("Light Red");
        colors.add(color8);

        ColorData color9 = new ColorData();
        color9.setName("Dark Orange");
        colors.add(color9);

        ColorData color10 = new ColorData();
        color10.setName("Orange");
        colors.add(color10);

        ColorData color11 = new ColorData();
        color11.setName("Green");
        colors.add(color11);

        ColorData color12 = new ColorData();
        color12.setName("Gray");
        colors.add(color12);

        ColorData color13 = new ColorData();
        color13.setName("Light Gray");
        colors.add(color13);

        ColorData color14 = new ColorData();
        color14.setName("Purple");
        colors.add(color14);

        ColorData color15 = new ColorData();
        color15.setName("Light Purple");
        colors.add(color15);

        ColorData color16 = new ColorData();
        color16.setName("White");
        colors.add(color16);

        adapter.addItems(colors);

        return rootView;
    }
}