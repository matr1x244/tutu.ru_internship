package com.geekbrains.tuturuinternship.dataFilms

import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import kotlinx.parcelize.Parcelize

@Parcelize
data class DataFilms(
    @SerializedName("poster") var poster: String? = "",
    @SerializedName("id") val id: Int = 0,
    @SerializedName("name") var name: String? = "name",
    @SerializedName("imdb") var imdb: Double? = 0.0,
    @SerializedName("year") var year: Int? = 0,
    @SerializedName("description") var description: String? = "description",
    @SerializedName("slogan") var slogan: String? = "slogan",
    @SerializedName("type") var type: String? = "type"
) : Parcelable