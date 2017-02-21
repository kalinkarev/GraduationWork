package com.example.kalin.graduationwork.fragments;

import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.View;
import android.widget.TextView;

import com.example.kalin.graduationwork.R;

/**
 * Created by Kalin on 19.1.2017 г..
 */

public class AddFragment extends BaseFragment {

    protected View addView;
    Toolbar toolbarAddfragment;

    TextView tvForStartDate;
    TextView tvForFinishDate;

    @Override
    protected int getLayoutId() {
        return R.layout.activity_new_event;
    }

    @Override
    protected void onCreateView() {

//        tvForStartDate = (TextView) getMainActivity().getLayoutInflater().inflate(R.layout.activity_new_event, null);
//        getMainActivity().findViewById(R.id.activity_new_event);


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
    }

    @Override
    protected void setupToolbar() {

//        Toolbar toolbar_for_add_event = (Toolbar) getMainActivity().getLayoutInflater().inflate(R.layout.toolbar_for_add_event, null);
//
//        getMainActivity().setSupportActionBar(toolbar_for_add_event);


        toolbarAddfragment = (Toolbar) getMainActivity().getLayoutInflater().inflate(R.layout.toolbar_addfragment, null);

        getMainActivity().setSupportActionBar(toolbarAddfragment);

//        TextView mTitle = (TextView) toolbarAddfragment.findViewById(R.id.title_toolbar);
    }

    public boolean onCreateOptionsMenu(Menu menu) {
        return true;
    }

}