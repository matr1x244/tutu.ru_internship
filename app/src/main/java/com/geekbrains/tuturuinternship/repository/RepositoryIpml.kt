package com.geekbrains.tuturuinternship.repository

import com.geekbrains.tuturuinternship.dataFilms.DataFilms


private const val KEY_KINOPOISK = "1KK4612-HEMM8RX-P3QSGPJ-VR0AQ82"

class RepositoryIpml: Repository {

    override fun getFromServerDetail(id: Int?): DataFilms {
        val dto = MovieRetrofitImpl.getRetrofitImpl.getMovieDetails(
            id,
            field = "id",
            token = "$KEY_KINOPOISK"
        ).execute().body()

        return DataFilms(
            id = dto?.id ?: 0,
            name = dto?.name,
            year = dto?.year ?: 0,
            imdb = dto?.rating?.imdb ?: 0.0,
            description = dto?.description,
            poster = dto?.poster?.url,
            slogan = dto?.slogan
        )
    }

    override fun getFromServer(): List<DataFilms> {
        val dtoFilmsServers = MovieRetrofitImpl.getRetrofitImpl.getMovieRecyclerFilms(
            fieldRating = "rating.kp",
            searchRating = "7-10",
            fieldYear = "year",
            searchYear = "2021",
            fieldTypeNumber = "typeNumber",
            searchTypeNumber = "1",
            token = "$KEY_KINOPOISK"
        ).execute().body()

        val dtoLoad = dtoFilmsServers?.docs
        val listMovieFilms = mutableListOf<DataFilms>()
        dtoLoad?.forEach {
            listMovieFilms.add(
                DataFilms(
                    id = it?.id ?: 0,
                    name = it?.name,
                    year = it?.year ?: 0,
                    imdb = it?.rating?.imdb ?: 0.0,
                    poster = it?.poster?.url,
                    type = it?.type
                )
            )
        }
        return listMovieFilms
    }
}

