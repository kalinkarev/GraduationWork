package com.example.kalin.graduationwork.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.kalin.graduationwork.R;
import com.example.kalin.graduationwork.model.ColorData;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Kalin on 1.4.2017 г..
 */

public class ColorsAdapter extends RecyclerView.Adapter<ColorsAdapter.ColorsViewHolder> {

    List<ColorData> items = new ArrayList<>();
    private int selectedPosition = -1;
    ColorData col;

    private static ColorsAdapter instance;

    public static ColorsAdapter getInstance(Context context) {
        if (instance == null) {
            instance = new ColorsAdapter();
        }
        return instance;
    }

    @Override
    public ColorsAdapter.ColorsViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.colors_list_row_dialogfragment, parent, false);

        ColorsAdapter.ColorsViewHolder vh = new ColorsAdapter.ColorsViewHolder(view);
        return vh;
    }

    @Override
    public void onBindViewHolder(ColorsAdapter.ColorsViewHolder holder, final int position) {
        ColorData colorData = items.get(position);

        holder.colorName.setText(colorData.getName());

        if (selectedPosition == position) {
            col.getName();
        }

    }

    public void getName() {
        col.getName();
    }

    @Override
    public int getItemCount() {
        return items.size();
    }

    static class ColorsViewHolder extends RecyclerView.ViewHolder {

        TextView colorName;

        public ColorsViewHolder(View itemView) {
            super(itemView);

            colorName = (TextView) itemView.findViewById(R.id.colors_row_title);

        }

    }

    public void addItems(List<ColorData> colors) {

        items.clear();
        items.addAll(colors);
        notifyDataSetChanged();

    }

}