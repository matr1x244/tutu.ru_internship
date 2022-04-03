package com.geekbrains.tuturuinternship.repository

import com.geekbrains.tuturuinternship.dataFilms.DataFilms

interface Repository {
        fun getFromServer(): List<DataFilms> // задаем список
        fun getFromServerDetail(id: Int?): DataFilms // уходим в детали по ID
}