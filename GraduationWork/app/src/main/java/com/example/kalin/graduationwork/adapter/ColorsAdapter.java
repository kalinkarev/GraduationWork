package com.example.kalin.graduationwork.adapter;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.kalin.graduationwork.R;
import com.example.kalin.graduationwork.interfaces.ColorSelectedListener;
import com.example.kalin.graduationwork.model.ColorData;
import com.example.kalin.graduationwork.views.ColorView;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Kalin on 1.4.2017 г..
 */

public class ColorsAdapter extends RecyclerView.Adapter<ColorsAdapter.ColorsViewHolder> {

    List<ColorData> items = new ArrayList<>();
    private int selectedPosition = -1;
    ColorData col;
    ColorSelectedListener listener;

    public ColorsAdapter(ColorSelectedListener listener) {
        this.listener = listener;
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
        final ColorData colorData = items.get(position);

        holder.colorName.setText(colorData.getName());
        holder.colorCircle.setCircleColor(colorData.getColor());

        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (listener != null) {
                    listener.onColorSelected(colorData);
                }
            }
        });
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
        ColorView colorCircle;

        public ColorsViewHolder(View itemView) {
            super(itemView);

            colorName = (TextView) itemView.findViewById(R.id.colors_row_title);
            colorCircle = (ColorView) itemView.findViewById(R.id.circleView);

        }

    }

    public void addItems(List<ColorData> colors) {

        items.clear();
        items.addAll(colors);
        notifyDataSetChanged();

    }

}