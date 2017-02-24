package com.example.kalin.graduationwork.fragments;

import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.View;
import android.widget.CompoundButton;
import android.widget.Switch;
import android.widget.TextView;

import com.example.kalin.graduationwork.R;

import java.text.DateFormat;
import java.util.Date;

/**
 * Created by Kalin on 19.1.2017 г..
 */

public class AddFragment extends BaseFragment {

    protected View addView;
    Toolbar toolbarAddfragment;

    @Override
    protected int getLayoutId() {
        return R.layout.activity_new_event;
    }

    @Override
    protected void onCreateView() {

        final TextView tvForStartDate = (TextView) mainView.findViewById(R.id.textViewForStartDate);
        final TextView tvForFinishDate = (TextView) mainView.findViewById(R.id.textViewForFinishDate);

        String formatCurrentDate = DateFormat.getDateInstance(DateFormat.FULL).format(new Date());

        tvForStartDate.setText(formatCurrentDate);
        tvForFinishDate.setText(formatCurrentDate);

        Switch switchForDateDuration = (Switch) mainView.findViewById(R.id.switchForDuration);

        // set the switch to ON
        switchForDateDuration.setChecked(false);
        //attach a listener to check for changes in state
        switchForDateDuration.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {

            @Override
            public void onCheckedChanged(CompoundButton buttonView,
                                         boolean isChecked) {

                if (isChecked) {
                    //switchStatus.setText("Switch is currently ON");
                    tvForStartDate.setVisibility(View.GONE);
                    tvForFinishDate.setVisibility(View.GONE);
                } else {
                    //switchStatus.setText("Switch is currently OFF");
                    tvForStartDate.setVisibility(View.VISIBLE);
                    tvForFinishDate.setVisibility(View.VISIBLE);
                }

            }
        });

        //check the current state before we display the screen
        if (switchForDateDuration.isChecked()) {
            //switchStatus.setText("Switch is currently ON");
            //tvForData.setVisibility(View.GONE);
            tvForStartDate.setVisibility(View.GONE);
            tvForFinishDate.setVisibility(View.GONE);
        } else {
            //switchStatus.setText("Switch is currently OFF");
            //tvForData.setVisibility(View.VISIBLE);
            tvForStartDate.setVisibility(View.VISIBLE);
            tvForFinishDate.setVisibility(View.VISIBLE);
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
    }

    @Override
    protected void setupToolbar() {
    }

    public boolean onCreateOptionsMenu(Menu menu) {
        return true;
    }

}