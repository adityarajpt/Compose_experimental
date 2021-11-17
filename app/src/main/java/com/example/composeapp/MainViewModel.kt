package com.example.composeapp

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class MainViewModel : ViewModel() {
    var dataList = mutableListOf<DataModel>()
    var _progressList = MutableLiveData<MutableList<DataModel>>()
    var progressList = _progressList as LiveData<MutableList<DataModel>>


    fun appendList(){
        dataList.add(DataModel("", ""))
        _progressList.value = dataList
        progressList = _progressList
        Log.i("Size", progressList.value?.size.toString())
    }

}