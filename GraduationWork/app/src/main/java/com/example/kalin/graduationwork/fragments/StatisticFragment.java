package com.example.kalin.graduationwork.fragments;

import android.app.AlertDialog;
import android.app.DatePickerDialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.TextView;

import com.example.kalin.graduationwork.R;
import com.example.kalin.graduationwork.model.Event;

import java.text.SimpleDateFormat;
import java.util.Calendar;
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

    TextView startDate;
    TextView finishDate;

    int mYear, mMonth, mDay;

    Calendar start;
    Calendar finish;

    @Override
    protected int getLayoutId() {
        return R.layout.activity_statistic;
    }

    @Override
    protected void onCreateView() {

        start = Calendar.getInstance();
        finish = Calendar.getInstance();

        Button btnShowStatistics = (Button) mainView.findViewById(R.id.btnShowStatistics);
        btnShowStatistics.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
//                getMainActivity().showFragmentAndAddToBackstack(FullStatisticFragment.newInstance(selectedEvent, events,
//                        startDate.getTimeInMillis()));
                getMainActivity().showFragmentAndAddToBackstack(FullStatisticFragment.newInstance(selectedEvent, events,
                        start.getTimeInMillis(), finish.getTimeInMillis()));

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

        startDate = (TextView) mainView.findViewById(R.id.tvForChoosingPeriod);
        finishDate = (TextView) mainView.findViewById(R.id.tvForChoosingFinishPeriod);

        startDate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                datePickerStartDate();
            }
        });

        finishDate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                datePickerFinishDate();
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
        builder.setTitle("Choose from the activities");
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


    public void datePickerStartDate() {
        if (start == null ) {
            start = Calendar.getInstance();
        }

        DatePickerDialog datePickerDialog = new DatePickerDialog(getActivity(), new DatePickerDialog.OnDateSetListener(){
            @Override
            public void onDateSet(DatePicker view, int year, int monthOfYear, int dayOfMonth) {
                Calendar calendar = Calendar.getInstance();
                calendar.set(year, monthOfYear, dayOfMonth);
                SimpleDateFormat format = new SimpleDateFormat("dd. MM");
                String dateString = format.format(calendar.getTime());
                startDate.setText(dateString);
                start.set(year, monthOfYear, dayOfMonth);
            }
        }, start.get(Calendar.YEAR), mMonth, mDay);

        datePickerDialog.getDatePicker().setMinDate(System.currentTimeMillis());
        datePickerDialog.show();
    }

    public void datePickerFinishDate() {
        if (finish == null) {
            finish = Calendar.getInstance();
        }

        DatePickerDialog datePickerDialog = new DatePickerDialog(getActivity(), new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker view, int year, int monthOfYear, int dayOfMonth) {
                Calendar calendar = Calendar.getInstance();
                calendar.set(year, monthOfYear, dayOfMonth);
                SimpleDateFormat format = new SimpleDateFormat("dd. MM");
                String dateString = format.format(calendar.getTime());
                finishDate.setText(dateString);
                finish.set(year, monthOfYear, dayOfMonth);
            }
        }, mYear, mMonth, mDay);

        datePickerDialog.getDatePicker().setMinDate(System.currentTimeMillis());
        datePickerDialog.show();
    }
}