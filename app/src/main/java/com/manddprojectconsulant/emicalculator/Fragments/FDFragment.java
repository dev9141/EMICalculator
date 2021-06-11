package com.manddprojectconsulant.emicalculator.Fragments;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.text.Editable;
import android.text.Selection;
import android.text.TextWatcher;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Spinner;
import android.widget.Toast;

import com.google.android.material.button.MaterialButton;
import com.google.android.material.textfield.TextInputEditText;
import com.manddprojectconsulant.emicalculator.R;


public class FDFragment extends Fragment {

    TextInputEditText fdamount_text, noofperiod_text, fdrateofinterest_text, maturity_text, interestearnedamount_text;
    MaterialButton btnfdcalculate;
    String[] choice = {"", "Days", "Months", "Years"};
    Spinner choiceforPeriod;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment

        View view = inflater.inflate(R.layout.fragment_f_d, container, false);

        fdamount_text = view.findViewById(R.id.fdamount_edittext);
        noofperiod_text = view.findViewById(R.id.noofperiod_edittext);
        fdrateofinterest_text = view.findViewById(R.id.fdinterestrate_edittext);
        maturity_text = view.findViewById(R.id.maturityvalue_text);
        interestearnedamount_text = view.findViewById(R.id.interestearnedamount_text);

        btnfdcalculate = view.findViewById(R.id.fdcalculate_button);
        choiceforPeriod = view.findViewById(R.id.choiceforspinner);


        ArrayAdapter arrayAdapter = new ArrayAdapter(getContext(), android.R.layout.simple_spinner_item, choice);

        arrayAdapter.setDropDownViewResource(
                android.R.layout
                        .simple_spinner_dropdown_item);


        choiceforPeriod.setAdapter(arrayAdapter);


        btnfdcalculate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                CalculatesforFd();

            }
        });


        return view;
    }

    private void CalculatesforFd() {

        double P = Double.parseDouble(fdamount_text.getText().toString());
        double r = Double.parseDouble(fdrateofinterest_text.getText().toString());
        double t = Double.parseDouble(noofperiod_text.getText().toString());

        double r1 = r / 100;

        maturity_text.setVisibility(View.VISIBLE);
        interestearnedamount_text.setVisibility(View.VISIBLE);


        //Years

        if (choiceforPeriod.getSelectedItem().equals("Years")) {


            double n = 1;

            double temp = (1 + r1 / n);

            double result = Math.round(P * Math.pow(temp, (n * t)));

            maturity_text.setText(String.valueOf(result));
            maturity_text.setEnabled(false);

            double i = Math.round(result - P);
            interestearnedamount_text.setText(String.valueOf(i));
            ;
            interestearnedamount_text.setEnabled(false);


        }

        //Months

        if (choiceforPeriod.getSelectedItem().equals("Months")) {

            double t1 = t / 12;


            double n = 1;

            //Maturity Calculation
            double temp = (1 + r1 / n);
            double result = Math.round(P * Math.pow(temp, (n * t1)));


            maturity_text.setText(String.valueOf(result));
            maturity_text.setEnabled(false);

            //Interest Rate
            double i = Math.round(result - P);

            interestearnedamount_text.setText(String.valueOf(i));
            ;

            interestearnedamount_text.setEnabled(false);


        }

        //Days

        if (choiceforPeriod.getSelectedItem().equals("Days")) {

            double t1 = t / 12;


            double n = 1;

            //Maturity Calculation
            double temp = (1 + r1 / n);
            double result = Math.round(P * Math.pow(temp, (n * t1)));


            maturity_text.setText(String.valueOf(result));
            maturity_text.setEnabled(false);

            //Interest Rate
            double i = Math.round(result - P);

            interestearnedamount_text.setText(String.valueOf(i));
            ;

            interestearnedamount_text.setEnabled(false);


        }


    }
}