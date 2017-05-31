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
import com.example.kalin.graduationwork.model.ColorData;
import com.example.kalin.graduationwork.model.Event;

import java.util.ArrayList;
import java.util.List;

public class EventsAdapter extends RecyclerView.Adapter<EventsAdapter.EventsViewHolder> {

    List<Event> items = new ArrayList<>();
    private int selectedPosition = -1;
    private DBManager dbmanager;
    Context context;
    ColorData currentColor;
    private EventsListener listener;

    public EventsAdapter(EventsListener listener) {
        this.listener = listener;
    }

    @Override
    public EventsViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.events_list_row, parent, false);

        Context mContext = parent.getContext();
        context = mContext;

        EventsViewHolder vh = new EventsViewHolder(view);
        return vh;
    }

    @Override
    public void onBindViewHolder(final EventsViewHolder holder, final int position) {
        final Event event = items.get(position);

        holder.title.setText(event.getName());
        holder.linearLayoutColorEvent.setBackgroundColor(event.getColor().getColor());
        holder.ivForReadyTask.setVisibility(View.GONE);

        if (event.isChecked()) {
            holder.ivForReadyTask.setVisibility(View.VISIBLE);
        } else {
            holder.ivForReadyTask.setVisibility(View.GONE);
        }

        if (selectedPosition == position) {
            holder.buttonsLayout.setVisibility(View.VISIBLE);

            holder.ibForReadyTask.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    holder.ivForReadyTask.setVisibility(View.VISIBLE);
                    event.setChecked(!event.isChecked());
                    update(event, holder);
                }
            });

            holder.ibForDelete.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    DBManager.getInstance(context).deleteEvent(event);
                    update(event, holder);
                }
            });

            holder.ibForEdit.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    listener.onEditClicked(event);
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
                if (selectedPosition == position) {
                    selectedPosition = -1;
                } else {
                    selectedPosition = position;
                }
                notifyDataSetChanged();
            }
        });

    }

    private void update(Event event, EventsViewHolder holder) {
        DBManager.getInstance(context).addEvent(event, true);
        addItems(DBManager.getInstance(context).getAllEvents());
        holder.buttonsLayout.setVisibility(View.GONE);
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
        ImageButton ibForReadyTask;
        ImageButton ibForDelete;
        ImageButton ibForEdit;
        ImageView ivForReadyTask;

        public EventsViewHolder(View itemView) {
            super(itemView);

            title = (TextView) itemView.findViewById(R.id.events_row_title);
            buttonsLayout = (LinearLayout) itemView.findViewById(R.id.buttonsLayout);
            linearLayoutEvent = (LinearLayout) itemView.findViewById(R.id.linearLayoutEvent);
            linearLayoutColorEvent = (LinearLayout) itemView.findViewById(R.id.linearLayoutForColorEvent);
            ibForReadyTask = (ImageButton) itemView.findViewById(R.id.buttonForReadyTask);
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

    public interface EventsListener {
        void onEditClicked(Event event);
    }

    public List<Event> getItems() {
        return items;
    }
}