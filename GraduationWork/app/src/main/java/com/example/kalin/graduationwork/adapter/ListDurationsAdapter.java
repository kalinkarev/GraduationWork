package com.example.kalin.graduationwork.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.example.kalin.graduationwork.R;

/**
 * Created by Kalin on 30.1.2017 г..
 */

public class ListDurationsAdapter extends BaseAdapter {

    public static final String TAG = "ListDurationsAdapter";

    private List<Duration> mItems;
    private LayoutInflater mInflater;

    public ListDurationsAdapter(Context context, List<Duration> listEvents) {
        this.setItems(listEvents);
        this.mInflater = LayoutInflater.from(context);
    }

    @Override
    public int getCount() {
        return (getItems() != null && !getItems().isEmpty()) ? getItems().size() : 0;
    }

    @Override
    public Duration getItem(int position) {
        return (getItems() != null && !getItems().isEmpty()) ? getItems().get(position) : null;
    }

    @Override
    public long getItemId(int position) {
        return (getItems() != null && !getItems().isEmpty()) ? getItems().get(position).getId() : position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View v = convertView;
        ListDurationsAdapter.ViewHolder holder;

        if (v == null) {
            v = mInflater.inflate(R.layout.list_item_duration, parent, false);
            holder = new ListDurationsAdapter.ViewHolder();
                holder.txtEventName = (TextView) v.findViewById(R.id.txt_event_name);
                holder.txtStart = (TextView) v.findViewById(R.id.txt_start);
                holder.txtFinish = (TextView) v.findViewById(R.id.txt_finish);
                holder.txtRepeat = (TextView) v.findViewById(R.id.txt_repeat);
                holder.txtAllday = (TextView) v.findViewById(R.id.txt_allday);
        } else {
            holder = (ListDurationsAdapter.ViewHolder) v.getTag();
        }

        // fill row data
        Duration currentItem = getItem(position);

        if (currentItem != null) {
            holder.txtStart.setText(String.valueOf(currentItem.getStart()));
            holder.txtFinish.setText(String.valueOf(currentItem.getFinish()));
            holder.txtRepeat.setText(currentItem.getRepat());
            holder.txtAllday.setText(currentItem.getAllday());
            holder.txtEventName.setText(currentItem.getEvent().getName());
        }
        return v;
    }

    public List<Duration> getItems() {
        return mItems;
    }

    public void setItems(List<Duration> mItems) {
        this.mItems = mItems;
    }

    class ViewHolder {
        TextView txtStart;
        TextView txtFinish;
        TextView txtRepeat;
        TextView txtAllday;
        TextView txtEventName;
    }

}