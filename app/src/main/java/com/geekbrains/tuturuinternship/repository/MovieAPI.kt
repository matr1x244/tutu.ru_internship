import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query

interface MovieAPI {

    @GET("movie") //запрос

    fun getMovieDetails(
        @Query("search") id: Int?,
        @Query("field") field: String,
        @Query("token") token: String
        ) : Call <MovieDetailsDTO>

    @GET("movie?sortField=votes.imdb&sortType=-1") //запрос

    fun getMovieRecyclerFilms(
        @Query("field") fieldRating: String,
        @Query("search") searchRating: String,
        @Query("field") fieldYear: String,
        @Query("search") searchYear: String,
        @Query("field") fieldTypeNumber: String,
        @Query("search") searchTypeNumber: String,
        @Query("token") token: String
        ) : Call <MovieLoadDTO>

}

