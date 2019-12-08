package com.example.musicianmanager.fragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.musicianmanager.R;

import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

public class EvaluationFragment extends Fragment {

    // This Page is for Evaluating performers, and it is not the Main Scenario Event
    // This part will not be generated in code, in this Domain Analysis and Software Design class

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_evaluate,container,false);
        setHasOptionsMenu(true);

        return view;
    }

}
