package com.example.kalin.graduationwork.fragments;

import android.app.DatePickerDialog;
import android.app.TimePickerDialog;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.View;
import android.widget.CompoundButton;
import android.widget.DatePicker;
import android.widget.Switch;
import android.widget.TextView;
import android.widget.TimePicker;

import com.example.kalin.graduationwork.R;

import java.text.DateFormat;
import java.util.Calendar;
import java.util.Date;

/**
 * Created by Kalin on 19.1.2017 г..
 */

public class AddFragment extends BaseFragment {

    protected View addView;
    Toolbar toolbarAddfragment;

    int mYear, mMonth, mDay, mDayWeek, mHour, mMinute;

    TextView tvForStartDate;
    TextView tvForFinishDate;
    TextView tvForStartTime;
    TextView tvForFinishTime;

    @Override
    protected int getLayoutId() {
        return R.layout.activity_new_event;
    }

    @Override
    protected void onCreateView() {

        String formatCurrentDate = DateFormat.getDateInstance(DateFormat.FULL).format(new Date());

        tvForStartDate = (TextView) mainView.findViewById(R.id.textViewForStartDate);
        tvForFinishDate = (TextView) mainView.findViewById(R.id.textViewForFinishDate);

        tvForStartDate.setText(formatCurrentDate);
        tvForFinishDate.setText(formatCurrentDate);

        tvForStartDate.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view) {
                    datePickerStartDate();
            }
        });

        tvForFinishDate.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view) {
                datePickerFinishDate();
            }
        });

        String formatCurrentTime = DateFormat.getTimeInstance().format(new Date());

        tvForStartTime = (TextView) mainView.findViewById(R.id.textViewForStartTime);
        tvForFinishTime = (TextView) mainView.findViewById(R.id.textViewForFinishTime);

        tvForStartTime.setText(formatCurrentTime);
        tvForFinishTime.setText(formatCurrentTime);

        tvForStartTime.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view) {
                timePickerStartTime();
            }
        });

        tvForFinishTime.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view) {
                timePickerFinishTime();
            }
        });

        Switch switchForDateDuration = (Switch) mainView.findViewById(R.id.switchForDuration);

        // set the switch to ON
        switchForDateDuration.setChecked(false);
        //attach a listener to check for changes in state
        switchForDateDuration.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {

            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (isChecked) {
                    //switchStatus.setText("Switch is currently ON");
                    tvForStartTime.setVisibility(View.GONE);
                    tvForFinishTime.setVisibility(View.GONE);
                } else {
                    //switchStatus.setText("Switch is currently OFF");
                    tvForStartTime.setVisibility(View.VISIBLE);
                    tvForFinishTime.setVisibility(View.VISIBLE);
                }
            }
        });

        //check the current state before we display the screen
        if (switchForDateDuration.isChecked()) {
            //switchStatus.setText("Switch is currently ON");
            //tvForData.setVisibility(View.GONE);
            tvForStartTime.setVisibility(View.GONE);
            tvForFinishTime.setVisibility(View.GONE);
        } else {
            //switchStatus.setText("Switch is currently OFF");
            //tvForData.setVisibility(View.VISIBLE);
            tvForStartTime.setVisibility(View.VISIBLE);
            tvForFinishTime.setVisibility(View.VISIBLE);
        }
    }

//        final Calendar cal = Calendar.getInstance();
//        cal.get(Calendar.HOUR_OF_DAY);
//
//        TimePickerDialog dialog = new TimePickerDialog(getActivity(), new TimePickerDialog.OnTimeSetListener(){
//            @Override
//            public void onTimeSet(TimePicker view, int hourOfDay, int minute) {
//                cal.set(Calendar.HOUR_OF_DAY, hourOfDay);
//            }
//        }, cal.get(Calendar.HOUR_OF_DAY), 0, true);
//
//        getActivity().getActionBar().setDisplayHomeAsUpEnabled(false);
//        ((AppCompatActivity)getActivity()).getSupportActionBar().setDisplayHomeAsUpEnabled(false);
//    }

    @Override
    protected void setupToolbar() {
    }

    public boolean onCreateOptionsMenu(Menu menu) {
        return true;
    }

    public void datePickerStartDate() {
        final Calendar calendar = Calendar.getInstance();
        calendar.setTimeInMillis(System.currentTimeMillis());

        DatePickerDialog datePickerDialog = new DatePickerDialog(getActivity(), new DatePickerDialog.OnDateSetListener(){
            @Override
            public void onDateSet(DatePicker view, int year, int monthOfYear, int dayOfMonth) {
                calendar.set(Calendar.YEAR, year);
                calendar.set(Calendar.MONTH, monthOfYear);
                calendar.set(Calendar.DAY_OF_MONTH, dayOfMonth);

                monthOfYear = monthOfYear + 1;

                String format = "yyyy-MM-dd";

                tvForStartDate.setText(dayOfMonth+"-"+monthOfYear+"-"+year);
            }
        }, mYear, mMonth, mDay);

        datePickerDialog.getDatePicker().setMinDate(System.currentTimeMillis());
        datePickerDialog.show();

    }

    public void datePickerFinishDate() {
        final Calendar calendar = Calendar.getInstance();
        calendar.setTimeInMillis(System.currentTimeMillis());

        DatePickerDialog datePickerDialog = new DatePickerDialog(getActivity(), new DatePickerDialog.OnDateSetListener(){
            @Override
            public void onDateSet(DatePicker view, int year, int monthOfYear, int dayOfMonth) {
                calendar.set(Calendar.YEAR, year);
                calendar.set(Calendar.MONTH, monthOfYear);
                calendar.set(Calendar.DAY_OF_MONTH, dayOfMonth);

                monthOfYear = monthOfYear + 1;

                String format = "yyyy-MM-dd";

                tvForFinishDate.setText(dayOfMonth+"-"+monthOfYear+"-"+year);
            }
        }, mYear, mMonth, mDay);

        datePickerDialog.getDatePicker().setMinDate(System.currentTimeMillis());
        datePickerDialog.show();

    }

    public void timePickerStartTime() {
        final Calendar c = Calendar.getInstance();
        mHour = c.get(Calendar.HOUR_OF_DAY);
        mMinute = c.get(Calendar.MINUTE);

        TimePickerDialog timePickerDialog = new TimePickerDialog(getActivity(), new TimePickerDialog.OnTimeSetListener(){
            @Override
            public void onTimeSet(TimePicker view, int hourOfDay, int minute) {

                tvForStartTime.setText(hourOfDay+":"+minute);

            }
        }, mHour, mMinute, false);
        timePickerDialog.show();
    }

    public void timePickerFinishTime() {
        final Calendar c = Calendar.getInstance();
        mHour = c.get(Calendar.HOUR_OF_DAY);
        mMinute = c.get(Calendar.MINUTE);

        TimePickerDialog timePickerDialog = new TimePickerDialog(getActivity(), new TimePickerDialog.OnTimeSetListener(){
            @Override
            public void onTimeSet(TimePicker view, int hourOfDay, int minute) {

                tvForFinishTime.setText(hourOfDay+":"+minute);

            }
        }, mHour, mMinute, false);
        timePickerDialog.show();
    }

}