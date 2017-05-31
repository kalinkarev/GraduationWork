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
import com.example.kalin.graduationwork.model.Event;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

public class HomeFragment extends BaseFragment {

    private EventsAdapter adapter;
    private RecyclerView list;
    private LinearLayout layout;
    Calendar currentDay;

    private DBManager dbmanager;

    @Override
    protected int getLayoutId() {
        return R.layout.content_main;
    }

    @Override
    protected void onCreateView() {

        list = (RecyclerView) mainView.findViewById(R.id.kalinsRecyclerView);
        adapter = new EventsAdapter(new EventsAdapter.EventsListener() {
            @Override
            public void onEditClicked(Event event) {
                getMainActivity().showFragmentAndAddToBackstack(AddFragment.newInstance(event));
            }
        });
        list.setAdapter(adapter);
        list.setLayoutManager(new LinearLayoutManager(getActivity()));
        layout = (LinearLayout) mainView.findViewById(R.id.layout_for_no_events);

        currentDay = Calendar.getInstance();

        List<Event> events = new ArrayList<>();

        events = DBManager.getInstance(getActivity()).getAllEvents();
        adapter.addItems(events);

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
                previousDay();
            }
        });

        TextView title = (TextView) mainView.findViewById(R.id.day);

        ImageView imageViewNextDay = (ImageView) mainView.findViewById(R.id.imageViewNextDay);

        imageViewNextDay.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                Toast.makeText(getMainActivity(), "You have clicked the right arrow", Toast.LENGTH_SHORT).show();
                nextDay();
            }
        });

        ImageView imageViewSeeStatistics = (ImageView) mainView.findViewById(R.id.imageViewSeeStatistics);

        imageViewSeeStatistics.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                getMainActivity().showFragmentAndAddToBackstack(StatisticFragment.newInstance(adapter.getItems()));

            }
        });
    }

    public void nextDay() {
        Calendar cal = Calendar.getInstance();
        cal.getTimeInMillis();
        int hours = cal.get(Calendar.HOUR_OF_DAY);
        currentDay.set(Calendar.HOUR_OF_DAY, hours + 24);
        Toast.makeText(getMainActivity(), "Those are the tasks for tomorrow", Toast.LENGTH_SHORT).show();
    }

    public void previousDay() {
        Calendar prevDay = Calendar.getInstance();
        prevDay.getTimeInMillis();
        int hours = prevDay.get(Calendar.HOUR_OF_DAY);
        currentDay.set(Calendar.HOUR_OF_DAY, hours - 24);
    }


}