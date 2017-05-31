package com.example.kalin.graduationwork.fragments;

import android.app.DatePickerDialog;
import android.app.ProgressDialog;
import android.app.TimePickerDialog;
import android.content.Context;
import android.location.Address;
import android.location.Geocoder;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.text.Editable;
import android.text.TextUtils;
import android.util.Log;
import android.view.Menu;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.CompoundButton;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.Filter;
import android.widget.Filterable;
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
import com.example.kalin.graduationwork.model.Duration;
import com.example.kalin.graduationwork.model.Event;
import com.example.kalin.graduationwork.model.Location;
import com.example.kalin.graduationwork.utils.ColorUtil;
import com.example.kalin.graduationwork.views.ColorView;
import com.google.android.gms.maps.model.LatLng;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLEncoder;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

public class AddFragment extends BaseFragment implements ColorSelectedListener, AdapterView.OnItemClickListener {

    private static final String PLACES_API_BASE = "https://maps.googleapis.com/maps/api/place";
    private static final String TYPE_AUTOCOMPLETE = "/autocomplete";
    private static final String OUT_JSON = "/json";

    private static final String ARG_EVENT = "ARG_EVENT";

    //------------ the API key for find places ------------
    private static final String API_KEY = "AIzaSyCjKffmFemdUklvZVeUZEoBnaSuOm3cGds";

    public static AddFragment newInstance() {
        Bundle args = new Bundle();

        AddFragment fragment = new AddFragment();
        fragment.setArguments(args);
        return fragment;
    }

    public static AddFragment newInstance(Event event) {
        Bundle args = new Bundle();

        AddFragment fragment = new AddFragment();
        args.putSerializable(ARG_EVENT, event);
        fragment.setArguments(args);
        return fragment;
    }

    protected View addView;
    Toolbar toolbarAddfragment;
    Context context;

    EditText txtTitle;
    AutoCompleteTextView txtLocation;
    EditText txtPrice;
    EditText txtNotification;

    int mYear, mMonth, mDay, mDayWeek, mHour, mHourFinish, mMinute;

    TextView tvForStartDate;
    TextView tvForFinishDate;
    TextView tvForStartTime;
    TextView tvForFinishTime;
    Calendar startDate;
    Calendar endDate;

    ColorView circleColor;
    TextView editColor;
    ColorData currentColor;
    Calendar calendar;

    TextView buttonSave;
    ImageView buttonCancel;

    private ColorsAdapter adapter;
    private RecyclerView list;

    private DBManager mdbmanager;

    int optionForAllDay = 0;
    int newFinishTime;
    int newStartTime;

    Event oldEvent;

    @Override
    protected int getLayoutId() {
        return R.layout.activity_new_event;
    }

    @Override
    protected void onCreateView() {

        txtTitle = (EditText) mainView.findViewById(R.id.editTextTitle);
        txtLocation = (AutoCompleteTextView) mainView.findViewById(R.id.editTextLocation);
        txtPrice = (EditText) mainView.findViewById(R.id.editTextPrice);
        tvForStartDate = (TextView) mainView.findViewById(R.id.textViewForStartDate);
        tvForFinishDate = (TextView) mainView.findViewById(R.id.textViewForFinishDate);
        tvForStartTime = (TextView) mainView.findViewById(R.id.textViewForStartTime);
        tvForFinishTime = (TextView) mainView.findViewById(R.id.textViewForFinishTime);
        Switch switchForDateDuration = (Switch) mainView.findViewById(R.id.switchForDuration);
        editColor = (TextView) mainView.findViewById(R.id.TextViewColor);
        circleColor = (ColorView) mainView.findViewById(R.id.circleView);

        if (getArguments() != null) {
            oldEvent = (Event) getArguments().getSerializable(ARG_EVENT);
        }

        if (oldEvent != null) {
            txtTitle.setText(oldEvent.getName());
            txtLocation.setText(oldEvent.getLocation().getName());
            txtPrice.setText(Integer.toString(oldEvent.getPrice()));
//            tvForStartDate.setText((int) oldEvent.getDuration().getStart());
        } else {
            String formatCurrentDate = DateFormat.getDateInstance(DateFormat.FULL).format(new Date());

            tvForStartDate.setText(formatCurrentDate);
            tvForFinishDate.setText(formatCurrentDate);

            tvForStartDate.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    datePickerStartDate();
                }
            });

            tvForFinishDate.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    datePickerFinishDate();
                }
            });

            calendar = Calendar.getInstance();
            mMinute = calendar.get(Calendar.MINUTE);
            mHour = calendar.get(Calendar.HOUR_OF_DAY);
            calendar.getTime();

            if (mMinute >= 30) {
                mMinute = 0;
                if (mHour >= 24)
                    calendar.set(Calendar.HOUR_OF_DAY, 0);
                mHour = mHour + 1;

                tvForStartTime.setText(mHour + ":" + mMinute);
            } else {
                mMinute = 30;
                tvForStartTime.setText(mHour + ":" + mMinute);
            }

            mHourFinish = mHour + 1;
            tvForFinishTime.setText(mHourFinish + ":" + mMinute);
            newStartTime = mHour;
            newFinishTime = mHourFinish;
        }

        tvForStartTime.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view) {
                timePickerStartTime();
                mHour = newStartTime;
            }
        });

        tvForFinishTime.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view) {
                timePickerFinishTime();
                mHourFinish = newFinishTime;
            }
        });

        // set the switch to ON
        switchForDateDuration.setChecked(oldEvent != null ? oldEvent.getDuration().getAllday() : false);
        //attach a listener to check for changes in state
        switchForDateDuration.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {

            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (isChecked) {
                    //switchStatus.setText("Switch is currently ON");
                    tvForStartTime.setVisibility(View.GONE);
                    tvForFinishTime.setVisibility(View.GONE);
                    optionForAllDay = 1;
                } else {
                    //switchStatus.setText("Switch is currently OFF");
                    tvForStartTime.setVisibility(View.VISIBLE);
                    tvForFinishTime.setVisibility(View.VISIBLE);
                    optionForAllDay = 0;
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

        if (oldEvent != null) {
            currentColor = oldEvent.getColor();
        } else {
            currentColor = ColorUtil.getInstance(getActivity()).getColors().get(0);
        }

        editColor.setText(currentColor.getName());

        circleColor.setCircleColor(currentColor.getColor());

        txtLocation.setAdapter(new GooglePlacesAutocompleteAdapter(getMainActivity(), R.layout.list_item_location));

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
                getThings();
            }
        });
    }

    public void getThings() {
        final ProgressDialog pd = new ProgressDialog(getActivity());
        pd.setCancelable(false);
        pd.show();
        new Thread(new Runnable() {
            @Override
            public void run() {

                Editable eventTitle = txtTitle.getText();
                Editable location = txtLocation.getText();
                Editable price = txtPrice.getText();
                int finalPrice = Integer.parseInt(price.toString());

                String finalLocation = location.toString();
                Geocoder gc = new Geocoder(getMainActivity());
                List<LatLng> ll = null;
                try {
                    List<Address> addresses = gc.getFromLocationName(finalLocation, 5);

                    ll = new ArrayList<LatLng>(addresses.size());
                    for (Address a : addresses) {
                        if (a.hasLatitude() && a.hasLongitude()) {
                            ll.add(new LatLng(a.getLatitude(), a.getLongitude()));
                        }
                    }
                    Log.d("The latitude is", String.valueOf(ll));
                } catch (IOException e) {}

                Location newLocation = new Location();
                newLocation.setName(location.toString());
                newLocation.setLatitude(String.valueOf(ll));
                newLocation.setLongitute(String.valueOf(ll));

                Duration newDuration = new Duration();

                if (optionForAllDay == 1) {
                    newDuration.setAllday(true);
                } else if (optionForAllDay == 0) {
                    newDuration.setAllday(false);
                    startDate = Calendar.getInstance();
                    endDate = Calendar.getInstance();
                    newDuration.setStart(startDate.getTimeInMillis());
                    newDuration.setFinish(endDate.getTimeInMillis());
                }

                Event newEvent = oldEvent != null ? oldEvent : new Event();
                    newEvent.setName(eventTitle.toString());
                    newEvent.setColor(currentColor);
                    newEvent.setNotification(false);
                    newEvent.setPrice(finalPrice);
                    newEvent.setDuration(newDuration);
                    newEvent.setLocation(newLocation);

                if (!TextUtils.isEmpty(eventTitle) && !TextUtils.isEmpty(price)) {
                    DBManager.getInstance(getActivity()).addEvent(newEvent, oldEvent != null);
                    new Handler(Looper.getMainLooper()).post(new Runnable() {
                        @Override
                        public void run() {
                            pd.dismiss();
                            getMainActivity().onBackPressed();
                        }
                    });
                } else {
                    new Handler(Looper.getMainLooper()).post(new Runnable() {
                        @Override
                        public void run() {
                            pd.dismiss();
                            Toast.makeText(getMainActivity(), "You haven`t complete the needed fields", Toast.LENGTH_SHORT).show();
                        }
                    });
                }
            }
        }).start();
    }

    public boolean onCreateOptionsMenu(Menu menu) {
        return true;
    }

    public void datePickerFinishDate() {
        if (endDate == null) {
            endDate = Calendar.getInstance();
        }

            DatePickerDialog datePickerDialog = new DatePickerDialog(getActivity(), new DatePickerDialog.OnDateSetListener() {
                @Override
                public void onDateSet(DatePicker view, int year, int monthOfYear, int dayOfMonth) {
                    Calendar calendar = Calendar.getInstance();
                    calendar.set(year, monthOfYear, dayOfMonth);
                    SimpleDateFormat format = new SimpleDateFormat("EEEE, MMMM dd yyyy");
                    String dateString = format.format(calendar.getTime());
                    tvForStartDate.setText(dateString);
                    endDate.set(year, monthOfYear, dayOfMonth);
                }
            }, mYear, mMonth, mDay);

            datePickerDialog.getDatePicker().setMinDate(System.currentTimeMillis());
            datePickerDialog.show();

    }

    public void datePickerStartDate() {
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
                startDate.set(year, monthOfYear, dayOfMonth);
            }
        }, startDate.get(Calendar.YEAR), mMonth, mDay);

        datePickerDialog.getDatePicker().setMinDate(System.currentTimeMillis());
        datePickerDialog.show();
    }

    public void timePickerStartTime() {
        TimePickerDialog timePickerDialog = new TimePickerDialog(getActivity(), new TimePickerDialog.OnTimeSetListener(){
            @Override
            public void onTimeSet(TimePicker view, int hourOfDay, int minute) {
                tvForStartTime.setText(hourOfDay+":"+minute);
                newStartTime = hourOfDay;
                startDate.set(Calendar.HOUR_OF_DAY, hourOfDay);
                startDate.set(Calendar.MINUTE, minute);

            }
        }, mHour, mMinute, false);
        timePickerDialog.show();
    }

    public void timePickerFinishTime() {
        TimePickerDialog timePickerDialog = new TimePickerDialog(getActivity(), new TimePickerDialog.OnTimeSetListener(){
            @Override
            public void onTimeSet(TimePicker view, int hourOfDay, int minute) {

                tvForFinishTime.setText(hourOfDay+":"+minute);
                newFinishTime = hourOfDay;
                endDate.set(Calendar.HOUR_OF_DAY, hourOfDay);
                endDate.set(Calendar.MINUTE, minute);

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

    public void onItemClick(AdapterView<?> adapterView, View view, int position, long id) {
        String str = (String) adapterView.getItemAtPosition(position);
        Toast.makeText(getMainActivity(), str, Toast.LENGTH_SHORT).show();
    }

    public static ArrayList<String> autocomplete(String input) {
        ArrayList<String> resultList = null;

        HttpURLConnection conn = null;
        StringBuilder jsonResults = new StringBuilder();
        try {
            StringBuilder sb = new StringBuilder(PLACES_API_BASE + TYPE_AUTOCOMPLETE + OUT_JSON);
            sb.append("?key=" + API_KEY);
            sb.append("&components=country:bg");
            sb.append("&input=" + URLEncoder.encode(input, "utf8"));

            URL url = new URL(sb.toString());

            System.out.println("URL: "+url);
            conn = (HttpURLConnection) url.openConnection();
            InputStreamReader in = new InputStreamReader(conn.getInputStream());

            // Load the results into a StringBuilder
            int read;
            char[] buff = new char[1024];
            while ((read = in.read(buff)) != -1) {
                jsonResults.append(buff, 0, read);
            }
        } catch (MalformedURLException e) {
//            Log.e(LOG_TAG, "Error processing Places API URL", e);
            return resultList;
        } catch (IOException e) {
//            Log.e(LOG_TAG, "Error connecting to Places API", e);
            return resultList;
        } finally {
            if (conn != null) {
                conn.disconnect();
            }
        }

        try {

            // Create a JSON object hierarchy from the results
            JSONObject jsonObj = new JSONObject(jsonResults.toString());
            JSONArray predsJsonArray = jsonObj.getJSONArray("predictions");

            // Extract the Place descriptions from the results
            resultList = new ArrayList<String>(predsJsonArray.length());
            for (int i = 0; i < predsJsonArray.length(); i++) {
                System.out.println(predsJsonArray.getJSONObject(i).getString("description"));
                System.out.println("============================================================");
                resultList.add(predsJsonArray.getJSONObject(i).getString("description"));
            }
        } catch (JSONException e) {
//            Log.e(LOG_TAG, "Cannot process JSON results", e);
        }

        return resultList;
    }

    class GooglePlacesAutocompleteAdapter extends ArrayAdapter<String> implements Filterable {
        private ArrayList<String> resultList;

        public GooglePlacesAutocompleteAdapter(Context context, int textViewResourceId) {
            super(context, textViewResourceId);
        }

        @Override
        public int getCount() {
            return resultList.size();
        }

        @Override
        public String getItem(int index) {
            return resultList.get(index);
        }

        @Override
        public Filter getFilter() {
            Filter filter = new Filter() {
                @Override
                protected FilterResults performFiltering(CharSequence constraint) {
                    FilterResults filterResults = new FilterResults();
                    if (constraint != null) {
                        // Retrieve the autocomplete results.
                        resultList = autocomplete(constraint.toString());

                        // Assign the data to the FilterResults
                        filterResults.values = resultList;
                        filterResults.count = resultList.size();
                    }
                    return filterResults;
                }

                @Override
                protected void publishResults(CharSequence constraint, FilterResults results) {
                    if (results != null && results.count > 0) {
                        notifyDataSetChanged();
                    } else {
                        notifyDataSetInvalidated();
                    }
                }
            };
            return filter;
        }

    }

}