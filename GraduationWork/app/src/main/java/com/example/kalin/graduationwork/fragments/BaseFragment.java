package com.example.kalin.graduationwork.fragments;

import android.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.kalin.graduationwork.MainActivity;

/**
 * Created by Kalin on 18.1.2017 г..
 */

public abstract class BaseFragment extends Fragment {

    protected View mainView;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        mainView = inflater.inflate(getLayoutId(), null);

        onCreateView();
        setupToolbar();

        return mainView;
    }

    protected abstract int getLayoutId();

    protected abstract void onCreateView();

    protected abstract void setupToolbar();

    protected MainActivity getMainActivity() {
        return (MainActivity) getActivity();
    }
}
