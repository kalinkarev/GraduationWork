package com.example.kalin.graduationwork.fragments;

import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.widget.TextView;

import com.example.kalin.graduationwork.R;
import com.example.kalin.graduationwork.model.Event;

import java.util.List;

/**
 * Created by Kalin on 4.3.2017 г..
 */

public class FullStatisticFragment extends BaseFragment {

    Event selectedEvent = new Event();
    long startDate;
    long finishDate;

    public static FullStatisticFragment newInstance(Event event, List<Event> events, long start, long end) {

        Bundle args = new Bundle();

        FullStatisticFragment fragment = new FullStatisticFragment();
        fragment.events = events;
        fragment.selectedEvent = event;
        fragment.startDate = start;
        fragment.finishDate = end;
        fragment.setArguments(args);
        return fragment;
    }

    List<Event> events;

    TextView nameOfTheActivity;
    TextView startDateOfThePeriod;
    TextView finishDateOfThePeriod;
    TextView numberOfHours;
    TextView tvattendedHours;

    @Override
    protected int getLayoutId() {
        return R.layout.activity_full_statistics;
    }

    @Override
    protected void onCreateView() {

        nameOfTheActivity = (TextView) mainView.findViewById(R.id.tvForNameActivity);
        startDateOfThePeriod = (TextView) mainView.findViewById(R.id.tvForChoosingPeriod);
        finishDateOfThePeriod= (TextView) mainView.findViewById(R.id.tvForChoosingFinishPeriod);
        numberOfHours = (TextView) mainView.findViewById(R.id.tvForHours);
        tvattendedHours = (TextView) mainView.findViewById(R.id.tvForAttended);

//        String timeStart = String.valueOf(startDate);

        String startValue = Long.toString(startDate);

        nameOfTheActivity.setText(selectedEvent.getName());
//        startDateOfThePeriod.setText(String.valueOf(((Long) startDate)));
        startDateOfThePeriod.setText(startValue);

        double hours = 0;
        double attendedHours = 0;
      for (Event event : events) {
              if (event.getName() != selectedEvent.getName()) {
                  hours = hours + 1;
              } else

          if (event.isChecked()) {
//              attendedHours += (event.getDuration().getFinish() - event.getDuration().getStart()) / 1000 / 60;
              attendedHours = attendedHours + 1;
          }
      }

        numberOfHours.setText(String.valueOf(hours));
        tvattendedHours.setText(String.valueOf(attendedHours));
    }

    @Override
    protected void setupToolbar() {
        Toolbar toolbarAdd = (Toolbar) mainView.findViewById(R.id.toolbar_statistics);
    }

}