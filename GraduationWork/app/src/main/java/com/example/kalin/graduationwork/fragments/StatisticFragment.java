package com.example.kalin.graduationwork.fragments;

import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Button;

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

        Button btnShowStatistics = (Button) mainView.findViewById(R.id.btnShowStatistics);

        btnShowStatistics.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                getMainActivity().showFragmentAndAddToBackstack(new FullStatisticFragment());
            }
        });

    }

    @Override
    protected void setupToolbar() {
        Toolbar toolbarAdd = (Toolbar) mainView.findViewById(R.id.toolbar_statistics);
    }


}
