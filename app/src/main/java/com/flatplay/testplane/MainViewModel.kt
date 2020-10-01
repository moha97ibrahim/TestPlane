package com.flatplay.testplane

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class MainViewModel : ViewModel() {
    private var _spaceOccupied: String = "50"
    private var _spaceOcupiedLiveData = MutableLiveData<String>()

    fun getInitialCount(): MutableLiveData<String> {
        _spaceOcupiedLiveData.value = _spaceOccupied
        return _spaceOcupiedLiveData
    }

}