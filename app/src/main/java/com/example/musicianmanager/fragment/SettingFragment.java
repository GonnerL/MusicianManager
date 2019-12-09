package com.example.musicianmanager.fragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.example.musicianmanager.MainActivity;
import com.example.musicianmanager.R;

import androidx.annotation.Nullable;
import androidx.appcompat.app.ActionBar;
import androidx.fragment.app.Fragment;

public class SettingFragment extends Fragment {

    // This Page is for Setting, and it is not the Main Scenario Event
    // This part will not be generated in code, in this Domain Analysis and Software Design class

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_setting,container,false);
        setHasOptionsMenu(true);
        androidx.appcompat.app.ActionBar actionBar = ((MainActivity) getActivity()).getSupportActionBar();

        actionBar.setDisplayOptions(ActionBar.DISPLAY_SHOW_CUSTOM);
        actionBar.setCustomView(R.layout.home_toolbar_title);

        return view;
    }

    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
        super.onCreateOptionsMenu(menu, inflater);
        inflater.inflate(R.menu.home_toolbar_menu,menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        switch(item.getItemId()) {
            case R.id.home_toolbar_account:
                Toast.makeText(getContext(),"My Profile",Toast.LENGTH_LONG).show();
                return true;

            default: return super.onOptionsItemSelected(item);
        }
    }

}
