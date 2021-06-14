package com.manddprojectconsulant.emicalculator.Fragments;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

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


public class RDFragment extends Fragment {


    TextInputEditText rdamount_edittext, rdnoofperiod_edittext, rdinterestrate_edittext,rdmaturityvalue_text,rdinterestearnedamount_text;
    MaterialButton btnrdcalculate;
    String[] choice = {"", "Months"};
    Spinner choiceforPeriod;

    double A, m, aa = 0.0;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment

        View view = inflater.inflate(R.layout.fragment_r_d, container, false);

        rdamount_edittext = view.findViewById(R.id.rdamount_edittext);
        rdnoofperiod_edittext = view.findViewById(R.id.rdnoofperiod_edittext);
        rdinterestrate_edittext = view.findViewById(R.id.rdinterestrate_edittext);
        rdmaturityvalue_text = view.findViewById(R.id.rdmaturityvalue_text);
        rdinterestearnedamount_text = view.findViewById(R.id.rdinterestearnedamount_text);
        btnrdcalculate = view.findViewById(R.id.rdcalculate_button);


        //Spinner
        choiceforPeriod = view.findViewById(R.id.rdchoiceforspinner);


        ArrayAdapter arrayAdapter = new ArrayAdapter(getContext(), android.R.layout.simple_spinner_item, choice);

        arrayAdapter.setDropDownViewResource(
                android.R.layout
                        .simple_spinner_dropdown_item);


        choiceforPeriod.setAdapter(arrayAdapter);

        btnrdcalculate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {


                CalculatesforRd();


            }
        });


        return view;
    }

    private void CalculatesforRd() {

        double P = Double.parseDouble(rdamount_edittext.getText().toString());
        double r = Double.parseDouble(rdinterestrate_edittext.getText().toString());
        double t = Double.parseDouble(rdnoofperiod_edittext.getText().toString());

        double r1 = r + 0.50;



        double n = 4;



        if (choiceforPeriod.getSelectedItem().equals("Months")) {


            for (int i = 0; i < t; i++) {

                A = (1 + (r / 100) / n);
                m = Math.round(P * Math.pow(A, (n * ((double) (i + 1) / 12))));

                aa = aa + (m);
                Log.d("CalculatesforRd", "CalculatesforRd: " + aa);

            }

            //Maturity Calculate
            rdmaturityvalue_text.setVisibility(View.VISIBLE);
            rdmaturityvalue_text.setEnabled(false);
            rdmaturityvalue_text.setText(String.valueOf(aa));

            //Total Interest

            double a = P * 12;
            double interest = Math.round(aa - a);
            rdinterestearnedamount_text.setVisibility(View.VISIBLE);
            rdinterestearnedamount_text.setEnabled(false);
            rdinterestearnedamount_text.setText(String.valueOf(interest));


        }
        else {

            Toast.makeText(getContext(), "Please select dropdown Value", Toast.LENGTH_SHORT).show();

        }

    }
}