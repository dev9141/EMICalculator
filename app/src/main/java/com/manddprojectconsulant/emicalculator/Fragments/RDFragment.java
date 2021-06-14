package com.manddprojectconsulant.emicalculator.Fragments;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Spinner;

import com.google.android.material.button.MaterialButton;
import com.google.android.material.textfield.TextInputEditText;
import com.manddprojectconsulant.emicalculator.R;


public class RDFragment extends Fragment {


    TextInputEditText rdamount_edittext,rdnoofperiod_edittext,rdinterestrate_edittext ;
    MaterialButton btnrdcalculate;
    String[] choice = {"", "Days", "Months", "Years"};
    Spinner choiceforPeriod;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment

        View view= inflater.inflate(R.layout.fragment_r_d, container, false);

        rdamount_edittext=view.findViewById(R.id.rdamount_edittext);
        rdnoofperiod_edittext=view.findViewById(R.id.rdnoofperiod_edittext);
        rdinterestrate_edittext=view.findViewById(R.id.rdinterestrate_edittext);
        btnrdcalculate=view.findViewById(R.id.rdcalculate_button);


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

        double  r1=r/100;


        double n=4;

        double temp=(1+r1/n);
        double result=P*Math.pow(temp,(n*t));



        Log.d("RD result", "CalculatesforRd: "+result);







    }
}