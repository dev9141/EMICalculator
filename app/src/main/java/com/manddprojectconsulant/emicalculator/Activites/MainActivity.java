package com.manddprojectconsulant.emicalculator.Activites;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;

import com.google.android.material.button.MaterialButton;
import com.manddprojectconsulant.emicalculator.Fragments.CalculateEMIFragment;
import com.manddprojectconsulant.emicalculator.R;

public class MainActivity extends AppCompatActivity {


    CalculateEMIFragment calculateEMIFragment = new CalculateEMIFragment();


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        getSupportFragmentManager().beginTransaction().replace(R.id.relative,calculateEMIFragment).commit();

    }
}