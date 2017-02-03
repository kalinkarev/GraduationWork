package com.example.kalin.graduationwork.fragments;

import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

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

//        Event event1 = new Event();
//        event1.setName("Tenis");
//        ArrayList<Event> events = new ArrayList<>();
//        events.add(event1);
//        adapter.addItems(events);

        Event event1 = new Event();
        event1.setName("Free");
        Event event2 = new Event();
        event2.setName("Lunch");
        Event event3 = new Event();
        event3.setName("Dinner");
        ArrayList<Event> events = new ArrayList<>();
        events.add(event1);
        events.add(event2);
        events.add(event3);
        adapter.addItems(events);
    }

    @Override
    protected void setupToolbar() {

    }
}
