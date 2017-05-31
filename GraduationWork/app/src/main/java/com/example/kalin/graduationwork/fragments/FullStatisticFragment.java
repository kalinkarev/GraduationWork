package com.example.kalin.graduationwork.fragments;

import android.os.Bundle;
import android.support.v7.widget.Toolbar;

import com.example.kalin.graduationwork.R;
import com.example.kalin.graduationwork.model.Event;

import java.util.List;

/**
 * Created by Kalin on 4.3.2017 г..
 */

public class FullStatisticFragment extends BaseFragment {

    public static FullStatisticFragment newInstance(Event event, List<Event> events, long start, long end) {

        Bundle args = new Bundle();

        FullStatisticFragment fragment = new FullStatisticFragment();
        fragment.events = events;
        fragment.setArguments(args);
        return fragment;
    }

    List<Event> events;

    @Override
    protected int getLayoutId() {
        return R.layout.activity_full_statistics;
    }

    @Override
    protected void onCreateView() {
        double hours = 0;
        double attendedHours = 0;
      for (Event event : events) {
          if (event.isChecked()) {
              attendedHours += (event.getDuration().getFinish() - event.getDuration().getStart()) / 1000 / 60;
          }
      }
    }

    @Override
    protected void setupToolbar() {
        Toolbar toolbarAdd = (Toolbar) mainView.findViewById(R.id.toolbar_statistics);
    }

}
