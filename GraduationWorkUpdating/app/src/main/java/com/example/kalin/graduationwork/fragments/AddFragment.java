package com.example.kalin.graduationwork.fragments;

import android.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

/**
 * Created by Kalin on 19.1.2017 г..
 */

public abstract class AddFragment extends Fragment {

    protected View addView;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        addView = inflater.inflate(getLayoutId(), null);

        onCreateView();
        setupToolbar();

        return addView;
    }

    protected abstract int getLayoutId();

    protected abstract void onCreateView();

    protected abstract void setupToolbar();

//    protected MainActivity getMainActivity() {
//        return (MainActivity) getActivity();
//    }

//    protected NewEventActivity getNewActivity() {
//        return (NewEventActivity) getActivity();
//    }

}