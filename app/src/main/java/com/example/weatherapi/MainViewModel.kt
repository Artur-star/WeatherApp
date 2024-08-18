package com.example.weatherapi

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class MainViewModel :ViewModel() {
    val livaDataCurrent = MutableLiveData<String>()
    val liveDataList = MutableLiveData<List<String>>()
}