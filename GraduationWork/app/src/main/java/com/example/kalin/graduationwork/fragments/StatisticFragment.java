package com.example.kalin.graduationwork.fragments;

import android.support.v7.widget.Toolbar;

import com.example.kalin.graduationwork.R;

/**
 * Created by Kalin on 3.3.2017 г..
 */

public class StatisticFragment extends BaseFragment {

    @Override
    protected int getLayoutId() {
        return R.layout.activity_statistic;
    }

    @Override
    protected void onCreateView() {

    }

    @Override
    protected void setupToolbar() {
        Toolbar toolbarAdd = (Toolbar) mainView.findViewById(R.id.toolbar_statistics);
    }


}
