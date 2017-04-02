package com.example.kalin.graduationwork.adapter;

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

    @Override
    public ColorsAdapter.ColorsViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.colors_dialogfragment, parent, false);

        ColorsAdapter.ColorsViewHolder vh = new ColorsAdapter.ColorsViewHolder(view);
        return vh;
    }

    @Override
    public void onBindViewHolder(ColorsAdapter.ColorsViewHolder holder, final int position) {
        ColorData colorData = items.get(position);

        holder.colorName.setText(colorData.getName());
    }

    @Override
    public int getItemCount() {
        return items.size();
    }

    static class ColorsViewHolder extends RecyclerView.ViewHolder {

        TextView colorName;

        public ColorsViewHolder(View itemView) {
            super(itemView);

//            colorName = (TextView) itemView.findViewById(R.id.color_row_name);

        }

    }

}