package com.manddprojectconsulant.emicalculator;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.manddprojectconsulant.emicalculator.Fragments.ResultforCalculateFragment;

import java.util.ArrayList;

public class StatisticsAdapter extends RecyclerView.Adapter<StatisticsAdapter.MyViewHolder> {

    ArrayList<ResultforCalculateFragment.Statistics> arrStatistics = new ArrayList<>();
    Context context;

    public StatisticsAdapter(ArrayList<ResultforCalculateFragment.Statistics> arrStatistics, Context context) {
        this.arrStatistics = arrStatistics;
        this.context = context;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());
        View listItem= layoutInflater.inflate(R.layout.custom_statistic_view, parent, false);
        MyViewHolder myViewHolder = new MyViewHolder(listItem);
        return myViewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {
        holder.tvMonthNo.setText(arrStatistics.get(position).getMonthNo());
        holder.tvOpeningPri.setText(arrStatistics.get(position).getOpeningPrincipal());
        holder.tvEMI.setText(arrStatistics.get(position).getEMI());
        holder.tvMonthInterest.setText(arrStatistics.get(position).getMonthInterest());
        holder.tvMonthlyPrincipal.setText(arrStatistics.get(position).getMonthlyPrincipal());
        holder.tvClosingPri.setText(arrStatistics.get(position).getClosingPrincipal());
    }

    @Override
    public int getItemCount() {
        return arrStatistics.size();
    }


    public class MyViewHolder extends RecyclerView.ViewHolder{

        TextView tvMonthNo, tvOpeningPri, tvClosingPri, tvEMI, tvMonthlyPrincipal, tvMonthInterest;

        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            tvMonthNo = itemView.findViewById(R.id.tvMonthNo);
            tvOpeningPri = itemView.findViewById(R.id.tvOpeningPri);
            tvClosingPri = itemView.findViewById(R.id.tvClosingPri);
            tvEMI = itemView.findViewById(R.id.tvEMI);
            tvMonthlyPrincipal = itemView.findViewById(R.id.tvMonthlyPrincipal);
            tvMonthInterest = itemView.findViewById(R.id.tvMonthInterest);
        }
    }
}
