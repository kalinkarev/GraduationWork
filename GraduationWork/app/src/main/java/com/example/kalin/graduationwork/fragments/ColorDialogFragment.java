package com.example.kalin.graduationwork.fragments;

import android.app.DialogFragment;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.kalin.graduationwork.R;
import com.example.kalin.graduationwork.adapter.ColorsAdapter;
import com.example.kalin.graduationwork.interfaces.ColorSelectedListener;
import com.example.kalin.graduationwork.model.ColorData;
import com.example.kalin.graduationwork.utils.ColorUtil;

import java.util.List;

public class ColorDialogFragment extends DialogFragment {

    private ColorsAdapter adapter;
    private RecyclerView list;
    ColorSelectedListener listener;
    ColorData data;

    public ColorDialogFragment() { }

    public static ColorDialogFragment newInstance(ColorSelectedListener listener, ColorData data) {
        Bundle args = new Bundle();

        ColorDialogFragment fragment = new ColorDialogFragment();
        fragment.listener = listener;
        fragment.data=data;
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        // android how to make dialogfragment transparent
//        onStart();
        return inflater.inflate(R.layout.content_colors_dialogfragment, container, false);
    }

/*
    @Override
    public void onStart() {
        super.onStart();
        if (getDialog() == null)
            return;

        int dialogWidth = -100;
        int dialogHeight = -100;

        getDialog().getWindow().setLayout(dialogWidth, dialogHeight);
    }
*/

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        list = (RecyclerView) view.findViewById(R.id.colorsRecycleView);

//        onStart();

        adapter = new ColorsAdapter(listener);

        list.setAdapter(adapter);
        list.setLayoutManager(new LinearLayoutManager(getActivity()));

        ColorUtil color = ColorUtil.getInstance(getActivity().getApplicationContext());

        List colornames = color.getColors();
        adapter.setCol(data);
        adapter.addItems(colornames);

    }

    @Override
    public void onResume() {
        super.onResume();
    }
}