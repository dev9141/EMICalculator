package com.manddprojectconsulant.emicalculator.Fragments;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.text.Html;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.github.mikephil.charting.charts.PieChart;
import com.github.mikephil.charting.data.PieData;
import com.github.mikephil.charting.data.PieDataSet;
import com.github.mikephil.charting.data.PieEntry;
import com.manddprojectconsulant.emicalculator.R;

import java.text.DecimalFormat;
import java.util.ArrayList;


public class ResultforCalculateFragment extends Fragment {

    //Pie Data
    PieChart Emi_pieChart;


    //Normal

    TextView loanamountTextView, tenure_text, iofr_text, emirs_textview, totalratewithrate_text, totalinterest_text, intererst_monthTextView;
    String principal, tenure, rate, totalIntrest, monthlyemi;

    TextView totalno_text;
    DecimalFormat formatter;

    double totalamount;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_resultfor_calculate, container, false);




        principal = getArguments().getString("principal");
        tenure = getArguments().getString("tenure");
        rate = getArguments().getString("interestrate");
        totalIntrest = getArguments().getString("totalpayment");
        monthlyemi = getArguments().getString("monthlyemi");


         formatter = new DecimalFormat("#,###");

        //EMI/Month
        double emi = Double.parseDouble(monthlyemi);
        String emiformatting = formatter.format(emi);


        //Total Amount with Interest
         totalamount = Double.parseDouble(principal) + Double.parseDouble(totalIntrest);

        //ID's

        Emi_pieChart=view.findViewById(R.id.piechart);
        totalno_text=view.findViewById(R.id.totalno_text);

        getEntries();




       loanamountTextView = view.findViewById(R.id.loanamount_text);
        tenure_text = view.findViewById(R.id.tenure_text);
        iofr_text = view.findViewById(R.id.iofr_text);
        emirs_textview = view.findViewById(R.id.emirs_textview);

       // totalratewithrate_text = view.findViewById(R.id.totalratewithrate_text);
       /* totalinterest_text = view.findViewById(R.id.interest_text);
        intererst_monthTextView = view.findViewById(R.id.totalinterest_text);
*/

        //set Text value
        loanamountTextView.setText("\u20B9 " + formatter.format(Double.parseDouble(principal+"")));
        tenure_text.setText(tenure + " years");
        iofr_text.setText(rate + " %");
        emirs_textview.setText("\u20B9 " + emiformatting);


      /*  totalratewithrate_text.setHint("\u20B9 " + formatter.format(Double.parseDouble(totalamount+"")));
        totalinterest_text.setHint("\u20B9 " + formatter.format(Double.parseDouble(totalIntrest+"")));
        intererst_monthTextView.setHint(rate + " %");*/

        return view;
    }

    private void getEntries() {

    ArrayList<PieEntry> emientry=new ArrayList<>();

    Double InterestPer = (Double.parseDouble(totalIntrest)*100)/totalamount;
    Double PrinciplePer = (Double.parseDouble(principal)*100)/totalamount;

    emientry.add(new PieEntry(Float.parseFloat(String.format("%.2f", InterestPer)),"Interest"));
    emientry.add(new PieEntry(Float.parseFloat(String.format("%.2f", PrinciplePer)),"Principle"));
        PieDataSet  pieDataSet=new PieDataSet(emientry,"");
        pieDataSet.setColors(getResources().getColor(R.color.light_orange), getResources().getColor(R.color.light_green));
        pieDataSet.setValueTextSize(12f);

        pieDataSet.setSliceSpace(3f);
        pieDataSet.setValueTextColor(getResources().getColor(R.color.white));


        PieData pieData=new PieData(pieDataSet);
        Emi_pieChart.setData(pieData);
        Emi_pieChart.getDescription().setEnabled(false);
        Emi_pieChart.setCenterText("EMI CALCULATE %");
        Emi_pieChart.animate();


        String ChartIntrest = "<span style=\"color: #ffa040;\">"+ totalIntrest+" </span>";
        String ChartPrincipal = "<span style=\"color: #a5d6a7;\">"+ principal+" </span>";


        String ChartTotalText = totalamount +" "+ "( "+ ChartIntrest +" + "+ChartPrincipal +")";


        totalno_text.setText(Html.fromHtml(ChartTotalText));




    }
}