package com.manddprojectconsulant.emicalculator.Fragments;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.os.Handler;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.manddprojectconsulant.emicalculator.R;


public class SplashFragment extends Fragment {

    DashboardFragment dashboardFragment=new DashboardFragment();

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment

        View view=inflater.inflate(R.layout.fragment_splash, container, false);

        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {

                getFragmentManager().beginTransaction().replace(R.id.relative,dashboardFragment).commit();


            }
        },6000);



        return view;
    }
}