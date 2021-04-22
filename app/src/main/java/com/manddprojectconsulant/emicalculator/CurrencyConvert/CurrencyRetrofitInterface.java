package com.manddprojectconsulant.emicalculator.CurrencyConvert;

import com.google.gson.JsonObject;

import org.json.JSONObject;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Path;
import retrofit2.http.Query;

public interface CurrencyRetrofitInterface  {

    @GET("convert?apiKey=7128baa679983d3db715&compact=ultra")
    Call<JsonObject> getExchangeCurrency(@Query("q") String USD_INR,
                                         @Query("date") String date);

    //&compact=ultra&date=2021-04-22





}
