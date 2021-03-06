package com.manddprojectconsulant.emicalculator.Fragments;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.appcompat.widget.SwitchCompat;
import androidx.fragment.app.Fragment;

import android.view.DragEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CompoundButton;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.material.button.MaterialButton;
import com.google.android.material.slider.RangeSlider;
import com.google.android.material.textfield.TextInputEditText;
import com.google.android.material.textfield.TextInputLayout;
import com.manddprojectconsulant.emicalculator.NumberTextWatcher;
import com.manddprojectconsulant.emicalculator.R;

import java.util.regex.Pattern;


public class CalculateEMIFragment extends Fragment {

    String numberregex = "\\p{Digit}+";
    MaterialButton calculateMaterialButton;
    TextInputEditText interestrateTextInputEditText,AmountTextInputEditText, tenureTextInputEditText,InmonthtenureTextInputEditText;
    SwitchCompat inmonthSwitchCompat;
    ResultforCalculateFragment resultforCalculateFragment = new ResultforCalculateFragment();
    TextInputLayout interestrateTextInputLayout;






    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_calculate_e_m_i, container, false);


        // ID's

        interestrateTextInputLayout = view.findViewById(R.id.rate_textinput);
        InmonthtenureTextInputEditText = view.findViewById(R.id.inmonth_edittext);
        AmountTextInputEditText = view.findViewById(R.id.Amount_edittext);
        tenureTextInputEditText = view.findViewById(R.id.tenure_edittext);
        interestrateTextInputEditText = view.findViewById(R.id.interestrate_edittext);
        calculateMaterialButton = view.findViewById(R.id.calculate_button);
        inmonthSwitchCompat = view.findViewById(R.id.inmonth_switch);






        //Amount thousand separator

        AmountTextInputEditText.addTextChangedListener(new NumberTextWatcher(AmountTextInputEditText));






   /*     AmountRangeSlider.addOnSliderTouchListener(new RangeSlider.OnSliderTouchListener() {
            @Override
            public void onStartTrackingTouch(@NonNull RangeSlider slider) {

            }

            @Override
            public void onStopTrackingTouch(@NonNull RangeSlider slider) {

                String amount = String.valueOf(slider.getValues());
                amount = amount.replace("[", "");
                amount = amount.replace("]", "");
                amount = amount.replace(".0", "");
                AmountTextInputEditText.setText("\u20B9 " + amount);
                AmountTextInputEditText.setTextColor(getResources().getColor(R.color.light_orange));

            }
        });*/





        //Switch Process

        inmonthSwitchCompat.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
                if (b == true) {


                    InmonthtenureTextInputEditText.setVisibility(View.VISIBLE);
                    tenureTextInputEditText.setVisibility(View.GONE);


                } else {
                    InmonthtenureTextInputEditText.setVisibility(View.GONE);
                    tenureTextInputEditText.setVisibility(View.VISIBLE);

                }
            }
        });


        //Process for emi calculator

        calculateMaterialButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {



                String amount = AmountTextInputEditText.getText().toString();
                amount = amount.replace(",", "");



                String year = tenureTextInputEditText.getText().toString();

                if(inmonthSwitchCompat.isChecked()){
                   // year = year.replace(" Months", "");
                    year = String.valueOf(Float.parseFloat(year)/12);
                }


                float p = Float.parseFloat(amount);
                float i = Float.parseFloat(interestrateTextInputEditText.getText().toString());
                float y = Float.parseFloat(year);
                float Principal = calPric(p);
                float Rate = calInt(i);
                float Months = calMonth(y);
                float Dvdnt = calDvdnt(Rate, Months);
                float FD = calFinalDvdnt(Principal, Rate, Dvdnt);
                float D = calDivider(Dvdnt);
                float emi = calEmi(FD, D);
                float TA = calTa(emi, Months);
                float ti = calTotalInt(TA, Principal);


                Bundle emical = new Bundle();
                emical.putString("principal", String.valueOf(p));
                emical.putString("tenure", String.valueOf(y));
                emical.putString("interestrate", String.valueOf(i));
                emical.putString("monthlyemi", String.valueOf(emi));
                emical.putString("totalpayment", String.valueOf(ti));

                resultforCalculateFragment.setArguments(emical);


                getFragmentManager().beginTransaction().replace(R.id.relative, resultforCalculateFragment).addToBackStack(null).commit();


            }
        });


        return view;


    }

    public float calPric(float p) {
        return (float) (p);
    }

    public float calInt(float i) {
        return (float) (i / 12 / 100);
    }

    public float calMonth(float y)
    {
        return (float) (y * 12);
    }

    public float calDvdnt(float Rate, float Months) {
        return (float) (Math.pow(1 + Rate, Months));
    }

    public float calFinalDvdnt(float Principal, float Rate, float Dvdnt) {
        return (float) (Principal * Rate * Dvdnt);
    }

    public float calDivider(float Dvdnt) {
        return (float) (Dvdnt - 1);
    }

    public float calEmi(float FD, Float D) {
        return (float) (FD / D);
    }

    public float calTa(float emi, Float Months) {
        return (float) (emi * Months);
    }

    public float calTotalInt(float TA, float Principal) {
        return (float) (TA - Principal);
    }


/*
    private boolean ValidateTenure() {


        Pattern principal = Pattern.compile(numberregex);

        if (principal.matcher(tenureTextInputEditText.getText().toString()).matches()) {

            tenureTextInputLayout.setErrorEnabled(false);

            return true;
        } else {
            tenureTextInputLayout.setErrorEnabled(true);
            tenureTextInputLayout.setError("Please Enter your Value");
            return false;

        }


    }

    private boolean ValidateInterestofRate() {
        Pattern principal = Pattern.compile(numberregex);

        if (principal.matcher(interestrateTextInputEditText.getText().toString()).matches()) {

            interestrateTextInputLayout.setErrorEnabled(false);

            return true;
        } else {
            interestrateTextInputLayout.setErrorEnabled(true);
            interestrateTextInputLayout.setError("Please Enter your Value");
            return false;

        }


    }

    private boolean ValidationPrincipal() {

        Pattern principal = Pattern.compile(numberregex);

        if (principal.matcher(AmountTextInputEditText.getText().toString()).matches()) {

            principalTextInputLayout.setErrorEnabled(false);

            return true;
        } else {
            principalTextInputLayout.setErrorEnabled(true);
            principalTextInputLayout.setError("Please Enter your Value");
            return false;

        }


    }*/
}