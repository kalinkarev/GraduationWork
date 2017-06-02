package com.example.kalin.graduationwork.fragments;

import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.widget.TextView;

import com.example.kalin.graduationwork.R;
import com.example.kalin.graduationwork.dao.DBManager;
import com.example.kalin.graduationwork.model.Event;

import java.text.SimpleDateFormat;
import java.util.List;

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
    TextView tvRate;
    TextView tvSpent;


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
        tvRate = (TextView) mainView.findViewById(R.id.tvForRate);
        tvSpent = (TextView) mainView.findViewById(R.id.tvForSpent);

        SimpleDateFormat format = new SimpleDateFormat("dd. MM");
        String startString = format.format(startDate);

        String finishString = format.format(finishDate);

        nameOfTheActivity.setText(selectedEvent.getName());
        startDateOfThePeriod.setText(startString);
        finishDateOfThePeriod.setText(finishString);

        events = DBManager.getInstance(getActivity()).getAllEventsForStatistics(startDate, finishDate);

        Log.d("The start time", String.valueOf(startDate));
        Log.d("The finish time", String.valueOf(finishDate));

        Log.d("The found events are", String.valueOf(events));

        double hours = 0;
        double attendedHours = 0;
        double prices = 0;
      for (Event event : events) {

//          Log.d("The time is", String.valueOf(event.getDuration().getFinish() - event.getDuration().getStart()));
//          hours = hours + ((event.getDuration().getFinish() - event.getDuration().getStart()));

          if (event.getName().equals(selectedEvent.getName())) {
              hours = hours + 1;
          }



          if (event.isChecked() == true && event.getName().equals(selectedEvent.getName())) {

              attendedHours = attendedHours + 1;

              prices = event.getPrice();

              if (attendedHours == 0.0) {
                  tvSpent.setText("0.0");
              } else if (attendedHours == 1) {
                  tvSpent.setText(String.valueOf(selectedEvent.getPrice()));
              } else  {
                  double newPrice = selectedEvent.getPrice();
                  tvSpent.setText(String.valueOf(prices + newPrice));
              }

//              attendedHours += (event.getDuration().getFinish() - event.getDuration().getStart()) / 1000 / 60;
//              attendedHours += (((event.getDuration().getFinish() - event.getDuration().getStart()) / 1000) / 60);
//                  attendedHours = (attendedHours + (event.getDuration().getFinish() - event.getDuration().getStart()));// / 100000000 / 60;
          }

      }

        double rate = 0;
        rate = (attendedHours / hours) * 100;


        numberOfHours.setText(String.valueOf(hours));
        tvattendedHours.setText(String.valueOf(attendedHours));
        tvRate.setText(String.valueOf(rate)+"%");

    }

    @Override
    protected void setupToolbar() {
        Toolbar toolbarAdd = (Toolbar) mainView.findViewById(R.id.toolbar_statistics);
    }

}