package com.example.kalin.graduationwork.fragments;

import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.Toast;

import com.example.kalin.graduationwork.MainActivity;
import com.example.kalin.graduationwork.R;

/**
 * Created by Kalin on 18.1.2017 г..
 */

public class HomeFragment extends BaseFragment {
    @Override
    protected int getLayoutId() {
        return R.layout.content_main;
    }

    @Override
    protected void onCreateView() {
        String[] activities = {"Tennis", "Fitness", "Free"};
        ListAdapter kalinsAdapter = new ArrayAdapter<String>(getActivity(), android.R.layout.simple_list_item_1, activities);
        ListView kalinsListView = (ListView) mainView.findViewById(R.id.kalinsListView);
        kalinsListView.setAdapter(kalinsAdapter);

        kalinsListView.setOnItemClickListener(
                new AdapterView.OnItemClickListener(){
                    @Override
                    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                        String activity = String.valueOf(parent.getItemAtPosition(position));
                        Toast.makeText(getActivity(), activity, Toast.LENGTH_LONG).show();
                    }
                }
        );
    }

    @Override
    protected void setupToolbar() {

    }
}
