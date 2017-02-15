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

    @Override
    protected int getLayoutId() {
        return R.layout.activity_new_event;
    }

    @Override
    protected void onCreateView() {

    }

    @Override
    protected void setupToolbar() {

//        Toolbar toolbar_for_add_event = (Toolbar) getMainActivity().getLayoutInflater().inflate(R.layout.toolbar_for_add_event, null);
//
//        getMainActivity().setSupportActionBar(toolbar_for_add_event);


        Toolbar toolbarAddfragment = (Toolbar) getMainActivity().getLayoutInflater().inflate(R.layout.toolbar_addfragment, null);

        getMainActivity().setSupportActionBar(toolbarAddfragment);

        TextView mTitle = (TextView) toolbarAddfragment.findViewById(R.id.title_toolbar);
    }

    public boolean onCreateOptionsMenu(Menu menu) {
        return true;
    }

}