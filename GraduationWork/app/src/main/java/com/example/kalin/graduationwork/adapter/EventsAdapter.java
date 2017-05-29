package com.example.kalin.graduationwork.adapter;

import android.content.Context;
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
import com.example.kalin.graduationwork.dao.DBManager;
import com.example.kalin.graduationwork.interfaces.ColorSelectedListener;
import com.example.kalin.graduationwork.model.ColorData;
import com.example.kalin.graduationwork.model.Event;

import java.util.ArrayList;
import java.util.List;

public class EventsAdapter extends RecyclerView.Adapter<EventsAdapter.EventsViewHolder> implements ColorSelectedListener {

    List<Event> items = new ArrayList<>();
    private int selectedPosition = -1;
    private DBManager dbmanager;
    Context context;
    ColorData currentColor;

    @Override
    public void onColorSelected(ColorData data) {
        currentColor = data;
    }

    @Override
    public EventsViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.events_list_row, parent, false);

        EventsViewHolder vh = new EventsViewHolder(view);
        return vh;
    }

    @Override
    public void onBindViewHolder(final EventsViewHolder holder, final int position) {
        final Event event = items.get(position);

        holder.title.setText(event.getName());
//        holder.linearLayoutColorEvent.setBackgroundColor(currentColor.getColor());
        holder.ivForReadyTask.setVisibility(View.GONE);

        if (selectedPosition == position) {
            holder.buttonsLayout.setVisibility(View.VISIBLE);
            holder.ibReadyTask.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    holder.ivForReadyTask.setVisibility(View.VISIBLE);
//                    Toast.makeText(context, "The color is:" + currentColor.getName(), Toast.LENGTH_SHORT).show();
                }
            });
            holder.ibForDelete.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    DBManager.getInstance(context).deleteEvent(event);
                    notifyDataSetChanged();

                    List<Event> newEvents = new ArrayList<>();
                    newEvents = DBManager.getInstance(context).getAllEvents();

                }
            });
            holder.ibForEdit.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
//                    getMainActivity().showFragmentAndAddToBackstack(new StatisticFragment());
                }
            });
        } else {
            holder.buttonsLayout.setVisibility(View.GONE);
        }

        if (position % 2 == 0) {
            holder.linearLayoutEvent.setBackgroundColor(Color.TRANSPARENT);
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
        LinearLayout linearLayoutEvent;
        LinearLayout linearLayoutColorEvent;
        ImageButton ibReadyTask;
        ImageButton ibForDelete;
        ImageButton ibForEdit;
        ImageView ivForReadyTask;

        public EventsViewHolder(View itemView) {
            super(itemView);

            title = (TextView) itemView.findViewById(R.id.events_row_title);
            buttonsLayout = (LinearLayout) itemView.findViewById(R.id.buttonsLayout);
            linearLayoutEvent = (LinearLayout) itemView.findViewById(R.id.linearLayoutEvent);
            linearLayoutColorEvent = (LinearLayout) itemView.findViewById(R.id.linearLayoutForColorEvent);
            ibReadyTask = (ImageButton) itemView.findViewById(R.id.buttonForReadyTask);
            ibForDelete = (ImageButton) itemView.findViewById(R.id.buttonForDelete);
            ibForEdit = (ImageButton) itemView.findViewById(R.id.buttonForEdit);
            ivForReadyTask = (ImageView) itemView.findViewById(R.id.ready_task);
        }
    }

    public void addItems(List<Event> events) {

        items.clear();
        items.addAll(events);
        notifyDataSetChanged();

    }

}