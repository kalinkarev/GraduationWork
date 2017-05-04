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
//        /* Set title for this dialog */
//        getDialog().setTitle("Choose a color");

        return inflater.inflate(R.layout.content_colors_dialogfragment, container, false);
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        list = (RecyclerView) view.findViewById(R.id.colorsRecycleView);

        adapter = new ColorsAdapter(listener);

        list.setAdapter(adapter);
        list.setLayoutManager(new LinearLayoutManager(getActivity()));

        ColorUtil color = ColorUtil.getInstance(getActivity().getApplicationContext());

        final List colornames = color.getColors();
        adapter.setCol(data);
        adapter.addItems(colornames);

//        AlertDialog.Builder builderSingle = new AlertDialog.Builder(getActivity());
//        builderSingle.setTitle("Choose a color");
//
////        final ArrayAdapter<String> arrayAdapter = new ArrayAdapter<String>(DialogActivity.this, android.R.layout.select_dialog_singlechoice);
////        arrayAdapter.add("Hardik");
////        arrayAdapter.add("Archit");
////        arrayAdapter.add("Jignesh");
////        arrayAdapter.add("Umang");
////        arrayAdapter.add("Gatti");
//
//
//
//        builderSingle.setNegativeButton("cancel", new DialogInterface.OnClickListener() {
//            @Override
//            public void onClick(DialogInterface dialog, int which) {
//                dialog.dismiss();
//            }
//        });
//
//        builderSingle.setAdapter(colornames, new DialogInterface.OnClickListener() {
//            @Override
//            public void onClick(DialogInterface dialog, int which) {
////                String strName = colornames.get(which);//.getItem(which);
//                AlertDialog.Builder builderInner = new AlertDialog.Builder(getActivity());
////                builderInner.setMessage(strName);
//                builderInner.setTitle("Your Selected Item is");
//                builderInner.setPositiveButton("Ok", new DialogInterface.OnClickListener() {
//                    @Override
//                    public void onClick(DialogInterface dialog,int which) {
//                        dialog.dismiss();
//                    }
//                });
//                builderInner.show();
//            }
//        });
//        builderSingle.show();
    }


//    @Override
//    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
//        super.onViewCreated(view, savedInstanceState);
//        list = (RecyclerView) view.findViewById(R.id.colorsRecycleView);
//
//        adapter = new ColorsAdapter(listener);
//
//        list.setAdapter(adapter);
//        list.setLayoutManager(new LinearLayoutManager(getActivity()));
//
//        ColorUtil color = ColorUtil.getInstance(getActivity().getApplicationContext());
//
//        List colornames = color.getColors();
//        adapter.setCol(data);
//        adapter.addItems(colornames);
//    }

//    @Override
//    public Dialog onCreateDialog(Bundle savedInstanceState) {
//        String title = getArguments().getString("Choose a color");
//        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
//        builder.setTitle(title);
////        builder.setMessage(colornames);
//
//        // Edited: Overriding onCreateView is not necessary in your case
//        LayoutInflater inflater = LayoutInflater.from(getActivity());
//        View newFileView = inflater.inflate(R.layout.content_colors_dialogfragment, null);
//        builder.setView(newFileView);
//
//        builder.setPositiveButton("OK",  new DialogInterface.OnClickListener() {
//            @Override
//            public void onClick(DialogInterface dialog, int which) {
//                // on success
//            }
//        });
//        builder.setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
//            @Override
//            public void onClick(DialogInterface dialog, int which) {
//                dialog.dismiss();
//            }
//        });
//
//        return builder.create();
//    }

}