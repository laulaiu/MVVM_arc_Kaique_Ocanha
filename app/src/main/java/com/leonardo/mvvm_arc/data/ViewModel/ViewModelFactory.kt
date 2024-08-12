package com.leonardo.mvvm_arc.data.ViewModel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.leonardo.mvvm_arc.data.repositories.DataRepository

class ViewModelFactory (private val repository: DataRepository) : ViewModelProvider.Factory{

    override fun <T : ViewModel> create(modelClass: Class<T>): T {

        return if(modelClass.isAssignableFrom(ViewModelLive::class.java)){
            ViewModelLive(this.repository) as T
        }else{
            throw IllegalArgumentException("ViewModel not found")
        }
    }

}