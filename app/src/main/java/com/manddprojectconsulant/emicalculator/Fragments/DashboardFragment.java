package com.manddprojectconsulant.emicalculator.Fragments;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.google.android.material.button.MaterialButton;
import com.manddprojectconsulant.emicalculator.R;


public class DashboardFragment extends Fragment {

    CompareFragment compareFragment=new CompareFragment();
    CalculateEMIFragment calculateEMIFragment = new CalculateEMIFragment();
    MaterialButton emicalculates_button, compare_button;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment

        View view = inflater.inflate(R.layout.fragment_dashboard, container, false);

        //ID's
        emicalculates_button=view.findViewById(R.id.emicalculates_button);
        compare_button=view.findViewById(R.id.compareloan_button);


        //Set Click Events

        emicalculates_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                getFragmentManager().beginTransaction().replace(R.id.relative,calculateEMIFragment).addToBackStack(null).commit();


            }
        });

        compare_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                getFragmentManager().beginTransaction().replace(R.id.relative,compareFragment).addToBackStack(null).commit();

            }
        });


        return view;


    }
}