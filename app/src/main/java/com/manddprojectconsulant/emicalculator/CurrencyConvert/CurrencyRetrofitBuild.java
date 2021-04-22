package com.manddprojectconsulant.emicalculator.CurrencyConvert;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class CurrencyRetrofitBuild {

    public static Retrofit retrofit;
    public  static String url="https://free.currconv.com/api/v7/";
    public static Retrofit getRetrofit() {

        if (retrofit==null)
        {
            retrofit = new Retrofit.Builder()
                    .baseUrl(url)
                    .addConverterFactory(GsonConverterFactory.create())
                    .build();
        }

        return retrofit;
    }
}
