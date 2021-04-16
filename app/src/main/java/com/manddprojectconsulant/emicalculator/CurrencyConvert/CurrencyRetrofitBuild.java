package com.manddprojectconsulant.emicalculator.CurrencyConvert;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class CurrencyRetrofitBuild {

    public static Retrofit retrofit;

    public static Retrofit getRetrofit() {

        if (retrofit==null)
        {
            retrofit = new Retrofit.Builder()
                    .baseUrl("https://v6.exchangerate-api.com/")
                    .addConverterFactory(GsonConverterFactory.create())
                    .build();
        }

        return retrofit;
    }
}
