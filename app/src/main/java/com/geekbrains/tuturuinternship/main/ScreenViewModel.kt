package com.geekbrains.tuturuinternship.main

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.geekbrains.tuturuinternship.repository.Repository
import com.geekbrains.tuturuinternship.AppState
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

class ScreenViewModel (private val repository: Repository) : ViewModel() {

    private val liveData = MutableLiveData<AppState>()

    fun getLiveData(): LiveData<AppState> = liveData // подписываемся на данные которые меняются

    fun getFilmsFromLoad() = getDataFromLoad()

    private fun getDataFromLoad() {

        liveData.value = AppState.Loading
        viewModelScope.launch(Dispatchers.IO) {
            delay(1000) //  задержка
            liveData.postValue(AppState.Success(repository.getFromServer()))
        }
    }
}