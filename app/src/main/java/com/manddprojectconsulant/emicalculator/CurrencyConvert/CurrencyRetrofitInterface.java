package com.manddprojectconsulant.emicalculator.CurrencyConvert;

import com.google.gson.JsonObject;

import org.json.JSONObject;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Path;
import retrofit2.http.Query;

public interface CurrencyRetrofitInterface  {

    @GET("convert?apiKey=7128baa679983d3db715&q=USD_INR,INR_USD")
    Call<JsonObject> getExchangeCurrency(@Query("USD_INR") String USD_INR);





}
