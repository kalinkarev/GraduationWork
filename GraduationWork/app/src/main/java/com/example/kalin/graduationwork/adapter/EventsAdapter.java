package com.example.kalin.graduationwork.adapter;

import android.graphics.Color;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.kalin.graduationwork.R;
import com.example.kalin.graduationwork.model.Event;

import java.util.ArrayList;
import java.util.List;

public class EventsAdapter extends RecyclerView.Adapter<EventsAdapter.EventsViewHolder> {

    List<Event> items = new ArrayList<>();
    private int selectedPosition = -1;

    @Override
    public EventsViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.events_list_row, parent, false);

        EventsViewHolder vh = new EventsViewHolder(view);
        return vh;
    }

    @Override
    public void onBindViewHolder(final EventsViewHolder holder, final int position) {
        Event event = items.get(position);

        holder.title.setText(event.getName());
        holder.ivForReadyTask.setVisibility(View.GONE);

        if (selectedPosition == position) {
            holder.buttonsLayout.setVisibility(View.VISIBLE);
            holder.ibReadyTask.setOnClickListener(new View.OnClickListener(){
                @Override
                public void onClick(View v) {
                    holder.ivForReadyTask.setVisibility(View.VISIBLE);
                }
            });
        } else {
            holder.buttonsLayout.setVisibility(View.GONE);
        }


        if (position % 2 == 0) {
//            holder.title.setBackgroundColor(Color.);
        } else {
            holder.title.setBackgroundColor(Color.BLUE);
        }

        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                selectedPosition = position;
                notifyDataSetChanged();
            }
        });

    }

    @Override
    public int getItemCount() {
        return items.size();
    }

    static class EventsViewHolder extends RecyclerView.ViewHolder {

        TextView title;
        LinearLayout buttonsLayout;
        ImageButton ibReadyTask;
        ImageView ivForReadyTask;

        public EventsViewHolder(View itemView) {
            super(itemView);

            title = (TextView) itemView.findViewById(R.id.events_row_title);
            buttonsLayout = (LinearLayout) itemView.findViewById(R.id.buttonsLayout);
            ibReadyTask = (ImageButton) itemView.findViewById(R.id.buttonForReadyTask);
            ivForReadyTask = (ImageView) itemView.findViewById(R.id.ready_task);
        }
    }

    public void addItems(List<Event> events) {

        items.clear();
        items.addAll(events);
        notifyDataSetChanged();

    }
}