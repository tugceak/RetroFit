package com.example.retrofitmain.service

import com.example.retrofitmain.model.CrypoModel
import retrofit2.Call
import retrofit2.http.GET

interface CrypoAPI {
    //https://raw.githubusercontent.com/
    // atilsamancioglu/K21-JSONDataSet/master/crypto.json
    @GET( "atilsamancioglu/K21-JSONDataSet/master/crypto.json")
    fun getData() :Call<List<CrypoModel>>
}