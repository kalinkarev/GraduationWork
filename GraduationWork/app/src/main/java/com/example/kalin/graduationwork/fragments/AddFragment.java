package com.example.kalin.graduationwork.fragments;

import android.app.DatePickerDialog;
import android.app.TimePickerDialog;
import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.text.Editable;
import android.view.Menu;
import android.view.View;
import android.widget.CompoundButton;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Switch;
import android.widget.TextView;
import android.widget.TimePicker;
import android.widget.Toast;

import com.example.kalin.graduationwork.R;
import com.example.kalin.graduationwork.adapter.ColorsAdapter;
import com.example.kalin.graduationwork.dao.DBManager;
import com.example.kalin.graduationwork.interfaces.ColorSelectedListener;
import com.example.kalin.graduationwork.model.ColorData;
import com.example.kalin.graduationwork.utils.ColorUtil;
import com.example.kalin.graduationwork.views.ColorView;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

public class AddFragment extends BaseFragment implements ColorSelectedListener{

    protected View addView;
    Toolbar toolbarAddfragment;
    Context context;

    EditText txtTitle;
    EditText txtLocation;
    EditText txtPrice;
    EditText txtNotification;

    int mYear, mMonth, mDay, mDayWeek, mHour, mHourFinish, mMinute;

    TextView tvForStartDate;
    TextView tvForFinishDate;
    TextView tvForStartTime;
    TextView tvForFinishTime;
    Calendar startDate;

    ColorView circleColor;
    TextView editColor;
    ColorData currentColor;

    TextView buttonSave;
    ImageView buttonCancel;

    private ColorsAdapter adapter;
    private RecyclerView list;

    private DBManager mdbManager;

    @Override
    protected int getLayoutId() {
        return R.layout.activity_new_event;
    }

    @Override
    protected void onCreateView() {

        txtTitle = (EditText) mainView.findViewById(R.id.editTextTitle);
        txtLocation = (EditText) mainView.findViewById(R.id.editTextLocation);
        txtPrice = (EditText) mainView.findViewById(R.id.editTextPrice);
        tvForStartDate = (TextView) mainView.findViewById(R.id.textViewForStartDate);
        tvForFinishDate = (TextView) mainView.findViewById(R.id.textViewForFinishDate);
        tvForStartTime = (TextView) mainView.findViewById(R.id.textViewForStartTime);
        tvForFinishTime = (TextView) mainView.findViewById(R.id.textViewForFinishTime);
        Switch switchForDateDuration = (Switch) mainView.findViewById(R.id.switchForDuration);
        editColor = (TextView) mainView.findViewById(R.id.TextViewColor);
        circleColor = (ColorView) mainView.findViewById(R.id.circleView);

        String formatCurrentDate = DateFormat.getDateInstance(DateFormat.FULL).format(new Date());

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


        final Calendar calendar = Calendar.getInstance();
        mMinute = calendar.get(Calendar.MINUTE);
        mHour = calendar.get(Calendar.HOUR_OF_DAY);

        if (mMinute >= 30) {
            mMinute = 0;
            if (mHour >= 24)
                calendar.set(Calendar.HOUR_OF_DAY, 0);
            mHour  = mHour + 1;

            tvForStartTime.setText(mHour+":"+mMinute);
        } else {
            mMinute = 30;
            tvForStartTime.setText(mHour+":"+mMinute);
        }

        mHourFinish = mHour + 1;
        tvForFinishTime.setText(mHourFinish +":"+ mMinute);

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

        editColor.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view) {
                ColorDialogFragment colorDialogFragment = ColorDialogFragment.newInstance(AddFragment.this, currentColor);
                getMainActivity().showColorDialog(colorDialogFragment);
            }
        });

        currentColor = ColorUtil.getInstance(getActivity()).getColors().get(0);

        editColor.setText(currentColor.getName());

        circleColor.setCircleColor(currentColor.getColor());

//        Editable eventTitle = txtTitle.getText();
//        Editable location = txtLocation.getText();
//        Editable price = txtPrice.getText();

    }

    @Override
    protected void setupToolbar() {
        Toolbar toolbarAdd = (Toolbar) mainView.findViewById(R.id.toolbar_newEvent);
        buttonCancel = (ImageView) mainView.findViewById(R.id.buttonCancel);

        buttonCancel.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                getMainActivity().showFragmentAndAddToBackstack(new HomeFragment());
            }
        });

        buttonSave = (TextView) mainView.findViewById(R.id.buttonSave);

        buttonSave.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                Editable eventTitle = txtTitle.getText();
                Editable location = txtLocation.getText();
                Editable price = txtPrice.getText();
                Editable notification = txtNotification.getText();
                int color = circleColor.getCircleColor();

//                Event createdEvent = mdbManager.addEvent(
//                        eventTitle.toString(),
//                        color,
//                        notification,
////                        location.toString(),
//                        price, false);

                Toast.makeText(getMainActivity(), "The title of the event is" + eventTitle + "the location" + location + "the price" + price, Toast.LENGTH_SHORT).show();
            }
        });

    }

    public boolean onCreateOptionsMenu(Menu menu) {
        return true;
    }

    public void datePickerStartDate() {
        DatePickerDialog datePickerDialog = new DatePickerDialog(getActivity(), new DatePickerDialog.OnDateSetListener(){
            @Override
            public void onDateSet(DatePicker view, int year, int monthOfYear, int dayOfMonth) {
                Calendar calendar = Calendar.getInstance();
                calendar.set(year, monthOfYear, dayOfMonth);
                SimpleDateFormat format = new SimpleDateFormat("EEEE, MMMM dd yyyy");
                String dateString = format.format(calendar.getTime());
                tvForStartDate.setText(dateString);
            }
        }, mYear, mMonth, mDay);

        datePickerDialog.getDatePicker().setMinDate(System.currentTimeMillis());
        datePickerDialog.show();
    }

    public void datePickerFinishDate() {
        if (startDate == null ) {
            startDate = Calendar.getInstance();
        }

        DatePickerDialog datePickerDialog = new DatePickerDialog(getActivity(), new DatePickerDialog.OnDateSetListener(){
            @Override
            public void onDateSet(DatePicker view, int year, int monthOfYear, int dayOfMonth) {

                Calendar calendarfinish = Calendar.getInstance();
                calendarfinish.set(year, monthOfYear, dayOfMonth);
                SimpleDateFormat format = new SimpleDateFormat("EEEE, MMMM dd yyyy");
                String dateString = format.format(calendarfinish.getTime());
                tvForFinishDate.setText(dateString);
            }
        }, startDate.get(Calendar.YEAR), mMonth, mDay);

        datePickerDialog.getDatePicker().setMinDate(System.currentTimeMillis());
        datePickerDialog.show();
    }

    private void updateCalendar(Calendar cal) {
        if (cal.get(Calendar.MINUTE) > 30) {
            cal.set(Calendar.MINUTE, 0);
            cal.set(Calendar.HOUR_OF_DAY, cal.get(Calendar.HOUR_OF_DAY) + 1);
        } else {
            cal.set(Calendar.MINUTE, 30);
        }
    }

    public void timePickerStartTime() {
        TimePickerDialog timePickerDialog = new TimePickerDialog(getActivity(), new TimePickerDialog.OnTimeSetListener(){
            @Override
            public void onTimeSet(TimePicker view, int hourOfDay, int minute) {

                tvForStartTime.setText(hourOfDay+":"+minute);

            }
        }, mHour, mMinute, false);
        timePickerDialog.show();
    }

    public void timePickerFinishTime() {
        TimePickerDialog timePickerDialog = new TimePickerDialog(getActivity(), new TimePickerDialog.OnTimeSetListener(){
            @Override
            public void onTimeSet(TimePicker view, int hourOfDay, int minute) {

                tvForFinishTime.setText(hourOfDay+":"+minute);

            }
        }, mHourFinish, mMinute, false);
        timePickerDialog.show();
    }

    public void selectedColor() {
        editColor.setText(currentColor.getName());
        circleColor.setCircleColor(currentColor.getColor());
    }

    @Override
    public void onColorSelected(ColorData data) {
        Toast.makeText(getActivity(), "You have selected " + data.getName() + " color", Toast.LENGTH_SHORT).show();
        currentColor = data;
        selectedColor();
    }

}