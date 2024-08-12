package com.leonardo.mvvm_arc.data.repositories

import com.leonardo.mvvm_arc.data.api.Ret

class DataRepository (private val retrofitService:Ret) {

    fun getAllLives() = retrofitService.getAllLives()


}