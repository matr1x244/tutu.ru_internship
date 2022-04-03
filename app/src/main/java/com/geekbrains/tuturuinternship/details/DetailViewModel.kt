package com.geekbrains.tuturuinternship.details

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.geekbrains.tuturuinternship.repository.Repository
import com.geekbrains.tuturuinternship.AppState
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class DetailViewModel (private val repository: Repository) : ViewModel() {

    private val mutableLiveData: MutableLiveData<AppState> = MutableLiveData()

    val liveData: LiveData<AppState>
        get() {
            return mutableLiveData
        }

    fun loadData(id: Int?) {
        mutableLiveData.value = AppState.Loading
        viewModelScope.launch(Dispatchers.IO) {
            val data = repository.getFromServerDetail(id) // берем в потоке id
            withContext(Dispatchers.Main) {
                mutableLiveData.value =
                    (AppState.Success(listOf(data))) // записываем в livedata value данные
            }
        }
    }
}