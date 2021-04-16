package com.manddprojectconsulant.emicalculator.CurrencyConvert;

import org.json.JSONObject;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;

public interface CurrencyRetrofitInterface  {

    @GET("v6/6ccfaf3d315863007e85aef9/latest/{currency}")
    Call<JSONObject> getExchangeCurrency(@Path("currency") String currency);


}
