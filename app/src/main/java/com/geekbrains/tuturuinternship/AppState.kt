package com.geekbrains.tuturuinternship

import com.geekbrains.tuturuinternship.dataFilms.DataFilms

sealed class AppState {

    data class Success(val filmsData: List<DataFilms>): AppState()

    data class Error(val error: Throwable): AppState()

    object Loading: AppState()

}