package com.example.kalin.graduationwork.fragments;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.example.kalin.graduationwork.R;
import com.example.kalin.graduationwork.model.Event;

import java.util.List;

/**
 * Created by Kalin on 3.3.2017 г..
 */

public class StatisticFragment extends BaseFragment {

    public static StatisticFragment newInstance(List<Event> events) {

        Bundle args = new Bundle();

        StatisticFragment fragment = new StatisticFragment();
        fragment.events = events;
        fragment.setArguments(args);
        return fragment;
    }

    List<Event> events;
    int checkedItem;
    Event selectedEvent;
    TextView eventName;

    @Override
    protected int getLayoutId() {
        return R.layout.activity_statistic;
    }

    @Override
    protected void onCreateView() {

        Button btnShowStatistics = (Button) mainView.findViewById(R.id.btnShowStatistics);
        btnShowStatistics.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
//                getMainActivity().showFragmentAndAddToBackstack(FullStatisticFragment.newInstance(selectedEvent, events,
//                        startDate.getTimeInMillis()));
            }
        });

        eventName = (TextView) mainView.findViewById(R.id.tvForNameActivity);
        eventName.setText(events.get(checkedItem).getName());
        eventName.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showEventsDialog();
            }
        });
    }

    private void showEventsDialog() {
        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
        CharSequence[] choices = new CharSequence[events.size()];
        for (int i = 0; i < choices.length; i++) {
            choices[i] = events.get(i).getName();
        }
        builder.setSingleChoiceItems(choices, checkedItem, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                selectedEvent = events.get(which);
                checkedItem = which;
                eventName.setText(events.get(checkedItem).getName());
                dialog.dismiss();
            }
        });
        builder.setTitle("Pesho");
        builder.setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {

            }
        });
        builder.show();
    }

    @Override
    protected void setupToolbar() {
        Toolbar toolbarAdd = (Toolbar) mainView.findViewById(R.id.toolbar_statistics);
    }


}
