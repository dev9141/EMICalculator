package com.manddprojectconsulant.emicalculator.Fragments;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;


import com.google.android.material.button.MaterialButton;
import com.manddprojectconsulant.emicalculator.R;


public class DashboardFragment extends Fragment {

    CompareFragment compareFragment = new CompareFragment();
    CurrencyConverterFragment currencyConverterFragment = new CurrencyConverterFragment();
    CalculateEMIFragment calculateEMIFragment = new CalculateEMIFragment();

    RDFragment rdFragment=new RDFragment();
    FDFragment FdFragment=new FDFragment();


    MaterialButton emicalculates_button, compare_button, currencyconverter_button, rdButton, fd_Button;
    Toolbar toolbar;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment

        View view = inflater.inflate(R.layout.fragment_dashboard, container, false);

        //ID's
        emicalculates_button = view.findViewById(R.id.emicalculates_button);
        compare_button = view.findViewById(R.id.compareloan_button);
        currencyconverter_button = view.findViewById(R.id.currencyconverter_button);
        rdButton = view.findViewById(R.id.rd_button);
        fd_Button = view.findViewById(R.id.fd_button);

        //Toolbar
        toolbar = view.findViewById(R.id.toolbar);
        AppCompatActivity activity = (AppCompatActivity) getActivity();
        activity.setTitle("EMI Calculator");
        activity.setSupportActionBar(toolbar);
        //Set Click Events
        emicalculates_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                getFragmentManager().beginTransaction().replace(R.id.relative, calculateEMIFragment).addToBackStack(null).commit();


            }
        });

        compare_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                getFragmentManager().beginTransaction().replace(R.id.relative, compareFragment).addToBackStack(null).commit();

            }
        });


        currencyconverter_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                getFragmentManager().beginTransaction().replace(R.id.relative, currencyConverterFragment).addToBackStack(null).commit();


            }
        });
        fd_Button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                getFragmentManager().beginTransaction().replace(R.id.relative, FdFragment).addToBackStack(null).commit();


            }
        });
        rdButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                getFragmentManager().beginTransaction().replace(R.id.relative, rdFragment).addToBackStack(null).commit();


            }
        });


        return view;


    }
}