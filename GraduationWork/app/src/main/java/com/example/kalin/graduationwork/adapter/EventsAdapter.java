package com.example.kalin.graduationwork.adapter;

import android.graphics.Color;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.kalin.graduationwork.R;
import com.example.kalin.graduationwork.model.Event;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Kalin on 1.2.2017 г..
 */

public class EventsAdapter extends RecyclerView.Adapter<EventsAdapter.EventsViewHolder> {

    List<Event> items = new ArrayList<>();

    @Override
    public EventsViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.events_list_row, parent, false);

        EventsViewHolder vh = new EventsViewHolder(view);
        return vh;
    }

    @Override
    public void onBindViewHolder(EventsViewHolder holder, int position) {
        Event event = items.get(position);

        holder.title.setText(event.getName());

        if (position % 2 ==0) {
            holder.title.setBackgroundColor(Color.WHITE);
        } else {
            holder.title.setBackgroundColor(Color.BLUE);
        }

    }

    @Override
    public int getItemCount() {
        return items.size();
    }

     static class EventsViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

         TextView title;

        public EventsViewHolder(View itemView) {
            super(itemView);

            title = (TextView) itemView.findViewById(R.id.events_row_title);

            title.setOnClickListener(this);
        }

         public TextView getTitle() {
             return title;
         }

         @Override
         public void onClick(View v) {
             Log.i("positon-of-clicked-item", String.valueOf(getAdapterPosition()));
             Log.i("positon-of-clicked-item", String.valueOf(getLayoutPosition()));
             Log.i("positon-of-clicked-item", String.valueOf(title.getText()));
         }
     }

    public void addItems(List<Event> events) {

        items.clear();
        items.addAll(events);
        notifyDataSetChanged();

    }
}