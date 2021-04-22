package com.manddprojectconsulant.emicalculator.Fragments;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.material.button.MaterialButton;
import com.google.android.material.textfield.TextInputEditText;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.manddprojectconsulant.emicalculator.CurrencyConvert.CurrencyRetrofitBuild;
import com.manddprojectconsulant.emicalculator.CurrencyConvert.CurrencyRetrofitInterface;
import com.manddprojectconsulant.emicalculator.R;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


public class CurrencyConverterFragment extends Fragment {

    Spinner fromcurrency, tocurrency;
    TextInputEditText amountEditText;
    MaterialButton convertButton;
    String[] countryNames = {"INR", "USD", "CAD", "CNY", "EUR", "NZD"};
    int flags[] = {R.drawable.ic_india, R.drawable.ic_usa, R.drawable.ic_canada, R.drawable.ic_china, R.drawable.ic_united, R.drawable.ic_newzealand};
    TextView resultforcurrency_textview;
    String FromUSD_INR;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_currency_converter, container, false);

        //Id's
        fromcurrency = view.findViewById(R.id.fromcurrency_spinner);
        tocurrency = view.findViewById(R.id.tourrency_spinner);
        amountEditText = view.findViewById(R.id.amountforcurrency_edittext);
        convertButton = view.findViewById(R.id.convert_button);
        resultforcurrency_textview = view.findViewById(R.id.resultforcurrency_textview);


        ArrayAdapter<String> adapter = new ArrayAdapter<String>(getContext(), R.layout.support_simple_spinner_dropdown_item, countryNames);
        tocurrency.setAdapter(adapter);
        fromcurrency.setAdapter(adapter);


/*
        CountrySymbolAdapter customAdapter = new CountrySymbolAdapter(getContext(), flags, countryNames);
        fromcurrency.setAdapter(customAdapter);
        tocurrency.setAdapter(customAdapter);*/




        convertButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Convertfunction();


            }
        });


        return view;
    }

    private void Convertfunction() {


        String ToINR_USD=tocurrency.getSelectedItem().toString()+"_"+fromcurrency.getSelectedItem().toString();
        FromUSD_INR=fromcurrency.getSelectedItem().toString()+"_"+tocurrency.getSelectedItem().toString();


        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        Date date = Calendar.getInstance().getTime();
        String strDate = sdf.format(date);

        CurrencyRetrofitInterface currencyRetrofitInterface = CurrencyRetrofitBuild.getRetrofit().create(CurrencyRetrofitInterface.class);
        Call<JsonObject> call = currencyRetrofitInterface.getExchangeCurrency(FromUSD_INR+","+ToINR_USD, strDate);
        call.enqueue(new Callback<JsonObject>() {
            @Override
            public void onResponse(Call<JsonObject> call, Response<JsonObject> response) {
                Log.d("TAG", "onResponse: "+response.body());
                String u = String.valueOf(response.raw().request().url());
                String a = u;
                /*JsonObject res=response.body();
                JsonObject object=res.getAsJsonObject("USD_INR");
                JsonObject add=object.getAsJsonObject(strDate);*/


                // USD >> INR
                JsonObject res=response.body();
                JsonObject object=res.getAsJsonObject(FromUSD_INR);
                //JsonObject add=object.getAsJsonObject(strDate);

                Double amount= Double.valueOf(amountEditText.getText().toString());


                //add.get("val")

                    FromUSD_INR= String.valueOf(object.get(strDate));

                    Double h= Double.valueOf(FromUSD_INR);
                    double result=amount*h;

                    String r= String.valueOf(result);

                String aa = fromcurrency.getSelectedItem().toString()+" >> "+tocurrency.getSelectedItem().toString() + ": " + h;
                String bb = fromcurrency.getSelectedItem().toString()+" >> "+tocurrency.getSelectedItem().toString() + ": " + h;


                resultforcurrency_textview.setText(r);









                /*//INR >> USD

                //to
                //to fromcurrency.getSelectedItem().toString()+"_"+tocurrency.getSelectedItem().toString()
                //INR >> USD && USD >> INR
                //Chart

                String result1= String.valueOf(result);
                resultforcurrency_textview.setText(result1);
*/

            }

            @Override
            public void onFailure(Call<JsonObject> call, Throwable t) {

                Toast.makeText(getContext(), "Fault in Internet"+t.getMessage(), Toast.LENGTH_SHORT).show();

            }
        });



    }

}