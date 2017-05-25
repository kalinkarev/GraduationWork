package com.example.kalin.graduationwork.fragments;

import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.example.kalin.graduationwork.R;
import com.example.kalin.graduationwork.adapter.EventsAdapter;
import com.example.kalin.graduationwork.dao.DBManager;
import com.example.kalin.graduationwork.model.ColorData;
import com.example.kalin.graduationwork.model.Duration;
import com.example.kalin.graduationwork.model.Event;
import com.example.kalin.graduationwork.model.Location;

import java.util.ArrayList;
import java.util.List;

public class HomeFragment extends BaseFragment {

    private EventsAdapter adapter;
    private RecyclerView list;
    private LinearLayout layout;

    private DBManager dbmanager;

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

        List<Event> events = new ArrayList<>();

        ColorData color = new ColorData();
        color.setName("blue");

        Duration duration = new Duration();
        duration.setStart(15);
        duration.setFinish(16);
//        duration.setRepeat(false);
        duration.setAllday(false);

        Location location = new Location();
        location.setName("Birthday");
        location.setLongitute("100.00");
        location.setLatitude("50.00");

        Event event = new Event();
        event.setName("Sleep");
        event.setColor(color);
//        event.setNote("This task is for today!");
        event.setNotification(false);
        event.setPrice(50);
        event.setDuration(duration);
        event.setLocation(location);

        DBManager.getInstance(getActivity()).addEvent(event, false);
        DBManager.getInstance(getActivity()).getAllEvents();

        events.add(event);
        adapter.addItems(events);

//        Event event = new Event();
//        event.setName("Free");
//        event.getId();
//        event.getName();
//        event.getColor();
//        event.getNote();
//        event.getNotification();
//        event.getPrice();
//        event.getDuration();
//        event.getLocation();
//
//        DBManager.getInstance(getActivity()).addEvent(event, false);
//
//        DBManager.getInstance(getActivity()).getAllEvents();


//        ArrayList<Event> values = new ArrayList<>();
//
//
//        List<Event> events = dbmanager.getAllEvents();


        // fill the recyclerView

//        Event event1 = new Event();
//        event1.setName("Tenis");
//        ArrayList<Event> events = new ArrayList<>();
//        events.add(event1);
//        adapter.addItems(events);

//        ArrayList<Event> events = new ArrayList<>();
//        Event event1 = new Event();
//        event1.setName("Free");
//        Event event2 = new Event();
//        event2.setName("Lunch");
//        Event event3 = new Event();
//        event3.setName("Dinner");
//        Event event4 = new Event();
//        event4.setName("Free");
//        Event event5 = new Event();
//        event5.setName("Working");
//        Event event6 = new Event();
//        event6.setName("Sleeping");
//        events.add(event1);
//        events.add(event2);
//        events.add(event3);
//        events.add(event4);
//        events.add(event5);
//        events.add(event6);
//        adapter.addItems(events);
//
        if (events.size() == 0) {
            list.setVisibility(View.GONE);
            layout.setVisibility(View.VISIBLE);
        } else {
            list.setVisibility(View.VISIBLE);
            layout.setVisibility(View.GONE);
        }

        getMainActivity().getFab().show();
    }

    @Override
    public void onPause() {
        super.onPause();
        getMainActivity().getFab().hide();
    }

    @Override
    protected void setupToolbar() {

        Toolbar toolbarAdd = (Toolbar) mainView.findViewById(R.id.toolbar_mainScreen);
        ImageView imageViewPreviousDay = (ImageView) mainView.findViewById(R.id.imageViewPreviousDay);

        imageViewPreviousDay.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                Toast.makeText(getMainActivity(), "You have clicked the left arrow", Toast.LENGTH_SHORT).show();
            }
        });

        TextView title = (TextView) mainView.findViewById(R.id.day);

        ImageView imageViewNextDay = (ImageView) mainView.findViewById(R.id.imageViewNextDay);

        imageViewNextDay.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                Toast.makeText(getMainActivity(), "You have clicked the right arrow", Toast.LENGTH_SHORT).show();
            }
        });

        ImageView imageViewSeeStatistics = (ImageView) mainView.findViewById(R.id.imageViewSeeStatistics);

        imageViewSeeStatistics.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
//                Toast.makeText(getMainActivity(), "You have clicked the statistics button", Toast.LENGTH_SHORT).show();

                getMainActivity().showFragmentAndAddToBackstack(new StatisticFragment());

            }
        });
    }
}