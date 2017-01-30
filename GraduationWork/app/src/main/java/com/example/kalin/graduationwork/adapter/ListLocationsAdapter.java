package com.example.kalin.graduationwork.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.example.kalin.graduationwork.R;

import com.example.kalin.graduationwork.model.Location;

import java.util.List;

/**
 * Created by Kalin on 29.1.2017 г..
 */

public class ListLocationsAdapter extends BaseAdapter {

    public static final String TAG = "ListLocationsAdapter";

    private List<Location> mItems;
    private LayoutInflater mInflater;

    public ListLocationsAdapter(Context context, List<Location> listEvents) {
        this.setItems(listEvents);
        this.mInflater = LayoutInflater.from(context);
    }

    @Override
    public int getCount() {
        return (getItems() != null && !getItems().isEmpty()) ? getItems().size() : 0;
    }

    @Override
    public Location getItem(int position) {
        return (getItems() != null && !getItems().isEmpty()) ? getItems().get(position) : null;
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
            v = mInflater.inflate(R.layout.list_item_location, parent, false);
            holder = new ViewHolder();
            holder.txtLocationName = (TextView) v.findViewById(R.id.txt_location_name);
            holder.txtEventName = (TextView) v.findViewById(R.id.txt_event_name);
            holder.txtLongitute = (TextView) v.findViewById(R.id.txt_longitute);
            holder.txtLatitude = (TextView) v.findViewById(R.id.txt_latitude);
        } else {
            holder = (ViewHolder) v.getTag();
        }

        // fill row data
        Location currentItem = getItem(position);

        if (currentItem != null) {
            holder.txtLocationName.setText(currentItem.getName());
            holder.txtLongitute.setText(String.valueOf(currentItem.getLongitute()));
            holder.txtLatitude.setText(String.valueOf(currentItem.getLatitude()));
            holder.txtEventName.setText(currentItem.getEvent().getName());
        }
        return v;
    }

    public List<Location> getItems() {
        return mItems;
    }

    public void setItems(List<Location> mItems) {
        this.mItems = mItems;
    }

    class ViewHolder {
        TextView txtLocationName;
        TextView txtLongitute;
        TextView txtLatitude;
        TextView txtEventName;
    }

}