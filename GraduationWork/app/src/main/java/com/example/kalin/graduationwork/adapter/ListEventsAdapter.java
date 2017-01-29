package com.example.kalin.graduationwork.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.example.kalin.graduationwork.R;

/**
 * Created by Kalin on 29.1.2017 г..
 */

public class ListEventsAdapter extends BaseAdapter {

    // Adapter object acts as a bridge between an AdapterView and the underluing data for that view.
    // The Adapter provides access to the data items.
    // The Adapter is also responsible for making a View for each item in the data set.

    public static final String TAG = "ListEventsAdapter";

    private List<Event> mItems;
    private LayoutInflater mInflater; // to make my own view

    public ListEventsAdapter(Context context, List<Event> listEvents) {
        this.setItems(listEvents);
        this.mInflater = LayoutInflater.from(context);
    }

    @Override
    public int getCount() {
        return (getItems() != null && !getItems().isEmpty()) ? getItems().size() : 0;
    }

    @Override
    public Event getItem(int position) {
        return (getItems() != null && !getItems().isEmpty()) ? getItems().get(position).getId() : position;
    }

    @Override
    public long getItemId(int position) {
        return (getItems() != null && !getItems().isEmpty()) ? getItems().get(position).getId() : position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View v = convertView;
        ViewHolder holder;

        if (v == null) {
            v = mInflater.inflate(R.layout.list_item_event, parent, false);
            holder = new ViewHolder();
            holder.txtEventName = (TextView) v.findViewById(R.id.txt_event_name);
            holder.txtEventColor = (TextView) v.findViewById(R.id.txt_event_color);
            holder.txtEventNote = (TextView) v.findViewById(R.id.txt_event_note);
            holder.txtEventNotification = (...) v.findViewById(R.id.txt_event_notification);
            holder.txtEventPrice = (...) v.findViewById(R.id.txt_event_price);
        } else {
            holder = (ViewHolder) v.getTag();
        }

        // fill row data
        Event currentItem = getItem(position);

        if (currentItem != null) {
            holder.txtEventName.setText(currentItem.getName());
            holder.txtEventColor.setText(currentItem.getColor());
            holder.txtEventNote.setText(currentItem.getNote());
            holder.txtEventNotification.setText(currentItem.getNotification());
            holder.txtEventPrice.setText(currentItem.getPrice());
        }

        return v;
    }

    public List<Event> getItems() {
        return mItems;
    }

    public void setItems(List<Event> mItems) {
        this.mItems = mItems;
    }

    class ViewHolder {
        TextView txtEventName;
        TextView txtEventColor;
        TextView txtEventNote;
        TextView txtEventNotification;
        TextView txtEventPrice;
    }

}