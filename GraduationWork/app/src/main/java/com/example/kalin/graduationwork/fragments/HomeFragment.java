package com.example.kalin.graduationwork.fragments;

import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.LinearLayout;

import com.example.kalin.graduationwork.R;
import com.example.kalin.graduationwork.adapter.EventsAdapter;
import com.example.kalin.graduationwork.model.Event;

import java.util.ArrayList;

/**
 * Created by Kalin on 18.1.2017 г..
 */

public class HomeFragment extends BaseFragment {

    private EventsAdapter adapter;
    private RecyclerView list;
    private LinearLayout layout;

    @Override
    protected int getLayoutId() {
        return R.layout.content_main;
    }

    @Override
    protected void onCreateView() {
        list = (RecyclerView) mainView.findViewById(R.id.kalinsRecyclerView);
        adapter = new EventsAdapter();
        list.setAdapter(adapter);
        list.setLayoutManager(new LinearLayoutManager(getActivity()));
        layout = (LinearLayout) mainView.findViewById(R.id.layout_for_no_events);

        // fill the recyclerView

//        Event event1 = new Event();
//        event1.setName("Tenis");
//        ArrayList<Event> events = new ArrayList<>();
//        events.add(event1);
//        adapter.addItems(events);

        ArrayList<Event> events = new ArrayList<>();
        Event event1 = new Event();
        event1.setName("Free");
        Event event2 = new Event();
        event2.setName("Lunch");
        Event event3 = new Event();
        event3.setName("Dinner");
        Event event4 = new Event();
        event4.setName("Free");
        Event event5 = new Event();
        event5.setName("Working");
        Event event6 = new Event();
        event6.setName("Sleeping");
        events.add(event1);
        events.add(event2);
        events.add(event3);
        events.add(event4);
        events.add(event5);
        events.add(event6);
        adapter.addItems(events);

        if (events.size() == 0) {
            list.setVisibility(View.GONE);
            layout.setVisibility(View.VISIBLE);
        } else {
            list.setVisibility(View.VISIBLE);
            layout.setVisibility(View.GONE);
        }

    }

    @Override
    protected void setupToolbar() {

    }
}
