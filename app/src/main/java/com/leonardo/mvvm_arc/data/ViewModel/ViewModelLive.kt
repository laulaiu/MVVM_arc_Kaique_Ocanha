package com.leonardo.mvvm_arc.data.ViewModel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

import com.leonardo.mvvm_arc.data.model.LiveModelo
import com.leonardo.mvvm_arc.data.repositories.DataRepository
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class ViewModelLive(private val repository: DataRepository) : ViewModel() {

    private val _liveData = MutableLiveData<List<LiveModelo>>()
    private val _liveData_Error = MutableLiveData<String>()

    val liveData: LiveData<List<LiveModelo>> get() = _liveData
    val liveData_Error: LiveData<String> get() = _liveData_Error


    fun getALlLives(){
        val request = repository.getAllLives()
        request.enqueue(object : Callback<List<LiveModelo>>{
            override fun onResponse(p0: Call<List<LiveModelo>>, p1: Response<List<LiveModelo>>) {
                _liveData.postValue(p1.body())
            }

            override fun onFailure(p0: Call<List<LiveModelo>>, p1: Throwable) {
                _liveData_Error.postValue(p1.message)
            }

        })
    }



}