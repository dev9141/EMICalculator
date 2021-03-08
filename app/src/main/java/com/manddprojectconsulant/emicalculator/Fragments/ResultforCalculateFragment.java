package com.manddprojectconsulant.emicalculator.Fragments;

import android.content.Intent;
import android.os.Bundle;

import androidx.cardview.widget.CardView;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.text.Html;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TableLayout;
import android.widget.TableRow;
import android.widget.TextView;

import com.github.mikephil.charting.charts.PieChart;
import com.github.mikephil.charting.data.PieData;
import com.github.mikephil.charting.data.PieDataSet;
import com.github.mikephil.charting.data.PieEntry;
import com.google.android.material.card.MaterialCardView;
import com.manddprojectconsulant.emicalculator.R;
import com.manddprojectconsulant.emicalculator.StatisticsAdapter;

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

    Button btnStatstic;

    double totalamount;
    ArrayList<Statistics> ArrStatistics;
    RecyclerView rv_Statistics;
    MaterialCardView llStaticsheader;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_resultfor_calculate, container, false);


        ArrStatistics = new ArrayList<>();

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

        Emi_pieChart = view.findViewById(R.id.piechart);
        totalno_text = view.findViewById(R.id.totalno_text);
        btnStatstic = view.findViewById(R.id.btnStatstic);
        rv_Statistics = view.findViewById(R.id.rv_Statistics);
        llStaticsheader = view.findViewById(R.id.llStaticsheader);


       loanamountTextView = view.findViewById(R.id.loanamount_text);
        tenure_text = view.findViewById(R.id.tenure_text);
        iofr_text = view.findViewById(R.id.iofr_text);
        emirs_textview = view.findViewById(R.id.emirs_textview);


        getEntries();

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


        btnStatstic.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //Formula to get both path Principal and interest from EMI
                //MP = (tla * IR) / 12  , Where if IR is in percentage then IR = (IR/100)
                //MI = EMI - MP


                llStaticsheader.setVisibility(View.VISIBLE);
                rv_Statistics.setVisibility(View.VISIBLE);

                Double tla = Double.parseDouble(principal); // tla = Total Loan Amount
                Double IR = Double.valueOf(rate)/100; // IR == Rate of Interest
                Double EMI = emi; //EMI == Total Monthly Pay
                Double MP = 0.0; // MP == Monthly Principal from EMI
                Double MI = 0.0; // MI == Monthly Interest from EMI



                Double t = Double.valueOf(tenure);
                int n = t.intValue();

                for(int i = 0; i < n; i++){

                    MI = (tla * IR)/12;
                    MI = Double.valueOf(Math.round(MI));
                    MP = EMI - MI;
                    MP = Double.valueOf(Math.round(MP));

                    /*MI = (tla * IR)/12;
                    MI = Double.valueOf(String.format("%.2f", MI));
                    MP = EMI - MI;
                    MP = Double.valueOf(String.format("%.2f", MP));
*/


                    Statistics statistics = new Statistics();

                    statistics.monthNo = String.valueOf((i+1));
                    statistics.OpeningPrincipal = String.valueOf(Math.round(tla));
                    tla = tla - MP;
                    if((i+1)==n){
                        tla = 0.0;
                    }
                    statistics.ClosingPrincipal = String.valueOf(Math.round(tla));
                    statistics.EMI = String.valueOf(Math.round(emi));
                    statistics.monthlyPrincipal = String.valueOf(Math.round(MP));
                    statistics.monthInterest = String.valueOf(Math.round(MI));
/*
statistics.monthNo = String.valueOf((i+1));
                    statistics.OpeningPrincipal = String.format("%.2f", tla);
                    tla = tla - MP;
                    if((i+1)==n){
                        tla = 0.0;
                    }
                    statistics.ClosingPrincipal = String.format("%.2f", tla);
                    statistics.EMI = String.format("%.2f", emi);
                    statistics.monthlyPrincipal = String.format("%.2f", MP);
                    statistics.monthInterest = String.format("%.2f", MI);
*/

                    ArrStatistics.add(statistics);
                }


                StatisticsAdapter adapter = new StatisticsAdapter(ArrStatistics, getContext());
                rv_Statistics.setHasFixedSize(true);
                rv_Statistics.setLayoutManager(new LinearLayoutManager(getContext()));
                rv_Statistics.setAdapter(adapter);
                adapter.notifyDataSetChanged();

            }
        });

        return view;
    }

    private void getEntries() {

    ArrayList<PieEntry> emientry=new ArrayList<>();

    Double InterestPer = (Double.parseDouble(totalIntrest)*100)/totalamount;
    Double PrincipalPer = (Double.parseDouble(principal)*100)/totalamount;

    emientry.add(new PieEntry(Float.parseFloat(String.format("%.2f", InterestPer)),"Interest"));
    emientry.add(new PieEntry(Float.parseFloat(String.format("%.2f", PrincipalPer)),"Principal"));
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

    public class Statistics{
        public String monthNo;
        public String OpeningPrincipal;
        public String ClosingPrincipal;
        public String EMI;
        public String monthlyPrincipal;
        public String monthInterest;

        public String getMonthNo() {
            return monthNo;
        }

        public void setMonthNo(String monthNo) {
            this.monthNo = monthNo;
        }

        public String getOpeningPrincipal() {
            return OpeningPrincipal;
        }

        public void setOpeningPrincipal(String openingPrincipal) {
            OpeningPrincipal = openingPrincipal;
        }

        public String getClosingPrincipal() {
            return ClosingPrincipal;
        }

        public void setClosingPrincipal(String closingPrincipal) {
            ClosingPrincipal = closingPrincipal;
        }

        public String getEMI() {
            return EMI;
        }

        public void setEMI(String EMI) {
            this.EMI = EMI;
        }

        public String getMonthlyPrincipal() {
            return monthlyPrincipal;
        }

        public void setMonthlyPrincipal(String monthlyPrincipal) {
            this.monthlyPrincipal = monthlyPrincipal;
        }

        public String getMonthInterest() {
            return monthInterest;
        }

        public void setMonthInterest(String monthInterest) {
            this.monthInterest = monthInterest;
        }
    }
}