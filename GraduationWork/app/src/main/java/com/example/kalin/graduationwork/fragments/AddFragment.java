package com.example.kalin.graduationwork.fragments;

import android.support.v7.widget.Toolbar;
import android.view.View;

import com.example.kalin.graduationwork.R;

/**
 * Created by Kalin on 19.1.2017 г..
 */

public class AddFragment extends BaseFragment {

    protected View addView;
    private Toolbar toolbar_add_event;

    @Override
    protected int getLayoutId() {
        return R.layout.activity_new_event;
    }

    @Override
    protected void onCreateView() {

    }

    @Override
    protected void setupToolbar() {

//    toolbar_add_event = (Toolbar) addView.findViewById(R.id.toolbar_for_add_event);
////        ((AppCompatActivity) getActivity()).getSupportActionBar().setSubtitle("");
//    ((AppCompatActivity) getActivity()).setSupportActionBar(toolbar_add_event);


    }


}