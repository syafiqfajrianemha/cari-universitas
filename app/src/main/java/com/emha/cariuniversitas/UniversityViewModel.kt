package com.emha.cariuniversitas

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.emha.cariuniversitas.api.RetrofitClient
import com.emha.cariuniversitas.data.model.UniversityResponse
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class UniversityViewModel : ViewModel() {

    val listUniversity = MutableLiveData<ArrayList<UniversityResponse>>()

    fun setSearchUniversity(univ: String) {
        RetrofitClient.instance.getSearchUniversity(univ)
            .enqueue(object : Callback<ArrayList<UniversityResponse>> {
                override fun onResponse(
                    call: Call<ArrayList<UniversityResponse>>,
                    response: Response<ArrayList<UniversityResponse>>
                ) {
                    if (response.isSuccessful) {
                        listUniversity.postValue(response.body())
                    }
                }

                override fun onFailure(call: Call<ArrayList<UniversityResponse>>, t: Throwable) {
                    Log.d("FailureSearchUniversity", t.message.toString())
                }
            })
    }

    fun getSearchUniversity(): LiveData<ArrayList<UniversityResponse>> {
        return listUniversity
    }
}