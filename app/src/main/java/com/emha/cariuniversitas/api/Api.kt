package com.emha.cariuniversitas.api

import com.emha.cariuniversitas.data.model.UniversityResponse
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query

interface Api {

    @GET("university/search/university")
    fun getSearchUniversity(
        @Query("univ") univ: String
    ): Call<ArrayList<UniversityResponse>>
}