package com.example.kalin.graduationwork.fragments;

import android.app.DialogFragment;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import com.example.kalin.graduationwork.R;
import com.example.kalin.graduationwork.adapter.ColorsAdapter;
import com.example.kalin.graduationwork.interfaces.ColorSelectedListener;
import com.example.kalin.graduationwork.model.ColorData;
import com.example.kalin.graduationwork.utils.ColorUtil;

import java.util.List;

public class ColorDialogFragment extends DialogFragment {

    private ColorsAdapter adapter;
    private RecyclerView list;
    private Button btn_OK;
    private Button btn_Cancel;
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
        /* Set title for this dialog */
        getDialog().setTitle("Choose a color");

        return inflater.inflate(R.layout.content_colors_dialogfragment, container, false);
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        list = (RecyclerView) view.findViewById(R.id.colorsRecycleView);
        btn_OK = (Button) view.findViewById(R.id.btnOK);
        btn_Cancel = (Button) view.findViewById(R.id.btnCancel);

        adapter = new ColorsAdapter(listener);

        list.setAdapter(adapter);
        list.setLayoutManager(new LinearLayoutManager(getActivity()));

        ColorUtil color = ColorUtil.getInstance(getActivity().getApplicationContext());

        final List colornames = color.getColors();
        adapter.setCol(data);
        adapter.addItems(colornames);

        btn_OK.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dismiss();
            }
        });

        btn_Cancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                dismiss();
                data = ColorUtil.getInstance(getActivity()).getColors().get(0);


                /* При натискане на cancel на dialogfragment-a трябва да остане default-ния цвят.
                Ако пторебителят е избрал някой цвят и натисне cancel след това кой цвят трябва да се вземе */

            }
        });

    }
}