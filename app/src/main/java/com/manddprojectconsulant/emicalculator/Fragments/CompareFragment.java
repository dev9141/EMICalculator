package com.manddprojectconsulant.emicalculator.Fragments;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.google.android.material.button.MaterialButton;
import com.google.android.material.card.MaterialCardView;
import com.google.android.material.textfield.TextInputEditText;
import com.manddprojectconsulant.emicalculator.NumberTextWatcher;
import com.manddprojectconsulant.emicalculator.R;

import java.text.DecimalFormat;


public class CompareFragment extends Fragment {


    TextInputEditText amountoneText, tenureoneText, interestrateoneText, amounttwoText, tenuretwoText, interestratetwoText;
    MaterialButton comparebutton;
    MaterialCardView cardemi, cardinterest, cardtotal;
    TextView resultone, resulttwo, difference_text, resultotalinterest_one, resultotalinterest_two, differenceforinterestrate_text,resultotalpayable_one,resultotalpayable_two,differencefortotalpayable_text;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment

        View view = inflater.inflate(R.layout.fragment_compare, container, false);

        //ID's
        amountoneText = view.findViewById(R.id.loanamountone);
        amounttwoText = view.findViewById(R.id.loanamounttwo);
        tenureoneText = view.findViewById(R.id.tenureone);
        tenuretwoText = view.findViewById(R.id.tenuretwo);
        interestrateoneText = view.findViewById(R.id.interestrateone);
        interestratetwoText = view.findViewById(R.id.interestratetwo);
        difference_text = view.findViewById(R.id.differenceforemi_text);
        comparebutton = view.findViewById(R.id.compare_button);
        cardemi = view.findViewById(R.id.cardmonthlyemi);
        cardinterest = view.findViewById(R.id.cardinterestrate);
        cardtotal = view.findViewById(R.id.cardtotal);
        resultone = view.findViewById(R.id.resultone);
        resulttwo = view.findViewById(R.id.resulttwo);
        resultotalinterest_one = view.findViewById(R.id.resultotalinterest_one);
        resultotalinterest_two = view.findViewById(R.id.resultotalinterest_two);
        differenceforinterestrate_text = view.findViewById(R.id.differenceforinterestrate_text);
        resultotalpayable_one=view.findViewById(R.id.resultotalpayable_one);
        resultotalpayable_two=view.findViewById(R.id.resultotalpayable_two);
        differencefortotalpayable_text=view.findViewById(R.id.differencefortotalpayable_text);
        //Thousand separator
        amountoneText.addTextChangedListener(new NumberTextWatcher(amountoneText));
        amounttwoText.addTextChangedListener(new NumberTextWatcher(amounttwoText));


        comparebutton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {


                cardemi.setVisibility(View.VISIBLE);
                cardinterest.setVisibility(View.VISIBLE);
                cardtotal.setVisibility(View.VISIBLE);

                loanone();
                loantwo();
                differencebetweentwo();
                differencebetweeninterest();
                differencebetweentotal();






            }
        });


        return view;
    }

    private void differencebetweentotal() {

        //One

        String a= amountoneText.getText().toString();
        a=a.replace(",","");
        
        String b=resultotalinterest_one.getText().toString();
        b=b.replace(",","");
        b=b.replace("₹ ","");
        
        double c= Double.parseDouble(a)+Double.parseDouble(b);
        String d= String.valueOf(c);

        resultotalpayable_one.setText(d);

      //two

        String a1= amounttwoText.getText().toString();
        a1=a1.replace(",","");

        String b1=resultotalinterest_two.getText().toString();
        b1=b1.replace(",","");
        b1=b1.replace("₹ ","");

        double c1= Double.parseDouble(a1)+Double.parseDouble(b1);
        String d1= String.valueOf(c1);

        resultotalpayable_two.setText(d1);


        //Result

        double result=c-c1;
        String e = String.valueOf(result);
        differencefortotalpayable_text.setText("Difference: "+"₹ "+e);









    }

    private void differencebetweeninterest()
    {

        String result2 = resultotalinterest_two.getText().toString();
        result2 = result2.replace("₹ ", "");
        result2 = result2.replace(",", "");


        String result1 = resultotalinterest_one.getText().toString();
        result1 = result1.replace("₹ ", "");
        result1 = result1.replace(",", "");

        double result = Double.parseDouble(result1) - Double.parseDouble(result2);

        String difference= String.valueOf(result);

        differenceforinterestrate_text.setText("Difference : " + "₹ "+difference);


    }


    private void differencebetweentwo() {

        String result2 = resulttwo.getText().toString();
        result2 = result2.replace("₹ ", "");
        result2 = result2.replace(",", "");


        String result1 = resultone.getText().toString();
        result1 = result1.replace("₹ ", "");
        result1 = result1.replace(",", "");

        double result = Double.parseDouble(result1) - Double.parseDouble(result2);
        String r= String.valueOf(result);

        difference_text.setText("Difference : " + "₹ " + r);

    }

    private void loantwo() {


        String amount = amounttwoText.getText().toString();
        amount = amount.replace(",", "");

        String year = tenuretwoText.getText().toString();

        float p = Float.parseFloat(amount);
        float i = Float.parseFloat(interestratetwoText.getText().toString());
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



        /* */


        //ti1 is for Total Interest
        //emi1 is for Monthlyemi


        DecimalFormat formatter = new DecimalFormat("#,###");
        double em2 = Double.parseDouble(String.valueOf(emi));
        String emil2 = formatter.format(em2);
        resulttwo.setText("₹ " + emil2);


        double interestrate = Double.parseDouble(String.valueOf(ti));
        String ir = formatter.format(interestrate);
        resultotalinterest_two.setText("₹ " +ir);



    }

    private void loanone() {

        String amount = amountoneText.getText().toString();
        amount = amount.replace(",", "");

        String year = tenureoneText.getText().toString();

        float p1 = Float.parseFloat(amount);
        float i1 = Float.parseFloat(interestrateoneText.getText().toString());
        float y1 = Float.parseFloat(year);
        float Principal1 = calPric1(p1);
        float Rate1 = calInt1(i1);
        float Months1 = calMonth1(y1);
        float Dvdnt1 = calDvdnt1(Rate1, Months1);
        float FD1 = calFinalDvdnt1(Principal1, Rate1, Dvdnt1);
        float D1 = calDivider1(Dvdnt1);
        float emi1 = calEmi1(FD1, D1);
        float TA1 = calTa1(emi1, Months1);
        float ti1 = calTotalInt1(TA1, Principal1);


        //ti1 is for Total Interest
        //emi1 is for Monthlyemi


        DecimalFormat formatter = new DecimalFormat("#,###");
        double emi = Double.parseDouble(String.valueOf(emi1));
        String emiformatting = formatter.format(emi);
        resultone.setText("₹ " + emiformatting);


        double interestrate = Double.parseDouble(String.valueOf(ti1));
        String ir = formatter.format(interestrate);
        resultotalinterest_one.setText("₹ " +ir);


    }


    public float calPric(float p) {
        return (float) (p);
    }

    public float calInt(float i) {
        return (float) (i / 12 / 100);
    }

    public float calMonth(float y) {
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


    //for second result

    public float calPric1(float p) {
        return (float) (p);
    }

    public float calInt1(float i) {
        return (float) (i / 12 / 100);
    }

    public float calMonth1(float y) {
        return (float) (y * 12);
    }

    public float calDvdnt1(float Rate, float Months) {
        return (float) (Math.pow(1 + Rate, Months));
    }

    public float calFinalDvdnt1(float Principal, float Rate, float Dvdnt) {
        return (float) (Principal * Rate * Dvdnt);
    }

    public float calDivider1(float Dvdnt) {
        return (float) (Dvdnt - 1);
    }

    public float calEmi1(float FD, Float D) {
        return (float) (FD / D);
    }

    public float calTa1(float emi, Float Months) {
        return (float) (emi * Months);
    }

    public float calTotalInt1(float TA, float Principal) {
        return (float) (TA - Principal);
    }


}